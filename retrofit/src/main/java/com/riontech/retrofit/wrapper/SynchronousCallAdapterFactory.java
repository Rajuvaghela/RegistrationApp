package com.riontech.retrofit.wrapper;


import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Executor;

import okhttp3.Request;
import retrofit2.Call;
import retrofit2.CallAdapter;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.HEAD;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;

/**
 * Created by Vaghela Mithun R. on 03-Jul-17.
 * vaghela.mithun@gmail.com
 */

public class SynchronousCallAdapterFactory extends CallAdapter.Factory {
    private final HashMap<String, Call> mQueuedCalls;
    private final HashMap<Call, Callback> mQueuedCallbacks;

    private SynchronousCallAdapterFactory() {
        mQueuedCalls = new HashMap<>();
        mQueuedCallbacks = new HashMap<>();
    }

    public static CallAdapter.Factory create() {
        return new SynchronousCallAdapterFactory();
    }

    @Override
    public CallAdapter<Object, Object> get(final Type returnType, Annotation[] annotations, Retrofit retrofit) {
        // if returnType is retrofit2.Call, do nothing
        if (returnType.toString().contains("retrofit2.Call")) {
            return null;
        }

        boolean hasTagAnnotation = false;
        String value = "";

        for (Annotation annotation : annotations) {
            if (annotation instanceof DELETE) {
                value = ((DELETE) annotation).value();
            } else if (annotation instanceof GET) {
                value = ((GET) annotation).value();
            } else if (annotation instanceof HEAD) {
                value = ((HEAD) annotation).value();
            } else if (annotation instanceof PATCH) {
                value = ((PATCH) annotation).value();
            } else if (annotation instanceof POST) {
                value = ((POST) annotation).value();
            } else if (annotation instanceof PUT) {
                value = ((PUT) annotation).value();
            }
        }

        final boolean isTagged = hasTagAnnotation;
        final String tag = value;
        final CallAdapter delegate = retrofit.nextCallAdapter(this, returnType, annotations);
        final Executor executor = retrofit.callbackExecutor();

        return new CallAdapter<Object, Object>() {
            @Override
            public Type responseType() {
                return delegate.responseType();
            }

            @Override
            public Object adapt(Call<Object> call) {
                return delegate.adapt(isTagged ? new TaggedCall<>(call, tag, mQueuedCalls, mQueuedCallbacks, executor) : call);
            }
        };
    }

    static final class TaggedCall<T> implements Call<T> {
        private final Call<T> mDelegate;
        private final String mTag;
        private final HashMap<String, Call> mQueuedCalls;
        private final HashMap<Call, Callback> mQueuedCallbacks;
        private final Executor mExecutor;

        TaggedCall(Call<T> delegate, String tag, HashMap<String, Call> queuedCalls, HashMap<Call, Callback> queuedCallbacks, Executor executor) {
            mQueuedCalls = queuedCalls;
            mQueuedCallbacks = queuedCallbacks;
            mTag = tag;
            mDelegate = delegate;
            mExecutor = executor;
        }

        @Override
        public Response<T> execute() throws IOException {
            return mDelegate.execute();
        }

        @Override
        public void enqueue(Callback<T> callback) {
            synchronized (mQueuedCalls) {
                // Cancel enqueued call for the same tag
                if (mQueuedCalls.containsKey(mTag)) {
                    final Call queuedCall = mQueuedCalls.get(mTag);
                    if (queuedCall != null) {
                        // WORKAROUND: https://github.com/square/okhttp/issues/1592 Call.cancel() is triggering StrictMode
                        mExecutor.execute(new Runnable() {
                            @Override
                            public void run() {
                                queuedCall.cancel();
                            }
                        });
                    }
                    mQueuedCalls.remove(mTag);

                    final Callback queuedCallback = mQueuedCallbacks.get(queuedCall);
                    if (queuedCallback != null) {
                        //noinspection unchecked
                        callback = new CompositeCallback<>(queuedCallback);
                    }
                }
                // Add call to enqueued calls
                mQueuedCalls.put(mTag, mDelegate);
                mQueuedCallbacks.put(mDelegate, callback);
            }
            mDelegate.enqueue(callback);
        }

        @Override
        public boolean isExecuted() {
            return mDelegate.isExecuted();
        }

        @Override
        public void cancel() {
            mDelegate.cancel();
        }

        @Override
        public boolean isCanceled() {
            return mDelegate.isCanceled();
        }

        @SuppressWarnings("CloneDoesntCallSuperClone" /* Performing deep clone */)
        @Override
        public Call<T> clone() {
            return new TaggedCall<>(mDelegate.clone(), mTag, mQueuedCalls, mQueuedCallbacks, mExecutor);
        }

        @Override
        public Request request() {
            return mDelegate.request();
        }
    }

    static final class CompositeCallback<T> implements Callback<T> {
        private final List<Callback<T>> mChainedCallbacks;

        CompositeCallback(Callback<T> callback) {
            if (callback instanceof CompositeCallback) {
                mChainedCallbacks = ((CompositeCallback<T>) callback).mChainedCallbacks;
            } else {
                mChainedCallbacks = new LinkedList<>();
                mChainedCallbacks.add(callback);
            }
        }

        @Override
        public void onResponse(Call<T> call, Response<T> response) {
            for (Callback<T> callback : mChainedCallbacks) {
                callback.onResponse(call, response);
            }
        }

        @Override
        public void onFailure(Call<T> call, Throwable t) {
            for (Callback<T> callback : mChainedCallbacks) {
                callback.onFailure(call, t);
            }
        }
    }
}
package com.riontech.retrofit.wrapper;

import android.util.Log;

import com.riontech.retrofit.callback.OnCallExecuted;
import com.riontech.retrofit.errorhandler.APIError;
import com.riontech.retrofit.errorhandler.ErrorUtils;
import com.riontech.retrofit.errorhandler.HttpStatusCodes;
import com.xtensolution.core.utils.AppLog;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.util.concurrent.TimeoutException;

import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Vaghela Mithun R. on 04-Jul-17.
 * vaghela.mithun@gmail.com
 */
public class CallWrapper<R> implements Call<R> {
    private static final String TAG = CallWrapper.class.getSimpleName();
    private Call<R> mCall;
    private OnCallExecuted<R> onCallExecuted;

    public CallWrapper(Call<R> call) {
        this.mCall = call;
    }

    @Override
    public Response<R> execute() throws IOException {
        return mCall.execute();
    }

    @Override
    public void enqueue(Callback<R> callback) {
        mCall.enqueue(callback);
    }

    @Override
    public boolean isExecuted() {
        return mCall.isExecuted();
    }

    @Override
    public void cancel() {
        mCall.cancel();
    }

    @Override
    public boolean isCanceled() {
        return mCall.isCanceled();
    }

    @Override
    public Call<R> clone() {
        return new CallWrapper<>(mCall.clone());
    }

    @Override
    public Request request() {
        return mCall.request();
    }

    public void executeCall(OnCallExecuted<R> callExecuted) {
        this.onCallExecuted = callExecuted;
        enqueue(callback);
    }


    private void handleException(Throwable throwable) {
        if (throwable != null) {
            if (throwable instanceof SocketTimeoutException || throwable instanceof TimeoutException) {
                onCallExecuted.onTimeout();
            } else if (throwable instanceof ConnectException) {
                onCallExecuted.onConnectionError();
            } else {
                AppLog.e(TAG, throwable.getMessage());
            }
        }
    }

    private Callback<R> callback = new Callback<R>() {
        @Override
        public void onResponse(Call<R> call, Response<R> response) {
            try {
                Log.d(TAG, "onResponse: ");
                if (response.isSuccessful() && (response.code() == 200 || response.code() == 201)) {
                    if (response.body() != null) {
                        Log.d(TAG, "response.body(): " + response.body());
                        onCallExecuted.onResponse(response.body());
                    } else {
                        onCallExecuted.noDataFound(response.body());
                        Log.d(TAG, "noDataFound: ");
                    }

                } else if (response.code() == 401) {
                    // TODO Authenticate user
                    Log.d(TAG, "401: ");
                } else {
                    Log.d(TAG, "APIError: ");
                    APIError error = ErrorUtils.parseError(response);
                    if (error != null)
                        onCallExecuted.onError(error);
                    else {
                        String errorMsg = HttpStatusCodes.getErrorMessage(response.code());
                        error = new APIError();
                        error.setMessage(errorMsg);
                        error.setStatusCode(response.code());
                        onCallExecuted.onError(error);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onFailure(Call<R> call, Throwable t) {
            Log.d(TAG, "onResponse: ");
            handleException(t);
        }
    };
}



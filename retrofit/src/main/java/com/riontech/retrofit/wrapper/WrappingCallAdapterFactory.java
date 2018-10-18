package com.riontech.retrofit.wrapper;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import retrofit2.CallAdapter;
import retrofit2.Retrofit;

/**
 * Created by Vaghela Mithun R. on 04-Jul-17.
 * vaghela.mithun@gmail.com
 */
public class WrappingCallAdapterFactory extends CallAdapter.Factory {

    public static CallAdapter.Factory create() {
        return new WrappingCallAdapterFactory();
    }

    @Override
    public CallAdapter<?, ?> get(Type returnType, Annotation[] annotations, Retrofit retrofit) {
        final CallAdapter delegate = retrofit.nextCallAdapter(this, returnType, annotations);
        return new WrappingCallAdapter<>(delegate);
    }
}

package com.riontech.retrofit.wrapper;

import java.lang.reflect.Type;

import retrofit2.Call;
import retrofit2.CallAdapter;

/**
 * Created by Vaghela Mithun R. on 04-Jul-17.
 * vaghela.mithun@gmail.com
 */
public class WrappingCallAdapter<R> implements CallAdapter<R, Object> {
    private final CallAdapter<R, Object> adapter;

    public WrappingCallAdapter(CallAdapter<R, Object> adapter) {
        this.adapter = adapter;
    }

    @Override
    public Type responseType() {
        return adapter.responseType();
    }

    @Override
    public Object adapt(Call<R> call) {
        return adapter.adapt(new CallWrapper<R>(call));
    }
}

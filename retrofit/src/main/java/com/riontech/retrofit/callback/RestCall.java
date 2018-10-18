package com.riontech.retrofit.callback;

import android.util.Log;

import com.riontech.retrofit.errorhandler.APIError;
import com.riontech.retrofit.errorhandler.ErrorUtils;
import com.riontech.retrofit.errorhandler.HttpStatusCodes;
import com.xtensolution.core.utils.AppLog;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.util.concurrent.TimeoutException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Vaghela Mithun R. on 30-Jun-17.
 * vaghela.mithun@gmail.com
 * https://github.com/square/retrofit/issues/1208
 */
public class RestCall<T> {

    private static final String TAG = RestCall.class.getSimpleName();
    private OnCallExecuted<T> onCallExecuted;

    public void executeCall(OnCallExecuted onCallExecuted, Call<T> call) {
        this.onCallExecuted = onCallExecuted;
        call.enqueue(callback);
    }

    private void handleException(Throwable throwable) {
        if (throwable != null) {
            if (throwable instanceof SocketTimeoutException || throwable instanceof TimeoutException) {
                onCallExecuted.onTimeout();
            } else if (throwable instanceof ConnectException) {
                onCallExecuted.onConnectionError();
            } else {
                onCallExecuted.onConnectionError();
                AppLog.e(TAG, throwable.getMessage());
            }
        }
    }

    private Callback<T> callback = new Callback<T>() {
        @Override
        public void onResponse(Call<T> call, Response<T> response) {
            try {
//
                Log.d(TAG, "onResponse: ");
                if (response.isSuccessful()
                        && (response.code() == 200 || response.code() == 201)) {
                    Log.d(TAG, "onResponse: 1");
                    if (response.body() != null) {
                        Log.d(TAG, "response.body(): " + response.body());
                        onCallExecuted.onResponse(response.body());
                    } else {
                        Log.d(TAG, "noDataFound: ");
                        onCallExecuted.noDataFound(response.body());
                    }
                } else if (response.code() == 401) {
                    // TODO Authenticate user
                    Log.d(TAG, "401: ");
                    onCallExecuted.onResponse(response.body());
                } else {
                    Log.d(TAG, "APIError: ");
                    APIError error = ErrorUtils.parseError(response);
                    if (error != null) {
                        Log.d(TAG, "onResponse: 2");
                        onCallExecuted.onError(error);
                    } else {
                        Log.d(TAG, "onResponse: 3");
                        String errorMsg = HttpStatusCodes.getErrorMessage(response.code());
                        error = new APIError();
                        error.setMessage(errorMsg);
                        error.setStatusCode(response.code());
                        onCallExecuted.onError(error);
                    }
                }
            } catch (Exception e) {
                Log.d(TAG, "onResponse: 5");
                AppLog.e(TAG, e.getMessage(), e);
                onCallExecuted.noDataFound(response.body());
            }
        }

        @Override
        public void onFailure(Call<T> call, Throwable t) {
            Log.d(TAG, "onFailure: ");
            handleException(t);
        }
    };
}

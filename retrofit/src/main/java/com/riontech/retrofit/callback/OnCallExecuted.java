package com.riontech.retrofit.callback;

import com.riontech.retrofit.errorhandler.APIError;

/**
 * Created by Vaghela Mithun R. on 30-Jun-17.
 * vaghela.mithun@gmail.com
 */
public interface OnCallExecuted<T> {
    void onTimeout();
    void onResponse(T response);
    void onError(APIError error);
    void noDataFound(T response);
    void onConnectionError();


}

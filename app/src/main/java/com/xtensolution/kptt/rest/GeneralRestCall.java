package com.xtensolution.kptt.rest;

import com.riontech.retrofit.callback.OnCallExecuted;
import com.riontech.retrofit.errorhandler.APIError;
import com.xtensolution.kptt.model.Response;

/**
 * Created by riontech on 6/11/17.
 */

public abstract class GeneralRestCall<T> implements OnCallExecuted<Response<T>> {

    void setDataInView(T data) {
    }

    public GeneralRestCall() {
    }

    @Override
    public void onConnectionError() {
    }

    @Override
    public void onError(APIError error) {
    }

    @Override
    public void onTimeout() {
    }

    @Override
    public void noDataFound(Response<T> response) {
    }
}
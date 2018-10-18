package com.riontech.retrofit.errorhandler;


import com.riontech.retrofit.RestApplication;

import java.io.IOException;
import java.lang.annotation.Annotation;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Response;

/**
 * Created by Vaghela Mithun R. on 30-Jun-17.
 * vaghela.mithun@gmail.com
 */
public class ErrorUtils {
    public static APIError parseError(Response<?> response) {
        Converter<ResponseBody, APIError> converter =
                RestApplication.getInstance().getRetrofit()
                        .responseBodyConverter(APIError.class, new Annotation[0]);
        APIError error;
        try {
            error = converter.convert(response.errorBody());
        } catch (IOException e) {
            return new APIError();
        }

        return error;
    }
}

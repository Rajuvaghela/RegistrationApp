package com.riontech.retrofit.exception;

import java.util.concurrent.ExecutionException;

/**
 * Throw error at runtime exception when base user not found or empty
 *
 * Created by Vaghela Mithun R. on 30-Jun-17.
 * vaghela.mithun@gmail.com
 */
public class BaseUrlNotFoundException extends RuntimeException {
    public BaseUrlNotFoundException(String error) {
        super(error);
    }
}

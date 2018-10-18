package com.xtensolution.kptt;

/**
 * Created by RIONTECH
 * Riontech on 3/2/18.
 */

public class FinalWrapper<T> {
    public final T value;

    public FinalWrapper(T value) {
        this.value = value;
    }
}
package com.xtensolution.kptt.ui.viewmodel;

import com.xtensolution.kptt.KPTApp;
import com.xtensolution.kptt.rest.RestClient;

/**
 * Created by suresh dobariya
 *  2/11/17.
 */

public class BaseViewModel {
    protected RestClient client;
    protected static final String KEY_SEARCH = "search";
    public BaseViewModel(){
        client = KPTApp.getInstance().getResClient();
    }
}

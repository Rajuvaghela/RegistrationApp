package com.xtensolution.kptt.ui.viewmodel;

import com.riontech.retrofit.callback.OnCallExecuted;
import com.riontech.retrofit.callback.RestCall;
import com.xtensolution.kptt.model.Login;
import com.xtensolution.kptt.model.Response;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;

/**
 * Created by riontech on 6/12/17.
 */

public class LoginViewModel extends BaseViewModel {

    public LoginViewModel() {
        super();
    }

    public void login(OnCallExecuted callback, String username, String password) {
        HashMap<String, String> map = new HashMap<>();
        map.put("username", username);
        map.put("password", password);
        //  Call<Response<List<LoginWithPhone>>> call = client.loginWithPhone(map);
        //  LoginWithPhone loginWithPhone = new LoginWithPhone();
        Call<Response<Login>> call = client.login(map);
        RestCall<Response<Login>> restCall = new RestCall<>();
        restCall.executeCall(callback, call);
    }
}

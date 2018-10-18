package com.xtensolution.kptt.ui.viewmodel;

import com.riontech.retrofit.callback.OnCallExecuted;
import com.riontech.retrofit.callback.RestCall;
import com.xtensolution.kptt.model.WorkSpace;

import java.util.HashMap;

import retrofit2.Call;

/**
 * Created by riontech on 16/1/18.
 */

public class WorkSpaceViewModel extends BaseViewModel {


    public WorkSpaceViewModel() {
        super();
    }

    public void workspace(OnCallExecuted callback, String workspace) {
        HashMap<String, String> map = new HashMap<>();
        map.put("workspace", workspace);

        //  Call<Response<List<LoginWithPhone>>> call = client.loginWithPhone(map);
        //  LoginWithPhone loginWithPhone = new LoginWithPhone();
        Call<com.xtensolution.kptt.model.Response<WorkSpace>> call = client.workSpace(map);
        RestCall<com.xtensolution.kptt.model.Response<WorkSpace>> restCall = new RestCall<>();
        restCall.executeCall(callback, call);
    }
}

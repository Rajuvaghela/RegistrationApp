package com.xtensolution.kptt.ui.viewmodel;

import com.riontech.retrofit.callback.OnCallExecuted;
import com.riontech.retrofit.callback.RestCall;

import com.xtensolution.kptt.model.ContactDetails;
import com.xtensolution.kptt.model.Response;
import com.xtensolution.kptt.model.SubmitData;

import java.util.HashMap;
import java.util.List;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Multipart;
import retrofit2.http.Part;

public class SubmitViewModel extends BaseViewModel {

    public SubmitViewModel(){
        super();
    }

    /*public void submitData(RequestBody requestFile, RequestBody descBody) {
        Call<SubmitData> call = client.submitData(requestFile,descBody);
     *//*   Call<Response<List<SubmitData>>> call = client.submitData(submitData);
        RestCall<Response<List<SubmitData>>> restCall = new RestCall<>();
        restCall.executeCall(callback, call);*//*
    }*/



    public void submitData(RequestBody passport_picture,RequestBody international_passport,
                           RequestBody proof_address, RequestBody proof_employment,
                           RequestBody passport_pic_upload,RequestBody international_pic_upload,
                           RequestBody proof_address_pic_upload,RequestBody proof_employment_pic_upload,
                           @Body SubmitData mSubmitData) {
      /*  HashMap<String, String> map = new HashMap<>();
        map.put("confirmnumber", confirmnumber);*/
        Call<SubmitData> call = client.submitData(passport_picture, international_passport,
                proof_address,proof_employment,passport_pic_upload,international_pic_upload,
                proof_address_pic_upload, proof_employment_pic_upload,mSubmitData);
     /*   Call<Response<List<SubmitData>>> call = client.submitData(submitData);
        RestCall<Response<List<SubmitData>>> restCall = new RestCall<>();
        restCall.executeCall(callback, call);*/
    }


    public void getCellNo(OnCallExecuted callback, String confirmnumber) {
        HashMap<String, String> map = new HashMap<>();
        map.put("confirmnumber", confirmnumber);

        Call<Response<List<ContactDetails>>> call = client.getCellNo(map);
        RestCall<Response<List<ContactDetails>>> restCall = new RestCall<>();
        restCall.executeCall(callback, call);
    }
}

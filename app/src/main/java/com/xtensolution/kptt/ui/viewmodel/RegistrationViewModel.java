package com.xtensolution.kptt.ui.viewmodel;

import com.riontech.retrofit.callback.OnCallExecuted;
import com.riontech.retrofit.callback.RestCall;

import com.xtensolution.kptt.model.ContactDetails;
import com.xtensolution.kptt.model.PdfClass;
import com.xtensolution.kptt.model.Registration;
import com.xtensolution.kptt.model.Response;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;

public class RegistrationViewModel extends BaseViewModel {
    public RegistrationViewModel() {
        super();
    }
    /**
     * @param callback
     */
  /*  public void createRegistration(OnCallExecuted callback, Registration registration) {
        Call<Response<List<Registration>>> call = client.createRegistration(registration);
        RestCall<Response<List<Registration>>> restCall = new RestCall<>();
        restCall.executeCall(callback, call);
    }*/



    public void uplodePdfFile(OnCallExecuted callback, String id,String pdf) {
        HashMap<String, String> map = new HashMap<>();
        map.put("title", id);
        map.put("pdf", pdf);

        Call<Response<PdfClass>>call = client.pdfUploadFunction(map);
        RestCall<Response<PdfClass>> restCall = new RestCall<>();
        restCall.executeCall(callback, call);
    }
}

package com.xtensolution.kptt.ui.viewmodel;

import com.riontech.retrofit.callback.OnCallExecuted;
import com.riontech.retrofit.callback.RestCall;
import com.xtensolution.kptt.model.Customer;
import com.xtensolution.kptt.model.Response;
import com.xtensolution.kptt.rest.DataWrapper;
import com.xtensolution.kptt.utils.PreferenceHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;

/**
 * Created by RIONTECH
 * Riontech on 3/11/17.
 */

public class CustomerViewModel extends BaseViewModel {
    private ArrayList<Customer> customers = DataWrapper.getInstance().getList(DataWrapper.CUSTOMER);
    public CustomerViewModel() {
        super();
    }

    /**
     * @param callback
     */
    public void fetchCustomer(OnCallExecuted callback) {
        HashMap<String, String> map = new HashMap<>();
        /*map.put("workspaceId", PreferenceHelper.getInstance().getUser().getWorkspaceId());*/
        Call<Response<List<Customer>>> call = client.getCustomer(map);
        RestCall<Response<List<Customer>>> restCall = new RestCall<>();
        restCall.executeCall(callback, call);
    }

    /**
     * @param callback
     */
    public void createCustomer(OnCallExecuted callback, Customer customer) {
        Call<Response<List<Customer>>> call = client.createCustomer(customer);
        RestCall<Response<List<Customer>>> restCall = new RestCall<>();
        restCall.executeCall(callback, call);
    }
//    public void DeleteCustomer(OnCallExecuted callback,Customer customer){
//        Call<Response<List<Customer>>> call=client.deleteCustomer(customer);
//        RestCall<Response<List<Customer>>> restCall=new RestCall<>();
//        restCall.executeCall(callback,call);
//    }

    /**
     * @param callback
     */
    public void searchCustomer(OnCallExecuted callback, String query) {
        HashMap<String, String> map = new HashMap<>();
        map.put(KEY_SEARCH, query);
        Call<Response<List<Customer>>> call = client.searchCustomer(map);
        RestCall<Response<List<Customer>>> restCall = new RestCall<>();
        restCall.executeCall(callback, call);
    }

    /**
     * @param callback
     */

  /*  public void getCustomerById(OnCallExecuted callback, String id) {
        HashMap<String, String> map = new HashMap<>();
        map.put("id", id);

        Call<Response<List<Customer>>> call = client.getCustomerById(map);
        RestCall<Response<List<Customer>>> restCall = new RestCall<>();
        restCall.executeCall(callback, call);
    }*/

    /**
     * @param callback
     */
    public void updateCustomer(OnCallExecuted callback, Customer customer) {
        Call<Response<List<Customer>>> call = client.updateCustomer(customer);
        RestCall<Response<List<Customer>>> restCall = new RestCall<>();
        restCall.executeCall(callback, call);
    }



 // wrapper class method

    public void updateCustomer(Customer editedCustomer) {
        int i = 0;
        for (Customer customer : customers) {
            if(editedCustomer.getCustomerId().equalsIgnoreCase(customer.getCustomerId())){
                customers.set(i, editedCustomer);
            }
            i = i + 1;
        }

        DataWrapper.getInstance().addList(DataWrapper.CUSTOMER, customers);
    }

}
package com.xtensolution.kptt.model;


import android.text.TextUtils;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by HalfBloodPrince(RIONTECH)
 * Riontech on 2/11/17.
 */

public class Address implements Serializable {
    private String id = null;
    private String addressId = null;
    private String line1 = "";
    private String line2 = "";
    private String city = null;
    private String state = null;
    private String country = "";

    @SerializedName("stateId")
    private int stateNo = 0;
    
    private String pincode = "";
    private String fullAddress = "";

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    public String getPincode() {
        if (!TextUtils.isEmpty(pincode))
            return pincode;

        return "";
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getState() {
        if (!TextUtils.isEmpty(state))
            return state;

        return "";
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getLine1() {
        if (!TextUtils.isEmpty(line1))
            return line1;

        return "";
    }

    public void setLine1(String line1) {
        this.line1 = line1;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        if (!TextUtils.isEmpty(city))
            return city;

        return "";
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getLine2() {
        if (!TextUtils.isEmpty(line2))
            return line2;

        return "";
    }

    public void setLine2(String line2) {
        this.line2 = line2;
    }

    public String getFullAddress() {
        return fullAddress;
    }

    public void setFullAddress(String fullAddress) {
        this.fullAddress = fullAddress;
    }


    public int getStateNo() {
        return stateNo;
    }

    public void setStateNo(int stateNo) {
        this.stateNo = stateNo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}

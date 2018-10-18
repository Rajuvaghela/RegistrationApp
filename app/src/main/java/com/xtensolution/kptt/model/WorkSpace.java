package com.xtensolution.kptt.model;

import java.io.Serializable;

/**
 * Created by riontech on 16/1/18.
 */

public class WorkSpace implements Serializable {

    private String id = null;
    private String workspaceId = null;
    private String companyName = null;
    private String gstNo = null;
    private String subscriptionStart = null;
    private String subscriptionEnd = null;

    private String addressId = null;
    private String contactId = null;
    private String line1 = null;
    private String line2 = null;
    private String city = null;
    private String state = null;
    private String pincode = null;
    private String stateCode;

    private String displayDiscount = null;
    private String otherDiscount = null;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWorkspaceId() {
        return workspaceId;
    }

    public void setWorkspaceId(String workspaceId) {
        this.workspaceId = workspaceId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getGstNo() {
        return gstNo;
    }

    public void setGstNo(String gstNo) {
        this.gstNo = gstNo;
    }

    public String getSubscriptionStart() {
        return subscriptionStart;
    }

    public void setSubscriptionStart(String subscriptionStart) {
        this.subscriptionStart = subscriptionStart;
    }

    public String getSubscriptionEnd() {
        return subscriptionEnd;
    }

    public void setSubscriptionEnd(String subscriptionEnd) {
        this.subscriptionEnd = subscriptionEnd;
    }

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    public String getContactId() {
        return contactId;
    }

    public void setContactId(String contactId) {
        this.contactId = contactId;
    }

    public String getLine1() {
        return line1;
    }

    public void setLine1(String line1) {
        this.line1 = line1;
    }

    public String getLine2() {
        return line2;
    }

    public void setLine2(String line2) {
        this.line2 = line2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }

    public String getStateCode() {
        return stateCode;
    }

    public String getDisplayDiscount() {
        return displayDiscount;
    }

    public void setDisplayDiscount(String displayDiscount) {
        this.displayDiscount = displayDiscount;
    }

    public String getOtherDiscount() {
        return otherDiscount;
    }

    public void setOtherDiscount(String otherDiscount) {
        this.otherDiscount = otherDiscount;
    }
}

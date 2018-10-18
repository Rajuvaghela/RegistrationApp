package com.xtensolution.kptt.model;


import java.io.Serializable;
import java.util.List;

/**
 * Created by RIONTECH
 * Riontech on 2/11/17.
 */

public class Agent implements Serializable {
    private String id = null;
    private String workspaceId = null;
    private boolean isChecked;
    private String agentId = null;
    private String companyName = null;
    private String firstName = null;
    private String middleName;
    private String lastName = null;
    private String gstNo;
    private String gstStateCode;
    //    @SerializedName("addresses")
//    private List<Address> address;
    private List<Contact> contacts = null;
    private List<Address> addresses = null;

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

//    public List<Address> getAddress() {
//        return address;
//    }
//
//    public void setAddress(List<Address> address) {
//        this.address = address;
//    }

    public String getGstNo() {
        return gstNo;
    }

    public void setGstNo(String gstNo) {
        this.gstNo = gstNo;
    }

    public String getAgentId() {
        return agentId;
    }

    public void setAgentId(String agentId) {
        this.agentId = agentId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }


    public String getGstStateCode() {
        return gstStateCode;
    }

    public void setGstStateCode(String gstStateCode) {
        this.gstStateCode = gstStateCode;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public String getWorkspaceId() {
        return workspaceId;
    }

    public void setWorkspaceId(String workspaceId) {
        this.workspaceId = workspaceId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}

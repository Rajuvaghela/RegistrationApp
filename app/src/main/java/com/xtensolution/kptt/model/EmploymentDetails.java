package com.xtensolution.kptt.model;

import java.io.Serializable;

public class EmploymentDetails implements Serializable {
    private String occupation = null;
    private String areyou = null;
    private String employersName = null;
    private String employersAddress = null;
    private String postalCode = null;
    private String employersTel = null;
    private String areyouid = null;

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getAreyou() {
        return areyou;
    }

    public void setAreyou(String areyou) {
        this.areyou = areyou;
    }

    public String getEmployersName() {
        return employersName;
    }

    public void setEmployersName(String employersName) {
        this.employersName = employersName;
    }

    public String getEmployersAddress() {
        return employersAddress;
    }

    public void setEmployersAddress(String employersAddress) {
        this.employersAddress = employersAddress;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getEmployersTel() {
        return employersTel;
    }

    public void setEmployersTel(String employersTel) {
        this.employersTel = employersTel;
    }

    public String getAreyouid() {
        return areyouid;
    }

    public void setAreyouid(String areyouid) {
        this.areyouid = areyouid;
    }
}

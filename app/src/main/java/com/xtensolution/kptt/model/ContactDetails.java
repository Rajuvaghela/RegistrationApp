package com.xtensolution.kptt.model;



import java.io.Serializable;

public class ContactDetails implements Serializable {
    private  String cellNo =null;
    private  String confirmCellNo =null;
    private  String email =null;
    private  String houseNumber =null;
    private  String streetName =null;
    private  String suburbDistrict =null;
    private  String cityTown =null;
    private  String province =null;
    private  String postalCode =null;


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCellNo() {
        return cellNo;
    }

    public void setCellNo(String cellNo) {
        this.cellNo = cellNo;
    }

    public String getConfirmCellNo() {
        return confirmCellNo;
    }

    public void setConfirmCellNo(String confirmCellNo) {
        this.confirmCellNo = confirmCellNo;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getSuburbDistrict() {
        return suburbDistrict;
    }

    public void setSuburbDistrict(String suburbDistrict) {
        this.suburbDistrict = suburbDistrict;
    }

    public String getCityTown() {
        return cityTown;
    }

    public void setCityTown(String cityTown) {
        this.cityTown = cityTown;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
}

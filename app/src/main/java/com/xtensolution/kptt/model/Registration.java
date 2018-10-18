package com.xtensolution.kptt.model;



import java.io.Serializable;

public class Registration implements Serializable {

    private  String  firstName = null;
    private  String  lastName = null;
    private  String  mobile = null;
    private  String  email = null;
    private  String  address = null;
    private  int     profilePicUplode ;

    private String   passportUplode = null;
    private String   internationalPassportUplode = null;
    private String   kycUplode = null;
    private String   addressUplode = null;
    private String   addressPdfUplode = null;

    private String title = null;
    private String pdf = null;




    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getProfilePicUplode() {
        return profilePicUplode;
    }

    public void setProfilePicUplode(int profilePicUplode) {
        this.profilePicUplode = profilePicUplode;
    }

    public String getPassportUplode() {
        return passportUplode;
    }

    public void setPassportUplode(String passportUplode) {
        this.passportUplode = passportUplode;
    }

    public String getInternationalPassportUplode() {
        return internationalPassportUplode;
    }

    public void setInternationalPassportUplode(String internationalPassportUplode) {
        this.internationalPassportUplode = internationalPassportUplode;
    }

    public String getKycUplode() {
        return kycUplode;
    }

    public void setKycUplode(String kycUplode) {
        this.kycUplode = kycUplode;
    }

    public String getAddressUplode() {
        return addressUplode;
    }

    public void setAddressUplode(String addressUplode) {
        this.addressUplode = addressUplode;
    }

    public String getAddressPdfUplode() {
        return addressPdfUplode;
    }

    public void setAddressPdfUplode(String addressPdfUplode) {
        this.addressPdfUplode = addressPdfUplode;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPdf() {
        return pdf;
    }

    public void setPdf(String pdf) {
        this.pdf = pdf;
    }
}

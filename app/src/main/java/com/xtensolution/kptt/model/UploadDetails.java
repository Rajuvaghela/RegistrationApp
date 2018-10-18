package com.xtensolution.kptt.model;

import java.io.Serializable;

import retrofit2.http.Multipart;

public class UploadDetails implements Serializable {
    private  String passportPictureCamera = null;
    private  String passportPictureUpload = null;
    private  String internationalPassportPictureCamera = null;
    private  String internationalPassportPictureUplode = null;
    private  String proofOfAddressCamera = null;
    private  String proofOfAddressUplode = null;
    private  String proofOfEmploymentCamera = null;
    private  String proofOfEmploymentUplode = null;
    private  String amountPaid = null;
    private  String agentNo =  null;
    private  String visaCardNumber  = null;

    // multi part
    private  String passportPictureCamera_filename = null;
    private  String internationalPassportPictureCamera_filename = null;
    private  String proofOfAddressCamera_filename = null;
    private  String proofOfEmploymentCamera_filename = null;





    public String getPassportPictureCamera() {
        return passportPictureCamera;
    }

    public void setPassportPictureCamera(String passportPictureCamera) {
        this.passportPictureCamera = passportPictureCamera;
    }

    public String getPassportPictureUpload() {
        return passportPictureUpload;
    }

    public void setPassportPictureUpload(String passportPictureUpload) {
        this.passportPictureUpload = passportPictureUpload;
    }

    public String getInternationalPassportPictureCamera() {
        return internationalPassportPictureCamera;
    }

    public void setInternationalPassportPictureCamera(String internationalPassportPictureCamera) {
        this.internationalPassportPictureCamera = internationalPassportPictureCamera;
    }

    public String getInternationalPassportPictureUplode() {
        return internationalPassportPictureUplode;
    }

    public void setInternationalPassportPictureUplode(String internationalPassportPictureUplode) {
        this.internationalPassportPictureUplode = internationalPassportPictureUplode;
    }

    public String getProofOfAddressCamera() {
        return proofOfAddressCamera;
    }

    public void setProofOfAddressCamera(String proofOfAddressCamera) {
        this.proofOfAddressCamera = proofOfAddressCamera;
    }

    public String getProofOfAddressUplode() {
        return proofOfAddressUplode;
    }

    public void setProofOfAddressUplode(String proofOfAddressUplode) {
        this.proofOfAddressUplode = proofOfAddressUplode;
    }

    public String getProofOfEmploymentCamera() {
        return proofOfEmploymentCamera;
    }

    public void setProofOfEmploymentCamera(String proofOfEmploymentCamera) {
        this.proofOfEmploymentCamera = proofOfEmploymentCamera;
    }

    public String getProofOfEmploymentUplode() {
        return proofOfEmploymentUplode;
    }

    public void setProofOfEmploymentUplode(String proofOfEmploymentUplode) {
        this.proofOfEmploymentUplode = proofOfEmploymentUplode;
    }

    public String getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(String amountPaid) {
        this.amountPaid = amountPaid;
    }

    public String getAgentNo() {
        return agentNo;
    }

    public void setAgentNo(String agentNo) {
        this.agentNo = agentNo;
    }

    public String getVisaCardNumber() {
        return visaCardNumber;
    }

    public void setVisaCardNumber(String visaCardNumber) {
        this.visaCardNumber = visaCardNumber;
    }


    public String getPassportPictureCamera_filename() {
        return passportPictureCamera_filename;
    }

    public void setPassportPictureCamera_filename(String passportPictureCamera_filename) {
        this.passportPictureCamera_filename = passportPictureCamera_filename;
    }

    public String getInternationalPassportPictureCamera_filename() {
        return internationalPassportPictureCamera_filename;
    }

    public void setInternationalPassportPictureCamera_filename(String internationalPassportPictureCamera_filename) {
        this.internationalPassportPictureCamera_filename = internationalPassportPictureCamera_filename;
    }

    public String getProofOfAddressCamera_filename() {
        return proofOfAddressCamera_filename;
    }

    public void setProofOfAddressCamera_filename(String proofOfAddressCamera_filename) {
        this.proofOfAddressCamera_filename = proofOfAddressCamera_filename;
    }

    public String getProofOfEmploymentCamera_filename() {
        return proofOfEmploymentCamera_filename;
    }

    public void setProofOfEmploymentCamera_filename(String proofOfEmploymentCamera_filename) {
        this.proofOfEmploymentCamera_filename = proofOfEmploymentCamera_filename;
    }
}

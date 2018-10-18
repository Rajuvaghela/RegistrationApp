
package com.xtensolution.kptt.model;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data implements Serializable
{

    @SerializedName("id")
    @Expose
    public String id;
    @SerializedName("ordering")
    @Expose
    public String ordering;
    @SerializedName("state")
    @Expose
    public String state;
    @SerializedName("checked_out")
    @Expose
    public String checkedOut;
    @SerializedName("checked_out_time")
    @Expose
    public String checkedOutTime;
    @SerializedName("date_of_registration")
    @Expose
    public String dateOfRegistration;
    @SerializedName("title")
    @Expose
    public String title;
    @SerializedName("surname")
    @Expose
    public String surname;
    @SerializedName("middle_name")
    @Expose
    public String middleName;
    @SerializedName("first_name")
    @Expose
    public String firstName;
    @SerializedName("gender")
    @Expose
    public String gender;
    @SerializedName("dob")
    @Expose
    public String dob;
    @SerializedName("id_password")
    @Expose
    public String idPassword;
    @SerializedName("nationality")
    @Expose
    public String nationality;
    @SerializedName("race1")
    @Expose
    public String race1;
    @SerializedName("email")
    @Expose
    public String email;
    @SerializedName("cellnumber")
    @Expose
    public String cellnumber;
    @SerializedName("alternativenumber")
    @Expose
    public String alternativenumber;
    @SerializedName("housenumber")
    @Expose
    public String housenumber;
    @SerializedName("streetname")
    @Expose
    public String streetname;
    @SerializedName("surbubdistrict")
    @Expose
    public String surbubdistrict;
    @SerializedName("postalcode")
    @Expose
    public String postalcode;
    @SerializedName("city_town")
    @Expose
    public String cityTown;
    @SerializedName("province_state")
    @Expose
    public String provinceState;
    @SerializedName("occupation")
    @Expose
    public String occupation;
    @SerializedName("are_you")
    @Expose
    public String areYou;
    @SerializedName("employer_name")
    @Expose
    public String employerName;
    @SerializedName("employer_add")
    @Expose
    public String employerAdd;
    @SerializedName("employer_postalcode")
    @Expose
    public String employerPostalcode;
    @SerializedName("employer_phone")
    @Expose
    public String employerPhone;
    @SerializedName("passport_picture")
    @Expose
    public String passportPicture;
    @SerializedName("international_passport")
    @Expose
    public String internationalPassport;
    @SerializedName("proof_address")
    @Expose
    public String proofAddress;
    @SerializedName("proof_employment")
    @Expose
    public String proofEmployment;
    @SerializedName("amountpaid")
    @Expose
    public String amountpaid;
    @SerializedName("agentnumber")
    @Expose
    public String agentnumber;
    @SerializedName("visa_card_number")
    @Expose
    public String visaCardNumber;
    @SerializedName("passport_pic_upload")
    @Expose
    public Object passportPicUpload;
    @SerializedName("international_pic_upload")
    @Expose
    public Object internationalPicUpload;
    @SerializedName("proof_address_pic_upload")
    @Expose
    public Object proofAddressPicUpload;
    @SerializedName("proof_employment_pic_upload")
    @Expose
    public Object proofEmploymentPicUpload;
    private final static long serialVersionUID = -7257380116589055059L;

}

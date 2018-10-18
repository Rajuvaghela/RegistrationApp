package com.xtensolution.kptt.retrofit;
import com.xtensolution.kptt.model.SubmitData;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ApiInterface {
    @Multipart
    @POST("index.php?option=com_kycregistration&task=webservice&tmpl=component&format=json")
    Call<SubmitData> submitData( @Part("agentnumber") String agentnumber,
                                 @Part("alternativenumber") String alternativenumber,
                                 @Part("amountpaid") String amountpaid,
                                 @Part("are_you") String are_you,
                                 @Part("cellnumber") String cellnumber,
                                 @Part("city_town") String city_town,
                                 @Part("dob") String dob,
                                 @Part("email") String email,
                                 @Part("employer_add") String employer_add,
                                 @Part("employer_name") String employer_name,
                                 @Part("employer_phone") String employer_phone,
                                 @Part("employer_postalcode") String employer_postalcode,
                                 @Part("first_name") String first_name,
                                 @Part("gender") String gender,
                                 @Part("housenumber") String housenumber,
                                 @Part("id_password") String id_password,
                                 @Part("international_passport") String international_passport,
                                 @Part("middle_name") String middle_name,
                                 @Part("nationality") String nationality,
                                 @Part("occupation") String occupation,
                                 @Part("passport_picture") String passport_picture,
                                 @Part("postalcode") String postalcode,
                                 @Part("proof_address") String proof_address,
                                 @Part("proof_employment") String proof_employment,
                                 @Part("province_state") String province_state,
                                 @Part("race") String race,
                                 @Part("streetname") String streetname,
                                 @Part("surbubdistrict") String surbubdistrict,
                                 @Part("surname") String surname,
                                 @Part("title") String title,
                                 @Part("visa_card_number") String visa_card_number,
                                 @Part("passport_pic_upload\"; filename=\"myfile.jpg\" ") RequestBody requestFile,
                                 @Part("international_pic_upload\"; filename=\"myfile.jpg\" ") RequestBody file,
                                 @Part("proof_address_pic_upload\"; filename=\"myfile.jpg\" ") RequestBody requestBody,
                                 @Part("proof_employment_pic_upload\"; filename=\"myfile.jpg\" ") RequestBody body
                                 );
/*
    Call<SubmitData> submitData(@FieldMap HashMap<String, String> params,
                                @Part("image\"; filename=\"myfile.jpg\" ") RequestBody requestFile,
                                @Part("image\"; filename=\"myfile.jpg\" ") RequestBody file,
                                @Part("image\"; filename=\"myfile.jpg\" ") RequestBody requestBody,
                                @Part("image\"; filename=\"myfile.jpg\" ") RequestBody body);*/
}
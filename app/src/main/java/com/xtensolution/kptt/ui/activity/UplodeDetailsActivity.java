/*
package com.xtensolution.kptt.ui.activity;

import android.Manifest;


import android.app.Activity;
import android.app.Dialog;


import android.content.ActivityNotFoundException;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;

import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;


import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.text.TextUtils;

import android.util.Base64;
import android.util.Base64OutputStream;
import android.util.Log;
import android.view.View;
import android.view.Window;


import android.webkit.WebView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;




import com.xtensolution.core.utils.AppLog;


import com.xtensolution.kptt.R;

import com.xtensolution.kptt.model.Registrationss;
import com.xtensolution.kptt.model.Response;
import com.xtensolution.kptt.model.SubmitData;
import com.xtensolution.kptt.model.UploadDetails;
import com.xtensolution.kptt.ui.viewmodel.SubmitViewModel;


import com.xtensolution.kptt.utils.Constants;
import com.xtensolution.kptt.utils.ImagePickerDialog;
import com.xtensolution.kptt.utils.PermissionUtils;
import com.xtensolution.kptt.utils.PreferenceHelper;

import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import java.net.URISyntaxException;
import java.util.List;

public class UplodeDetailsActivity extends BaseActionBarActivity<List<SubmitData>> implements View.OnClickListener
        , ImagePickerDialog.ImagePickerListener,
        PermissionUtils.OnPermissionGrantCallback {

    private String TAG = UplodeDetailsActivity.class.getSimpleName();
    private SubmitViewModel mViewModel;
    SubmitData mSubmitData = new SubmitData();
    Registrationss mRegistrationss = new Registrationss();

    UploadDetails mUplodeDetails = new UploadDetails();

    private TextView mTxtPassportCamera;
    private TextView mTxtPassportUpload;
    private TextView mTxtInternationalPassportCamera;
    private TextView mTxtInternantionalPassportUpload;
    private TextView mTxtProofOfAddressCamera;
    private TextView mTxtProofOfAddressUpload;
    private TextView mTxtProofOfEmploymentCamera;
    private TextView mTxtProofOfEmploymentupload;
    private TextView mTxtReadTearmsAndCondition;

    private EditText mEdtAmountpaid;
    private EditText mEdtAgentNo;
    private EditText mEdtVisaCardNumber;
    private CheckBox mCbTerms;

    private Button mBtnSubmit;

    private File mFile;
    private boolean isImgPickerOpen = false;
    private PermissionUtils mPermissionManager;
    private ImagePickerDialog mPickerDialog;

    private static final int PDF_REQUEST = 777;
    public static final int REQUEST_CAMERA = 103;


    private Bitmap bitmap;

    public int PDF_REQ_CODE = 1;

    String PdfNameHolder, PdfPathHolder, PdfID;
    Uri uri;
    String checkVar = "0";
    private static final int REQUEST_CODE_DOC = 888;
    private static final int PICKFILE_RESULT_CODE = 2;
    Activity activity;

    String path = null;


    @Override
    public void onResponse(Response<List<SubmitData>> response) {
        hideProgress();
     */
/*   Intent ii = new Intent(UplodeDetailsActivity.this,ThankYouActivity.class);
        startActivity(ii);*//*

      */
/*  if (response.isSuccess()) {
             response.getData();
//             AppUtils.onSubmitDataClear(this);
            Intent ii = new Intent(UplodeDetailsActivity.this,ThankYouActivity.class);
            startActivity(ii);
        }*//*

        if (response.getSuccess().equalsIgnoreCase("1")) {
            String df = String.valueOf(response.getSuccess());
            */
/*AppLog.d("is success=======================",df);
            Intent ii = new Intent(UplodeDetailsActivity.this, EmploymentDetailsActivity.class);
//                           startActivity(ii);
            startActivityForResult(ii, 2);*//*

            PreferenceHelper.getInstance().delete(Constants.PREF_PERSONALDETAILS);
            PreferenceHelper.getInstance().delete(Constants.PREF_CONTACTDETAILS);
            PreferenceHelper.getInstance().delete(Constants.PREF_EMPLOYMENTDETAILS);
            PreferenceHelper.getInstance().delete(Constants.PREF_UPLOADDETAILS);
            Intent intent = new Intent(UplodeDetailsActivity.this,
                    ThankYouActivity.class);

            intent.addCategory(Intent.CATEGORY_HOME);
            intent.addCategory(Intent.CATEGORY_LAUNCHER);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                    | Intent.FLAG_ACTIVITY_NEW_TASK
                    | Intent.FLAG_ACTIVITY_CLEAR_TASK
                    | Intent.FLAG_ACTIVITY_NO_HISTORY);
            startActivity(intent);

        } else {
            showSnackbar(R.string.no_internet_connection, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                }
            });
        }


    }


    @Override
    public void noDataFound(Response<List<SubmitData>> response) {
        hideProgress();
        */
/*noDataFoundSnackBar();*//*

    }

    @Override
    protected void onActionBarAttached() {
        activity = this;
        mViewModel = new SubmitViewModel();
        mTxtPassportCamera = _findViewById(R.id.txtPassportPicsCamera);
        mTxtPassportUpload = _findViewById(R.id.txtPassportPicsUplode);
        mTxtInternationalPassportCamera = _findViewById(R.id.txtIntPassportCameraPic);
        mTxtInternantionalPassportUpload = _findViewById(R.id.txtIntPassportUplodePics);
        mTxtProofOfAddressCamera = _findViewById(R.id.txtProofOfAddressCameraPic);
        mTxtProofOfAddressUpload = _findViewById(R.id.txtProofOfAddressUplodePics);
        mTxtProofOfEmploymentCamera = _findViewById(R.id.txtProofOfEmployeementCameraPic);
        mTxtProofOfEmploymentupload = _findViewById(R.id.txtProofOfEmployeementUplodePics);

        mTxtReadTearmsAndCondition = _findViewById(R.id.txtReadTermsAndCondition);

        mEdtAmountpaid = _findViewById(R.id.edtAmountPaid);
        mEdtAgentNo = _findViewById(R.id.edtAgentNo);
        mEdtVisaCardNumber = _findViewById(R.id.edtVisaCardNo);
        mCbTerms = (CheckBox) findViewById(R.id.chkboxTermsAndCondition);

        mBtnSubmit = _findViewById(R.id.btnSubmit);

        mTxtPassportCamera.setOnClickListener(this);
        mTxtPassportUpload.setOnClickListener(this);
        mTxtInternationalPassportCamera.setOnClickListener(this);
        mTxtInternantionalPassportUpload.setOnClickListener(this);
        mTxtProofOfAddressCamera.setOnClickListener(this);
        mTxtProofOfAddressUpload.setOnClickListener(this);
        mTxtProofOfEmploymentCamera.setOnClickListener(this);
        mTxtProofOfEmploymentupload.setOnClickListener(this);
        mBtnSubmit.setOnClickListener(this);
        mTxtReadTearmsAndCondition.setOnClickListener(this);

    }


    private void validateAndSave() {

        if (mTxtPassportCamera.getText().equals("camera") && mTxtPassportUpload.getText().equals("upload")) {
            showSnackbar(R.string.passport_image_validation, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                }
            });
        } else if (!mTxtPassportCamera.getText().equals("camera") && !mTxtPassportUpload.getText().equals("upload")) {
            showSnackbar(R.string.passport_image_onlyone_doc_, new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    PreferenceHelper.getInstance().getUploadDetails().setPassportPictureCamera("");
                    PreferenceHelper.getInstance().getUploadDetails().setPassportPictureUpload("");
                    mTxtPassportCamera.setText("camera");
                    mTxtPassportUpload.setText("upload");
                }
            });

        } else if (mTxtInternationalPassportCamera.getText().equals("camera") &&
                mTxtInternantionalPassportUpload.getText().equals("upload")) {
            showSnackbar(R.string.international_passport_image_validation, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                }
            });
        } else if (!mTxtInternationalPassportCamera.getText().equals("camera") &&
                !mTxtInternantionalPassportUpload.getText().equals("upload")) {
            showSnackbar(R.string.international_passport_image_onlyone_doc_, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    PreferenceHelper.getInstance().getUploadDetails().setInternationalPassportPictureCamera("");
                    PreferenceHelper.getInstance().getUploadDetails().setInternationalPassportPictureUplode("");
                    mTxtInternationalPassportCamera.setText("camera");
                    mTxtInternantionalPassportUpload.setText("upload");
                }
            });

        } else if (!mTxtProofOfAddressCamera.getText().equals("camera") &&
                !mTxtProofOfAddressUpload.getText().equals("upload")) {
            showSnackbar(R.string.proof_of_address_image_onlyone_doc_, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    PreferenceHelper.getInstance().getUploadDetails().setProofOfAddressCamera("");
                    PreferenceHelper.getInstance().getUploadDetails().setProofOfAddressUplode("");
                    mTxtProofOfAddressCamera.setText("camera");
                    mTxtProofOfAddressUpload.setText("upload");
                }
            });

        } else if (!mTxtProofOfEmploymentCamera.getText().equals("camera") &&
                !mTxtProofOfEmploymentupload.getText().equals("upload")) {
            showSnackbar(R.string.proof_of_employment_address_image_onlyone_doc_, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    PreferenceHelper.getInstance().getUploadDetails().setProofOfEmploymentCamera("");
                    PreferenceHelper.getInstance().getUploadDetails().setProofOfEmploymentUplode("");
                    mTxtProofOfEmploymentCamera.setText("camera");
                    mTxtProofOfEmploymentupload.setText("upload");
                }
            });
        } else if (TextUtils.isEmpty(mEdtAmountpaid.getText().toString())) {
            showError(mEdtAmountpaid);
        } else if (TextUtils.isEmpty(mEdtAgentNo.getText().toString())) {
            showError(mEdtAgentNo);
        } else if (TextUtils.isEmpty(mEdtVisaCardNumber.getText().toString())) {
            showError(mEdtVisaCardNumber);
        } else if (!mCbTerms.isChecked()) {
            showSnackbar(R.string.terms_chk_validation, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                }
            });
        } else {
//            getImage();
            mSubmitData.setTitle(PreferenceHelper.getInstance().getPersonalDetails().getTitle());
            mSubmitData.setGender(PreferenceHelper.getInstance().getPersonalDetails().getGender());
            mSubmitData.setSurname(PreferenceHelper.getInstance().getPersonalDetails().getSurname());
            mSubmitData.setMiddle_name(PreferenceHelper.getInstance().getPersonalDetails().getMiddlename());
            mSubmitData.setFirst_name(PreferenceHelper.getInstance().getPersonalDetails().getFirstnme());
            mSubmitData.setDob(PreferenceHelper.getInstance().getPersonalDetails().getDateofbirth());
            mSubmitData.setRace(PreferenceHelper.getInstance().getPersonalDetails().getRace());
            mSubmitData.setId_password(PreferenceHelper.getInstance().getPersonalDetails().getIdpassport());
            mSubmitData.setNationality(PreferenceHelper.getInstance().getPersonalDetails().getNationality());

            mSubmitData.setCellnumber(PreferenceHelper.getInstance().getContactDetails().getCellNo());
            mSubmitData.setAlternativenumber(PreferenceHelper.getInstance().getContactDetails().getConfirmCellNo());
            mSubmitData.setEmail(PreferenceHelper.getInstance().getContactDetails().getEmail());
            mSubmitData.setHousenumber(PreferenceHelper.getInstance().getContactDetails().getHouseNumber());
            mSubmitData.setStreetname(PreferenceHelper.getInstance().getContactDetails().getStreetName());
            mSubmitData.setSurbubdistrict(PreferenceHelper.getInstance().getContactDetails().getSuburbDistrict());
            mSubmitData.setCity_town(PreferenceHelper.getInstance().getContactDetails().getCityTown());
            mSubmitData.setProvince_state(PreferenceHelper.getInstance().getContactDetails().getProvince());
            mSubmitData.setPostalcode(PreferenceHelper.getInstance().getContactDetails().getPostalCode());

            mSubmitData.setOccupation(PreferenceHelper.getInstance().getEmploymentDetails().getOccupation());
            mSubmitData.setAre_you(PreferenceHelper.getInstance().getEmploymentDetails().getAreyou());
            mSubmitData.setEmployer_name(PreferenceHelper.getInstance().getEmploymentDetails().getEmployersName());
            mSubmitData.setEmployer_add(PreferenceHelper.getInstance().getEmploymentDetails().getEmployersAddress());
            mSubmitData.setEmployer_postalcode(PreferenceHelper.getInstance().getEmploymentDetails().getPostalCode());
            mSubmitData.setEmployer_phone(PreferenceHelper.getInstance().getEmploymentDetails().getEmployersTel());

          */
/*  String sss= PreferenceHelper.getInstance().getUploadDetails().getPassportPictureCamera();
           String ssss= PreferenceHelper.getInstance().getUploadDetails().getPassportPictureUpload();

          if(sss!=null || ssss!=null){

          }*//*


          */
/* if(PreferenceHelper.getInstance().getUploadDetails().getPassportPictureCamera()!=null ||
                   PreferenceHelper.getInstance().getUploadDetails().getPassportPictureUpload()!=null){

           }*//*

           */
/* mSubmitData.setPassport_picture(PreferenceHelper.getInstance().getUploadDetails().getPassportPictureCamera());
            mSubmitData.setPassport_pic_upload(PreferenceHelper.getInstance().getUploadDetails().getPassportPictureUpload());
            mSubmitData.setInternational_passport(PreferenceHelper.getInstance().getUploadDetails().getInternationalPassportPictureCamera());
            mSubmitData.setInternational_pic_upload(PreferenceHelper.getInstance().getUploadDetails().getInternationalPassportPictureUplode());
            mSubmitData.setProof_address(PreferenceHelper.getInstance().getUploadDetails().getProofOfAddressCamera());
            mSubmitData.setProof_address_pic_upload(PreferenceHelper.getInstance().getUploadDetails().getProofOfAddressUplode());
            mSubmitData.setProof_employment(PreferenceHelper.getInstance().getUploadDetails().getProofOfEmploymentCamera());
            mSubmitData.setProof_employment_pic_upload(PreferenceHelper.getInstance().getUploadDetails().getProofOfEmploymentUplode());*//*


           */
/* mSubmitData.setPassport_picture(PreferenceHelper.getInstance().getUploadDetails().getPassportPictureCamera());
            mSubmitData.setPassport_pic_upload(PreferenceHelper.getInstance().getUploadDetails().getPassportPictureUpload());
            mSubmitData.setInternational_passport(PreferenceHelper.getInstance().getUploadDetails().getInternationalPassportPictureCamera());
            mSubmitData.setInternational_pic_upload(PreferenceHelper.getInstance().getUploadDetails().getInternationalPassportPictureUplode());
            mSubmitData.setProof_address(PreferenceHelper.getInstance().getUploadDetails().getProofOfAddressCamera());
            mSubmitData.setProof_address_pic_upload(PreferenceHelper.getInstance().getUploadDetails().getProofOfAddressUplode());
            mSubmitData.setProof_employment(PreferenceHelper.getInstance().getUploadDetails().getProofOfEmploymentCamera());
            mSubmitData.setProof_employment_pic_upload(PreferenceHelper.getInstance().getUploadDetails().getProofOfEmploymentUplode());*//*


           */
/* mSubmitData.setPassport_picture("jayganesh.jpeg");
            mSubmitData.setPassport_pic_upload("sureshdobariy.jpeg");
            mSubmitData.setInternational_passport("");
            mSubmitData.setInternational_pic_upload("ccccccccccc.jpeg");
            mSubmitData.setProof_address("sssssssssss.jpeg");
            mSubmitData.setProof_address_pic_upload("");
            mSubmitData.setProof_employment("dddddddddd.jpeg");
            mSubmitData.setProof_employment_pic_upload("");*//*


            mSubmitData.setAmountpaid(mEdtAmountpaid.getText().toString());
            mSubmitData.setAgentnumber(mEdtAgentNo.getText().toString());
            mSubmitData.setVisa_card_number(mEdtVisaCardNumber.getText().toString());

            showProgress();
//            mViewModel.submitData(this, mSubmitData);

          */
/* PreferenceHelper.getInstance().delete(Constants.PREF_PERSONALDETAILS);
           PreferenceHelper.getInstance().delete(Constants.PREF_CONTACTDETAILS);
           PreferenceHelper.getInstance().delete(Constants.PREF_EMPLOYMENTDETAILS);
           PreferenceHelper.getInstance().delete(Constants.PREF_UPLOADDETAILS);
           Intent intent = new Intent(UplodeDetailsActivity.this,
                   ThankYouActivity.class);

           intent.addCategory(Intent.CATEGORY_HOME);
           intent.addCategory(Intent.CATEGORY_LAUNCHER);
           intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                   | Intent.FLAG_ACTIVITY_NEW_TASK
                   | Intent.FLAG_ACTIVITY_CLEAR_TASK
                   | Intent.FLAG_ACTIVITY_NO_HISTORY);
           startActivity(intent);*//*



//            AppUtils.onSubmitDataClear(this);


//            AppLog.d("getTitle========================",PreferenceHelper.getInstance().getPersonalDetails().getTitle());
        }


    }




    @Override
    public int getProgressBarId() {
        return R.id.progressBar;
    }


    private void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }


    private void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }




    private void getImage() {
        mPickerDialog.getBase64StringFromImage(mFile, new ImagePickerDialog.Base64CompressCallBack() {
            @Override
            public void getCompressString(String base64String) {
                        */
/*final Registration registration = new Registration();

                        registration.setPassportUplode(base64String);
                        String ss =base64String;
                        AppLog.d("Base64 String=============================",base64String.toString());
//                        hideProgress();
//                        mViewModel.onNewInstallation(installation)
                        mViewModel.createRegistration(RegistrationActivity.this, mRegistration);*//*


//                 mRegistration = new Registration();
                if (checkVar.equalsIgnoreCase("0")) {
                    mUplodeDetails.setPassportPictureCamera(base64String);
                    String ss = base64String;
                    AppLog.d("Base64 String Passport camera=============================", base64String.toString());
              */
/*  } else if (checkVar.equalsIgnoreCase("1")) {
                    mUplodeDetails.setPassportPictureUpload(base64String);
                    String ss = base64String;
                    AppLog.d("Base64 String Passport Upload=============================", base64String.toString());*//*

                } else if (checkVar.equalsIgnoreCase("2")) {
                    mUplodeDetails.setInternationalPassportPictureCamera(base64String);
                    String ss = base64String;
                    AppLog.d("Base64 String international pass camera=============================", base64String.toString());
               */
/* } else if (checkVar.equalsIgnoreCase("3")) {
                    mUplodeDetails.setInternationalPassportPictureUplode(base64String);
                    String ss = base64String;
                    AppLog.d("Base64 String International pass Uplode=============================", base64String.toString());*//*



                }else if (checkVar.equalsIgnoreCase("4")) {
                    mUplodeDetails.setProofOfAddressCamera(base64String);
                    String ss = base64String;
                    AppLog.d("Base64 String Proof of address camera=============================", base64String.toString());
              */
/*  } else if (checkVar.equalsIgnoreCase("5")) {
                    mUplodeDetails.setProofOfAddressUplode(base64String);
                    String ss = base64String;
                    AppLog.d("Base64 String Proof Of Address Uplode=============================", base64String.toString());*//*

                } else if (checkVar.equalsIgnoreCase("6")) {
                    mUplodeDetails.setProofOfEmploymentCamera(base64String);
                    String ss = base64String;
                    AppLog.d("Base64 String proof of employment Camera=============================", base64String.toString());
                }
               */
/* } else if (checkVar.equalsIgnoreCase("7")) {
                    mUplodeDetails.setProofOfEmploymentUplode(base64String);
                    String ss = base64String;
                    AppLog.d("Base64 String proof of employment Upload=============================", base64String.toString());
                }*//*

//                mRegistrationss.setUploadDetails(mUplodeDetails);

                PreferenceHelper.getInstance().saveUploadDetails(mUplodeDetails);


//                        hideProgress();
//                        mViewModel.onNewInstallation(installation)
//                mViewModel.createRegistration(RegistrationActivity.this, mRegistration);
            }
        });

    }

    private void getUplode() {


                if (checkVar.equalsIgnoreCase("1")) {
                    mUplodeDetails.setPassportPictureUpload(readFileAsBase64String(path));
                    String ss = readFileAsBase64String(path);
                    AppLog.d("Base64 String Passport Upload=============================", ss.toString());

                } else if (checkVar.equalsIgnoreCase("3")) {
                    mUplodeDetails.setInternationalPassportPictureUplode(readFileAsBase64String(path));
                    String ss = readFileAsBase64String(path);
                    AppLog.d("Base64 String International pass Uplode=============================",ss.toString());



                } else if (checkVar.equalsIgnoreCase("5")) {
                    mUplodeDetails.setProofOfAddressUplode(readFileAsBase64String(path));
                    String ss = readFileAsBase64String(path);
                    AppLog.d("Base64 String Proof Of Address Uplode=============================", ss.toString());

                } else if (checkVar.equalsIgnoreCase("7")) {
                    mUplodeDetails.setProofOfEmploymentUplode(readFileAsBase64String(path));
                    String ss = readFileAsBase64String(path);
                    AppLog.d("Base64 String proof of employment Upload=============================", ss.toString());
                }


                PreferenceHelper.getInstance().saveUploadDetails(mUplodeDetails);




    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case PICKFILE_RESULT_CODE:
                if (resultCode == RESULT_OK) {
                    Uri FilePath = data.getData();
//                    String path = null;
                    try {
                        path = getFilePath(activity, FilePath);
                    } catch (URISyntaxException e) {
                        e.printStackTrace();
                    }

                    String file = path.substring(path.lastIndexOf("/") + 1);

                    String file_extension = "." + file.substring(file.lastIndexOf(".") + 1);
                    Log.e("path: ", "" + path);
                    Log.e("filename: ", "" + file);
                    Log.e("extension: ", "" + file_extension);

                    String filename = file.substring(0, file.lastIndexOf('.'));

                    Log.e("log", "64+++++++++++++++" + readFileAsBase64String(path));



                }
                getUplode();
                break;

            case ImagePickerDialog.REQUEST_CAMERA:
                if (isImgPickerOpen) {
                    mPickerDialog.onActivityResult(requestCode, resultCode, data);

                }

                 getImage();
       */
/* if (requestCode == PDF_REQ_CODE && resultCode == RESULT_OK && data != null && data.getData() != null) {
            uri = data.getData();
            mEdtPdfFileName.setText(uri.toString());
        }*//*




                break;
        }


    }






    private String readFileAsBase64String(String path) {
        try {
            InputStream is = new FileInputStream(path);
//            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            Base64OutputStream b64os = new Base64OutputStream(baos, Base64.DEFAULT);
            byte[] buffer = new byte[8192];
            int bytesRead;
            try {
                while ((bytesRead = is.read(buffer)) > -1) {
                    b64os.write(buffer, 0, bytesRead);
                }
                return baos.toString();
            } catch (IOException e) {
                Log.e("can not read", "Cannot read file " + path, e);
                // Or throw if you prefer
                return "";
            } finally {
                closeQuietly(is);
                closeQuietly(b64os); // This also closes baos
            }
        } catch (FileNotFoundException e) {
            Log.e("file not found", "File not found " + path, e);
            // Or throw if you prefer
            return "";
        }
    }

    private void closeQuietly(Closeable closeable) {
        try {
            closeable.close();
        } catch (IOException e) {
        }
    }


    private void selectFileCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        startActivityForResult(intent, PICKFILE_RESULT_CODE);
        getBitmapUplode();



        startActivityForResult(intent, REQUEST_CAMERA);

    }
    //change

    private void selectFile() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);

        startActivityForResult(intent, PICKFILE_RESULT_CODE);
        getBitmapUplode();

    }

    public String getFilePath(Context context, Uri uri) throws URISyntaxException {
        String selection = null;
        String[] selectionArgs = null;

        if (Build.VERSION.SDK_INT >= 19 && DocumentsContract.isDocumentUri(context.getApplicationContext(), uri)) {
            if (isExternalStorageDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                return Environment.getExternalStorageDirectory() + "/" + split[1];
            } else if (isDownloadsDocument(uri)) {
                final String id = DocumentsContract.getDocumentId(uri);
                uri = ContentUris.withAppendedId(
                        Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));
            } else if (isMediaDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];
                if ("image".equals(type)) {
                    uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(type)) {
                    uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(type)) {
                    uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }
                selection = "_id=?";
                selectionArgs = new String[]{
                        split[1]
                };
            }
        }
        if ("content".equalsIgnoreCase(uri.getScheme())) {
            String[] projection = {
                    MediaStore.Images.Media.DATA
            };
            Cursor cursor = null;
            try {
                cursor = context.getContentResolver()
                        .query(uri, projection, selection, selectionArgs, null);
                int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                if (cursor.moveToFirst()) {
                    return cursor.getString(column_index);
                }
            } catch (Exception e) {
            }
        } else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }
        return null;
    }

    public boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    public boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    public boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }


    private void showError(EditText editText) {
        try {
            editText.setText("");
            editText.requestFocus();
            editText.setError("Please enter value.");
        } catch (Exception e) {
            AppLog.e(TAG, e.getMessage(), e);
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.txtPassportPicsCamera:
                checkVar = "0";

                mPermissionManager = new PermissionUtils(UplodeDetailsActivity.this,
                        new String[]{
                                Manifest.permission.READ_EXTERNAL_STORAGE,
                                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                Manifest.permission.CAMERA}, UplodeDetailsActivity.this);
//                validateAndSave();
                selectFile();
                break;

            case R.id.txtIntPassportCameraPic:
//                validateAndSave();
                checkVar = "2";
                mPermissionManager = new PermissionUtils(UplodeDetailsActivity.this,
                        new String[]{
                                Manifest.permission.READ_EXTERNAL_STORAGE,
                                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                Manifest.permission.CAMERA}, UplodeDetailsActivity.this);
                break;

            case R.id.txtProofOfAddressCameraPic:
                checkVar = "4";

                mPermissionManager = new PermissionUtils(UplodeDetailsActivity.this,
                        new String[]{
                                Manifest.permission.READ_EXTERNAL_STORAGE,
                                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                Manifest.permission.CAMERA}, UplodeDetailsActivity.this);
//                validateAndSave();
                break;

            case R.id.txtProofOfEmployeementCameraPic:
//                validateAndSave();
                checkVar = "6";
                mPermissionManager = new PermissionUtils(UplodeDetailsActivity.this,
                        new String[]{
                                Manifest.permission.READ_EXTERNAL_STORAGE,
                                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                Manifest.permission.CAMERA}, UplodeDetailsActivity.this);
                break;


            case R.id.txtPassportPicsUplode:
//                validateAndSave();
                checkVar = "1";
                mPermissionManager = new PermissionUtils(UplodeDetailsActivity.this,
                        new String[]{
                                Manifest.permission.READ_EXTERNAL_STORAGE,
                                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                Manifest.permission.CAMERA}, UplodeDetailsActivity.this);
                selectFile();
                break;


            case R.id.txtIntPassportUplodePics:
//                validateAndSave();
                checkVar = "3";
                mPermissionManager = new PermissionUtils(UplodeDetailsActivity.this,
                        new String[]{
                                Manifest.permission.READ_EXTERNAL_STORAGE,
                                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                Manifest.permission.CAMERA}, UplodeDetailsActivity.this);
                selectFile();
                break;


            case R.id.txtProofOfAddressUplodePics:
//                validateAndSave();
                checkVar = "5";
                mPermissionManager = new PermissionUtils(UplodeDetailsActivity.this,
                        new String[]{
                                Manifest.permission.READ_EXTERNAL_STORAGE,
                                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                Manifest.permission.CAMERA}, UplodeDetailsActivity.this);
                selectFile();
                break;


            case R.id.txtProofOfEmployeementUplodePics:
//                validateAndSave();
                checkVar = "7";
                mPermissionManager = new PermissionUtils(UplodeDetailsActivity.this,
                        new String[]{
                                Manifest.permission.READ_EXTERNAL_STORAGE,
                                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                Manifest.permission.CAMERA}, UplodeDetailsActivity.this);
                selectFile();
                break;


            case R.id.btnSubmit:
                validateAndSave();
                */
/*Intent btnNext = new Intent(EmploymentDetailsActivity.this,
                        UplodeDetailsActivity.class);
                startActivity(btnNext);*//*

                break;

            case R.id.txtReadTermsAndCondition:

                final Dialog dialog = new Dialog(UplodeDetailsActivity.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.dialog_teams_condition);
                ImageView imgClose = (ImageView) dialog.findViewById(R.id.imgPopUpCancel);
                WebView wb = (WebView) dialog.findViewById(R.id.webview);
                wb.getSettings().setJavaScriptEnabled(true);
                wb.loadUrl("file:///android_asset/index.html");

                imgClose.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();

                    }
                });

                dialog.show();


                break;

        }
    }


    @Override
    protected String getScreenTitle() {
        return null;
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_uplode_details;
    }


    @Override
    public void getBitmapImageFromPhone(File file, Uri fileUri) {
        mFile = file;

        if (checkVar.equalsIgnoreCase("0")) {
            mTxtPassportCamera.setText(mFile.getPath());
            isImgPickerOpen = false;

        }
      */
/*  if (checkVar.equalsIgnoreCase("1")) {
            mTxtPassportUpload.setText(mFile.getPath());
            isImgPickerOpen = false;
        }*//*

        if (checkVar.equalsIgnoreCase("2")) {
            mTxtInternationalPassportCamera.setText(mFile.getPath());
            isImgPickerOpen = false;
        }
      */
/*  if (checkVar.equalsIgnoreCase("3")) {
            mTxtInternantionalPassportUpload.setText(mFile.getPath());
            isImgPickerOpen = false;
        }*//*


        if (checkVar.equalsIgnoreCase("4")) {
            mTxtProofOfAddressCamera.setText(mFile.getPath());
            isImgPickerOpen = false;
        }
      */
/*  if (checkVar.equalsIgnoreCase("5")) {
            mTxtProofOfAddressUpload.setText(mFile.getPath());
            isImgPickerOpen = false;
        }*//*

        if (checkVar.equalsIgnoreCase("6")) {
            mTxtProofOfEmploymentCamera.setText(mFile.getPath());
            isImgPickerOpen = false;
        }
       */
/* if (checkVar.equalsIgnoreCase("7")) {
            mTxtProofOfEmploymentupload.setText(mFile.getPath());
            isImgPickerOpen = false;
        }*//*

      */
/*  if(checkVar.equalsIgnoreCase("7")) {
            mTxtProofOfEmploymentupload.setImageBitmap(mPickerDialog.getBitmapFromFile(file));
            isImgPickerOpen = false;
        }*//*


    }

    //

    public void getBitmapUplode() {

        if (checkVar.equalsIgnoreCase("1")) {
            mTxtPassportUpload.setText("/storag");
            isImgPickerOpen = false;
        }

        if (checkVar.equalsIgnoreCase("3")) {
            mTxtInternantionalPassportUpload.setText("/storag");
            isImgPickerOpen = false;
        }


        if (checkVar.equalsIgnoreCase("5")) {
            mTxtProofOfAddressUpload.setText("/storag");
            isImgPickerOpen = false;
        }

        if (checkVar.equalsIgnoreCase("7")) {
            mTxtProofOfEmploymentupload.setText("/storag");
            isImgPickerOpen = false;
        }
      */
/*  if(checkVar.equalsIgnoreCase("7")) {
            mTxtProofOfEmploymentupload.setImageBitmap(mPickerDialog.getBitmapFromFile(file));
            isImgPickerOpen = false;
        }*//*


    }
    //




    private void openImageDialog() {
        isImgPickerOpen = true;
        if (mPickerDialog == null) {
            mPickerDialog = new ImagePickerDialog(this, this);
        }
        if (checkVar.equalsIgnoreCase("0")
                || checkVar.equalsIgnoreCase("2")
                || checkVar.equalsIgnoreCase("4")
                || checkVar.equalsIgnoreCase("6")) {
            mPickerDialog.openImagePickedFromDialog();
       */
/* }else if(checkVar.equalsIgnoreCase("1")
                || checkVar.equalsIgnoreCase("3")
                || checkVar.equalsIgnoreCase("5")
                || checkVar.equalsIgnoreCase("7")){
                mPickerDialog.openImagePickedFromGalllery();
        }*//*

        }

    }

    @Override
    public void onPermissionGranted() {
        openImageDialog();

    }

    @Override
    public void onPermissionError(String permission) {

    }
}
*/

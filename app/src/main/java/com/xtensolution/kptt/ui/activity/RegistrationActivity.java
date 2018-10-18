package com.xtensolution.kptt.ui.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;



import com.xtensolution.core.utils.AppLog;
import com.xtensolution.core.utils.ResourceUtils;
import com.xtensolution.kptt.R;
import com.xtensolution.kptt.model.PdfClass;
import com.xtensolution.kptt.model.Registration;
import com.xtensolution.kptt.model.Response;
import com.xtensolution.kptt.rest.GeneralRestCall;
import com.xtensolution.kptt.ui.listener.TextChangeListener;
import com.xtensolution.kptt.ui.viewmodel.RegistrationViewModel;


import com.xtensolution.kptt.utils.FilePath;
import com.xtensolution.kptt.utils.ImagePickerDialog;
import com.xtensolution.kptt.utils.PermissionUtils;

import com.xtensolution.kptt.utils.ValidationUtils;


import java.io.File;
import java.util.List;
import java.util.regex.Pattern;




public class RegistrationActivity extends BaseActionBarActivity<List<Registration>> implements View.OnClickListener
        , ImagePickerDialog.ImagePickerListener,
        PermissionUtils.OnPermissionGrantCallback {

    private String TAG = RegistrationActivity.class.getSimpleName();
    private RegistrationViewModel mViewModel;
    private Registration mRegistration = new Registration();
    private PdfClass mPdfClass = new PdfClass();

    // #1 General
    private TextInputEditText mEdtFirstName;
    private TextInputEditText mEdtLastName;
    private TextInputEditText mEdtMobile;
    private TextInputEditText mEdtEmail;
    private TextInputEditText mEdtAddress;

    private TextInputEditText mEdtPdfFileName;


    private TextInputLayout mTilFirstName;
    private TextInputLayout mTilLastName;
    private TextInputLayout mTilMobile;
    private TextInputLayout mTilEmail;
    private TextInputLayout mTilAddress;

    private ImageView mImgProfileUplode;
    private ImageView mBtnPassportUplode;
    private ImageView mBtnInternationalPassportUplode;
    private ImageView mBtnKyCUplode;
    private ImageView mBtnAddressUplode;



    private Pattern mPhonePattern;
    private ProgressBar mProgProfileUplode;

    private File mFile;
    private boolean isImgPickerOpen = false;
    private PermissionUtils mPermissionManager;
    private ImagePickerDialog mPickerDialog;

    private static final int PDF_REQUEST = 777;
    private Bitmap bitmap;

    public int PDF_REQ_CODE = 1;

    String PdfNameHolder, PdfPathHolder, PdfID;
    Uri uri;

    private Button mBtnChoosePdf;
    private Button mBtnUplodePdf;
    String checkVar= "0";


    @Override
    public void onResponse(Response<List<Registration>> response) {

    }

    @Override
    public void noDataFound(Response<List<Registration>> response) {

    }

    @Override
    protected void onActionBarAttached() {

        setHomeButtonEnable(false);
        mViewModel = new RegistrationViewModel();
        mPhonePattern = Pattern.compile("^(?:(?:\\+|0{0,2})91(\\s*[\\-]\\s*)?|[0]?)?[789]\\d{9}$");

        mTilFirstName = _findViewById(R.id.txtCreateProfileFirstName);
        mTilLastName = _findViewById(R.id.txtCreateProfileLastName);
        mTilMobile = _findViewById(R.id.txtCreateProfilePhoneNo);
        mTilEmail = _findViewById(R.id.txtCreateProfileEmail);
        mTilAddress = _findViewById(R.id.txtCreateProfileAddress);

        //General
        mEdtFirstName = _findViewById(R.id.edtCreateProfileFirstName);
        mEdtLastName = _findViewById(R.id.edtCreateProfileLastName);
        mEdtMobile = _findViewById(R.id.edtCreateProfilePhoneNo);
        mEdtEmail = _findViewById(R.id.edtCreateProfileEmail);
        mEdtAddress = _findViewById(R.id.edtCreateProfileAddress);

        mEdtPdfFileName = _findViewById(R.id.editPdfFileName);

        mImgProfileUplode = _findViewById(R.id.imgUserProfile);
        mProgProfileUplode = _findViewById(R.id.pbLoaderProfileUser);

        mBtnPassportUplode = _findViewById(R.id.imgButtonPassport);
        mBtnInternationalPassportUplode = _findViewById(R.id.imgButtonInternationalPassport);
        mBtnKyCUplode = _findViewById(R.id.imgButtonKyc);
        mBtnAddressUplode = _findViewById(R.id.imgButtonUplodeAddress);
//        mBtnUplodeAddressPdf = _findViewById(R.id.imgButtonUplodeAddressPdf);

        //pdf
        mBtnChoosePdf = _findViewById(R.id.buttonChoose);
        mBtnUplodePdf = _findViewById(R.id.buttonUpload);


        Button btnSave = _findViewById(R.id.btnCreateProfile);
        setTextChangeLister();
        btnSave.setOnClickListener(this);
        mBtnPassportUplode.setOnClickListener(this);
        mBtnInternationalPassportUplode.setOnClickListener(this);
        mBtnKyCUplode.setOnClickListener(this);
        mBtnAddressUplode.setOnClickListener(this);
//        mBtnUplodeAddressPdf.setOnClickListener(this);

        mBtnChoosePdf.setOnClickListener(this);
        mBtnUplodePdf.setOnClickListener(this);


    }


    @Override
    protected String getScreenTitle() {
        return ResourceUtils.getString(this, R.string.registration);
    }

   /* @Override
    public void showProgress() {
        mPbLoader.showHideProgress(true);
    }

    @Override
    public void hideProgress() {
        mPbLoader.showHideProgress(false);
    }*/

  /*  @Override
    public void showAlertMessage(String message) {
        AlertDialogUtils.showSnakbar(findViewById(R.id.rootView), message);
    }*/

    @Override
    protected int getLayout() {
        return R.layout.activity_registration;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.imgButtonPassport:
                checkVar ="0";

                mPermissionManager = new PermissionUtils(RegistrationActivity.this,
                        new String[]{
                                Manifest.permission.READ_EXTERNAL_STORAGE,
                                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                Manifest.permission.CAMERA}, RegistrationActivity.this);
//                validateAndSave();
                break;

            case R.id.imgButtonInternationalPassport:
//                validateAndSave();
                checkVar ="1";
                mPermissionManager = new PermissionUtils(RegistrationActivity.this,
                        new String[]{
                                Manifest.permission.READ_EXTERNAL_STORAGE,
                                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                Manifest.permission.CAMERA}, RegistrationActivity.this);
                break;

            case R.id.imgButtonKyc:
//                validateAndSave();
                checkVar ="2";
                mPermissionManager = new PermissionUtils(RegistrationActivity.this,
                        new String[]{
                                Manifest.permission.READ_EXTERNAL_STORAGE,
                                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                Manifest.permission.CAMERA}, RegistrationActivity.this);
                break;

            case R.id.imgButtonUplodeAddress:
//                validateAndSave();
                checkVar ="3";
                mPermissionManager = new PermissionUtils(RegistrationActivity.this,
                        new String[]{
                                Manifest.permission.READ_EXTERNAL_STORAGE,
                                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                Manifest.permission.CAMERA}, RegistrationActivity.this);
                break;

            case R.id.buttonChoose:
                selectPdf();
                break;

            case R.id.buttonUpload:
                pdfUploadFunction();

                break;

            case R.id.btnCreateProfile:
                validateAndSave();
                break;
        }

    }

    private void selectPdf() {
        Intent intent = new Intent();

        intent.setType("application/pdf");

        intent.setAction(Intent.ACTION_GET_CONTENT);

        startActivityForResult(Intent.createChooser(intent, "Select Pdf"), PDF_REQ_CODE);


    }

    public void pdfUploadFunction() {

      /*  PdfNameHolder = mEdtPdfFileName.getText().toString();
        AppLog.d("Pdf Name ================",PdfNameHolder);
        PdfPathHolder = FilePath.getPath(this, uri);

        //File file=new File(uri.getPath());


        RequestBody Title=RequestBody.create(MediaType.parse("text/plain"),mEdtPdfFileName.getText().toString());
        RequestBody Pdf=RequestBody.create(MediaType.parse("application/pdf"), PdfPathHolder);
        mRegistration.setTitle(Title.toString());
        mRegistration.setPdf(Pdf.toString());
        mViewModel.createRegistration(this,mRegistration);*/  // My Framework


        PdfNameHolder = mEdtPdfFileName.getText().toString();
        PdfPathHolder = FilePath.getPath(this, uri);

        //File file=new File(uri.getPath());

        String title = mEdtPdfFileName.getText().toString();
        String pdf = PdfPathHolder;

        mViewModel.uplodePdfFile(new GeneralRestCall<PdfClass>() {


            @Override
            public void onResponse(Response<PdfClass> response) {
                if (response.getData() != null ) {
                    mPdfClass = response.getData();
//                    setData(mCustomer);
                } else {
                    // customer is not available or something went wrong.
                    noDataFoundSnackBar();
                    finish();
                }
            }
        }, title, pdf);
    }

    private void noDataFoundSnackBar() {
        showSnackbar(R.string.nodeta_found, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }


    @Override
    public void onRequestPermissionsResult(int RC, String per[], int[] Result) {

        switch (RC) {

            case 1:

                if (Result.length > 0 && Result[0] == PackageManager.PERMISSION_GRANTED) {

                    Toast.makeText(this,"Permission Granted", Toast.LENGTH_LONG).show();

                } else {

                    Toast.makeText(this,"Permission Canceled", Toast.LENGTH_LONG).show();

                }
                break;
        }
    }




    private void validateAndSave() {
        try {
            if (!ValidationUtils.isValidString(mEdtFirstName.getText().toString().trim())) {
                mTilFirstName.setErrorEnabled(true);
                mTilFirstName.setError(ResourceUtils.getString(this,R.string.enter_first_name));
                mEdtFirstName.requestFocus();

            } else if (!ValidationUtils.isValidString(mEdtLastName.getText().toString().trim())) {
                mTilLastName.setErrorEnabled(true);
                mTilLastName.setError(ResourceUtils.getString(R.string.enter_last_name));
                mEdtLastName.requestFocus();

            } else if (!ValidationUtils.isValidMobileNo(mTilMobile, mEdtMobile)) {

            } else if (!ValidationUtils.isValidString(mEdtEmail.getText().toString().trim())) {
                mTilEmail.setErrorEnabled(true);
                mTilEmail.setError(ResourceUtils.getString(R.string.enter_email));
                mEdtEmail.requestFocus();

            } else if (!ValidationUtils.isValidString(mEdtAddress.getText().toString().trim())) {
                mTilAddress.setErrorEnabled(true);
                mTilAddress.setError(ResourceUtils.getString(R.string.enter_Address));
                mEdtAddress.requestFocus();

            } else {
                mTilFirstName.setErrorEnabled(false);
                mTilLastName.setErrorEnabled(false);
                mTilMobile.setErrorEnabled(false);
                mTilEmail.setErrorEnabled(false);
                mTilAddress.setErrorEnabled(false);


                mRegistration.setFirstName(mEdtFirstName.getText().toString());
                mRegistration.setLastName(mEdtLastName.getText().toString());
                mRegistration.setMobile(mEdtMobile.getText().toString());
                mRegistration.setEmail(mEdtEmail.getText().toString());
                mRegistration.setAddress(mEdtAddress.getText().toString());


                getImage();

//                showProgress();

             /*   if (mFile != null) {
                    getDetails();
                } else {
                    showAlertMessage("uplode Image");
                }*/

              /*  mPickerDialog.getBase64StringFromImage(mFile, new ImagePickerDialog.Base64CompressCallBack() {
                    @Override
                    public void getCompressString(String base64String) {
                        *//*final Registration registration = new Registration();

                        registration.setPassportUplode(base64String);
                        String ss =base64String;
                        AppLog.d("Base64 String=============================",base64String.toString());
//                        hideProgress();
//                        mViewModel.onNewInstallation(installation)
                        mViewModel.createRegistration(RegistrationActivity.this, mRegistration);*//*

                        final Registration registration = new Registration();
                        if(checkVar=="0"){
                            registration.setPassportUplode(base64String);
                            String ss =base64String;
                            AppLog.d("Base64 String setPassport=============================",base64String.toString());
                        }
                        else if(checkVar =="1"){
                            registration.setInternationalPassportUplode(base64String);
                            String ss =base64String;
                            AppLog.d("Base64 String SetInternational Passport=============================",base64String.toString());
                        }
                        else if(checkVar =="2"){
                            registration.setKycUplode(base64String);
                            String ss =base64String;
                            AppLog.d("Base64 String SetKyC Doc=============================",base64String.toString());
                        }
                        else if(checkVar =="3"){
                            registration.setAddressUplode(base64String);
                            String ss =base64String;
                            AppLog.d("Base64 String Uplode Address Image=============================",base64String.toString());
                        }


//                        hideProgress();
//                        mViewModel.onNewInstallation(installation)
                        mViewModel.createRegistration(RegistrationActivity.this, mRegistration);
                    }
                });*/

//                mViewModel.createRegistration(RegistrationActivity.this, mRegistration);
            }
        } catch (Exception e) {
            AppLog.e(TAG, e.getMessage(), e);
        }







  /*  private void getDetails() {
        AlertDialogUtils.showDialogWithYesNoButton(this, ResourceUtils.getString(this, R.string.continue_message),
                ResourceUtils.getString(this, R.string.yes),
                ResourceUtils.getString(this, R.string.no),
                new AlertDialogUtils.PositiveNegativeCallback() {
                    @Override
                    public void onPositiveClick() {
                        if (mPickerDialog != null) {
//                            showProgress();
                            mPickerDialog.getBase64StringFromImage(mFile, new ImagePickerDialog.Base64CompressCallBack() {
                                @Override
                                public void getCompressString(String base64String) {
                                    final Registration registration = new Registration();


                                    registration.setPassportUplode(base64String);
//                                    hideProgress();
                                    mViewModel.createRegistration(RegistrationActivity.this,registration);
                                }
                            });
                        }
//                        installation.setPicture(mPickerDialog.encodeTobase64(mFile));

                    }

                    @Override
                    public void onNegativeClick() {

                    }
                });
    }*/



  /*  private void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }*/
    }


    private void getImage(){
        mPickerDialog.getBase64StringFromImage(mFile, new ImagePickerDialog.Base64CompressCallBack() {
            @Override
            public void getCompressString(String base64String) {
                        /*final Registration registration = new Registration();

                        registration.setPassportUplode(base64String);
                        String ss =base64String;
                        AppLog.d("Base64 String=============================",base64String.toString());
//                        hideProgress();
//                        mViewModel.onNewInstallation(installation)
                        mViewModel.createRegistration(RegistrationActivity.this, mRegistration);*/

//                 mRegistration = new Registration();
                if(checkVar.equalsIgnoreCase("0")){
                    mRegistration.setPassportUplode(base64String);
                    String ss =base64String;
                    AppLog.d("Base64 String Passport=============================",base64String.toString());
                }
                else if(checkVar.equalsIgnoreCase("1")){
                    mRegistration.setInternationalPassportUplode(base64String);
                    String ss =base64String;
                    AppLog.d("Base64 String International Passport=============================",base64String.toString());
                }
                else if(checkVar.equalsIgnoreCase("2")){
                    mRegistration.setKycUplode(base64String);
                    String ss =base64String;
                    AppLog.d("Base64 String KyC Doc=============================",base64String.toString());
                }
                else if(checkVar.equalsIgnoreCase( "3")){
                    mRegistration.setAddressUplode(base64String);
                    String ss =base64String;
                    AppLog.d("Base64 String Uplode Address Image=============================",base64String.toString());
                }


//                        hideProgress();
//                        mViewModel.onNewInstallation(installation)
//                mViewModel.createRegistration(RegistrationActivity.this, mRegistration);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
           super.onActivityResult(requestCode, resultCode, data);
            if (isImgPickerOpen) {
                mPickerDialog.onActivityResult(requestCode, resultCode, data);
            }

            if (requestCode == PDF_REQ_CODE && resultCode == RESULT_OK && data != null && data.getData() != null) {
            uri = data.getData();
            mEdtPdfFileName.setText(uri.toString());
            }

               getImage();


            }

        private void setTextChangeLister () {
            mTilFirstName.getEditText().addTextChangedListener(new TextChangeListener(mTilFirstName));
            mTilLastName.getEditText().addTextChangedListener(new TextChangeListener(mTilLastName));
            mTilMobile.getEditText().addTextChangedListener(new TextChangeListener(mTilMobile));
            mTilEmail.getEditText().addTextChangedListener(new TextChangeListener(mTilEmail));
            mTilAddress.getEditText().addTextChangedListener(new TextChangeListener(mTilAddress));
        }

        @Override
        public void getBitmapImageFromPhone (File file, Uri fileUri){
           /* mFile = file;

            mBtnPassportUplode.setImageBitmap(mPickerDialog.getBitmapFromFile(file));
            isImgPickerOpen = false;*/

            mFile = file;

            if(checkVar.equalsIgnoreCase("0")) {
                mBtnPassportUplode.setImageBitmap(mPickerDialog.getBitmapFromFile(file));
                isImgPickerOpen = false;
            }
            if(checkVar.equalsIgnoreCase("1")) {
                mBtnInternationalPassportUplode.setImageBitmap(mPickerDialog.getBitmapFromFile(file));
                isImgPickerOpen = false;
            }
            if(checkVar.equalsIgnoreCase("2")) {
                mBtnKyCUplode.setImageBitmap(mPickerDialog.getBitmapFromFile(file));
                isImgPickerOpen = false;
            }
            if(checkVar.equalsIgnoreCase("3")) {
                mBtnAddressUplode.setImageBitmap(mPickerDialog.getBitmapFromFile(file));
                isImgPickerOpen = false;
            }
        }

        private void openImageDialog () {
            isImgPickerOpen = true;
            if (mPickerDialog == null) {
                mPickerDialog = new ImagePickerDialog(this, this);
            }
            mPickerDialog.openImagePickedFromDialog();
        }

        @Override
        public void onPermissionGranted () {
            openImageDialog();
        }

        @Override
        public void onPermissionError (String permission){

        }
    }


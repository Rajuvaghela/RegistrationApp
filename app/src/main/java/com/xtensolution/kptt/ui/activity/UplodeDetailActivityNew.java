package com.xtensolution.kptt.ui.activity;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.media.ExifInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.Parcelable;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.FileProvider;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;
import com.xtensolution.core.utils.AppLog;
import com.xtensolution.kptt.BuildConfig;
import com.xtensolution.kptt.KPTApp;
import com.xtensolution.kptt.R;
import com.xtensolution.kptt.model.ContactDetails;
import com.xtensolution.kptt.model.EmploymentDetails;
import com.xtensolution.kptt.model.PersonalDetails;
import com.xtensolution.kptt.model.Response;
import com.xtensolution.kptt.model.SubmitData;
import com.xtensolution.kptt.retrofit.ApiClient;
import com.xtensolution.kptt.retrofit.ApiInterface;
import com.xtensolution.kptt.ui.viewmodel.SubmitViewModel;
import com.xtensolution.kptt.utils.Constants;
import com.xtensolution.kptt.utils.PermissionUtils;
import com.xtensolution.kptt.utils.PreferenceHelper;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import cz.msebera.android.httpclient.Header;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UplodeDetailActivityNew extends BaseActionBarActivity<List<SubmitData>> implements View.OnClickListener {

    private String TAG = UplodeDetailActivityNew.class.getSimpleName();
    private SubmitViewModel mViewModel;
    SubmitData mSubmitData = new SubmitData();
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


    String checkVar = "0";


    Context activity;
    RequestBody passport_picture, international_passport, proof_address, proof_employment,
            passport_pic_upload, international_pic_upload, proof_address_pic_upload, proof_employment_pic_upload;
    private RequestBody requestFile;

    ProgressDialog dialog;
    private int REQUEST_CAMERA = 0, SELECT_FILE = 1;
    File file1, file2, file3, file4;
    String strCheckCameraClickListner = "0";
    String strCheckFileClickListner = "0";
    private Uri outputFileUri;

    @Override
    public void onResponse(Response<List<SubmitData>> response) {

        hideProgress();

        if (response.getSuccess().equalsIgnoreCase("1")) {
            String df = String.valueOf(response.getSuccess());
            PreferenceHelper.getInstance().delete(Constants.PREF_PERSONALDETAILS);
            PreferenceHelper.getInstance().delete(Constants.PREF_CONTACTDETAILS);
            PreferenceHelper.getInstance().delete(Constants.PREF_EMPLOYMENTDETAILS);
            PreferenceHelper.getInstance().delete(Constants.PREF_UPLOADDETAILS);
            Intent intent = new Intent(UplodeDetailActivityNew.this,
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
    }

    @Override
    protected void onActionBarAttached() {
        activity = this;
        dialog = new ProgressDialog(this);
        dialog.setMessage("Loading..");
        dialog.setCancelable(false);
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.txtPassportPicsCamera:
                if (checkPermission()) {
                    strCheckCameraClickListner = "1";
                    startActivityForResult(captureFile(), 0);
                } else {
                    requestPermission();
                }


                break;

            case R.id.txtIntPassportCameraPic:
                if (checkPermission()) {
                    strCheckCameraClickListner = "2";
                    startActivityForResult(captureFile(), 0);
                } else {
                    requestPermission();
                }

                break;

            case R.id.txtProofOfAddressCameraPic:
                if (checkPermission()) {
                    strCheckCameraClickListner = "3";
                    startActivityForResult(captureFile(), 0);
                } else {
                    requestPermission();
                }

                break;

            case R.id.txtProofOfEmployeementCameraPic:
                if (checkPermission()) {
                    strCheckCameraClickListner = "4";
                    startActivityForResult(captureFile(), 0);
                } else {
                    requestPermission();
                }

                break;


            case R.id.txtPassportPicsUplode:
                if (checkPermission()) {
                    strCheckFileClickListner = "1";
                    selectFile();
                } else {
                    requestPermission();
                }

                break;


            case R.id.txtIntPassportUplodePics:
                if (checkPermission()) {
                    strCheckFileClickListner = "2";
                    selectFile();
                } else {
                    requestPermission();
                }

                break;


            case R.id.txtProofOfAddressUplodePics:
                if (checkPermission()) {
                    strCheckFileClickListner = "3";
                    selectFile();
                } else {
                    requestPermission();
                }


                break;


            case R.id.txtProofOfEmployeementUplodePics:
                if (checkPermission()) {
                    strCheckFileClickListner = "4";
                    selectFile();
                } else {
                    requestPermission();
                }

                break;


            case R.id.btnSubmit:
                validateAndSave();
                break;

            case R.id.txtReadTermsAndCondition:

                final Dialog dialog = new Dialog(UplodeDetailActivityNew.this);
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


    private void validateAndSave() {

        if (file1 == null) {
            showSnackbar(R.string.passport_image_validation, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                }
            });
        } else if (file2 == null) {
            showSnackbar(R.string.international_passport_image_onlyone_doc_, new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
      /*  else if (file3 == null) {
            showSnackbar(R.string.proof_of_address_image_onlyone_doc_, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                }
            });
        } else if (file4 == null) {
            showSnackbar(R.string.proof_of_employeement, new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }*/
        else if (TextUtils.isEmpty(mEdtAmountpaid.getText().toString())) {
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
            PersonalDetails personalDetails = PreferenceHelper.getInstance().getPersonalDetails();
            ContactDetails contactDetails = PreferenceHelper.getInstance().getContactDetails();
            EmploymentDetails employmentDetails = PreferenceHelper.getInstance().getEmploymentDetails();
            uploadFile(personalDetails.getTitle(),
                    personalDetails.getGender(),
                    personalDetails.getSurname(),
                    personalDetails.getMiddlename(),
                    personalDetails.getFirstnme(),
                    personalDetails.getDateofbirth(),
                    personalDetails.getRace(),
                    personalDetails.getIdpassport(),
                    personalDetails.getNationality(),
                    contactDetails.getCellNo(),
                    contactDetails.getConfirmCellNo(),
                    contactDetails.getEmail(),
                    contactDetails.getHouseNumber(),
                    contactDetails.getStreetName(),
                    contactDetails.getSuburbDistrict(),
                    contactDetails.getCityTown(),
                    contactDetails.getProvince(),
                    contactDetails.getPostalCode(),
                    employmentDetails.getOccupation(),
                    employmentDetails.getAreyou(),
                    employmentDetails.getEmployersName(),
                    employmentDetails.getEmployersAddress(),
                    employmentDetails.getPostalCode(),
                    employmentDetails.getEmployersTel(),
                    mEdtAmountpaid.getText().toString(),
                    mEdtAgentNo.getText().toString(),
                    mEdtVisaCardNumber.getText().toString());
        }

    }

    public void uploadFile(String title, String gender,
                           String surname, String middlename,
                           String firstnme, String dateofbirth,
                           String race, String idpassport,
                           String nationality, String cellNo,
                           String confirmCellNo, String email,
                           String houseNumber, String streetName,
                           String suburbDistrict, String cityTown,
                           String province, String postalCode,
                           String occupation, String areyou,
                           String employersName, String employersAddress,
                           String code, String employersTel,
                           String etAmountpaid, String etAgentNo,
                           String etVisaCardNumber) {
        dialog = new ProgressDialog(this);
        dialog.setMessage("Loading..");
        dialog.setCancelable(false);
        dialog.show();
        if (isNetworkAvailable()) {
            AsyncHttpClient client = new AsyncHttpClient();
            client.setTimeout(800000);
            RequestParams params = new RequestParams();
            params.put("agentnumber", etAgentNo);
            params.put("alternativenumber", confirmCellNo);
            params.put("amountpaid", etAmountpaid);
            params.put("are_you", areyou);
            params.put("cellnumber", cellNo);
            params.put("dob", dateofbirth);
            params.put("city_town", cityTown);
            params.put("email", email);
            params.put("employer_add", employersAddress);
            params.put("employer_name", employersName);
            params.put("employer_phone", employersTel);
            params.put("first_name", firstnme);
            params.put("gender", gender);
            params.put("housenumber", houseNumber);
            params.put("id_password", idpassport);
            params.put("international_passport", international_passport);
            params.put("middle_name", middlename);
            params.put("nationality", nationality);
            params.put("occupation", occupation);
            params.put("postalcode", postalCode);
            params.put("province_state", province);
            params.put("race", race);
            params.put("streetname", streetName);
            params.put("surbubdistrict", suburbDistrict);
            params.put("surname", surname);
            params.put("title", title);
            params.put("visa_card_number", etVisaCardNumber);
            params.put("employer_postalcode", code);
            try {
                params.put("passport_pic_upload", file1);
                params.put("international_pic_upload", file2);
                params.put("proof_address_pic_upload", file3);
                params.put("proof_employment_pic_upload", file4);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            client.post("http://register.hillcrossfinance.co.za/index.php?option=com_kycregistration&task=webservicepost&tmpl=component&format=json", params, new TextHttpResponseHandler() {
                @Override
                public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                    Log.e("error", "" + responseString);
                    dialog.dismiss();
                }

                @Override
                public void onSuccess(int statusCode, Header[] headers, String responseString) {
                    Log.e("res", responseString);
                    try {
                        JSONObject object = new JSONObject(responseString.toString());
                        String str = object.getString("success");
                        if (str.equalsIgnoreCase("1")) {
                           /* Log.e("success", "++++++++++++++++++++++==");
                            startActivity(new Intent(activity, ThankYouActivity.class));*/

                            PreferenceHelper.getInstance().delete(Constants.PREF_PERSONALDETAILS);
                            PreferenceHelper.getInstance().delete(Constants.PREF_CONTACTDETAILS);
                            PreferenceHelper.getInstance().delete(Constants.PREF_EMPLOYMENTDETAILS);
                            PreferenceHelper.getInstance().delete(Constants.PREF_UPLOADDETAILS);
                            Intent intent = new Intent(UplodeDetailActivityNew.this,
                                    ThankYouActivity.class);

                            intent.addCategory(Intent.CATEGORY_HOME);
                            intent.addCategory(Intent.CATEGORY_LAUNCHER);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                                    | Intent.FLAG_ACTIVITY_NEW_TASK
                                    | Intent.FLAG_ACTIVITY_CLEAR_TASK
                                    | Intent.FLAG_ACTIVITY_NO_HISTORY);
                            startActivity(intent);

                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    dialog.dismiss();
                }
            });
        } else {
            dialog.dismiss();
            Toast.makeText(activity, "No Internet connection", Toast.LENGTH_SHORT).show();
        }
    }


    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == SELECT_FILE)
                onSelectFromFileResult(data);
            else if (requestCode == REQUEST_CAMERA)
                onCaptureImageResult();

        }

    }

    private Uri getCaptureImageOutputUri() {
        outputFileUri = null;
        File getImage = getExternalCacheDir();
        if (getImage != null) {

            File destination = new File(Environment.getExternalStorageDirectory() + "/RegistrationApp/UserProfile");

            if (!destination.exists()) {
                File wallpaperDirectory = new File("/sdcard/RegistrationApp/UserProfile");
                wallpaperDirectory.mkdirs();
            }

            String imagename = System.currentTimeMillis() + ".jpg";

            //  outputFileUri = Uri.fromFile(new File(Environment.getExternalStorageDirectory() + "/RegistrationApp/UserProfile", imagename));
            outputFileUri = FileProvider.getUriForFile(activity, BuildConfig.APPLICATION_ID + ".provider", new File(Environment.getExternalStorageDirectory() + "/RegistrationApp/UserProfile", imagename));
        }
        Log.e("uri", "" + outputFileUri.toString());
        return outputFileUri;
    }


    private void onCaptureImageResult() {

        //  String selectedPath = getPath(activity, outputFileUri.getPath());
        Log.e("Camera file", "" + outputFileUri.getPath().toString());
        Log.e("compressImage", "" + compressImage(outputFileUri.getPath()));
        if (strCheckCameraClickListner.equalsIgnoreCase("1")) {
            file1 = new File(compressImage(outputFileUri.getPath()));
        } else if (strCheckCameraClickListner.equalsIgnoreCase("2")) {
            file2 = new File(compressImage(outputFileUri.getPath()));
        } else if (strCheckCameraClickListner.equalsIgnoreCase("3")) {
            file3 = new File(compressImage(outputFileUri.getPath()));
        } else if (strCheckCameraClickListner.equalsIgnoreCase("4")) {
            file4 = new File(compressImage(outputFileUri.getPath()));
        }
        cameraValidation();

    }

    private void onSelectFromFileResult(Intent data) {
        Uri selectedImageUri = data.getData();
        Log.e("gallary path", "" + selectedImageUri.toString());
        String selectedPath = getPath(activity, selectedImageUri);
        String extension = selectedPath.substring(selectedPath.lastIndexOf("."));

        if (selectedPath != null) {
            if (strCheckFileClickListner.equalsIgnoreCase("1")) {
                /**
                 * if >jpg image than compress image
                 */
                if (extension.equalsIgnoreCase("jpg")) {
                    file1 = new File(compressImage(selectedPath));
                } else {
                    file1 = new File(selectedPath);
                }

            } else if (strCheckFileClickListner.equalsIgnoreCase("2")) {
                /**
                 * if >jpg image than compress image
                 */
                if (extension.equalsIgnoreCase("jpg")) {
                    file2 = new File(compressImage(selectedPath));
                } else {
                    file2 = new File(selectedPath);
                }

            } else if (strCheckFileClickListner.equalsIgnoreCase("3")) {
                /**
                 * if >jpg image than compress image
                 */
                if (extension.equalsIgnoreCase("jpg")) {
                    file3 = new File(compressImage(selectedPath));
                } else {
                    file3 = new File(selectedPath);
                }

            } else if (strCheckFileClickListner.equalsIgnoreCase("4")) {
                /**
                 * if >jpg image than compress image
                 */
                if (extension.equalsIgnoreCase("jpg")) {
                    file4 = new File(compressImage(selectedPath));
                } else {
                    file4 = new File(selectedPath);
                }

            }
            uploadevalidation();
        } else {
            Toast.makeText(activity, "file not selected", Toast.LENGTH_LONG).show();
        }
        uploadevalidation();

    }


    public static String getPath(final Context context, final Uri uri) {

        final boolean isKitKat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;

        // DocumentProvider
        if (isKitKat && DocumentsContract.isDocumentUri(context, uri)) {
            // ExternalStorageProvider
            if (isExternalStorageDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                if ("primary".equalsIgnoreCase(type)) {
                    return Environment.getExternalStorageDirectory() + "/" + split[1];
                }

                // TODO handle non-primary volumes
            }
            // DownloadsProvider
            else if (isDownloadsDocument(uri)) {

                final String id = DocumentsContract.getDocumentId(uri);
                final Uri contentUri = ContentUris.withAppendedId(
                        Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));

                return getDataColumn(context, contentUri, null, null);
            }
            // MediaProvider
            else if (isMediaDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                Uri contentUri = null;
                if ("image".equals(type)) {
                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(type)) {
                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(type)) {
                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }

                final String selection = "_id=?";
                final String[] selectionArgs = new String[]{
                        split[1]
                };

                return getDataColumn(context, contentUri, selection, selectionArgs);
            }
        }
        // MediaStore (and general)
        else if ("content".equalsIgnoreCase(uri.getScheme())) {

            // Return the remote address
            if (isGooglePhotosUri(uri))
                return uri.getLastPathSegment();

            return getDataColumn(context, uri, null, null);
        }
        // File
        else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }

        return null;
    }

    public static String getDataColumn(Context context, Uri uri, String selection,
                                       String[] selectionArgs) {

        Cursor cursor = null;
        final String column = "_data";
        final String[] projection = {
                column
        };

        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs,
                    null);
            if (cursor != null && cursor.moveToFirst()) {
                final int index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(index);
            }
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return null;
    }


    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is ExternalStorageProvider.
     */
    public static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is DownloadsProvider.
     */
    public static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is MediaProvider.
     */
    public static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is Google Photos.
     */
    public static boolean isGooglePhotosUri(Uri uri) {
        return "com.google.android.apps.photos.content".equals(uri.getAuthority());
    }


    private void selectFile() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");
        startActivityForResult(intent, SELECT_FILE);
    }

    public Intent captureFile() {

        // Determine Uri of camera image to save.
        Uri outputFileUri = getCaptureImageOutputUri();

        List<Intent> allIntents = new ArrayList<>();
        PackageManager packageManager = getPackageManager();

        // collect all camera intents
        Intent captureIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        List<ResolveInfo> listCam = packageManager.queryIntentActivities(captureIntent, 0);
        for (ResolveInfo res : listCam) {
            Intent intent = new Intent(captureIntent);
            intent.setComponent(new ComponentName(res.activityInfo.packageName, res.activityInfo.name));
            intent.setPackage(res.activityInfo.packageName);
            if (outputFileUri != null) {
                intent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);
            }
            allIntents.add(intent);
        }

 /*       // collect all gallery intents
        Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
        galleryIntent.setType("image/*");
        List<ResolveInfo> listGallery = packageManager.queryIntentActivities(galleryIntent, 0);
        for (ResolveInfo res : listGallery) {
            Intent intent = new Intent(galleryIntent);
            intent.setComponent(new ComponentName(res.activityInfo.packageName, res.activityInfo.name));
            intent.setPackage(res.activityInfo.packageName);
            allIntents.add(intent);
        }*/

        // the main intent is the last in the list (fucking android) so pickup the useless one
        Intent mainIntent = allIntents.get(allIntents.size() - 1);
        for (Intent intent : allIntents) {
            if (intent.getComponent().getClassName().equals("com.android.documentsui.DocumentsActivity")) {
                mainIntent = intent;
                break;
            }
        }
        allIntents.remove(mainIntent);

        // Create a chooser from the main intent
        Intent chooserIntent = Intent.createChooser(mainIntent, "Select source");

        // Add all other intents
        chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, allIntents.toArray(new Parcelable[allIntents.size()]));

        return chooserIntent;
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

    //suresh add
    private void uploadevalidation() {
        if (strCheckFileClickListner.equalsIgnoreCase("1")) {
            mTxtPassportCamera.setText("");
            mTxtPassportUpload.setText("/storag");
        }

        if (strCheckFileClickListner.equalsIgnoreCase("2")) {
            mTxtInternationalPassportCamera.setText("");
            mTxtInternantionalPassportUpload.setText("/storag");
        }

        if (strCheckFileClickListner.equalsIgnoreCase("3")) {
            mTxtProofOfAddressCamera.setText("");
            mTxtProofOfAddressUpload.setText("/storag");
        }

        if (strCheckFileClickListner.equalsIgnoreCase("4")) {
            mTxtProofOfEmploymentCamera.setText("");
            mTxtProofOfEmploymentupload.setText("/storag");
        }
    }


    private void cameraValidation() {
        if (strCheckCameraClickListner.equalsIgnoreCase("1")) {
            mTxtPassportUpload.setText("");
            mTxtPassportCamera.setText("/storag");


        }
        if (strCheckCameraClickListner.equalsIgnoreCase("2")) {
            mTxtInternantionalPassportUpload.setText("");
            mTxtInternationalPassportCamera.setText("/storag");

        }
        if (strCheckCameraClickListner.equalsIgnoreCase("3")) {
            mTxtProofOfAddressUpload.setText("");
            mTxtProofOfAddressCamera.setText("/storag");

        }
        if (strCheckCameraClickListner.equalsIgnoreCase("4")) {
            mTxtProofOfEmploymentupload.setText("");
            mTxtProofOfEmploymentCamera.setText("/storag");

        }
    }

    //
    @Override
    protected String getScreenTitle() {
        return null;
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_uplode_details;
    }


    private void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }


    private void requestPermission() {
        ActivityCompat.requestPermissions(UplodeDetailActivityNew.this, new
                String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA}, 1);
    }

    public boolean checkPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int result = ContextCompat.checkSelfPermission(UplodeDetailActivityNew.this,
                    android.Manifest.permission.READ_EXTERNAL_STORAGE);
            return result == PackageManager.PERMISSION_GRANTED;
        } else {
            return true;
        }
    }

    public String compressImage(String imageUri) {

        String filePath = getRealPathFromURI(imageUri);
        Bitmap scaledBitmap = null;

        BitmapFactory.Options options = new BitmapFactory.Options();

//      by setting this field as true, the actual bitmap pixels are not loaded in the memory. Just the bounds are loaded. If
//      you try the use the bitmap here, you will get null.
        options.inJustDecodeBounds = true;
        Bitmap bmp = BitmapFactory.decodeFile(filePath, options);

        int actualHeight = options.outHeight;
        int actualWidth = options.outWidth;

//      max Height and width values of the compressed image is taken as 816x612
        float maxHeight = 816.0f;
        float maxWidth = 612.0f;
        float imgRatio = actualWidth / actualHeight;
        float maxRatio = maxWidth / maxHeight;

//      width and height values are set maintaining the aspect ratio of the image
        if (actualHeight > maxHeight || actualWidth > maxWidth) {
            if (imgRatio < maxRatio) {
                imgRatio = maxHeight / actualHeight;
                actualWidth = (int) (imgRatio * actualWidth);
                actualHeight = (int) maxHeight;
            } else if (imgRatio > maxRatio) {
                imgRatio = maxWidth / actualWidth;
                actualHeight = (int) (imgRatio * actualHeight);
                actualWidth = (int) maxWidth;
            } else {
                actualHeight = (int) maxHeight;
                actualWidth = (int) maxWidth;
            }
        }

//      setting inSampleSize value allows to load a scaled down version of the original image
        options.inSampleSize = calculateInSampleSize(options, actualWidth, actualHeight);

//      inJustDecodeBounds set to false to load the actual bitmap
        options.inJustDecodeBounds = false;

//      this options allow android to claim the bitmap memory if it runs low on memory
        options.inPurgeable = true;
        options.inInputShareable = true;
        options.inTempStorage = new byte[16 * 1024];

        try {
//          load the bitmap from its path
            bmp = BitmapFactory.decodeFile(filePath, options);
        } catch (OutOfMemoryError exception) {
            exception.printStackTrace();

        }
        try {
            scaledBitmap = Bitmap.createBitmap(actualWidth, actualHeight, Bitmap.Config.ARGB_8888);
        } catch (OutOfMemoryError exception) {
            exception.printStackTrace();
        }

        float ratioX = actualWidth / (float) options.outWidth;
        float ratioY = actualHeight / (float) options.outHeight;
        float middleX = actualWidth / 2.0f;
        float middleY = actualHeight / 2.0f;

        Matrix scaleMatrix = new Matrix();
        scaleMatrix.setScale(ratioX, ratioY, middleX, middleY);

        Canvas canvas = new Canvas(scaledBitmap);
        canvas.setMatrix(scaleMatrix);
        canvas.drawBitmap(bmp, middleX - bmp.getWidth() / 2, middleY - bmp.getHeight() / 2, new Paint(Paint.FILTER_BITMAP_FLAG));

//      check the rotation of the image and display it properly
        ExifInterface exif;
        try {
            exif = new ExifInterface(filePath);

            int orientation = exif.getAttributeInt(
                    ExifInterface.TAG_ORIENTATION, 0);
            Log.d("EXIF", "Exif: " + orientation);
            Matrix matrix = new Matrix();
            if (orientation == 6) {
                matrix.postRotate(90);
                Log.d("EXIF", "Exif: " + orientation);
            } else if (orientation == 3) {
                matrix.postRotate(180);
                Log.d("EXIF", "Exif: " + orientation);
            } else if (orientation == 8) {
                matrix.postRotate(270);
                Log.d("EXIF", "Exif: " + orientation);
            }
            scaledBitmap = Bitmap.createBitmap(scaledBitmap, 0, 0,
                    scaledBitmap.getWidth(), scaledBitmap.getHeight(), matrix,
                    true);
        } catch (IOException e) {
            e.printStackTrace();
        }

        FileOutputStream out = null;
        String filename = getFilename();
        try {
            out = new FileOutputStream(filename);

//          write the compressed bitmap at the destination specified by filename.
            scaledBitmap.compress(Bitmap.CompressFormat.JPEG, 80, out);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return filename;
    }

    public String getFilename() {
        File file = new File(Environment.getExternalStorageDirectory().getPath(), "/RegistrationApp/UserProfile");
        if (!file.exists()) {
            file.mkdirs();
        }
        String uriSting = (file.getAbsolutePath() + "/temp" + System.currentTimeMillis() + ".jpg");
        return uriSting;
    }

    private String getRealPathFromURI(String contentURI) {
        Uri contentUri = Uri.parse(contentURI);
        Cursor cursor = getContentResolver().query(contentUri, null, null, null, null);
        if (cursor == null) {
            return contentUri.getPath();
        } else {
            cursor.moveToFirst();
            int index = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            return cursor.getString(index);
        }
    }

    public int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {
            final int heightRatio = Math.round((float) height / (float) reqHeight);
            final int widthRatio = Math.round((float) width / (float) reqWidth);
            inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
        }
        final float totalPixels = width * height;
        final float totalReqPixelsCap = reqWidth * reqHeight * 2;
        while (totalPixels / (inSampleSize * inSampleSize) > totalReqPixelsCap) {
            inSampleSize++;
        }

        return inSampleSize;
    }
}

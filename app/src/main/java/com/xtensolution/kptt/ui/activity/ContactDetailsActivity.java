package com.xtensolution.kptt.ui.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.riontech.retrofit.errorhandler.APIError;
import com.xtensolution.core.utils.AppLog;
import com.xtensolution.kptt.R;
import com.xtensolution.kptt.model.ContactDetails;
import com.xtensolution.kptt.model.EmploymentDetails;
import com.xtensolution.kptt.model.Registration;
import com.xtensolution.kptt.model.Response;
import com.xtensolution.kptt.rest.GeneralRestCall;
import com.xtensolution.kptt.ui.viewmodel.RegistrationViewModel;
import com.xtensolution.kptt.ui.viewmodel.SubmitViewModel;
import com.xtensolution.kptt.utils.PreferenceHelper;

import java.util.List;

public class ContactDetailsActivity extends BaseActionBarActivity<List<ContactDetails>> implements View.OnClickListener {
    private String TAG = PersonalDetailsActivity.class.getSimpleName();
    ContactDetails mContactDetails = new ContactDetails();
    private Button mBtnNext;
    private Button mBtnPrevious;
    private EditText mEdtCellNo;
    private EditText mEdtConfirmCellNo;
    private EditText mEdtEmail;
    private EditText mEdtHouseNumber;
    private EditText mEdtStreetName;
    private EditText mEdtSuburbDistrict;
    private EditText mEdtCityTown;
    private EditText mEdtProvince;
    private EditText mEdtPostalCode;
    private SubmitViewModel mViewModel;
    private String confirmnumber;

    @Override


    public void onResponse(Response<List<ContactDetails>> response) {

    }

    @Override
    public void noDataFound(Response<List<ContactDetails>> response) {

    }

    @Override
    protected void onActionBarAttached() {
        mViewModel = new SubmitViewModel();
        mBtnNext = _findViewById(R.id.btnNext);
        mBtnPrevious = _findViewById(R.id.btnPrevious);

        mEdtCellNo = _findViewById(R.id.edtCellNo);
        mEdtConfirmCellNo = _findViewById(R.id.edtConfirmCellNo);
        mEdtEmail = _findViewById(R.id.edtEmail);
        mEdtHouseNumber = _findViewById(R.id.edtHouseNo);
        mEdtStreetName = _findViewById(R.id.edtStreetName);
        mEdtSuburbDistrict = _findViewById(R.id.edtSuburbDistrict);
        mEdtCityTown = _findViewById(R.id.edtCityTown);
        mEdtProvince = _findViewById(R.id.edtProvinceState);
        mEdtPostalCode = _findViewById(R.id.edtPostalCode);

        mBtnNext.setOnClickListener(this);
        mBtnPrevious.setOnClickListener(this);


        if(PreferenceHelper.getInstance().getContactDetails() !=null){
            mEdtCellNo.setText(PreferenceHelper.getInstance().getContactDetails().getCellNo());
            mEdtConfirmCellNo.setText(PreferenceHelper.getInstance().getContactDetails().getConfirmCellNo());
            mEdtEmail.setText(PreferenceHelper.getInstance().getContactDetails().getEmail());
            mEdtHouseNumber.setText(PreferenceHelper.getInstance().getContactDetails().getHouseNumber());
            mEdtStreetName.setText(PreferenceHelper.getInstance().getContactDetails().getStreetName());
            mEdtSuburbDistrict.setText(PreferenceHelper.getInstance().getContactDetails().getSuburbDistrict());
            mEdtCityTown.setText(PreferenceHelper.getInstance().getContactDetails().getCityTown());
            mEdtProvince.setText(PreferenceHelper.getInstance().getContactDetails().getProvince());
            mEdtPostalCode.setText(PreferenceHelper.getInstance().getContactDetails().getPostalCode());
            }


    }


    private void validateAndSave() {

        String cellno = mEdtCellNo.getText().toString();
        String confirmCellno = mEdtConfirmCellNo.getText().toString();

        if (TextUtils.isEmpty(mEdtCellNo.getText().toString())) {
            showError(mEdtCellNo);
        }
        else if (TextUtils.isEmpty(mEdtConfirmCellNo.getText().toString())) {
            showError(mEdtConfirmCellNo);
        }

        else if (!cellno.equalsIgnoreCase(confirmCellno)) {
            showErrorr(mEdtConfirmCellNo);
        }
        else if (TextUtils.isEmpty(mEdtHouseNumber.getText().toString())) {
            showError(mEdtHouseNumber);


        }
        else if (TextUtils.isEmpty(mEdtStreetName.getText().toString())) {
            showError(mEdtStreetName);
        }
        else if (TextUtils.isEmpty(mEdtCityTown.getText().toString())) {
            showError(mEdtCityTown);
        }
        else if (TextUtils.isEmpty(mEdtProvince.getText().toString())) {
            showError(mEdtProvince);
        }
        else {
                         mContactDetails.setCellNo(mEdtCellNo.getText().toString());
                mContactDetails.setConfirmCellNo(mEdtConfirmCellNo.getText().toString());
                mContactDetails.setEmail(mEdtEmail.getText().toString());
                mContactDetails.setHouseNumber(mEdtHouseNumber.getText().toString());
                mContactDetails.setStreetName(mEdtStreetName.getText().toString());
                mContactDetails.setSuburbDistrict(mEdtSuburbDistrict.getText().toString());
                mContactDetails.setCityTown(mEdtCityTown.getText().toString());
                mContactDetails.setProvince(mEdtProvince.getText().toString());
                mContactDetails.setPostalCode(mEdtPostalCode.getText().toString());

                PreferenceHelper.getInstance().saveContactDetails(mContactDetails);
                confirmnumber = PreferenceHelper.getInstance().getContactDetails().getConfirmCellNo();
                AppLog.d("Prefrence Stored CellNo=====================***********", confirmnumber);

                String dd = PreferenceHelper.getInstance().getPersonalDetails().getFirstnme();
                AppLog.d("Prefrence Stored person FirstName=====================***********", dd);
                showProgress();
                mViewModel.getCellNo(new GeneralRestCall<List<ContactDetails>>() {


                    @Override
                    public void onResponse(Response<List<ContactDetails>> response) {
                        hideProgress();


                         if (response.getSuccess().equalsIgnoreCase("1")) {
                             String df = String.valueOf(response.getSuccess());
                             AppLog.d("is success=======================",df);
                             Intent ii = new Intent(ContactDetailsActivity.this, EmploymentDetailsActivity.class);
//                           startActivity(ii);
                             startActivityForResult(ii, 2);

                          }else {
                             showSnackbar(R.string.cellno_allready_exit, new View.OnClickListener() {
                                 @Override
                                 public void onClick(View v) {
                                 }
                              });
                         }

                    }

                    //

                    @Override
                    public void onError(APIError error) {
                        super.onError(error);
                        hideProgress();
                        generalSnackBar();
                    }

                    @Override
                    public void onTimeout() {
                        super.onTimeout();
                        hideProgress();
                        generalSnackBar();
                    }

                    @Override
                    public void onConnectionError() {
                        super.onConnectionError();
                        hideProgress();
                        generalSnackBar();
                    }


                    //

                },confirmnumber);

            }
        }

    private void generalSnackBar() {
        showSnackbar(R.string.no_internate, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }

    private void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    private void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }





    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

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

    private void showErrorr(EditText editText) {
        try {
            editText.setText("");
            editText.requestFocus();
            editText.setError("Phone Number doesn't match.");
        } catch (Exception e) {
            AppLog.e(TAG, e.getMessage(), e);
        }
    }

    @Override
    protected String getScreenTitle() {
        return null;
    }

    @Override
    protected int getLayout() {
       return R.layout.activity_contact_details;
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnNext:
                validateAndSave();
               /* Intent btnNext = new Intent(ContactDetailsActivity.this,
                        EmploymentDetailsActivity.class);
                startActivity(btnNext);*/
                break;

            case R.id.btnPrevious:
                Intent btnPrevious = new Intent(ContactDetailsActivity.this,
                        PersonalDetailsActivity.class);
//                startActivity(btnPrevious);

                setResult(1, btnPrevious);
                finish();


                break;


        }
    }
}

package com.xtensolution.kptt.ui.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.xtensolution.core.utils.AppLog;
import com.xtensolution.kptt.R;
import com.xtensolution.kptt.model.EmploymentDetails;
import com.xtensolution.kptt.model.Registration;
import com.xtensolution.kptt.model.Response;
import com.xtensolution.kptt.utils.PreferenceHelper;

import java.util.List;

public class EmploymentDetailsActivity extends BaseActionBarActivity<List<Registration>> implements View.OnClickListener {
    private String TAG = EmploymentDetailsActivity.class.getSimpleName();
    EmploymentDetails mEmploymentDetails = new EmploymentDetails();
    private Button mBtnNext,mBtnPrevious;
    private Spinner mSpnAreYou;
    private EditText mEdtOccupation;
    private EditText mEdtEmployersName;
    private EditText mEdtEmployersAddress;
    private EditText mEdtPostalCode;
    private EditText mEdtEmployersTel;


    String areyou;
    @Override
    public void onResponse(Response<List<Registration>> response) {

    }

    @Override
    public void noDataFound(Response<List<Registration>> response) {

    }

    @Override
    protected void onActionBarAttached() {
        mBtnNext = _findViewById(R.id.btnEmployersNext);
        mBtnPrevious = _findViewById(R.id.btnEmployersPrevious);
        mSpnAreYou = _findViewById(R.id.spnAreYou);
        mEdtOccupation = _findViewById(R.id.edtOccupation);
        mEdtEmployersName = _findViewById(R.id.edtEmployersName);
        mEdtEmployersAddress = _findViewById(R.id.edtEmployersAddress);
        mEdtPostalCode = _findViewById(R.id.edtEmployersPostalCode);
        mEdtEmployersTel = _findViewById(R.id.edtEmployersTel);

        mBtnNext.setOnClickListener(this);
        mBtnPrevious.setOnClickListener(this);

        if(PreferenceHelper.getInstance().getEmploymentDetails() !=null){
            mEdtOccupation.setText(PreferenceHelper.getInstance().getEmploymentDetails().getOccupation());
            mSpnAreYou.setSelection(Integer.parseInt(PreferenceHelper.getInstance().getEmploymentDetails().getAreyouid()));
            mEdtEmployersName.setText(PreferenceHelper.getInstance().getEmploymentDetails().getEmployersName());
            mEdtEmployersAddress.setText(PreferenceHelper.getInstance().getEmploymentDetails().getEmployersAddress());
            mEdtPostalCode.setText(PreferenceHelper.getInstance().getEmploymentDetails().getPostalCode());
            mEdtEmployersTel.setText(PreferenceHelper.getInstance().getEmploymentDetails().getEmployersTel());


        }

    }

    private void validateAndSave() {

        areyou = String.valueOf(mSpnAreYou.getSelectedItemPosition());

        if(areyou == "0") {
            showSnackbar(R.string.areyou_validation, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                }
            });
        }
        else {


             mEmploymentDetails.setOccupation(mEdtOccupation.getText().toString());
             mEmploymentDetails.setAreyou(mSpnAreYou.getSelectedItem().toString());
             mEmploymentDetails.setEmployersName(mEdtEmployersName.getText().toString());
             mEmploymentDetails.setEmployersAddress(mEdtEmployersAddress.getText().toString());
             mEmploymentDetails.setPostalCode(mEdtPostalCode.getText().toString());
             mEmploymentDetails.setEmployersTel(mEdtEmployersTel.getText().toString());
             mEmploymentDetails.setAreyouid(String.valueOf(mSpnAreYou.getSelectedItemPosition()));

            Intent iii = new Intent(EmploymentDetailsActivity.this,UplodeDetailActivityNew.class);
//            startActivity(iii);
            startActivityForResult(iii, 3);


            PreferenceHelper.getInstance().saveEmploymentDetails(mEmploymentDetails);
            String sureshDate =PreferenceHelper.getInstance().getEmploymentDetails().getEmployersName();
            String areyouname =PreferenceHelper.getInstance().getEmploymentDetails().getAreyou();
            AppLog.d("Prefrence Stored Employeename=====================***********",sureshDate);
            AppLog.d("Prefrence Stored Employeename areyou=====================***********",sureshDate);
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnEmployersNext:
                validateAndSave();
                /*Intent btnNext = new Intent(EmploymentDetailsActivity.this,
                        UplodeDetailsActivity.class);
                startActivity(btnNext);*/
                break;

            case R.id.btnEmployersPrevious:
                Intent btnPrevious = new Intent(EmploymentDetailsActivity.this,
                        ContactDetailsActivity.class);
//                startActivity(btnPrevious);
                setResult(2, btnPrevious);
                finish();

                break;


        }
    }

    @Override
    protected String getScreenTitle() {
        return null;
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_employment_details;
    }
}

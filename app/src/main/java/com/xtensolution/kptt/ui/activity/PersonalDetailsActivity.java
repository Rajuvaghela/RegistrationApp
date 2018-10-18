package com.xtensolution.kptt.ui.activity;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;

import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;


import com.xtensolution.core.utils.AppLog;
import com.xtensolution.core.utils.Toaster;
import com.xtensolution.kptt.R;
import com.xtensolution.kptt.model.PersonalDetails;
import com.xtensolution.kptt.model.Registration;
import com.xtensolution.kptt.model.Registrationss;
import com.xtensolution.kptt.model.Response;
import com.xtensolution.kptt.utils.DateTimePickerDialog;
import com.xtensolution.kptt.utils.DateTimeUtils;
import com.xtensolution.kptt.utils.PreferenceHelper;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class PersonalDetailsActivity extends BaseActionBarActivity<List<Registrationss>> implements View.OnClickListener {

    private String TAG = PersonalDetailsActivity.class.getSimpleName();
    private Registrationss mRegistrationss = new Registrationss();
    private PersonalDetails mPersonalDetails = new PersonalDetails();
   private Spinner mSpnTitle, mSpnGender,mSpnNationality;
   private Toolbar mToolbar;
   private ActionBar mActioonBar;
   private Button  mBtnNextPage;
   private EditText mEdtSurname;
   private EditText mEdtMiddleName;
   private EditText mEdtFirstName;
   private EditText mEdtDateOfBirth;
   private EditText mEdtIdPassportNo;
   private RadioGroup mRadGrpRace;
   private RadioButton mRadbtnRace;


   private  String title = null;
   private  String gender = null;
   private  String nationality = null;
   private  String surname = null;
   private  String middlename = null;
   private  String firstname = null;
   private  String dateOfBirth = null;
   private  String dbformateDate = null;
   private  String race = null;

    private DateTimePickerDialog mDialog;

    @Override
    protected void onActionBarAttached() {
        mToolbar = _findViewById(R.id.toolbar);


        mSpnTitle = _findViewById(R.id.spnTitle);
        mSpnGender = _findViewById(R.id.spnGenders);
        mSpnNationality = _findViewById(R.id.spnNationality);

        mBtnNextPage = _findViewById(R.id.btnNextPage);
        mBtnNextPage.setOnClickListener(this);

        mEdtSurname = _findViewById(R.id.edtSurname);
        mEdtMiddleName = _findViewById(R.id.edtMiddleName);
        mEdtFirstName = _findViewById(R.id.edtFirstName);
        mEdtDateOfBirth = _findViewById(R.id.edtDateOfBirth);
        mEdtIdPassportNo = _findViewById(R.id.edtIdPassportNo);

        mRadGrpRace = _findViewById(R.id.rg_race);
        mDialog = new DateTimePickerDialog(this);
          onDateOfBirthclick();

        }


    public void onDateOfBirthclick() {
        //start date
        mEdtDateOfBirth.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    startDate();
                    return true;
                }
                return false;
            }
        });
    }

    public void startDate() {
        mDialog.showDatePickerDialog(new DateTimePickerDialog.DateTimePickerListener() {
            @Override
            public void getDataTime(String datetime) {
                try {
                    Date date = null;
                    date = Calendar.getInstance().getTime();

                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                    String currentDate = df.format(date);
                    Date pickerDate = DateTimeUtils.getPickerDateFormat().parse(datetime);
                    String selectedDate = df.format(pickerDate);

                    if (selectedDate.compareTo(currentDate) > 0) {
                        Toaster.longToast("please select valid date");

                    } else if (selectedDate.compareTo(currentDate) < 0) {
                        mEdtDateOfBirth.setText(DateTimeUtils.getPickerDateFormat().format(pickerDate));

                    } else if (selectedDate.compareTo(currentDate) == 0) {
                        mEdtDateOfBirth.setText(DateTimeUtils.getPickerDateFormat().format(pickerDate));
                    }


                } catch (ParseException e) {
                    com.xtensolution.core.utils.AppLog.e(TAG, e.getMessage(), e);
                }


            }
        });

    }


    private void validateAndSave() {

        title = String.valueOf(mSpnTitle.getSelectedItemPosition());
        gender = String.valueOf(mSpnGender.getSelectedItemPosition());
        nationality = String.valueOf(mSpnNationality.getSelectedItemPosition());

        if(title == "0"){
           showSnackbar(R.string.title_validation, new View.OnClickListener() {
               @Override
               public void onClick(View v) {
               }
           });
       }else if(gender == "0"){
           showSnackbar(R.string.gender_validation, new View.OnClickListener() {
               @Override
               public void onClick(View v) {
               }
           });
       }else if(TextUtils.isEmpty(mEdtSurname.getText().toString())){
           showError(mEdtSurname);

       }else if(TextUtils.isEmpty(mEdtFirstName.getText().toString())){
            showError(mEdtFirstName);

       }else if(TextUtils.isEmpty(mEdtDateOfBirth.getText().toString())) {
           showError(mEdtDateOfBirth);
       }else if(mRadGrpRace.getCheckedRadioButtonId() == -1) {
           showSnackbar(R.string.race_validation, new View.OnClickListener() {
               @Override
               public void onClick(View v) {
               }
           });

       }else if(TextUtils.isEmpty(mEdtIdPassportNo.getText().toString())){
           showError(mEdtIdPassportNo);

       }else if(nationality.equalsIgnoreCase("0")){
           showSnackbar(R.string.nationality_validation, new View.OnClickListener() {
               @Override
               public void onClick(View v) {
               }
           });

       }else{

           mPersonalDetails.setTitle(mSpnTitle.getSelectedItem().toString());
           mPersonalDetails.setGender(mSpnGender.getSelectedItem().toString());
           mPersonalDetails.setSurname(mEdtSurname.getText().toString());
           mPersonalDetails.setMiddlename(mEdtMiddleName.getText().toString());
           mPersonalDetails.setFirstnme(mEdtFirstName.getText().toString());

           Date date = null;
           try {
                String ss =mEdtDateOfBirth.getText().toString();
                date = DateTimeUtils.getPickerDateFormat().parse(mEdtDateOfBirth.getText().toString());
                dbformateDate = DateTimeUtils.getDBFormat().format(date);
                AppLog.d("","db formate final-----------------------"+dbformateDate);
           } catch (ParseException e) {
               com.xtensolution.core.utils.AppLog.e(TAG, e.getMessage(), e);
           }

           mPersonalDetails.setDateofbirth(dbformateDate);
           mRadbtnRace  = (RadioButton)findViewById(mRadGrpRace.getCheckedRadioButtonId());
           mPersonalDetails.setRace(mRadbtnRace.getText().toString());
           AppLog.d("race selected value=====================",mRadbtnRace.getText().toString());

           mPersonalDetails.setIdpassport(mEdtIdPassportNo.getText().toString());
           mPersonalDetails.setNationality(mSpnNationality.getSelectedItem().toString());
           Intent intent = new Intent(PersonalDetailsActivity.this,ContactDetailsActivity.class);
           startActivityForResult(intent,1);
           PreferenceHelper.getInstance().savePersonalDetails(mPersonalDetails);
           String sureshDate =PreferenceHelper.getInstance().getPersonalDetails().getFirstnme();
           AppLog.d("Prefrence Stored IDPassport=====================***********",sureshDate);

          }

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


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnNextPage:
                validateAndSave();
               /* Intent i = new Intent(PersonalDetailsActivity.this,
                        ContactDetailsActivity.class);
                    startActivity(i);*/

                break;



        }
    }

    @Override
    public void onResponse(Response<List<Registrationss>> response) {

    }

    @Override
    public void noDataFound(Response<List<Registrationss>> response) {

    }



    @Override
    protected String getScreenTitle() {
        return "";
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_personal_details;
    }
}

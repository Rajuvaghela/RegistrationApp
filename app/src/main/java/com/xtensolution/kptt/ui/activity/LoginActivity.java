package com.xtensolution.kptt.ui.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.design.widget.TextInputLayout;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.xtensolution.core.utils.AppLog;
import com.xtensolution.core.utils.ResourceUtils;
import com.xtensolution.kptt.R;
import com.xtensolution.kptt.model.Login;
import com.xtensolution.kptt.model.Response;
import com.xtensolution.kptt.ui.listener.TextChangeListener;
import com.xtensolution.kptt.ui.viewmodel.LoginViewModel;
import com.xtensolution.kptt.utils.PreferenceHelper;
import com.xtensolution.kptt.utils.ValidationUtils;

/**
 * Created by riontech on 29/11/17.
 */

public class LoginActivity extends BaseActionBarActivity<Login> implements View.OnClickListener {
    private String TAG = LoginActivity.class.getSimpleName();
    private LoginViewModel loginViewModel = new LoginViewModel();
    private TextInputLayout textInputLayout;
    private TextInputLayout mTilUserName;
    private TextInputLayout mTilPassword;
    private TextInputLayout mTilmobileNo;


    private TextView mForgotPassword;
    private TextView mTxtLabelPass;
    private TextView mTxtLabelVerify;

    private Button mBtnLogin;

    private EditText mEdtUserName;
    private EditText mEdtPassword;
    private EditText mEdtMobile;

    private ImageView mImgNextBtn;
    final Context context = this;

    @Override
    protected void onActionBarAttached() {
        //login
        mEdtUserName = _findViewById(R.id.edtUserName);
        mEdtPassword = _findViewById(R.id.edtPassword);
        mTilmobileNo = _findViewById(R.id.tilEnterMobileNumber);
        mTilUserName = _findViewById(R.id.tilUserName);
        mTilPassword = _findViewById(R.id.tilPassword);

        mEdtMobile = _findViewById(R.id.edtMobileNumber);
        mForgotPassword = _findViewById(R.id.txtForgotPassword);
        mTxtLabelPass = _findViewById(R.id.txtLabelForgotPass);
        mTxtLabelPass = _findViewById(R.id.txtLabelVerify);
        mImgNextBtn = _findViewById(R.id.imgNextBtn);
        mBtnLogin = _findViewById(R.id.btnLogin);
        mBtnLogin.setOnClickListener(this);
        mForgotPassword.setOnClickListener(this);

        mEdtPassword.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                try {
                    if (actionId == EditorInfo.IME_ACTION_SEARCH
                            || actionId == EditorInfo.IME_ACTION_DONE
                            || keyEvent.getAction() == KeyEvent.ACTION_DOWN
                            && keyEvent.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
                        validateAndSave();
                        return true;
                    }
                } catch (Exception e) {
                    AppLog.e(TAG, e.getMessage(), e);
                }
                return false;
            }
        });

//        setTextChangeLister();

    }


    private void forgotPassword() {
        mForgotPassword.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // custom dialog
                final Dialog dialog = new Dialog(context);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.dialog_forgot_password);
                mTxtLabelPass = (TextView) dialog.findViewById(R.id.txtLabelForgotPass);
                mEdtMobile = (EditText) dialog.findViewById(R.id.edtMobileNumber);

                // if button is clicked, close the custom dialog
                mTxtLabelPass.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();

                    }
                });

                dialog.show();

                mImgNextBtn = (ImageView) dialog.findViewById(R.id.imgNextBtn);
                mImgNextBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        try {
                            String number = mEdtMobile.getText().toString();
                            if (!ValidationUtils.isValidString(number)) {
                                mTilmobileNo.setErrorEnabled(true);
                                mTilmobileNo.setError(ResourceUtils.getString(R.string.enter_mobile_no));
                            } else if (!ValidationUtils.isValidMobileNumber(mEdtMobile)) {
                                mTilmobileNo.setErrorEnabled(true);
                                mTilmobileNo.setError(ResourceUtils.getString(R.string.validate_mobile));
                            } else {
                                dialog.dismiss();
                                final Dialog dialogg = new Dialog(context);
                                dialogg.requestWindowFeature(Window.FEATURE_NO_TITLE);
                                dialogg.requestWindowFeature(Window.FEATURE_NO_TITLE);
                                dialogg.setContentView(R.layout.dialog_verify_code);
                                mTxtLabelVerify = (TextView) dialogg.findViewById(R.id.txtLabelVerify);
                                // if button is clicked, close the custom dialog
                                mTxtLabelVerify.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        dialogg.dismiss();
                                    }
                                });

                                dialogg.show();
                            }

                        } catch (Exception e) {
                            AppLog.e(TAG, e.getMessage(), e);

                        }
                    }

                });
            }
        });


    }

    private void setTextChangeLister() {
        mTilUserName.getEditText().addTextChangedListener(new TextChangeListener(mTilUserName));
        mTilPassword.getEditText().addTextChangedListener(new TextChangeListener(mTilPassword));

    }

    @Override
    protected String getScreenTitle() {
        return ResourceUtils.getString(R.string.login_page);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_login;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnLogin:
                validateAndSave();
//                Intent iLogin = new Intent(LoginActivity.this, MainActivity.class);
//                startActivity(iLogin);
                break;
            case R.id.txtForgotPassword:
                forgotPassword();
                break;
        }
    }

    private void validateAndSave() {
//        String user = mEdtUserName.getText().toString();
//        String password = mEdtPassword.getText().toString();

//        if (ValidationUtils.isValidString(user)) {
//            mTilUserName.setErrorEnabled(false);
//
//            if (ValidationUtils.isValidString(password)) {
//                mTilPassword.setErrorEnabled(false);
//                loginClick(user);
//            } else {
//                mTilPassword.setErrorEnabled(true);
//                mTilPassword.setError(getString(R.string.enter_password));
//            }
//        } else {
//            mTilUserName.setErrorEnabled(true);
//            mTilUserName.setError(getResources().getString(R.string.enter_user_name));
//        }


      /*  try {
            String user = mEdtUserName.getText().toString();
            String password = mEdtPassword.getText().toString();
            if (!ValidationUtils.isValidString(user)) {
                mTilUserName.setErrorEnabled(true);
                mTilUserName.setError(ResourceUtils.getString(R.string.enter_user_name));
            } else if (!ValidationUtils.isValidString(password)) {
                mTilPassword.setErrorEnabled(true);
                mTilPassword.setError(ResourceUtils.getString(R.string.enter_password));
            } else {
                mTilPassword.setErrorEnabled(false);
                mTilPassword.setErrorEnabled(false);
                mBtnLogin.setEnabled(false);
                mBtnLogin.setText(ResourceUtils.getString(R.string.login_progress));
                loginViewModel.login(this, mEdtUserName.getText().toString(),
                        mEdtPassword.getText().toString());
            }
        } catch (Exception e) {
            AppLog.e(TAG, e.getMessage(), e);
        }*/


        //
        try {
            if (TextUtils.isEmpty(mEdtUserName.getText().toString())) {
                showError(mEdtUserName);
            } else if (TextUtils.isEmpty(mEdtPassword.getText().toString())) {
                showError(mEdtPassword);
            } else {
                mBtnLogin.setEnabled(false);
                mBtnLogin.setText(ResourceUtils.getString(R.string.login_progress));
                hideSoftKeyboard();
                loginViewModel.login(this, mEdtUserName.getText().toString(),
                        mEdtPassword.getText().toString());
            }
        } catch (Exception e) {
            AppLog.e(TAG, e.getMessage(), e);
        }
        //
    }

    private void showError() {
        try {
            mBtnLogin.setActivated(true);
            mBtnLogin.setText(ResourceUtils.getString(R.string.dlg_error));
        } catch (Exception e) {
            AppLog.e(TAG, e.getMessage(), e);
        }
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

    private void hideSoftKeyboard() {
        if (getCurrentFocus() != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
    }

    @Override
    public void onResponse(Response<Login> response) {
        hideProgress();

        if (response.isStatus() && response.getData() != null) {
            mBtnLogin.setSelected(true);
            mBtnLogin.setText(ResourceUtils.getString(R.string.dlg_success));
            Login user = response.getData();
//            SharedPrefsHelper.getInstance().save("user", new Gson().toJson(user));
            PreferenceHelper.getInstance().saveUser(user);
            Intent it = new Intent(getApplicationContext(), MainActivityTwo.class);
            startActivity(it);
            finish();
        } else {
            showError();
            showSnackbar(R.string.upincorrect, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    resetView();
                }
            });
        }

    }


    @Override
    public void noDataFound(Response<Login> response) {
        hideProgress();
        showSnackbar(R.string.nodeta_found, new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public void onConnectionError() {
        super.onConnectionError();
        hideProgress();
        showError();
        showSnackbar(R.string.no_connection_found, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              resetView();
            }
        });
    }


    private void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    /**
     *
     */
    private void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    protected void resetView() {
        super.resetView();
        mBtnLogin.setActivated(false);
        mBtnLogin.setSelected(false);
        mBtnLogin.setEnabled(true);
        mBtnLogin.setText(ResourceUtils.getString(R.string.login));
    }
}
package com.xtensolution.kptt.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.xtensolution.core.utils.AppLog;
import com.xtensolution.core.utils.ResourceUtils;
import com.xtensolution.kptt.R;
import com.xtensolution.kptt.model.Response;
import com.xtensolution.kptt.model.WorkSpace;
import com.xtensolution.kptt.ui.viewmodel.WorkSpaceViewModel;
import com.xtensolution.kptt.utils.AppUtils;
import com.xtensolution.kptt.utils.PreferenceHelper;

/**
 * Created by riontech on 10/1/18.
 */

public class WorkSpaceActivity extends BaseActionBarActivity<WorkSpace> implements View.OnClickListener {
    private String TAG = WorkSpaceActivity.class.getSimpleName();
    WorkSpaceViewModel mViewModel = new WorkSpaceViewModel();
    private EditText mEdtWorkSpaceName;
    private Button mBtnNext;
    private TextView mTextView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onActionBarAttached() {
        mTextView= _findViewById(R.id.txtNoticeKptWorkSpaceID);
        mEdtWorkSpaceName = _findViewById(R.id.edtWorkSpaceName);
        mBtnNext = _findViewById(R.id.btnNextWorkSpace);
        mBtnNext.setOnClickListener(this);



        mEdtWorkSpaceName.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                // Identifier of the action. This will be either the identifier you supplied,
                // or EditorInfo.IME_NULL if being called due to the enter key being pressed.
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
                // Return true if you have consumed the action, else false.
                return false;
            }
        });
    }

    @Override
    protected String getScreenTitle() {
        return null;
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_workspace;
    }

    @Override
    public void onResponse(Response<WorkSpace> response) {
        hideProgress();

        if (response.isStatus() && response.getData() != null) {
            mBtnNext.setSelected(true);
            mBtnNext.setText(ResourceUtils.getString(R.string.dlg_success));

            PreferenceHelper.getInstance().saveWorkSpace(response.getData());

            Intent it = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(it);
            finish();
        } else {
            showError();
            showSnackbar(R.string.not_valid_workspace_id, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    resetView();
                }
            });
        }
    }


    @Override
    protected View getRootView() {
        return _findViewById(R.id.rootView);
    }

    @Override
    public void noDataFound(Response<WorkSpace> response) {
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

    private void showError(EditText editText) {
        try {

            editText.setText("");
            editText.requestFocus();
            editText.setError("Please enter workspace id.");
        } catch (Exception e) {
            AppLog.e(TAG, e.getMessage(), e);
        }
    }


    /**
     *
     */
    private void showError() {
        try {
            mBtnNext.setActivated(true);
            mBtnNext.setText(ResourceUtils.getString(R.string.dlg_error));
        } catch (Exception e) {
            AppLog.e(TAG, e.getMessage(), e);
        }
    }


    @Override
    protected void resetView() {
        super.resetView();
        mBtnNext.setActivated(false);
        mBtnNext.setSelected(false);
        mBtnNext.setEnabled(true);
        mBtnNext.setText(ResourceUtils.getString(R.string.next));
    }

    /**
     *
     */
    private void validateAndSave() {
        try {
            AppUtils.hideKeyboard(this, this);
            
            if (TextUtils.isEmpty(mEdtWorkSpaceName.getText().toString())) {
                showError(mEdtWorkSpaceName);
            } else {
                mBtnNext.setEnabled(false);
                mBtnNext.setText(ResourceUtils.getString(R.string.processing));
                mViewModel.workspace(this, mEdtWorkSpaceName.getText().toString());
            }
        } catch (Exception e) {
            AppLog.e(TAG, e.getMessage(), e);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnNextWorkSpace:
                validateAndSave();
//                Intent iLogin = new Intent(LoginActivity.this, MainActivity.class);
//                startActivity(iLogin);
                break;

        }
    }
}

package com.xtensolution.core.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;

import com.xtensolution.core.R;
import com.xtensolution.core.utils.AppLog;
import com.xtensolution.core.utils.CompatibilityUtil;
import com.xtensolution.core.utils.ResourceUtils;

/**
 * Created by RIONTECH
 * Riontech on 23/12/17.
 */

public class ProgressUIButton extends android.support.v7.widget.AppCompatButton {
    private static final String TAG = ProgressUIButton.class.getSimpleName();

    public enum STATES {SUCCESS, ERROR, PROGRESS, DEFAULT}

    private String btnLabel;

    public ProgressUIButton(Context context) {
        super(context);
        init();
    }

    public ProgressUIButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ProgressUIButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        try {
            Drawable drawable = ResourceUtils.getDrawable(R.drawable.progress_ui_btn_bg);
            if (CompatibilityUtil.isJellyBean()) {
                setBackground(drawable);
            } else {
                setBackgroundDrawable(drawable);
            }

            setAllCaps(true);
            btnLabel = getText().toString();
        } catch (Exception e) {
            AppLog.e(TAG, e.getMessage(), e);
        }
    }


    public void activeState(STATES states) {
        switch (states) {
            case ERROR: activatedError(); break;
            case DEFAULT: activatedDefault(); break;
            case SUCCESS: activatedSuccess(); break;
            case PROGRESS: activatedProgress(); break;
        }
    }

    private void activatedError() {
        setActivated(true);
        setText(ResourceUtils.getString(R.string.dlg_error));
    }

    private void activatedDefault() {
        setActivated(false);
        setSelected(false);
        setEnabled(true);
        setText(btnLabel);
    }

    private void activatedSuccess() {
        setSelected(true);
        setText(ResourceUtils.getString(R.string.dlg_success));
    }

    private void activatedProgress() {
        setEnabled(false);
        String progress = btnLabel + "...";
        setText(progress);
    }
}

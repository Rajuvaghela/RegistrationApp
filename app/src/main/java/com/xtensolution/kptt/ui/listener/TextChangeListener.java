package com.xtensolution.kptt.ui.listener;

import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextWatcher;

/**
 * Created by riontech on 29/12/17.
 */

public class TextChangeListener implements TextWatcher {
    private final TextInputLayout textInputLayout;

    public TextChangeListener(TextInputLayout inputLayout) {
        this.textInputLayout = inputLayout;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (s.toString().trim().length() > 0) {
            textInputLayout.setErrorEnabled(false);
        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}

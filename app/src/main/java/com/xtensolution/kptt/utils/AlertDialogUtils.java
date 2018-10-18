package com.xtensolution.kptt.utils;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.xtensolution.core.utils.AppLog;
import com.xtensolution.kptt.R;


import java.util.Date;

/**
 * USED FOR DISPLAY DIALOGS
 */

public class AlertDialogUtils {
    private static final String TAG = AlertDialogUtils.class.getSimpleName();

    public static void showSnakbar(View rootView, String msg) {
        Snackbar snackbar = Snackbar
                .make(rootView, msg, Snackbar.LENGTH_LONG);
        View view = snackbar.getView();
        TextView tv = (TextView) view.findViewById(android.support.design.R.id.snackbar_text);
        tv.setTextColor(Color.WHITE);
        snackbar.show();
    }

    public static void showForgotPassDialog(final Context ctx, final ForgotPassCallBack callBack) {
        final AlertDialog dialog = new AlertDialog.Builder(ctx).create();
        dialog.setCanceledOnTouchOutside(false);
        LayoutInflater inflater = LayoutInflater.from(ctx);
        final View alertView = inflater.inflate(R.layout.dialog_forgot_password, null);
        dialog.setView(alertView);
        final TextInputLayout tilNumber = (TextInputLayout) alertView.findViewById(R.id.tilEnterMobileNumber);
        final EditText editText = (EditText) alertView.findViewById(R.id.edtMobileNumber);
        final TextView txtLabelPass = (TextView) alertView.findViewById(R.id.txtLabelForgotPass);
        txtLabelPass.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int DRAWABLE_LEFT = 0;
                final int DRAWABLE_TOP = 1;
                final int DRAWABLE_RIGHT = 2;
                final int DRAWABLE_BOTTOM = 3;

                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (event.getRawX() >= (txtLabelPass.getRight() -
                            txtLabelPass.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                        // your action here
                        dialog.cancel();
                        return true;
                    }
                }
                return false;
            }
        });

        ImageView imgNext = (ImageView) alertView.findViewById(R.id.imgNextBtn);
        imgNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mobileNumberText = editText.getText().toString();
                if (ValidationUtils.isValidString(mobileNumberText)) {
                    tilNumber.setErrorEnabled(false);
                    callBack.onForgotPasswordRequest(mobileNumberText);
                    dialog.cancel();
                } else {
                    tilNumber.setErrorEnabled(true);
                    tilNumber.setError(ctx.getResources()
                            .getString(R.string.validate_mobile));
                }
            }
        });

        dialog.show();

    }


    public interface ForgotPassCallBack {
        void onForgotPasswordRequest(String requestCodeOrNumber);
    }

    public interface PositiveNegativeCallback {
        void onPositiveClick();

        void onNegativeClick();
    }

    public static void showToast(Context ctx, String string) {
        Toast.makeText(ctx, string, Toast.LENGTH_LONG).show();
    }

    public static void showDialogWithYesNoButton(Context ctx,
                                                 String message,
                                                 String positiveBtnName,
                                                 String negativeBtnName,
                                                 final PositiveNegativeCallback callback) {
        try {
            AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
            builder.setMessage(message)
                    .setCancelable(false)
                    .setPositiveButton(positiveBtnName,
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    callback.onPositiveClick();
                                    dialog.cancel();
                                }
                            })
                    .setNegativeButton(negativeBtnName,
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    callback.onNegativeClick();
                                    dialog.cancel();
                                }
                            });
            AlertDialog alert = builder.create();
            alert.show();
        } catch (Exception e) {
            AppLog.e(TAG, e.getMessage(), e);
        }
    }
}
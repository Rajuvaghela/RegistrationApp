package com.xtensolution.kptt.ui.activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import com.xtensolution.kptt.R;



/**
 * Created by suresh dobariya on 20/11/17.
 */

public class SplashActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mBtnStartRegistration;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.
                FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        mBtnStartRegistration = (Button) findViewById(R.id.btnStartRegistration);
        mBtnStartRegistration.setOnClickListener(this);

        }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnStartRegistration:
                Intent i = new Intent(SplashActivity.this,
                        PersonalDetailsActivity.class);
                startActivity(i);
                finish();
                break;


        }
    }
}





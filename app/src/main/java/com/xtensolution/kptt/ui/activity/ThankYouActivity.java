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



public class ThankYouActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mClose;
    private Button mNew;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.
                FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thankyou);

        mClose = (Button) findViewById(R.id.btnExit);
        mNew = (Button) findViewById(R.id.btnNew);
        mClose.setOnClickListener(this);
        mNew.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnExit:

                finish();
                break;
            case R.id.btnNew:

                Intent btnNext = new Intent(ThankYouActivity.this,
                        SplashActivity.class);
                startActivity(btnNext);
                break;
        }
    }
}

package com.jackson.ssrjmvp.view.activity.immersive;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.jackson.ssrjmvp.R;
import com.jackson.ssrjmvp.utils.StatusBarUtil;

public class WholeImageActivity extends AppCompatActivity {
    private int alpha = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_whole_image);
        StatusBarUtil.setTranslucent(this, alpha);
    }
}

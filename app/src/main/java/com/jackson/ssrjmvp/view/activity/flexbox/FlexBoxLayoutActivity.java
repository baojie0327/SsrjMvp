package com.jackson.ssrjmvp.view.activity.flexbox;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.google.android.flexbox.FlexboxLayout;
import com.jackson.ssrjmvp.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FlexBoxLayoutActivity extends AppCompatActivity {

    @BindView(R.id.btn_tag)
    Button mBtnTag;
    @BindView(R.id.btn_waterfall)
    Button mBtnWaterfall;
    @BindView(R.id.flexboxLayout)
    FlexboxLayout mFlexboxLayout;

    @OnClick({R.id.btn_tag,R.id.btn_waterfall})
    public void clickView(View view){
        switch (view.getId()){
            case R.id.btn_tag:
               startActivity(new Intent(this, FlexBoxFlowActivity.class));
                break;
            case R.id.btn_waterfall:
               startActivity(new Intent(this, FlexWaterFallActivity.class));
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flex_box_layout);
        ButterKnife.bind(this);
    }
}

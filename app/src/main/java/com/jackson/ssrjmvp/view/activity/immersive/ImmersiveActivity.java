package com.jackson.ssrjmvp.view.activity.immersive;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jackson.ssrjmvp.R;
import com.jackson.ssrjmvp.utils.StatusBarUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 沉浸式
 */
public class ImmersiveActivity extends AppCompatActivity {

    @BindView(R.id.ll_back)
    LinearLayout mLlBack;
    @BindView(R.id.tv_head_title)
    TextView mTvHeadTitle;
    @BindView(R.id.all_image)
    Button mAllImage;
    @BindView(R.id.image)
    Button mImage;
    private int mAlpha = StatusBarUtil.DEFAULT_STATUS_BAR_ALPHA;
    private int statusBarAlpha; // 状态栏透明度
    private Button btn_all_image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_immersive);
        ButterKnife.bind(this);
        setStatusBar();

        mAllImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ImmersiveActivity.this, WholeImageActivity.class));
            }
        });
        mImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ImmersiveActivity.this, ImageActivity.class));
            }
        });


    }

    protected void setStatusBar() {
        statusBarAlpha = 0;
        StatusBarUtil.setColor(this, getResources().getColor(R.color.main_color), statusBarAlpha);
        // 在Fragment中使用，在Fragment绑定的Activity中使用
     //   StatusBarUtil.setColor(this,getResources().getColor(R.color.colorPrimary),0);
     //   StatusBarUtil.setTranslucentForImageViewInFragment(UseInFragmentActivity.this,0, null);
    }
}

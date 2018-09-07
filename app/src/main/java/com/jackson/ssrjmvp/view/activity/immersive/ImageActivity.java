package com.jackson.ssrjmvp.view.activity.immersive;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.RelativeLayout;

import com.jackson.ssrjmvp.R;
import com.jackson.ssrjmvp.utils.StatusBarUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ImageActivity extends AppCompatActivity {



    @BindView(R.id.rl_rl)
    RelativeLayout mRlRl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        ButterKnife.bind(this);

       // StatusBarUtil.setTranslucentForImageView(this, 0, mRlRl);
        StatusBarUtil.setTranslucentForImageView(this, 0, null);
    }
}

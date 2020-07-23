package com.jackson.ssrjmvp.view.activity;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.jackson.ssrjmvp.R;
import com.jackson.ssrjmvp.svg.GlideToVectorYou;

public class TestActivity extends AppCompatActivity {

    ImageView mImageView;

    private String IMAGE_URL = "http://devapi.kunlunhealth.com.cn/ext/common/captcha?access_token=5556bd49ad7689b3bfee7cac5293f5e2851c77fa&a=0.7931963481924829";

    private String image_url="https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1382184082.17.webp";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        mImageView=findViewById(R.id.image);
        GlideToVectorYou.justLoadImage(this, Uri.parse(IMAGE_URL), mImageView);
    }
}

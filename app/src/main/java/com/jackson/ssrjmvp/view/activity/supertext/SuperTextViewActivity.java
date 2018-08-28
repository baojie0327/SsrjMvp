package com.jackson.ssrjmvp.view.activity.supertext;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.coorchice.library.SuperTextView;
import com.jackson.ssrjmvp.R;
import com.jackson.ssrjmvp.utils.MoveEffectAdjuster;
import com.jackson.ssrjmvp.utils.RippleAdjuster;

public class SuperTextViewActivity extends AppCompatActivity {
    private SuperTextView stv_17;
    private SuperTextView stv_18;
    private SuperTextView stv_123;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_super_text_view);

        stv_17 = (SuperTextView) findViewById(R.id.stv_17);
        stv_18 = (SuperTextView) findViewById(R.id.stv_18);
        stv_123 = (SuperTextView) findViewById(R.id.stv_123);
        stv_17.setAdjuster(new MoveEffectAdjuster().setOpportunity(SuperTextView.Adjuster.Opportunity.BEFORE_DRAWABLE))
                .setAutoAdjust(true)
                .startAnim();

        stv_18.setAdjuster(new RippleAdjuster(getResources().getColor(R.color.opacity_9_blue)));


        String url_avatar =
                "http://ogemdlrap.bkt.clouddn.com/timg-2.jpeg";

        stv_123.setUrlImage(url_avatar, false);

    }
}

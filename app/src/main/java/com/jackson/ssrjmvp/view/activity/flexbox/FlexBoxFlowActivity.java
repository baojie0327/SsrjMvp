package com.jackson.ssrjmvp.view.activity.flexbox;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.android.flexbox.AlignItems;
import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.jackson.ssrjmvp.R;
import com.jackson.ssrjmvp.adapter.FlexboxFlowAdapter;
import com.jackson.ssrjmvp.utils.CommonMethod;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * FlexBoxLayout实现流式布局
 */

public class FlexBoxFlowActivity extends AppCompatActivity {

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    private List<String> mDatas;

    private FlexboxFlowAdapter mFlexboxFlowAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flex_box_flow);
        ButterKnife.bind(this);
        initData();
    }

    private void initData() {
        mDatas = new ArrayList<>();
        mDatas.add("程序员");
        mDatas.add("影视天堂");
        mDatas.add("美食");
        mDatas.add("漫画.手绘");
        mDatas.add("广告圈");
        mDatas.add("旅行。在路上");
        mDatas.add("娱乐八卦");
        mDatas.add("青春");
        mDatas.add("谈写作");
        mDatas.add("短篇小说");
        mDatas.add("散文");
        mDatas.add("污段子");
        mDatas.add("小说");
        mDatas.add("程序员2");
        mDatas.add("影视天堂2");
        mDatas.add("美食2");
        mDatas.add("漫画.手绘2");
        mDatas.add("广告圈2");
        mDatas.add("旅行。在路上2");
        mDatas.add("娱乐八卦2");
        mDatas.add("青春2");

        FlexboxLayoutManager layoutManager = new FlexboxLayoutManager(this);
        layoutManager.setFlexDirection(FlexDirection.ROW);//设置主轴排列方式
        layoutManager.setFlexWrap(FlexWrap.WRAP);//设置是否换行
        layoutManager.setAlignItems(AlignItems.STRETCH);
        mRecyclerView.setLayoutManager(layoutManager);
        mFlexboxFlowAdapter=new FlexboxFlowAdapter(R.layout.item_flow_layout,mDatas);
        mRecyclerView.setAdapter(mFlexboxFlowAdapter);

        mFlexboxFlowAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                CommonMethod.showToast(FlexBoxFlowActivity.this,"click--"+position,false);
            }
        });
    }


}

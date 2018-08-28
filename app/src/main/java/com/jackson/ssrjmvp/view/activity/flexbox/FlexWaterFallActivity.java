package com.jackson.ssrjmvp.view.activity.flexbox;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import com.google.android.flexbox.AlignItems;
import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.jackson.ssrjmvp.Flexboxwaterfall1Adapter;
import com.jackson.ssrjmvp.R;
import com.jackson.ssrjmvp.adapter.FlexboxwaterfallAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FlexWaterFallActivity extends AppCompatActivity {

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    private List<String> mDatas;
    private FlexboxwaterfallAdapter mFlexboxwaterfallAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flex_water_fall);
        ButterKnife.bind(this);
        initData();
    }

    private void initData() {
        mDatas = new ArrayList<>();
       /* mDatas.add(R.drawable.cat_100);
        mDatas.add(R.drawable.cat_101);
        mDatas.add(R.drawable.cat_102);
        mDatas.add(R.drawable.cat_103);
        mDatas.add(R.drawable.cat_104);
        mDatas.add(R.drawable.cat_105);*/
        /*mDatas.add(R.drawable.cat_1);
        mDatas.add(R.drawable.cat_2);
        mDatas.add(R.drawable.cat_3);
        mDatas.add(R.drawable.cat_4);
        mDatas.add(R.drawable.cat_5);
        mDatas.add(R.drawable.cat_6);
        mDatas.add(R.drawable.cat_7);
        mDatas.add(R.drawable.cat_8);
        mDatas.add(R.drawable.cat_9);
        mDatas.add(R.drawable.cat_10);
        mDatas.add(R.drawable.cat_11);
        mDatas.add(R.drawable.cat_12);
        mDatas.add(R.drawable.cat_14);
        mDatas.add(R.drawable.cat_15);
        mDatas.add(R.drawable.cat_16);
        mDatas.add(R.drawable.cat_17);
        mDatas.add(R.drawable.cat_18);
        mDatas.add(R.drawable.cat_19);*/

        mDatas.add("https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=415293130,2419074865&fm=27&gp=0.jpg");
        mDatas.add("http://img4.imgtn.bdimg.com/it/u=2011641246,1136238184&fm=27&gp=0.jpg");
        mDatas.add("https://ss1.baidu.com/9vo3dSag_xI4khGko9WTAnF6hhy/image/h%3D300/sign=60c8d30476ec54e75eec1c1e89399bfd/314e251f95cad1c8e671a21d723e6709c83d51c5.jpg");
        mDatas.add("http://img4.imgtn.bdimg.com/it/u=2728385433,2457016079&fm=27&gp=0.jpg");
        mDatas.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1535372007146&di=e1fe0e26f11dee07ac63ffa74a548b2c&imgtype=0&src=http%3A%2F%2Fe.hiphotos.baidu.com%2Fnuomi%2Fpic%2Fitem%2Fc2cec3fdfc03924563d6c48c8f94a4c27c1e25d5.jpg");
        mDatas.add("https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=707163870,236573498&fm=200&gp=0.jpg");
        mDatas.add("https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=1704501784,1090474099&fm=26&gp=0.jpg");
        mDatas.add("https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=3119789682,3574591933&fm=26&gp=0.jpg");
        mDatas.add("https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=2784213826,678030885&fm=26&gp=0.jpg");
        mDatas.add("https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=2628782928,4000563725&fm=200&gp=0.jpg");
        mDatas.add("https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=3526912488,3200431830&fm=26&gp=0.jpg");
        mDatas.add("https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=2405361616,3143901983&fm=26&gp=0.jpg");
        mDatas.add("https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=3206723577,1505557967&fm=200&gp=0.jpg");
        mDatas.add("https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=1793343773,393517993&fm=200&gp=0.jpg");
        mDatas.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1535967424&di=f382fb3cd6ec7f198b096764a3f30909&imgtype=jpg&er=1&src=http%3A%2F%2Fd.hiphotos.baidu.com%2Flvpics%2Fw%3D1000%2Fsign%3D02b65049912bd40742c7d7fd4bb99f51%2Fb21bb051f81986183de821e54ced2e738bd4e620.jpg");
        mDatas.add("https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=3983449988,3542232129&fm=26&gp=0.jpg");


        FlexboxLayoutManager layoutManager = new FlexboxLayoutManager(this);
        layoutManager.setFlexWrap(FlexWrap.WRAP);//设置是否换行
        layoutManager.setFlexDirection(FlexDirection.ROW);//设置主轴排列方式
        layoutManager.setAlignItems(AlignItems.STRETCH);
        mRecyclerView.setLayoutManager(layoutManager);
        mFlexboxwaterfallAdapter = new FlexboxwaterfallAdapter(R.layout.item_flow_waterfall_layout, mDatas);
        mRecyclerView.setAdapter(mFlexboxwaterfallAdapter);
    }
}

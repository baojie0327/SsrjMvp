package com.jackson.ssrjmvp.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.SingleLayoutHelper;
import com.jackson.ssrjmvp.R;
import com.jackson.ssrjmvp.adapter.BaseDelegateAdapter1;
import com.jackson.ssrjmvp.adapter.home.BannerAdapter;
import com.jackson.ssrjmvp.bean.HomeBean;
import com.jackson.ssrjmvp.dagger.component.DaggerHomeComponent;
import com.jackson.ssrjmvp.dagger.module.HomeModule;
import com.jackson.ssrjmvp.presenter.HomePresenter;
import com.jackson.ssrjmvp.utils.LogUtil;
import com.jackson.ssrjmvp.view.IView;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.disposables.Disposable;

import static com.jackson.ssrjmvp.R.id.recyclerView;


public class HomeFragment extends Fragment implements IView.IHomeView {

    @BindView(R.id.ll_back)
    LinearLayout mLlBack;
    @BindView(R.id.tv_head_title)
    TextView mTvHeadTitle;
    @BindView(recyclerView)
    RecyclerView mRecyclerView;
    Unbinder unbinder;

    Unbinder unbinder1;
    private View view;


    @Inject
    HomePresenter mHomePresenter;

    private BannerAdapter mBannerAdapter;

    private List<HomeBean.DataBean> mDataList;  // 数据源
    private List<DelegateAdapter.Adapter> mAdapters;
    private DelegateAdapter delegateAdapter;
    private BaseDelegateAdapter1 mBaseAdApter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_home, null);
            unbinder = ButterKnife.bind(this, view);
            inject();
            initView();
            initData();
            mHomePresenter.getData();

        }

        unbinder1 = ButterKnife.bind(this, view);
        return view;
    }


    /**
     * 注入Presenter
     */
    private void inject() {
        DaggerHomeComponent.builder()
                .homeModule(new HomeModule(this))
                .build()
                .inject(this);
    }

    /**
     * 初始化界面
     */
    private void initView() {
        mLlBack.setVisibility(View.INVISIBLE);
        mTvHeadTitle.setText("首页");
        // 设置RecyclerView
       /* LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(layoutManager);*/
        mDataList = new ArrayList<>();
        mAdapters = new LinkedList<>();

        // 刷新设置
       /* mRefreshLayout.autoRefresh();  // 开启自动刷新
        mRefreshLayout.setEnableAutoLoadMore(true); // 开启自动加载功能
        // mRefreshLayout.setEnableAutoLoadMore(false);//使上拉加载具有弹性效果
        //  mRefreshLayout.setEnableOverScrollDrag(false);//禁止越界拖动（1.0.4以上版本）
        // mRefreshLayout.setEnableOverScrollBounce(false);//关闭越界回弹功能

        // 设置官方刷新主题
        mRefreshLayout.setRefreshHeader(new MaterialHeader(getActivity()));
        mRefreshLayout.setEnableHeaderTranslationContent(false);*/

    }


    private void initData() {
        //初始化
        //创建VirtualLayoutManager对象
        VirtualLayoutManager layoutManager = new VirtualLayoutManager(getActivity());
        // 将VirtualLayoutManager绑定到recyclerView
        mRecyclerView.setLayoutManager(layoutManager);

        //设置组件复用回收池,（如果一屏内相同类型的 View 个数比较多，需要设置一个合适的大小，防止来回滚动时重新创建 View）
        RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
        mRecyclerView.setRecycledViewPool(viewPool);
        viewPool.setMaxRecycledViews(0, 20);

        delegateAdapter = new DelegateAdapter(layoutManager);

    }

    /**
     * 网络请求成功后，获取数据
     *
     * @param mDatatList
     */
    @Override
    public void setData(List<HomeBean.DataBean> mDatatList) {
        LogUtil.d("size==" + mDatatList.size());
        this.mDataList = mDatatList;
        setBannerData(mDatatList.get(0).getItems());
        setAllData();
    }

    /**
     * 设置轮播
     *
     * @param items
     */
    private void setBannerData(List<HomeBean.DataBean.ItemsBean> items) {
        SingleLayoutHelper singleLayoutHelper = new SingleLayoutHelper();
        final List<String> imgList = new ArrayList<>();
        for (int i = 0; i < items.size(); i++) {
            imgList.add("http:" + items.get(i).getImg());
        }
        mBannerAdapter = new BannerAdapter(getActivity(), imgList, singleLayoutHelper, R.layout.item_home_banner_layout, 1);
       /* mBaseAdApter = new BaseDelegateAdapter1(getActivity(), new LinearLayoutHelper(), R.layout.item_home_banner_layout, 1, 1) {
            @Override
            public void onBindViewHolder(BaseViewHolder holder, int position) {
                super.onBindViewHolder(holder, position);
                Banner mBanner = holder.getView(R.id.banner);
                //设置图片加载器
                mBanner.setImageLoader(new GlideImageLoader());
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                layoutParams.height = CommonMethod.convertDpToPixel(getActivity(), 200);
                mBanner.setLayoutParams(layoutParams);
                mBanner.setImages(imgList);
                mBanner.start();
            }
        };*/
    }

    /**
     * 设置所有数据
     */
    private void setAllData() {
        // 添加Banner
        mAdapters.add(mBannerAdapter);
        delegateAdapter.setAdapters(mAdapters);
        mRecyclerView.setAdapter(delegateAdapter);

    }

    @Override
    public void closeDispose(Disposable disposable) {

    }


    /**
     * 提供Fragment实例
     *
     * @return
     */
    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}

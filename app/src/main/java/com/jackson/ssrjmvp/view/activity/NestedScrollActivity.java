package com.jackson.ssrjmvp.view.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ButtonBarLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.jackson.ssrjmvp.R;
import com.jackson.ssrjmvp.adapter.FragmentAdapter;
import com.jackson.ssrjmvp.customview.ColorFlipPagerTitleView;
import com.jackson.ssrjmvp.customview.JudgeNestedScrollView;
import com.jackson.ssrjmvp.utils.ScreenUtil;
import com.jackson.ssrjmvp.utils.StatusBarUtils;
import com.jackson.ssrjmvp.view.fragment.nesttab.TabLayoutFargment;
import com.jackson.ssrjmvp.view.fragment.nesttab.TabLayoutFargment1;
import com.jackson.ssrjmvp.view.fragment.nesttab.TabLayoutFargment2;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.scwang.smartrefresh.layout.listener.SimpleMultiPurposeListener;
import com.scwang.smartrefresh.layout.util.DensityUtil;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.UIUtil;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;


/**
 * NestedScrollView+ViewPager+RecyclerView+SmartRefreshLayout
 */

public class NestedScrollActivity extends AppCompatActivity {


    @BindView(R.id.iv_avatar)
    CircleImageView mIvAvatar;
    @BindView(R.id.tv_username)
    TextView mTvUsername;
    @BindView(R.id.tv_major)
    TextView mTvMajor;
    @BindView(R.id.tv_gender)
    TextView mTvGender;
    @BindView(R.id.tv_level_num)
    TextView mTvLevelNum;
    @BindView(R.id.tv_company)
    TextView mTvCompany;
    @BindView(R.id.tv_position)
    TextView mTvPosition;
    @BindView(R.id.tv_follow_num)
    TextView mTvFollowNum;
    @BindView(R.id.tv_fans_num)
    TextView mTvFansNum;
    @BindView(R.id.tv_introduce)
    TextView mTvIntroduce;
    @BindView(R.id.tv_authentication)
    TextView mTvAuthentication;
    @BindView(R.id.tv_edit_info)
    TextView mTvEditInfo;
    @BindView(R.id.tv_integral_num)
    TextView mTvIntegralNum;
    @BindView(R.id.tv_japanese_currency)
    TextView mTvJapaneseCurrency;
    @BindView(R.id.tv_prestige)
    TextView mTvPrestige;
    @BindView(R.id.tv_friendliness)
    TextView mTvFriendliness;
    @BindView(R.id.tv_label_one)
    TextView mTvLabelOne;
    @BindView(R.id.tv_label_two)
    TextView mTvLabelTwo;
    @BindView(R.id.tv_label_three)
    TextView mTvLabelThree;
    @BindView(R.id.tv_edit_label)
    TextView mTvEditLabel;
    @BindView(R.id.collapse)
    CollapsingToolbarLayout mCollapse;
    @BindView(R.id.magic_indicator)
    MagicIndicator mMagicIndicator;
    @BindView(R.id.view_pager)
    ViewPager mViewPager;
    @BindView(R.id.scrollView)
    JudgeNestedScrollView mScrollView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;
    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.toolbar_avatar)
    CircleImageView mToolbarAvatar;
    @BindView(R.id.toolbar_username)
    TextView mToolbarUsername;
    @BindView(R.id.buttonBarLayout)
    ButtonBarLayout mButtonBarLayout;
    @BindView(R.id.iv_menu)
    ImageView mIvMenu;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.magic_indicator_title)
    MagicIndicator mMagicIndicatorTitle;
    @BindView(R.id.fl_activity)
    FrameLayout mFlActivity;

    int toolBarPositionY = 0;
    @BindView(R.id.iv_header)
    ImageView mIvHeader;
    private int mOffset = 0;
    private int mScrollY = 0;

    private String[] mTitles = new String[]{"动态", "文章", "问答"};
    private List<String> mDataList = Arrays.asList(mTitles);
    FragmentAdapter mFragmentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nested_scroll);
        ButterKnife.bind(this);

        //    StatusBarUtil.setTranslucentForCoordinatorLayout(this, 0);
        StatusBarUtils.immersive(this);
        StatusBarUtils.setPaddingSmart(this, mToolbar);

        initView();
        refreshListen();


        //  initViewPager();


    }

    private void initView() {

        /**
         * 通过监听SmartRefreshLayout的滑动距离，来设置头像和ToolBar
         */
        mRefreshLayout.setOnMultiPurposeListener(new SimpleMultiPurposeListener() {
            @Override
            public void onHeaderMoving(RefreshHeader header, boolean isDragging, float percent, int offset, int headerHeight, int maxDragHeight) {
                mOffset = offset / 2;
                mIvHeader.setTranslationY(mOffset - mScrollY);
                mToolbar.setAlpha(1 - Math.min(percent, 1));
            }

           /* @Override
            public void onRefresh(@NonNull final RefreshLayout refreshLayout) {
                refreshHead();
                mFragmentAdapter.mFragments.get(mViewPager.getCurrentItem()).refreshData(refreshLayout,mViewPager.getCurrentItem());
            }

            @Override
            public void onLoadMore(@NonNull final RefreshLayout refreshLayout) {
                refreshLayout.finishLoadMore(2000);
                mFragmentAdapter.mFragments.get(mViewPager.getCurrentItem()).loadMore(refreshLayout,mViewPager.getCurrentItem());
            }*/
        });


        mToolbar.post(new Runnable() {
            @Override
            public void run() {
                dealWithViewPager();
            }
        });

        mScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            int lastScrollY = 0;
            int h = DensityUtil.dp2px(170);
            int color = ContextCompat.getColor(getApplicationContext(), R.color.mainWhite) & 0x00ffffff;

            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                int[] location = new int[2];
                mMagicIndicator.getLocationOnScreen(location);
                int yPosition = location[1];

                if (yPosition < toolBarPositionY) {
                    mMagicIndicatorTitle.setVisibility(View.VISIBLE);
                    mScrollView.setNeedScroll(false);
                } else {
                    mMagicIndicatorTitle.setVisibility(View.GONE);
                    mScrollView.setNeedScroll(true);
                }

                if (lastScrollY < h) {
                    scrollY = Math.min(h, scrollY);
                    mScrollY = scrollY > h ? h : scrollY;
                    mButtonBarLayout.setAlpha(1f * mScrollY / h);
                    mToolbar.setBackgroundColor(((255 * mScrollY / h) << 24) | color);
                    mIvHeader.setTranslationY(mOffset - mScrollY);
                }

                if (scrollY == 0) {
                    mIvBack.setImageResource(R.drawable.back_white);
                    mIvMenu.setImageResource(R.drawable.icon_menu_white);
                } else {
                    mIvBack.setImageResource(R.drawable.back_black);
                    mIvMenu.setImageResource(R.drawable.icon_menu_black);
                }

                lastScrollY = scrollY;
            }
        });

        mButtonBarLayout.setAlpha(0);
        mToolbar.setBackgroundColor(0);

        mFragmentAdapter = new FragmentAdapter(getSupportFragmentManager(), getFragment());
        mViewPager.setAdapter(mFragmentAdapter);
        mViewPager.setOffscreenPageLimit(10);

        initMagicIndicator();
        initMagicIndicatorTitle();


    }

    /**
     * 刷新加载监听
     */
    private void refreshListen() {
        // refresh
        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull final RefreshLayout refreshLayout) {
                refreshLayout.getLayout().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refreshHead();
                        mFragmentAdapter.mFragments.clear();
                        mFragmentAdapter = new FragmentAdapter(getSupportFragmentManager(), getFragment());
                        mViewPager.setAdapter(mFragmentAdapter);
                        mViewPager.setOffscreenPageLimit(10);
                        initMagicIndicator();
                        initMagicIndicatorTitle();
                        refreshLayout.finishRefresh();
                    }
                },2000);


            }
        });

    }

    private void refreshHead() {
        mTvUsername.setText(mTvUsername.getText() + "a");
    }

    private void dealWithViewPager() {
        toolBarPositionY = mToolbar.getHeight();
        ViewGroup.LayoutParams params = mViewPager.getLayoutParams();
        params.height = ScreenUtil.getScreenHeightPx(getApplicationContext()) - toolBarPositionY - mMagicIndicator.getHeight() + 1;

        mViewPager.setLayoutParams(params);
    }


    private List<Fragment> getFragment() {
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(TabLayoutFargment.newInstance());
        fragments.add(TabLayoutFargment1.newInstance());
        fragments.add(TabLayoutFargment2.newInstance());
        return fragments;
    }

    private void initMagicIndicator() {
        CommonNavigator commonNavigator = new CommonNavigator(this);
        commonNavigator.setScrollPivotX(0.65f);
        commonNavigator.setAdjustMode(true);
        commonNavigator.setAdapter(new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                return mDataList == null ? 0 : mDataList.size();
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                SimplePagerTitleView simplePagerTitleView = new ColorFlipPagerTitleView(context);
                simplePagerTitleView.setText(mDataList.get(index));
                simplePagerTitleView.setNormalColor(ContextCompat.getColor(NestedScrollActivity.this, R.color.mainBlack));
                simplePagerTitleView.setSelectedColor(ContextCompat.getColor(NestedScrollActivity.this, R.color.mainBlack));
                simplePagerTitleView.setTextSize(16);
                simplePagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mViewPager.setCurrentItem(index, false);
                    }
                });
                return simplePagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                LinePagerIndicator indicator = new LinePagerIndicator(context);
                indicator.setMode(LinePagerIndicator.MODE_EXACTLY);
                indicator.setLineHeight(UIUtil.dip2px(context, 2));
                indicator.setLineWidth(UIUtil.dip2px(context, 20));
                indicator.setRoundRadius(UIUtil.dip2px(context, 3));
                indicator.setStartInterpolator(new AccelerateInterpolator());
                indicator.setEndInterpolator(new DecelerateInterpolator(2.0f));
                indicator.setColors(ContextCompat.getColor(NestedScrollActivity.this, R.color.mainRed));
                return indicator;
            }
        });
        mMagicIndicator.setNavigator(commonNavigator);
        ViewPagerHelper.bind(mMagicIndicator, mViewPager);
    }

    private void initMagicIndicatorTitle() {
        CommonNavigator commonNavigator = new CommonNavigator(this);
        commonNavigator.setScrollPivotX(0.65f);
        commonNavigator.setAdjustMode(true);
        commonNavigator.setAdapter(new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                return mDataList == null ? 0 : mDataList.size();
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                SimplePagerTitleView simplePagerTitleView = new ColorFlipPagerTitleView(context);
                simplePagerTitleView.setText(mDataList.get(index));
                simplePagerTitleView.setNormalColor(ContextCompat.getColor(NestedScrollActivity.this, R.color.mainBlack));
                simplePagerTitleView.setSelectedColor(ContextCompat.getColor(NestedScrollActivity.this, R.color.mainBlack));
                simplePagerTitleView.setTextSize(16);
                simplePagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mViewPager.setCurrentItem(index, false);
                    }
                });
                return simplePagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                LinePagerIndicator indicator = new LinePagerIndicator(context);
                indicator.setMode(LinePagerIndicator.MODE_EXACTLY);
                indicator.setLineHeight(UIUtil.dip2px(context, 2));
                indicator.setLineWidth(UIUtil.dip2px(context, 20));
                indicator.setRoundRadius(UIUtil.dip2px(context, 3));
                indicator.setStartInterpolator(new AccelerateInterpolator());
                indicator.setEndInterpolator(new DecelerateInterpolator(2.0f));
                indicator.setColors(ContextCompat.getColor(NestedScrollActivity.this, R.color.mainRed));
                return indicator;
            }
        });
        mMagicIndicatorTitle.setNavigator(commonNavigator);
        ViewPagerHelper.bind(mMagicIndicatorTitle, mViewPager);

    }

   /* private void initViewPager() {
        List<String> titles = new ArrayList<>();
        titles.add("精选");
        titles.add("最新");
        titles.add("热门");
        for (int i = 0; i < titles.size(); i++) {
            mTabLayout.addTab(mTabLayout.newTab().setText(titles.get(i)));
        }
        List<Fragment> fragments = new ArrayList<>();
        for (int i = 0; i < titles.size(); i++) {
            fragments.add(new TabLayoutFargment());
        }
        *//*fragments.add(new TabLayoutFargment());
        fragments.add(new TabLayoutFargment());
        fragments.add(new TabLayoutFargment());*//*

        FragmentAdapter mFragmentAdapter = new FragmentAdapter(getSupportFragmentManager(), fragments, titles);
        //给ViewPager设置适配器
        mViewPager.setAdapter(mFragmentAdapter);
        //将TabLayout和ViewPager关联起来
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
        //给TabLayout设置适配器
        // mTab.setTabsFromPagerAdapter(mFragmentAdapter);
    }*/


}

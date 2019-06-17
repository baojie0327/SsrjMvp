package com.jackson.ssrjmvp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Gravity;
import android.widget.FrameLayout;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.ashokvarma.bottomnavigation.ShapeBadgeItem;
import com.ashokvarma.bottomnavigation.TextBadgeItem;
import com.jackson.ssrjmvp.utils.SharedPreferencesUtil;
import com.jackson.ssrjmvp.view.activity.BaseActivity;
import com.jackson.ssrjmvp.view.activity.LoginActivity;
import com.jackson.ssrjmvp.view.fragment.HomeFragment;
import com.jackson.ssrjmvp.view.fragment.HotShowFragment;
import com.jackson.ssrjmvp.view.fragment.MineFragment;
import com.jackson.ssrjmvp.view.fragment.TopicFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements BottomNavigationBar.OnTabSelectedListener {


    @BindView(R.id.ll_content)
    FrameLayout mLlContent;
    @BindView(R.id.bottom_navigation_bar)
    BottomNavigationBar mBottomNavigationBar;

    private int lastSelectedPosition;

    private TextBadgeItem mTextBadgeItem;

    private HomeFragment mHomeFragment;  // 首页
    private HotShowFragment mHotShowFragment;
    private TopicFragment mTopicFragment;
    private MineFragment mMineFragment;

    // Fragment管理器，和执行器
    private FragmentManager mManager;
    private FragmentTransaction mTransaction;

    private int messCount;
    private SharedPreferencesUtil mSharedPreferencesUtil;
    private boolean isLogin = false;//标记是否登录


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
        initData();
        setDefaultFragment();
    }

    /**
     * 初始化界面，初始化底部导航
     */
    private void initView() {
        // TODO 设置模式
        mBottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);
        // TODO 设置背景色样式
        mBottomNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);
        mBottomNavigationBar.setBarBackgroundColor(R.color.background_gray_color);

        mTextBadgeItem = new TextBadgeItem()
                .setBorderWidth(4)
                .setBackgroundColorResource(R.color.main_color)
                .setText("5")
                .setTextColorResource(R.color.white)
                .setBorderColorResource(R.color.colorPrimaryDark)  //外边界颜色
                .setHideOnSelect(false);

        ShapeBadgeItem mShapeBadgeItem = new ShapeBadgeItem()
                .setShape(ShapeBadgeItem.SHAPE_OVAL)
                .setShapeColor(R.color.main_color)
                .setShapeColorResource(R.color.main_color)
                .setSizeInDp(this, 10, 10)
                .setEdgeMarginInDp(this, 2)
//                .setSizeInPixels(30,30)
//                .setEdgeMarginInPixels(-1)
                .setGravity(Gravity.TOP | Gravity.END)
                .setHideOnSelect(false);

        /**
         *  new BottomNavigationItem(R.mipmap.tab_home_pressed,"首页") ,选中的图标，文字
         *  setActiveColorResource：选中的颜色
         *  setInactiveIconResource：未选中的图标
         *  setInActiveColorResource：未选中的颜色
         */
        mBottomNavigationBar
                .addItem(new BottomNavigationItem(R.mipmap.tab_home_pressed, "首页").setActiveColorResource(R.color.main_color).setInactiveIconResource(R.mipmap.tab_home_normal).setInActiveColorResource(R.color.icon_color))
                .addItem(new BottomNavigationItem(R.mipmap.tab_nearby, "热映").setActiveColorResource(R.color.main_color).setInactiveIconResource(R.mipmap.tab_nearby_off).setInActiveColorResource(R.color.icon_color))
                .addItem(new BottomNavigationItem(R.mipmap.tab_benefits_check, "资讯").setActiveColorResource(R.color.main_color).setInactiveIconResource(R.mipmap.tab_benefits_check_no).setInActiveColorResource(R.color.icon_color).setBadgeItem(mShapeBadgeItem))
                .addItem(new BottomNavigationItem(R.mipmap.tab_mine, "我的").setActiveColorResource(R.color.main_color).setInactiveIconResource(R.mipmap.tab_mine_off).setInActiveColorResource(R.color.icon_color).setBadgeItem(mTextBadgeItem))
                .setFirstSelectedPosition(lastSelectedPosition)
                .initialise();
       // mShapeBadgeItem.hide();

        mBottomNavigationBar.setTabSelectedListener(this);
    }

    /**
     * 初始化数据
     */
    private void initData(){
        mSharedPreferencesUtil=SharedPreferencesUtil.getInstance(this);
    }


    private void setDefaultFragment() {
        mHomeFragment=new HomeFragment();
        mManager = getSupportFragmentManager();
        mTransaction = mManager.beginTransaction();
        mTransaction.add(R.id.ll_content, mHomeFragment);
        mTransaction.commit();
    }

    @Override
    public void onTabSelected(int position) {
        lastSelectedPosition = position;
        //开启事务
        mTransaction = mManager.beginTransaction();
        hideFragment(mTransaction);

        /**
         * fragment 用 add + show + hide 方式
         * 只有第一次切换会创建fragment，再次切换不创建
         *
         * fragment 用 replace 方式
         * 每次切换都会重新创建
         *
         */
        switch (position){
            case 0:   // 首页
                if (mHomeFragment == null) {
                    mHomeFragment = HomeFragment.newInstance();
                    mTransaction.add(R.id.ll_content,
                            mHomeFragment);
                } else {
                    mTransaction.show(mHomeFragment);
                }
                break;
            case 1:    // 热映
                if (mHotShowFragment == null) {
                    mHotShowFragment =  HotShowFragment.newInstance();
                    mTransaction.add(R.id.ll_content,
                            mHotShowFragment);
                } else {
                    mTransaction.show(mHotShowFragment);
                }
                break;
            case 2:  // 资讯
                if (mTopicFragment == null) {
                    mTopicFragment = TopicFragment.newInstance();
                    mTransaction.add(R.id.ll_content,
                            mTopicFragment);
                } else {
                    mTransaction.show(mTopicFragment);
                }
                break;
            case 3:  // 我的
                isLogin=mSharedPreferencesUtil.getBoolean(SharedPreferencesUtil.LOGIN_STATUS, false);
                if (isLogin==false){
                    Intent intent=new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(intent);
                }else {
                    if (mMineFragment == null) {
                        mMineFragment = MineFragment.newInstance();
                        mTransaction.add(R.id.ll_content,
                                mMineFragment);
                    } else {
                        mTransaction.show(mMineFragment);
                    }
                }

                break;
        }
        // 事务提交
        mTransaction.commit();
      //  mTransaction.commitAllowingStateLoss();
    }

    @Override
    public void onTabUnselected(int position) {

    }

    @Override
    public void onTabReselected(int position) {

    }

    /**
     * 隐藏当前fragment
     * @param transaction
     */
    private void hideFragment(FragmentTransaction transaction){
        if (mHomeFragment != null){
            transaction.hide(mHomeFragment);
        }

        if (mHotShowFragment != null){
            transaction.hide(mHotShowFragment);
        }
        if (mTopicFragment != null){
            transaction.hide(mTopicFragment);
        }
        if (mMineFragment != null){
            transaction.hide(mMineFragment);
        }
    }

    public void addMessage(){
        messCount ++ ;
        mTextBadgeItem.setText(messCount + "");
        mTextBadgeItem.show();
    }
}

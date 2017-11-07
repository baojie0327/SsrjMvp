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
import com.jackson.ssrjmvp.view.fragment.DisCountFragment;
import com.jackson.ssrjmvp.view.fragment.MineFragment;
import com.jackson.ssrjmvp.view.fragment.NearbyFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements BottomNavigationBar.OnTabSelectedListener {


    @BindView(R.id.ll_content)
    FrameLayout mLlContent;
    @BindView(R.id.bottom_navigation_bar)
    BottomNavigationBar mBottomNavigationBar;

    private int lastSelectedPosition;

    private ShapeBadgeItem mShapeBadgeItem;
    private TextBadgeItem mTextBadgeItem;

    private NearbyFragment mNearbyFragment;
    private DisCountFragment mDisCountFragment;
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

        mShapeBadgeItem = new ShapeBadgeItem()
                .setShape(ShapeBadgeItem.SHAPE_OVAL)
                .setShapeColor(R.color.main_color)
                .setShapeColorResource(R.color.main_color)
                .setSizeInDp(this,10,10)
                .setEdgeMarginInDp(this,2)
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
        mNearbyFragment=new NearbyFragment();
        mManager = getSupportFragmentManager();
        mTransaction = mManager.beginTransaction();
        mTransaction.add(R.id.ll_content, mNearbyFragment);
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
            case 0:
                if (mNearbyFragment == null) {
                    mNearbyFragment = new NearbyFragment();
                    mTransaction.add(R.id.ll_content,
                            mNearbyFragment);
                } else {
                    mTransaction.show(mNearbyFragment);
                }
                break;
            case 1:
                if (mDisCountFragment == null) {
                    mDisCountFragment = new DisCountFragment();
                    mTransaction.add(R.id.ll_content,
                            mDisCountFragment);
                } else {
                    mTransaction.show(mDisCountFragment);
                }
                break;
            case 2:
                isLogin=mSharedPreferencesUtil.getBoolean(SharedPreferencesUtil.LOGIN_STATUS, false);
                if (isLogin==false){
                    Intent intent=new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(intent);
                }else {
                    if (mMineFragment == null) {
                        mMineFragment = new MineFragment();
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
        if (mNearbyFragment != null){
            transaction.hide(mNearbyFragment);
        }
        if (mDisCountFragment != null){
            transaction.hide(mDisCountFragment);
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

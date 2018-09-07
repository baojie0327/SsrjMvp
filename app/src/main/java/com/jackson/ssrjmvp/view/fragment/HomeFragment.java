package com.jackson.ssrjmvp.view.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.ColumnLayoutHelper;
import com.alibaba.android.vlayout.layout.FixLayoutHelper;
import com.alibaba.android.vlayout.layout.FloatLayoutHelper;
import com.alibaba.android.vlayout.layout.GridLayoutHelper;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.alibaba.android.vlayout.layout.OnePlusNLayoutHelper;
import com.alibaba.android.vlayout.layout.ScrollFixLayoutHelper;
import com.alibaba.android.vlayout.layout.SingleLayoutHelper;
import com.alibaba.android.vlayout.layout.StaggeredGridLayoutHelper;
import com.alibaba.android.vlayout.layout.StickyLayoutHelper;
import com.jackson.ssrjmvp.R;
import com.jackson.ssrjmvp.adapter.BaseDelegateAdapter;
import com.jackson.ssrjmvp.adapter.home.BannerAdapter;
import com.jackson.ssrjmvp.adapter.home.CareChoiceAdapter;
import com.jackson.ssrjmvp.adapter.home.ColumnAdapter;
import com.jackson.ssrjmvp.adapter.home.EverPrepareAdapter;
import com.jackson.ssrjmvp.adapter.home.FixAdapter;
import com.jackson.ssrjmvp.adapter.home.FloatAdapter;
import com.jackson.ssrjmvp.adapter.home.GridMenuAdapter;
import com.jackson.ssrjmvp.adapter.home.HotItemAdapter;
import com.jackson.ssrjmvp.adapter.home.MarqueeAdapter;
import com.jackson.ssrjmvp.adapter.home.OnePlusNAdapter;
import com.jackson.ssrjmvp.adapter.home.ScrollFixAdapter;
import com.jackson.ssrjmvp.adapter.home.StaggeredAdapter;
import com.jackson.ssrjmvp.adapter.home.StickyAdapter;
import com.jackson.ssrjmvp.bean.HomeBean;
import com.jackson.ssrjmvp.dagger.component.DaggerHomeComponent;
import com.jackson.ssrjmvp.dagger.module.HomeModule;
import com.jackson.ssrjmvp.presenter.HomePresenter;
import com.jackson.ssrjmvp.utils.CommonMethod;
import com.jackson.ssrjmvp.utils.Constant;
import com.jackson.ssrjmvp.utils.LogUtil;
import com.jackson.ssrjmvp.view.IView;
import com.jackson.ssrjmvp.view.activity.NestedScrollActivity;
import com.jackson.ssrjmvp.view.activity.flexbox.FlexBoxLayoutActivity;
import com.jackson.ssrjmvp.view.activity.immersive.ImmersiveActivity;
import com.jackson.ssrjmvp.view.activity.supertext.SuperTextViewActivity;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

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
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;
    @BindView(recyclerView)
    RecyclerView mRecyclerView;
    Unbinder unbinder;
    private View view;


    @Inject
    HomePresenter mHomePresenter;

    private BannerAdapter mBannerAdapter;  // Banner适配器
    private GridMenuAdapter mGridMenuAdapter;  // 功能菜单适配器
    private MarqueeAdapter mMarqueeAdapter; // 公告适配器
    private HotItemAdapter mHotItemAdapter;  // 七月爆品
    private EverPrepareAdapter mEverPrepareAdapter; // 常备好药
    private CareChoiceAdapter mCareChoiceAdapter; // 超值精选
    private FixAdapter mFixAdapter; // 固定布局
    private ScrollFixAdapter mScrollFixAdapter; //可选固定布局
    private FloatAdapter mFloatAdapter;  // 浮动布局
    private ColumnAdapter mColumnAdapter;  // 栏格布局
    private OnePlusNAdapter mOnePlusNAdapter; // 1拖N布局
    private StickyAdapter mStickyAdapter;  // Sticky布局
    private StaggeredAdapter mStaggeredAdapter; // 瀑布流布局

    private List<HomeBean.DataBean> dataList;  // 数据源，头布局
    private List<DelegateAdapter.Adapter> mAdapters;
    private DelegateAdapter delegateAdapter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_home, null);
            unbinder = ButterKnife.bind(this, view);
            inject();
            initView();
            initData();
            mHomePresenter.getData(0);
            refreshListen();  // 刷新，加载监听

        }

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
        dataList = new ArrayList<>();
        mAdapters = new LinkedList<>();

        // 刷新设置
        mRefreshLayout.autoRefresh();  // 开启自动刷新
        //  mRefreshLayout.setEnableAutoLoadMore(true); // 开启自动加载功能
        // mRefreshLayout.setEnableAutoLoadMore(false);//使上拉加载具有弹性效果
        //  mRefreshLayout.setEnableOverScrollDrag(false);//禁止越界拖动（1.0.4以上版本）
        // mRefreshLayout.setEnableOverScrollBounce(false);//关闭越界回弹功能

        // 设置官方刷新主题
        mRefreshLayout.setRefreshHeader(new MaterialHeader(getActivity()));
        mRefreshLayout.setEnableHeaderTranslationContent(false);
    }

    /**
     * 初始化数据
     * 设置RecyclerView
     */
    private void initData() {
        //初始化
        //创建VirtualLayoutManager对象
        VirtualLayoutManager layoutManager = new VirtualLayoutManager(getActivity());
        layoutManager.setRecycleOffset(5000);
        // 将VirtualLayoutManager绑定到recyclerView
        mRecyclerView.setLayoutManager(layoutManager);
        // 设置分割线
       /* RecyclerView.ItemDecoration itemDecoration = new RecyclerView.ItemDecoration() {
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                int position = ((VirtualLayoutManager.LayoutParams) view.getLayoutParams()).getViewPosition();
                outRect.set(4, 4, 4, 4);
            }
        };*/

        //设置组件复用回收池,（如果一屏内相同类型的 View 个数比较多，需要设置一个合适的大小，防止来回滚动时重新创建 View）
        RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
        mRecyclerView.setRecycledViewPool(viewPool);
        //  mRecyclerView.addItemDecoration(itemDecoration);
        viewPool.setMaxRecycledViews(0, 20);

        delegateAdapter = new DelegateAdapter(layoutManager, true);
        mRecyclerView.setAdapter(delegateAdapter);
    }


    /**
     * 设置数据
     * @param
     */
    @Override
    public void setData(List<HomeBean.DataBean> mDataList) {
        LogUtil.d("size==" + mDataList.size());
        if (dataList.size() > 0) {
            dataList.clear();
        }
        this.dataList = mDataList;
        setBannerData(dataList.get(0).getItems());
        setGridMenu(dataList.get(1).getItems());
        setScrollFix();
        setMarquee(dataList.get(2).getItems());
        setHotItem(dataList.get(3).getItems());  // grid
        setPrepare(dataList.get(4).getItems());  // 横向滑动
        setSticky();                                // 设置Sticky
        setCareChoice(dataList.get(5).getItems());  // linear
        setFixData();
        setFloat();
        setColumn(dataList.get(4).getItems());   // 栅格
        setOnePlusN(dataList.get(6).getItems());  // 1拖N
        setStaggered(dataList.get(7).getItems()); // 瀑布流
        setAllData();
    }

    /**
     * 刷新加载监听
     */
    private void refreshListen() {
        // refresh
        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                mRefreshLayout.getLayout().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mHomePresenter.getData(1);
                    }
                }, 2000);
            }
        });

        // loadMore
        mRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                mRefreshLayout.getLayout().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mRefreshLayout.finishLoadMoreWithNoMoreData();  //将不会再次触发加载更多事件
                        mRefreshLayout.finishLoadMore();

                    }
                }, 2000);
            }
        });

    }

    /**
     * 刷新
     * @param mDataList
     */
    @Override
    public void onRefresh(List<HomeBean.DataBean> mDataList) {
        if (mAdapters.size() > 0) {
            mAdapters.clear();
        }
        setData(mDataList);
        mRecyclerView.getAdapter().notifyDataSetChanged();
        mRefreshLayout.finishRefresh();
        mRefreshLayout.setNoMoreData(false);
    }


    /**
     * 设置轮播
     * @param items
     */
    private void setBannerData(List<HomeBean.DataBean.ItemsBean> items) {
        SingleLayoutHelper singleLayoutHelper = new SingleLayoutHelper();
        final List<String> imgList = new ArrayList<>();
        for (int i = 0; i < items.size(); i++) {
            imgList.add("http:" + items.get(i).getImg());
        }
        mBannerAdapter = new BannerAdapter(getActivity(), imgList, singleLayoutHelper, R.layout.item_home_banner_layout, 1, Constant.viewType.typeBanner);
        mBannerAdapter.setOnItemClickListener(new BaseDelegateAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(getActivity(), "click--" + position, Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * 设置功能按钮
     *
     * @param items
     */
    private void setGridMenu(List<HomeBean.DataBean.ItemsBean> items) {
        GridLayoutHelper gridLayoutHelper = new GridLayoutHelper(5);
        //  gridLayoutHelper.setMarginTop(300);  //设置距离顶部的距离
        //  gridLayoutHelper.setItemCount(30);  // 设置布局里Item个数
        gridLayoutHelper.setPadding(0, 0, 0, 0); // 设置LayoutHelper的子元素相对LayoutHelper边缘的距离
        // gridLayoutHelper.setMargin(20,10,20,10);   //设置LayoutHelper边缘相对父控件（即RecyclerView）的距离
        //  gridLayoutHelper.setAspectRatio(6); // 设置设置布局内每行布局的宽与高的比
        //  gridLayoutHelper.setWeights(new float[]{40, 10, 20,20,10});//设置每行中 每个网格宽度 占 每行总宽度 的比例
        gridLayoutHelper.setVGap(16);  // 控制子元素之间的垂直间距
        gridLayoutHelper.setHGap(0);  // 控制子元素之间的水平间距
        gridLayoutHelper.setAutoExpand(false);//是否自动填充空白区域
        //   gridLayoutHelper.setSpanCount(3);// 设置每行多少个网格
        gridLayoutHelper.setBgColor(Color.WHITE); // 设置背景颜色
        // 通过自定义SpanSizeLookup来控制某个Item的占网格个数
       /* gridLayoutHelper.setSpanSizeLookup(new GridLayoutHelper.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (position < 3) {
                    return 5;
                } else {
                    return 1;
                }

            }
        });*/
        mGridMenuAdapter = new GridMenuAdapter(getActivity(), items, gridLayoutHelper, R.layout.item_home_gridmenu_layout, items.size(), Constant.viewType.typeGvidMenu);
        // 监听
        mGridMenuAdapter.setOnItemClickListener(new BaseDelegateAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(getActivity(), "click--" + position, Toast.LENGTH_SHORT).show();
                switch (position){
                    case 0:  // SuperTextView使用
                        startActivity(new Intent(getActivity(), SuperTextViewActivity.class));
                        break;
                    case 1: // flexbox-layout
                      startActivity(new Intent(getActivity(), FlexBoxLayoutActivity.class));
                        break;
                    case 2:  // nestedscrollview
                        startActivity(new Intent(getActivity(), NestedScrollActivity.class));
                        break;
                    case 3: // 沉浸式
                        startActivity(new Intent(getActivity(), ImmersiveActivity.class));
                        break;
                }
            }

        });

      /*  mGridMenuAdapter.setOnItemChildClickListener(new BaseDelegateAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(View view, int position) {
                switch (view.getId()) {
                    case R.id.img_grid_menu:
                        Toast.makeText(getActivity(), "click-image-" + position, Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.tv_grid_menu:
                        Toast.makeText(getActivity(), "click-tv-" + position, Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });*/
    }

    /**
     * 设置公告
     */
    private void setMarquee(List<HomeBean.DataBean.ItemsBean> items) {
        LinearLayoutHelper linearLayoutHelper = new LinearLayoutHelper();
        List<String> infoList = new ArrayList<>();
        for (int i = 0; i < items.size(); i++) {
            infoList.add(items.get(i).getTitle());
        }
        mMarqueeAdapter = new MarqueeAdapter(getActivity(), infoList, linearLayoutHelper, R.layout.item_home_marquee_layout, 1, Constant.viewType.typeMarquee);
        mMarqueeAdapter.setOnItemClickListener(new BaseDelegateAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(getActivity(), "click--" + position, Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * 设置七月爆品
     */
    private void setHotItem(List<HomeBean.DataBean.ItemsBean> items) {
        GridLayoutHelper gridLayoutHelper = new GridLayoutHelper(4);
        // gridLayoutHelper.setPadding(0, 30, 0, 30); // 设置LayoutHelper的子元素相对LayoutHelper边缘的距离
        //    gridLayoutHelper.setVGap(10);  // 控制子元素之间的垂直间距
        //  gridLayoutHelper.setHGap(10);  // 控制子元素之间的水平间距
        //  gridLayoutHelper.setMargin(0,20,0,20);
        gridLayoutHelper.setAutoExpand(false);//是否自动填充空白区域
        gridLayoutHelper.setBgColor(Color.WHITE); // 设置背景颜色

        // 通过自定义SpanSizeLookup来控制某个Item的占网格个数
        final int count = mBannerAdapter.getItemCount() + mGridMenuAdapter.getItemCount() + mMarqueeAdapter.getItemCount() + mScrollFixAdapter.getItemCount();
        gridLayoutHelper.setSpanSizeLookup(new GridLayoutHelper.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (position <= count + 1) {
                    return 2;
                } else {
                    return 1;
                }


            }
        });
        mHotItemAdapter = new HotItemAdapter(getActivity(), items, gridLayoutHelper, R.layout.item_home_hotitem_layout, items.size(), Constant.viewType.typeHot);
        // 监听
        mHotItemAdapter.setOnItemClickListener(new BaseDelegateAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(getActivity(), "click--" + position, Toast.LENGTH_SHORT).show();
            }

        });
    }

    /**
     * 设置常备好药
     *
     * @param items
     */
    private void setPrepare(List<HomeBean.DataBean.ItemsBean> items) {
        SingleLayoutHelper singleLayoutHelper = new SingleLayoutHelper();
        mEverPrepareAdapter = new EverPrepareAdapter(getActivity(), items, singleLayoutHelper, R.layout.item_home_everprepare_layout, 1, Constant.viewType.typePrepare);
        // 监听
        mEverPrepareAdapter.setOnItemClickListener(new BaseDelegateAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(getActivity(), "click--" + position, Toast.LENGTH_SHORT).show();
            }

        });

      /*  mEverPrepareAdapter.setOnItemChildClickListener(new BaseDelegateAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(View view, int position) {
                switch (view.getId()) {
                    case R.id.img_prepare_menu:
                        Toast.makeText(getActivity(), "click-image-" + position, Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.tv_prepare_menu:
                        Toast.makeText(getActivity(), "click-tv-" + position, Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });*/
    }

    /**
     * 设置Sticky布局，设置吸边布局
     */
    private void setSticky() {
        StickyLayoutHelper stickyLayoutHelper = new StickyLayoutHelper();
        // 公共属性
        stickyLayoutHelper.setItemCount(1);// 设置布局里Item个数
        //  stickyLayoutHelper.setPadding(20, 20, 20, 20);// 设置LayoutHelper的子元素相对LayoutHelper边缘的距离
        //    stickyLayoutHelper.setMargin(20, 20, 20, 20);// 设置LayoutHelper边缘相对父控件（即RecyclerView）的距离
        //   stickyLayoutHelper.setBgColor(Color.GRAY);// 设置背景颜色
        // stickyLayoutHelper.setAspectRatio(2);// 设置设置布局内每行布局的宽与高的比

        // 特有属性
        stickyLayoutHelper.setStickyStart(true);
        // true = 组件吸在顶部
        // false = 组件吸在底部

        stickyLayoutHelper.setOffset(100);// 设置吸边位置的偏移量
        List<String> list = new ArrayList<>();
        mStickyAdapter = new StickyAdapter(getActivity(), list, stickyLayoutHelper, R.layout.item_home_sticky_layout, 1, Constant.viewType.typeSticky);

    }

    /**
     * 设置超值精选
     *
     * @param items
     */
    private void setCareChoice(List<HomeBean.DataBean.ItemsBean> items) {
        LinearLayoutHelper linearLayoutHelper = new LinearLayoutHelper();
        linearLayoutHelper.setAspectRatio(4.0f);
        linearLayoutHelper.setDividerHeight(5);
        linearLayoutHelper.setMargin(0, 0, 0, 0);
        linearLayoutHelper.setPadding(0, 0, 0, 10);
        mCareChoiceAdapter = new CareChoiceAdapter(getActivity(), items, linearLayoutHelper, R.layout.item__home_choice_layout, 5, Constant.viewType.typeChoice);
        // 监听
        mCareChoiceAdapter.setOnItemClickListener(new BaseDelegateAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(getActivity(), "click--" + position, Toast.LENGTH_SHORT).show();
            }

        });

        mCareChoiceAdapter.setOnItemChildClickListener(new BaseDelegateAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(View view, int position) {
                switch (view.getId()) {
                    case R.id.img_choice_menu:
                        Toast.makeText(getActivity(), "click-image-" + position, Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.tv_choice_name:
                        Toast.makeText(getActivity(), "click-tv-" + position, Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
    }

    /**
     * 设置固定布局
     */
    private void setFixData() {
        FixLayoutHelper fixLayoutHelper = new FixLayoutHelper(FixLayoutHelper.TOP_LEFT, 80, 60);
        fixLayoutHelper.setItemCount(1);// 设置布局里Item个数
        fixLayoutHelper.setPadding(20, 20, 20, 20);// 设置LayoutHelper的子元素相对LayoutHelper边缘的距离
        fixLayoutHelper.setMargin(20, 20, 20, 20);// 设置LayoutHelper边缘相对父控件（即RecyclerView）的距离
        fixLayoutHelper.setBgColor(Color.GRAY);// 设置背景颜色
        //  fixLayoutHelper.setAspectRatio(6);// 设置设置布局内每行布局的宽与高的比
        // fixLayoutHelper特有属性
        fixLayoutHelper.setAlignType(FixLayoutHelper.TOP_LEFT);// 设置吸边时的基准位置(alignType)
        fixLayoutHelper.setX(10);// 设置基准位置的横向偏移量X
        fixLayoutHelper.setY(10);// 设置基准位置的纵向偏移量Y
        List<String> list = new ArrayList<>();
        mFixAdapter = new FixAdapter(getActivity(), list, fixLayoutHelper, R.layout.item_home_fix_layout, 1, Constant.viewType.typeFix);
        mFixAdapter.setOnItemClickListener(new BaseDelegateAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                CommonMethod.showToast(getActivity(), "click--" + position, false);
            }
        });
    }

    /**
     * 设置可选固定b布局
     */
    private void setScrollFix() {
        ScrollFixLayoutHelper scrollFixLayoutHelper = new ScrollFixLayoutHelper(ScrollFixLayoutHelper.TOP_RIGHT, 80, 60);
        // 公共属性
        scrollFixLayoutHelper.setItemCount(1);// 设置布局里Item个数
        scrollFixLayoutHelper.setPadding(20, 20, 20, 20);// 设置LayoutHelper的子元素相对LayoutHelper边缘的距离
        scrollFixLayoutHelper.setMargin(20, 20, 20, 20);// 设置LayoutHelper边缘相对父控件（即RecyclerView）的距离
        scrollFixLayoutHelper.setBgColor(Color.GRAY);// 设置背景颜色
        //  scrollFixLayoutHelper.setAspectRatio(6);// 设置设置布局内每行布局的宽与高的比

        // fixLayoutHelper特有属性
        scrollFixLayoutHelper.setAlignType(FixLayoutHelper.TOP_RIGHT);// 设置吸边时的基准位置(alignType)
        scrollFixLayoutHelper.setX(10);// 设置基准位置的横向偏移量X
        scrollFixLayoutHelper.setY(10);// 设置基准位置的纵向偏移量Y
        scrollFixLayoutHelper.setShowType(ScrollFixLayoutHelper.SHOW_ON_LEAVE);// 设置Item的显示模式

        List<String> list = new ArrayList<>();
        mScrollFixAdapter = new ScrollFixAdapter(getActivity(), list, scrollFixLayoutHelper, R.layout.item_home_fix_layout, 1, Constant.viewType.typeScrollFix);
        mScrollFixAdapter.setOnItemClickListener(new BaseDelegateAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                CommonMethod.showToast(getActivity(), "click--" + position, false);
            }
        });
    }

    /**
     * 浮动布局
     */
    private void setFloat() {
        FloatLayoutHelper floatLayoutHelper = new FloatLayoutHelper();
       /* floatLayoutHelper.setItemCount(1);// 设置布局里Item个数
        floatLayoutHelper.setPadding(20, 20, 20, 20);// 设置LayoutHelper的子元素相对LayoutHelper边缘的距离
        floatLayoutHelper.setMargin(20, 20, 20, 20);// 设置LayoutHelper边缘相对父控件（即RecyclerView）的距离
        floatLayoutHelper.setBgColor(Color.RED);// 设置背景颜色*/
        //  floatLayoutHelper.setAspectRatio(6);// 设置设置布局内每行布局的宽与高的比

        // floatLayoutHelper特有属性
        floatLayoutHelper.setDefaultLocation(300, 300);// 设置布局里Item的初始位置

        List<String> list = new ArrayList<>();
        mFloatAdapter = new FloatAdapter(getActivity(), list, floatLayoutHelper, R.layout.item_home_float_layout, 1, Constant.viewType.typeFloat);
        mFloatAdapter.setOnItemClickListener(new BaseDelegateAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                CommonMethod.showToast(getActivity(), "click--" + position, false);
            }
        });
    }

    /**
     * 设置栏格布局
     */
    private void setColumn(List<HomeBean.DataBean.ItemsBean> items) {
        ColumnLayoutHelper columnLayoutHelper = new ColumnLayoutHelper();
        // 公共属性
        columnLayoutHelper.setItemCount(3);// 设置布局里Item个数
        //  columnLayoutHelper.setPadding(0, 0, 0, 10);// 设置LayoutHelper的子元素相对LayoutHelper边缘的距离
        columnLayoutHelper.setMargin(0, 0, 0, 10);// 设置LayoutHelper边缘相对父控件（即RecyclerView）的距离
        //  columnLayoutHelper.setBgColor(Color.GRAY);// 设置背景颜色
        columnLayoutHelper.setAspectRatio((float) 2.8);// 设置设置布局内每行布局的宽与高的比
        columnLayoutHelper.setPaddingBottom(10);
        // columnLayoutHelper特有属性
        columnLayoutHelper.setWeights(new float[]{40, Float.NaN, 40});// 设置该行每个Item占该行总宽度的比例
        mColumnAdapter = new ColumnAdapter(getActivity(), items, columnLayoutHelper, R.layout.item_home_column_layout, 3, Constant.viewType.typeColumn);
        mColumnAdapter.setOnItemClickListener(new BaseDelegateAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                CommonMethod.showToast(getActivity(), "click--" + position, false);
            }
        });
    }

    /**
     * 设置1拖N
     *
     * @param items
     */
    private void setOnePlusN(List<HomeBean.DataBean.ItemsBean> items) {
        OnePlusNLayoutHelper onePlusNLayoutHelper = new OnePlusNLayoutHelper(3);
        // 在构造函数里传入显示的Item数
        // 最多是1拖4,即5个
        // 公共属性
        onePlusNLayoutHelper.setItemCount(3);// 设置布局里Item个数
        //  onePlusNLayoutHelper.setPadding(20, 20, 20, 20);// 设置LayoutHelper的子元素相对LayoutHelper边缘的距离
        //  onePlusNLayoutHelper.setMargin(20, 20, 20, 20);// 设置LayoutHelper边缘相对父控件（即RecyclerView）的距离
        //  onePlusNLayoutHelper.setBgColor(Color.GRAY);// 设置背景颜色
        // onePlusNLayoutHelper.setColWeights(new float[]{40f, 50f, 50f});
        onePlusNLayoutHelper.setAspectRatio(2);// 设置设置布局内每行布局的宽与高的比

        mOnePlusNAdapter = new OnePlusNAdapter(getActivity(), items, onePlusNLayoutHelper, R.layout.item_home_oneplusn_layout, 3, Constant.viewType.typeOnePlusN);
        mOnePlusNAdapter.setOnItemClickListener(new BaseDelegateAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                CommonMethod.showToast(getActivity(), "click--" + position, false);
            }
        });
    }

    /**
     * 设置瀑布流布局
     *
     * @param items
     */
    private void setStaggered(List<HomeBean.DataBean.ItemsBean> items) {
        StaggeredGridLayoutHelper staggeredGridLayoutHelper = new StaggeredGridLayoutHelper(2);
        // 公有属性
        staggeredGridLayoutHelper.setItemCount(10);// 设置布局里Item个数
        //  staggeredGridLayoutHelper.setPadding(20, 20, 20, 20);// 设置LayoutHelper的子元素相对LayoutHelper边缘的距离
        //    staggeredGridLayoutHelper.setMargin(20, 20, 20, 20);// 设置LayoutHelper边缘相对父控件（即RecyclerView）的距离
        //   staggeredGridLayoutHelper.setBgColor(Color.GRAY);// 设置背景颜色
        //   staggeredGridLayoutHelper.setAspectRatio(1.0f);// 设置设置布局内每行布局的宽与高的比

        // 特有属性
        staggeredGridLayoutHelper.setLane(2);// 设置控制瀑布流每行的Item数
        //  staggeredGridLayoutHelper.setHGap(10);// 设置子元素之间的水平间距
        //  staggeredGridLayoutHelper.setVGap(15);// 设置子元素之间的垂直间距
        mStaggeredAdapter = new StaggeredAdapter(getActivity(), items, staggeredGridLayoutHelper, R.layout.item_home_staggered_layout, items.size(), Constant.viewType.typeStaggered);
        mStaggeredAdapter.setOnItemClickListener(new BaseDelegateAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                CommonMethod.showToast(getActivity(), "click--" + position, false);
            }
        });
    }


    /**
     * 设置所有数据
     */
    private void setAllData() {
        // 添加Banner
        mAdapters.add(mBannerAdapter);
        mAdapters.add(mGridMenuAdapter);
        mAdapters.add(mScrollFixAdapter);
        mAdapters.add(mMarqueeAdapter);
        mAdapters.add(mHotItemAdapter);
        mAdapters.add(mEverPrepareAdapter);
        mAdapters.add(mStickyAdapter);
        mAdapters.add(mCareChoiceAdapter);
        mAdapters.add(mFixAdapter);
        mAdapters.add(mFloatAdapter);
        mAdapters.add(mColumnAdapter);
        mAdapters.add(mOnePlusNAdapter);
        mAdapters.add(mStaggeredAdapter);
        delegateAdapter.setAdapters(mAdapters);


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

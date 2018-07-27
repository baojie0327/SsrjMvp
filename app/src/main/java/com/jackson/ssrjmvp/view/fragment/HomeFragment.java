package com.jackson.ssrjmvp.view.fragment;

import android.graphics.Color;
import android.os.Bundle;
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
import com.alibaba.android.vlayout.layout.FixLayoutHelper;
import com.alibaba.android.vlayout.layout.FloatLayoutHelper;
import com.alibaba.android.vlayout.layout.GridLayoutHelper;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.alibaba.android.vlayout.layout.ScrollFixLayoutHelper;
import com.alibaba.android.vlayout.layout.SingleLayoutHelper;
import com.jackson.ssrjmvp.R;
import com.jackson.ssrjmvp.adapter.BaseDelegateAdapter;
import com.jackson.ssrjmvp.adapter.BaseDelegateAdapter1;
import com.jackson.ssrjmvp.adapter.home.BannerAdapter;
import com.jackson.ssrjmvp.adapter.home.CareChoiceAdapter;
import com.jackson.ssrjmvp.adapter.home.EverPrepareAdapter;
import com.jackson.ssrjmvp.adapter.home.FixAdapter;
import com.jackson.ssrjmvp.adapter.home.FloatAdapter;
import com.jackson.ssrjmvp.adapter.home.GridMenuAdapter;
import com.jackson.ssrjmvp.adapter.home.HotItemAdapter;
import com.jackson.ssrjmvp.adapter.home.MarqueeAdapter;
import com.jackson.ssrjmvp.adapter.home.ScrollFixAdapter;
import com.jackson.ssrjmvp.bean.HomeBean;
import com.jackson.ssrjmvp.dagger.component.DaggerHomeComponent;
import com.jackson.ssrjmvp.dagger.module.HomeModule;
import com.jackson.ssrjmvp.presenter.HomePresenter;
import com.jackson.ssrjmvp.utils.CommonMethod;
import com.jackson.ssrjmvp.utils.Constant;
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

    private BannerAdapter mBannerAdapter;  // Banner适配器
    private GridMenuAdapter mGridMenuAdapter;  // 功能菜单适配器
    private MarqueeAdapter mMarqueeAdapter; // 公告适配器
    private HotItemAdapter mHotItemAdapter;  // 七月爆品
    private EverPrepareAdapter mEverPrepareAdapter; // 常备好药
    private CareChoiceAdapter mCareChoiceAdapter; // 超值精选
    private FixAdapter mFixAdapter; // 固定布局
    private ScrollFixAdapter mScrollFixAdapter; //可选固定布局
    private FloatAdapter mFloatAdapter;  // 浮动布局

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
        layoutManager.setRecycleOffset(300);
        // 将VirtualLayoutManager绑定到recyclerView
        mRecyclerView.setLayoutManager(layoutManager);

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

        delegateAdapter = new DelegateAdapter(layoutManager,true);
        mRecyclerView.setAdapter(delegateAdapter);
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
        setGridMenu(mDatatList.get(1).getItems());
        setScrollFix();
        setMarquee(mDatatList.get(2).getItems());
        setHotItem(mDatatList.get(3).getItems());
        setPrepare(mDatatList.get(4).getItems());
        setCareChoice(mDatatList.get(5).getItems());
        setFixData();
        setFloat();
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
        gridLayoutHelper.setPadding(0, 16, 0, 0); // 设置LayoutHelper的子元素相对LayoutHelper边缘的距离
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
        mGridMenuAdapter = new GridMenuAdapter(getActivity(), items, gridLayoutHelper, R.layout.item_home_gridmenu_layout, items.size(),Constant.viewType.typeGvidMenu);
        // 监听
        mGridMenuAdapter.setOnItemClickListener(new BaseDelegateAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(getActivity(), "click--" + position, Toast.LENGTH_SHORT).show();
            }

        });

        mGridMenuAdapter.setOnItemChildClickListener(new BaseDelegateAdapter.OnItemChildClickListener() {
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
        });
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
        mMarqueeAdapter = new MarqueeAdapter(getActivity(), infoList, linearLayoutHelper, R.layout.item_home_marquee_layout, 1,Constant.viewType.typeMarquee);
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
        final int count = mBannerAdapter.getItemCount() + mGridMenuAdapter.getItemCount() + mMarqueeAdapter.getItemCount()+mScrollFixAdapter.getItemCount();
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
        mHotItemAdapter = new HotItemAdapter(getActivity(), items, gridLayoutHelper, R.layout.item_home_hotitem_layout, items.size(),Constant.viewType.typeHot);
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
        mEverPrepareAdapter = new EverPrepareAdapter(getActivity(), items, singleLayoutHelper, R.layout.item_home_everprepare_layout, 1,Constant.viewType.typePrepare);
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
     * 设置超值精选
     * @param items
     */
    private void setCareChoice(List<HomeBean.DataBean.ItemsBean> items){
        LinearLayoutHelper linearLayoutHelper = new LinearLayoutHelper();
        linearLayoutHelper.setAspectRatio(4.0f);
        linearLayoutHelper.setDividerHeight(5);
        linearLayoutHelper.setMargin(0, 0, 0, 0);
        linearLayoutHelper.setPadding(0, 0, 0, 10);
        mCareChoiceAdapter=new CareChoiceAdapter(getActivity(),items,linearLayoutHelper,R.layout.item__home_choice_layout,5,Constant.viewType.typeChoice);
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
    private void setFixData(){
        FixLayoutHelper fixLayoutHelper = new FixLayoutHelper(FixLayoutHelper.TOP_LEFT,80,60);
        fixLayoutHelper.setItemCount(1);// 设置布局里Item个数
        fixLayoutHelper.setPadding(20, 20, 20, 20);// 设置LayoutHelper的子元素相对LayoutHelper边缘的距离
        fixLayoutHelper.setMargin(20, 20, 20, 20);// 设置LayoutHelper边缘相对父控件（即RecyclerView）的距离
        fixLayoutHelper.setBgColor(Color.GRAY);// 设置背景颜色
      //  fixLayoutHelper.setAspectRatio(6);// 设置设置布局内每行布局的宽与高的比

        // fixLayoutHelper特有属性
        fixLayoutHelper.setAlignType(FixLayoutHelper.TOP_LEFT);// 设置吸边时的基准位置(alignType)
        fixLayoutHelper.setX(10);// 设置基准位置的横向偏移量X
        fixLayoutHelper.setY(10);// 设置基准位置的纵向偏移量Y
        List<String> list=new ArrayList<>();
        mFixAdapter=new FixAdapter(getActivity(),list,fixLayoutHelper,R.layout.item_home_fix_layout,1,Constant.viewType.typeFix);
        mFixAdapter.setOnItemClickListener(new BaseDelegateAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                CommonMethod.showToast(getActivity(),"click--"+position,false);
            }
        });
    }

    /**
     * 设置可选固定b布局
     */
    private void setScrollFix(){
        ScrollFixLayoutHelper scrollFixLayoutHelper = new ScrollFixLayoutHelper(ScrollFixLayoutHelper.TOP_RIGHT,80,60);
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

        List<String> list=new ArrayList<>();
        mScrollFixAdapter=new ScrollFixAdapter(getActivity(),list,scrollFixLayoutHelper,R.layout.item_home_fix_layout,1,Constant.viewType.typeScrollFix);
        mScrollFixAdapter.setOnItemClickListener(new BaseDelegateAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                CommonMethod.showToast(getActivity(),"click--"+position,false);
            }
        });
    }

    private void setFloat(){
        FloatLayoutHelper floatLayoutHelper = new FloatLayoutHelper();
       /* floatLayoutHelper.setItemCount(1);// 设置布局里Item个数
        floatLayoutHelper.setPadding(20, 20, 20, 20);// 设置LayoutHelper的子元素相对LayoutHelper边缘的距离
        floatLayoutHelper.setMargin(20, 20, 20, 20);// 设置LayoutHelper边缘相对父控件（即RecyclerView）的距离
        floatLayoutHelper.setBgColor(Color.RED);// 设置背景颜色*/
      //  floatLayoutHelper.setAspectRatio(6);// 设置设置布局内每行布局的宽与高的比

        // floatLayoutHelper特有属性
        floatLayoutHelper.setDefaultLocation(300, 300);// 设置布局里Item的初始位置

        List<String> list=new ArrayList<>();
        mFloatAdapter=new FloatAdapter(getActivity(),list,floatLayoutHelper,R.layout.item_home_float_layout,1,Constant.viewType.typeFloat);
        mFloatAdapter.setOnItemClickListener(new BaseDelegateAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                CommonMethod.showToast(getActivity(),"click--"+position,false);
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
        mAdapters.add(mCareChoiceAdapter);
        mAdapters.add(mFixAdapter);

        mAdapters.add(mFloatAdapter);
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

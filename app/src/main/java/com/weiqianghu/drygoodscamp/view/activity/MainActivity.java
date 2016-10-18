package com.weiqianghu.drygoodscamp.view.activity;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.weiqianghu.drygoodscamp.R;
import com.weiqianghu.drygoodscamp.base.imageloader.ImageLoader;
import com.weiqianghu.drygoodscamp.entity.DryGoods;
import com.weiqianghu.drygoodscamp.presenter.WelfarePresenter;
import com.weiqianghu.drygoodscamp.utils.FragmentUtil;
import com.weiqianghu.drygoodscamp.view.fragment.AndroidFragment;
import com.weiqianghu.drygoodscamp.view.fragment.AppFragment;
import com.weiqianghu.drygoodscamp.view.fragment.ExpandFragment;
import com.weiqianghu.drygoodscamp.view.fragment.FrontFragment;
import com.weiqianghu.drygoodscamp.view.fragment.IosFragment;
import com.weiqianghu.drygoodscamp.view.fragment.RecommendFragment;
import com.weiqianghu.drygoodscamp.view.fragment.RestFragment;
import com.weiqianghu.drygoodscamp.view.fragment.SearchFragment;
import com.weiqianghu.drygoodscamp.view.fragment.TodayRecommendFragment;
import com.weiqianghu.drygoodscamp.view.fragment.WelfareFragment;
import com.weiqianghu.drygoodscamp.view.view.IWelfareView;
import com.weiqianghu.drygoodscamp.widget.BaseAnimationPopupWindow;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, IWelfareView {

    private ImageView mIvHeader;
    private WelfarePresenter mWelfarePresenter;
    private View mHeaderView;

    private FragmentManager mFragmentManager;
    private DrawerLayout mDrawer;
    private BaseAnimationPopupWindow mPopupWindow;
    private View mPopupWindowView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initData();

        gotoTodayRecommend();
    }

    private void initData() {
        mWelfarePresenter = new WelfarePresenter(this);
        mWelfarePresenter.loadWelfare();
    }

    private void initView() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        if (fab != null) {
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Snackbar.make(view, "联系我：weiqianghu_ecust@163.com", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            });
        }

        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        if (navigationView != null) {
            navigationView.setNavigationItemSelectedListener(this);
            mHeaderView = navigationView.getHeaderView(0);
        }
        mIvHeader = (ImageView) mHeaderView.findViewById(R.id.iv_header);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer != null && drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_about_me) {
            showAboutMeWindow();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        getSupportActionBar().setTitle(item.getTitle());
        switch (id) {
            case R.id.nav_welfare:
                gotoWelfare();
                break;
            case R.id.nav_today_recommend:
                gotoTodayRecommend();
                break;
            case R.id.nav_android:
                gotoAndroid();
                break;
            case R.id.nav_ios:
                gotoIos();
                break;
            case R.id.nav_front:
                gotoFront();
                break;
            case R.id.nav_expand:
                gotoExpand();
                break;
            case R.id.nav_recommend:
                gotoRecommend();
                break;
            case R.id.nav_rest:
                gotoRest();
                break;
            case R.id.nav_app:
                gotoApp();
                break;

            case R.id.nav_search:
                gotoSearch();
                break;
            case R.id.nav_about_me:
                showAboutMeWindow();
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer != null) {
            drawer.closeDrawer(GravityCompat.START);
        }
        return true;
    }

    private void gotoSearch() {
        if (mFragmentManager == null) {
            mFragmentManager = getSupportFragmentManager();
        }
        Fragment searchFragment = mFragmentManager.findFragmentByTag(SearchFragment.TAG);
        if (searchFragment == null) {
            searchFragment = new SearchFragment();
        }
        FragmentUtil.addContent(R.id.main_container, searchFragment, mFragmentManager, SearchFragment.TAG);
    }


    private void gotoApp() {
        if (mFragmentManager == null) {
            mFragmentManager = getSupportFragmentManager();
        }
        Fragment appFragment = mFragmentManager.findFragmentByTag(AppFragment.TAG);
        if (appFragment == null) {
            appFragment = new AppFragment();
        }
        FragmentUtil.addContent(R.id.main_container, appFragment, mFragmentManager, AppFragment.TAG);
    }

    private void gotoRest() {
        if (mFragmentManager == null) {
            mFragmentManager = getSupportFragmentManager();
        }
        Fragment restFragment = mFragmentManager.findFragmentByTag(RestFragment.TAG);
        if (restFragment == null) {
            restFragment = new RestFragment();
        }
        FragmentUtil.addContent(R.id.main_container, restFragment, mFragmentManager, RestFragment.TAG);
    }

    private void gotoRecommend() {
        if (mFragmentManager == null) {
            mFragmentManager = getSupportFragmentManager();
        }
        Fragment recommendFragment = mFragmentManager.findFragmentByTag(RecommendFragment.TAG);
        if (recommendFragment == null) {
            recommendFragment = new RecommendFragment();
        }
        FragmentUtil.addContent(R.id.main_container, recommendFragment, mFragmentManager, RecommendFragment.TAG);
    }

    private void gotoExpand() {
        if (mFragmentManager == null) {
            mFragmentManager = getSupportFragmentManager();
        }
        Fragment expandFragment = mFragmentManager.findFragmentByTag(ExpandFragment.TAG);
        if (expandFragment == null) {
            expandFragment = new ExpandFragment();
        }
        FragmentUtil.addContent(R.id.main_container, expandFragment, mFragmentManager, ExpandFragment.TAG);
    }

    private void gotoFront() {
        if (mFragmentManager == null) {
            mFragmentManager = getSupportFragmentManager();
        }
        Fragment frontFragment = mFragmentManager.findFragmentByTag(FrontFragment.TAG);
        if (frontFragment == null) {
            frontFragment = new FrontFragment();
        }
        FragmentUtil.addContent(R.id.main_container, frontFragment, mFragmentManager, FrontFragment.TAG);
    }

    private void gotoIos() {
        if (mFragmentManager == null) {
            mFragmentManager = getSupportFragmentManager();
        }
        Fragment iosFragment = mFragmentManager.findFragmentByTag(IosFragment.TAG);
        if (iosFragment == null) {
            iosFragment = new IosFragment();
        }
        FragmentUtil.addContent(R.id.main_container, iosFragment, mFragmentManager, IosFragment.TAG);
    }

    private void gotoAndroid() {
        if (mFragmentManager == null) {
            mFragmentManager = getSupportFragmentManager();
        }
        Fragment androidFragment = mFragmentManager.findFragmentByTag(AndroidFragment.TAG);
        if (androidFragment == null) {
            androidFragment = new AndroidFragment();
        }
        FragmentUtil.addContent(R.id.main_container, androidFragment, mFragmentManager, AndroidFragment.TAG);
    }

    private void gotoTodayRecommend() {
        if (mFragmentManager == null) {
            mFragmentManager = getSupportFragmentManager();
        }
        Fragment todayRecommendFragment = mFragmentManager.findFragmentByTag(TodayRecommendFragment.TAG);
        if (todayRecommendFragment == null) {
            todayRecommendFragment = new TodayRecommendFragment();
        }
        FragmentUtil.addContent(R.id.main_container, todayRecommendFragment, mFragmentManager, TodayRecommendFragment.TAG);
    }

    private void gotoWelfare() {
        if (mFragmentManager == null) {
            mFragmentManager = getSupportFragmentManager();
        }
        Fragment welfareFragment = mFragmentManager.findFragmentByTag(WelfareFragment.TAG);
        if (welfareFragment == null) {
            welfareFragment = new WelfareFragment();
        }
        FragmentUtil.addContent(R.id.main_container, welfareFragment, mFragmentManager, WelfareFragment.TAG);
    }

    @Override
    public void showWelfareImg(DryGoods dryGoods) {
        ImageLoader.displayImg(mIvHeader, dryGoods.url, R.mipmap.head, R.mipmap.head);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (mDrawer.isDrawerOpen(Gravity.LEFT)) {
                mDrawer.closeDrawer(Gravity.LEFT);
                return true;
            } else if (mFragmentManager.findFragmentByTag(TodayRecommendFragment.TAG) == null) {
                getSupportActionBar().setTitle(R.string.today_recommend);
                gotoTodayRecommend();
                return true;
            } else {
                return super.onKeyDown(keyCode, event);
            }
        } else {
            return super.onKeyDown(keyCode, event);
        }
    }


    private void showAboutMeWindow() {
        if (mPopupWindowView == null) {
            mPopupWindowView = LayoutInflater.from(this).inflate(R.layout.layout_about_me, null);

        }
        if (mPopupWindow == null) {
            mPopupWindow = new BaseAnimationPopupWindow(this, mPopupWindowView);
            mPopupWindow.setBackgroundDrawable(new ColorDrawable(0xffffff));
        }

        mPopupWindow.showAtLocation(getWindow().getDecorView(), Gravity.BOTTOM, 0, 0);
    }
}

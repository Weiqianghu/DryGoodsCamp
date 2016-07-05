package com.weiqianghu.drygoodscamp.view.activity;

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
import com.weiqianghu.drygoodscamp.view.fragment.TodayRecommendFragment;
import com.weiqianghu.drygoodscamp.view.fragment.WelfareFragment;
import com.weiqianghu.drygoodscamp.view.view.IWelfareView;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, IWelfareView {

    private ImageView mIvHeader;
    private WelfarePresenter mWelfarePresenter;
    private View mHeaderView;

    private FragmentManager mFragmentManager;
    private Fragment mWelfareFragment;
    private Fragment mTodayRecommendFragment;

    private Fragment mAndroidFragment;
    private Fragment mIosFragment;
    private Fragment mFrontFragment;
    private Fragment mExpandFragment;
    private Fragment mRecommendFragment;
    private Fragment mRestFragment;
    private Fragment mAppFragment;


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
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        mHeaderView = navigationView.getHeaderView(0);
        mIvHeader = (ImageView) mHeaderView.findViewById(R.id.iv_header);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
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

        if (id == R.id.action_settings) {
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
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void gotoApp() {
        if (mFragmentManager == null) {
            mFragmentManager = getSupportFragmentManager();
        }
        mAppFragment = mFragmentManager.findFragmentByTag(AppFragment.TAG);
        if (mAppFragment == null) {
            mAppFragment = new AppFragment();
        }
        FragmentUtil.addContent(R.id.main_container, mAppFragment, mFragmentManager, AppFragment.TAG);
    }

    private void gotoRest() {
        if (mFragmentManager == null) {
            mFragmentManager = getSupportFragmentManager();
        }
        mRestFragment = mFragmentManager.findFragmentByTag(RestFragment.TAG);
        if (mRestFragment == null) {
            mRestFragment = new RestFragment();
        }
        FragmentUtil.addContent(R.id.main_container, mRestFragment, mFragmentManager, RestFragment.TAG);
    }

    private void gotoRecommend() {
        if (mFragmentManager == null) {
            mFragmentManager = getSupportFragmentManager();
        }
        mRecommendFragment = mFragmentManager.findFragmentByTag(RecommendFragment.TAG);
        if (mRecommendFragment == null) {
            mRecommendFragment = new RecommendFragment();
        }
        FragmentUtil.addContent(R.id.main_container, mRecommendFragment, mFragmentManager, RecommendFragment.TAG);
    }

    private void gotoExpand() {
        if (mFragmentManager == null) {
            mFragmentManager = getSupportFragmentManager();
        }
        mExpandFragment = mFragmentManager.findFragmentByTag(ExpandFragment.TAG);
        if (mExpandFragment == null) {
            mExpandFragment = new ExpandFragment();
        }
        FragmentUtil.addContent(R.id.main_container, mExpandFragment, mFragmentManager, ExpandFragment.TAG);
    }

    private void gotoFront() {
        if (mFragmentManager == null) {
            mFragmentManager = getSupportFragmentManager();
        }
        mFrontFragment = mFragmentManager.findFragmentByTag(FrontFragment.TAG);
        if (mFrontFragment == null) {
            mFrontFragment = new FrontFragment();
        }
        FragmentUtil.addContent(R.id.main_container, mFrontFragment, mFragmentManager, FrontFragment.TAG);
    }

    private void gotoIos() {
        if (mFragmentManager == null) {
            mFragmentManager = getSupportFragmentManager();
        }
        mIosFragment = mFragmentManager.findFragmentByTag(IosFragment.TAG);
        if (mIosFragment == null) {
            mIosFragment = new IosFragment();
        }
        FragmentUtil.addContent(R.id.main_container, mIosFragment, mFragmentManager, IosFragment.TAG);
    }

    private void gotoAndroid() {
        if (mFragmentManager == null) {
            mFragmentManager = getSupportFragmentManager();
        }
        mAndroidFragment = mFragmentManager.findFragmentByTag(AndroidFragment.TAG);
        if (mAndroidFragment == null) {
            mAndroidFragment = new AndroidFragment();
        }
        FragmentUtil.addContent(R.id.main_container, mAndroidFragment, mFragmentManager, AndroidFragment.TAG);
    }

    private void gotoTodayRecommend() {
        if (mFragmentManager == null) {
            mFragmentManager = getSupportFragmentManager();
        }
        mTodayRecommendFragment = mFragmentManager.findFragmentByTag(TodayRecommendFragment.TAG);
        if (mTodayRecommendFragment == null) {
            mTodayRecommendFragment = new TodayRecommendFragment();
        }
        FragmentUtil.addContent(R.id.main_container, mTodayRecommendFragment, mFragmentManager, TodayRecommendFragment.TAG);
    }

    private void gotoWelfare() {
        if (mFragmentManager == null) {
            mFragmentManager = getSupportFragmentManager();
        }
        mWelfareFragment = mFragmentManager.findFragmentByTag(WelfareFragment.TAG);
        if (mWelfareFragment == null) {
            mWelfareFragment = new WelfareFragment();
        }
        FragmentUtil.addContent(R.id.main_container, mWelfareFragment, mFragmentManager, WelfareFragment.TAG);
    }

    @Override
    public void showWelfareImg(DryGoods dryGoods) {
        ImageLoader.displayImg(mIvHeader, dryGoods.url, R.mipmap.head, R.mipmap.head);
    }
}

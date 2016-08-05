package com.weiqianghu.drygoodscamp.view.activity;

import android.content.ClipboardManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.weiqianghu.drygoodscamp.R;
import com.weiqianghu.drygoodscamp.common.Constant;
import com.weiqianghu.drygoodscamp.utils.ToastUtil;

public class WebViewActivity extends AppCompatActivity {
    private WebView mWebView;
    private ProgressBar mProgressBar;
    private String mUrl;
    private RelativeLayout mLayoutError;
    private Button mBtnReload;

    private boolean isError = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        initView();
        initData();
    }

    private void initData() {
        Intent intent = getIntent();
        mUrl = intent.getStringExtra(Constant.ARG_URL);
        mWebView.loadUrl(mUrl);

        String title = intent.getStringExtra(Constant.ARG_TITLE);
        getSupportActionBar().setTitle(title);
    }

    private void initView() {
        initToolBar();
        initErrorView();

        mProgressBar = (ProgressBar) findViewById(R.id.progress_bar);
        mWebView = (WebView) findViewById(R.id.web_view);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView view, int progress) {
                setProgress(progress * 1000);
                mProgressBar.setProgress(progress);
                if (progress >= 100) {
                    mProgressBar.setVisibility(View.GONE);
                    if (!isError) {
                        mLayoutError.setVisibility(View.GONE);
                    }
                }
            }
        });
        mWebView.setWebViewClient(new WebViewClient() {
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                mLayoutError.setVisibility(View.VISIBLE);
                isError = true;
            }
        });
        mWebView.getSettings().setPluginState(WebSettings.PluginState.ON);
    }

    private void initErrorView() {
        mLayoutError = (RelativeLayout) findViewById(R.id.rl_error);
        mBtnReload = (Button) findViewById(R.id.btn_reload);
        mBtnReload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isError = false;
                mWebView.reload();
                mProgressBar.setVisibility(View.VISIBLE);
            }
        });
    }

    private void initToolBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.mipmap.close);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WebViewActivity.this.finish();
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        mWebView.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mWebView.onResume();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && mWebView.canGoBack()) {
            mWebView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.web, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_open_by_default_browser) {
            Intent intent = new Intent();
            intent.setAction("android.intent.action.VIEW");
            Uri content_url = Uri.parse(mUrl);
            intent.setData(content_url);
            startActivity(intent);
            return true;
        } else if (id == R.id.action_copy_url) {
            ClipboardManager cmb = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
            cmb.setText(mUrl);
            ToastUtil.show("链接已经复制到剪贴板");
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

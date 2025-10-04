package com.example.app;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

import androidx.webkit.WebViewAssetLoader;
import androidx.webkit.WebViewAssetLoader.AssetsPathHandler;
import androidx.webkit.WebViewAssetLoader.ResourcesPathHandler;

import java.util.Objects;

public class MainActivity extends Activity {

    private WebView mWebView;

    @Override
    @SuppressLint("SetJavaScriptEnabled")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mWebView = findViewById(R.id.activity_main_webview);

        // Configure WebView settings
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        // Disable risky file access for security (asset loader handles local securely)
        webSettings.setAllowFileAccess(false);
        webSettings.setAllowUniversalAccessFromFileURLs(false);
        webSettings.setAllowFileAccessFromFileURLs(false);

        // Set up asset loader for local content (including WASM)
        final WebViewAssetLoader assetLoader = new WebViewAssetLoader.Builder()
                .addPathHandler("/assets/", new AssetsPathHandler(this))
                .addPathHandler("/res/", new ResourcesPathHandler(this))
                .build();

        mWebView.setWebViewClient(new MyWebViewClient(assetLoader));

        // LOCAL RESOURCE (via virtual HTTPS for WASM compatibility)
        mWebView.loadUrl("https://appassets.androidplatform.net/assets/index.html");

        // REMOTE RESOURCE (uncomment to switch)
        // mWebView.loadUrl("https://example.com");
    }

    @Override
    public void onBackPressed() {
        if (mWebView.canGoBack()) {
            mWebView.goBack();
        } else {
            super.onBackPressed();
        }
    }
}

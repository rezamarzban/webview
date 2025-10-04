package com.example.app;

import android.content.Intent;
import android.net.Uri;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.webkit.WebViewAssetLoader;
import androidx.webkit.WebViewClientCompat;

class MyWebViewClient extends WebViewClientCompat {
    private final WebViewAssetLoader mAssetLoader;

    public MyWebViewClient(WebViewAssetLoader assetLoader) {
        mAssetLoader = assetLoader;
    }

    @Override
    public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
        return mAssetLoader.shouldInterceptRequest(request.getUrl());
    }

    @Override
    public WebResourceResponse shouldInterceptRequest(WebView view, String url) {
        return mAssetLoader.shouldInterceptRequest(Uri.parse(url));
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
        Uri uri = request.getUrl();
        String url = uri.toString();

        // Virtual hostname for asset loader (local content)
        String hostname = "appassets.androidplatform.net";

        if (url.startsWith("file:") || (uri.getHost() != null && uri.getHost().endsWith(hostname))) {
            return false;  // Load local content in WebView
        }

        // Open external URLs in system browser
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        view.getContext().startActivity(intent);
        return true;
    }
}

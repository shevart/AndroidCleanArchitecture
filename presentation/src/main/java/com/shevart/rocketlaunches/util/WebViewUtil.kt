package com.shevart.rocketlaunches.util

import android.annotation.SuppressLint
import android.webkit.WebView
import android.webkit.WebViewClient


@SuppressLint("SetJavaScriptEnabled")
fun WebView.setForWiki() {
    settings.javaScriptEnabled = true
    webViewClient = WikiWebViewClient()
}

private class WikiWebViewClient : WebViewClient() {
    override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
        view.loadUrl(url)
        return true
    }
}
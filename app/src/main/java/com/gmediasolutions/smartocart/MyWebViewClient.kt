package com.gmediasolutions.smartocart

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.webkit.SafeBrowsingResponseCompat
import androidx.webkit.WebViewClientCompat
import androidx.webkit.WebViewFeature

class MyWebViewClient : WebViewClientCompat() {

//    override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
//        if (Uri.parse(url).host == "www.smartocart.com") {
//            // This is my web site, so do not override; let my WebView load the page
//            return false
//        }
//        // Otherwise, the link is not for a page on my site, so launch another Activity that handles URLs
////        var intent= Intent(Intent.ACTION_VIEW, Uri.parse(url)).apply {
////            startActivity(intent)
////        }
//        return true
//    }

    @SuppressLint("NewApi")
    override fun shouldOverrideUrlLoading(view: WebView, request: WebResourceRequest): Boolean {
        Log.i("shouldUrlLoading",request.toString())
        view!!.loadUrl(request!!.url.toString())
//         view!!.loadUrl(url)
        return true
    }

    override fun onSafeBrowsingHit(
        view: WebView,
        request: WebResourceRequest,
        threatType: Int,
        callback: SafeBrowsingResponseCompat
    ) {
        // The "true" argument indicates that your app reports incidents like
        // this one to Safe Browsing.
        if (WebViewFeature.isFeatureSupported(WebViewFeature.SAFE_BROWSING_RESPONSE_BACK_TO_SAFETY)) {
            callback.backToSafety(true)
            Toast.makeText(view.context, "Unsafe web page blocked.", Toast.LENGTH_LONG).show()
        }
    }
}
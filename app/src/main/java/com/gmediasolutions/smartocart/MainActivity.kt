/*
package com.gmediasolutions.smartocart

import android.annotation.SuppressLint
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Message
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.view.ViewGroup
import android.webkit.*
import androidx.webkit.WebViewCompat
import androidx.webkit.WebViewFeature
import android.webkit.WebViewClient
import android.webkit.WebSettings
import android.widget.FrameLayout


class MainActivity : AppCompatActivity() {

    lateinit var myWebView: WebView
    var USER_AGENT= "Mozilla/5.0 (Linux; Android 4.1.1; Galaxy Nexus Build/JRO03C) AppleWebKit/535.19 (KHTML, like Gecko) Chrome/18.0.1025.166 Mobile Safari/535.19";

    private lateinit var superSafeWebView: WebView
    private var safeBrowsingIsInitialized: Boolean = false
    var windowWebView: WebView?=null
    lateinit var container: FrameLayout

    @SuppressLint("SetJavaScriptEnabled", "NewApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        container = findViewById(R.id.fl_container)
        myWebView = findViewById(R.id.webview)

//         myWebView = WebView(this)
//        setContentView(myWebView)



        myWebView.settings.javaScriptEnabled = true

        myWebView!!.clearCache(false)
        myWebView!!.setFocusable(true)
        myWebView!!.setFocusableInTouchMode(true)
        myWebView!!.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_COMPATIBILITY_MODE)
        myWebView!!.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW)
        myWebView!!.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH)
        myWebView!!.getSettings().setUserAgentString(USER_AGENT)
        myWebView.webViewClient = MyWebViewClient()
        myWebView!!.getSettings().setDomStorageEnabled(true)
        myWebView!!.getSettings().setDatabaseEnabled(true)
        myWebView!!.getSettings().setAppCacheEnabled(true)
        myWebView!!.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY)
        myWebView.settings.setJavaScriptCanOpenWindowsAutomatically(true)
//        myWebView!!.setWebChromeClient(WebChromeClient())
        myWebView!!.getSettings().setPluginState(WebSettings.PluginState.ON)
        myWebView!!.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE)
        if (Build.VERSION.SDK_INT >= 19) {
            myWebView!!.setLayerType(View.LAYER_TYPE_HARDWARE, null)
        }
        else {
            myWebView!!.setLayerType(View.LAYER_TYPE_SOFTWARE, null)
        }


        */
/*Managing WebView objects. *//*

        val webViewPackageInfo = WebViewCompat.getCurrentWebViewPackage(this)
        Log.d("MY_APP_TAG", "WebView version: ${webViewPackageInfo!!.versionName}")

        */
/*Google Safe Browsing Service*//*

        superSafeWebView = WebView(this)
        superSafeWebView.webViewClient = MyWebViewClient()
        safeBrowsingIsInitialized = false

        if (WebViewFeature.isFeatureSupported(WebViewFeature.START_SAFE_BROWSING)) {
            WebViewCompat.startSafeBrowsing(this, ValueCallback<Boolean> { success ->
                safeBrowsingIsInitialized = true
                if (!success) {
                    Log.e("MY_APP_TAG", "Unable to initialize Safe Browsing!")
                }
            })
        }

        */
/*Console in web apps*//*

//        myWebView.webChromeClient = object : WebChromeClient() {
//
//            override fun onConsoleMessage(consoleMessage: ConsoleMessage?): Boolean {
//                consoleMessage?.apply {
//                    Log.d("MyApplication", "${message()} -- From line ${lineNumber()} of ${sourceId()}")
//                }
//                return true
//            }
//        }
        */
/*support multiple screen in webview*//*

        myWebView.settings.setSupportMultipleWindows(true)
        myWebView.settings.javaScriptCanOpenWindowsAutomatically = true
        myWebView!!.settings.setUserAgentString(myWebView!!.settings.getUserAgentString().replace("; wv",""));
        myWebView.webChromeClient = object : WebChromeClient() {

            override fun onCreateWindow(view: WebView, isDialog: Boolean, isUserGesture: Boolean, resultMsg: android.os.Message): Boolean {
                handleCreateWebWindowRequest(resultMsg)
                return true
            }

            override fun onCloseWindow(window: WebView) {
                //				webView.removeView(window);
            }
//            override fun onConsoleMessage(consoleMessage: ConsoleMessage?): Boolean {
//                consoleMessage?.apply {
//                    Log.d("MyApplication", "${message()} -- From line ${lineNumber()} of ${sourceId()}")
//                }
//                return true
//            }

        }
        myWebView.loadUrl("https://www.smartocart.com")

    }

    @SuppressLint("SetJavaScriptEnabled")
    fun handleCreateWebWindowRequest(resultMsg: Message?) {
        if (resultMsg == null) return
        if (resultMsg.obj != null && resultMsg.obj is WebView.WebViewTransport)
        {
            val transport = resultMsg.obj as WebView.WebViewTransport
            windowWebView = WebView(this)
            windowWebView?.layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT)

            windowWebView?.settings?.javaScriptEnabled = true
            windowWebView?.settings?.javaScriptCanOpenWindowsAutomatically = true
//            windowWebView?.settings?.setSupportMultipleWindows(true)
            windowWebView?.webViewClient = WebViewClient()
            windowWebView?.settings?.userAgentString = USER_AGENT
            windowWebView?.webChromeClient = object : WebChromeClient() {
                override fun onCloseWindow(window: WebView?) {
                    super.onCloseWindow(window)
                    handleCloseWebWindowRequest()
                }
            }

            container.addView(windowWebView)
            myWebView.visibility = View.GONE
            transport.webView = windowWebView
            resultMsg.sendToTarget()
        }
    }

    private fun handleCloseWebWindowRequest() {
//        if (!isWebWindowOpened()) return

        container.removeView(windowWebView)
        myWebView.visibility = View.VISIBLE
        windowWebView = null
    }


    */
/*When your WebView overrides URL loading, it automatically accumulates a history of visited web pages.
    You can navigate backward and forward through the history with goBack() and goForward().*//*

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        // Check if the key event was the Back button and if there's history
        if (keyCode == KeyEvent.KEYCODE_BACK && myWebView.canGoBack()) {
            myWebView.goBack()
            return true
        }
        // If it wasn't the Back key or there's no web page history, bubble up to the default
        // system behavior (probably exit the activity)
        return super.onKeyDown(keyCode, event)
    }

}
*/

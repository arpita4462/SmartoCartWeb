package com.gmediasolutions.smartocart


import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Message
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.webkit.*
import android.widget.FrameLayout
import android.widget.RelativeLayout


import androidx.appcompat.app.AppCompatActivity


class WebviewJavaScriptActivity : AppCompatActivity() {
    lateinit var mWebView: WebView
    var windowWebView: WebView?=null
    lateinit var container: FrameLayout
    internal var mIsLogIn = false
    internal var USER_AGENT = "Mozilla/5.0 (Linux; Android 4.1.1; Galaxy Nexus Build/JRO03C) AppleWebKit/535.19 (KHTML, like Gecko) Chrome/18.0.1025.166 Mobile Safari/535.19"

    @SuppressLint("NewApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mWebView = findViewById(R.id.webview)
//        windowWebView = findViewById(R.id.webview_javascript)
        container = findViewById(R.id.fl_container)


        val webSettings = mWebView.settings
        webSettings.javaScriptEnabled = true
        webSettings.setSupportZoom(true)
        webSettings.builtInZoomControls = true
        webSettings.domStorageEnabled = true
        mWebView.settings.userAgentString = USER_AGENT

        mWebView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {

                if (url.startsWith("intent://")) {
                    val intent = Intent.parseUri(url, Intent.URI_INTENT_SCHEME)
                    if (intent != null) {
                        val fallbackUrl = intent.getStringExtra("browser_fallback_url")
                        return if (fallbackUrl != null) {
                            mWebView.loadUrl(fallbackUrl)
                            true
                        } else {
                            false
                        }
                    }
                }
                return false
            }



            override fun shouldInterceptRequest(view: WebView?, request: WebResourceRequest?): WebResourceResponse? {
                return super.shouldInterceptRequest(view, request)


            }

             override fun onPageFinished(view: WebView, url: String) {
                super.onPageFinished(view, url)
                //				 mWebView

                /*
	            if(mIsLogIn == false){//LogIn chỉ làm 1 lần duy nhất ở link đầu tiên
		            String javaScript = "javaScript:" + "document.getElementById('username').value='"+ new String ("chân thật")+ "';" +
							"document.getElementById('password').value ='" + new String("123") + "';" +
							"document.getElementsByName('login')[0].click();";

		            mWebView.evaluateJavascript(javaScript, new ValueCallback<String>() {

						@Override
						public void onReceiveValue(String value) {
							Log.v("javascript","value="+value);
						}
					});
		            mIsLogIn = true;
	            }
*/
            }
        }

        //clear cookies
        val cookieManager = CookieManager.getInstance()
//        cookieManager.setAcceptCookie(true)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            cookieManager.removeAllCookies(null)
        } else {
            cookieManager.removeAllCookie()
        }

        mWebView.loadUrl("https://smartocart.com")

        //lopen multiple window
        mWebView.settings.javaScriptEnabled = true
//        webSettings.javaScriptCanOpenWindowsAutomatically = true
//        mWebView.settings.setSupportMultipleWindows(true)

        mWebView.webChromeClient = object : WebChromeClient() {

            override fun onCreateWindow(view: WebView?, isDialog: Boolean, isUserGesture: Boolean, resultMsg: Message?): Boolean {
                handleCreateWebWindowRequest(resultMsg)
                return true
            }

            override fun onCloseWindow(window: WebView) {
                mWebView.removeView(window);
            }

        }

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
            mWebView.visibility = View.GONE
            transport.webView = windowWebView
            resultMsg.sendToTarget()
        }
    }

    private fun handleCloseWebWindowRequest() {
//        if (!isWebWindowOpened()) return

        container.removeView(windowWebView)
        mWebView.visibility = View.VISIBLE
        windowWebView = null
    }


}

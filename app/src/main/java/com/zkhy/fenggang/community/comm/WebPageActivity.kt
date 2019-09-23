package com.zkhy.fenggang.community.comm

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v4.content.ContextCompat.startActivity
import android.view.KeyEvent
import android.view.KeyEvent.KEYCODE_BACK
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import com.sinothk.comm.utils.StringUtil
import com.zkhy.fenggang.community.R
import com.zkhy.library.base.activity.TitleBarBaseActivity
import kotlinx.android.synthetic.main.activity_webpage.*


class WebPageActivity : TitleBarBaseActivity() {

    private var webTitle = ""
    private var webUrl = ""


    override fun getLayout(): Int = R.layout.activity_webpage

    @SuppressLint("SetJavaScriptEnabled", "NewApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        webUrl = intent.getStringExtra("webUrl")
        webTitle = intent.getStringExtra("webTitle")
        setTitleBar(StringUtil.getNotNullValue(webTitle, "信息详情"))

        val webSettings = webView.settings
//        webSettings.javaScriptEnabled = true
//        webSettings.useWideViewPort = true //将图片调整到适合webview的大小 //设置自适应屏幕，两者合用
//        webSettings.loadWithOverviewMode = true // 缩放至屏幕的大小

        webSettings.javaScriptEnabled = true
        webSettings.useWideViewPort = true //将图片调整到适合webview的大小 //设置自适应屏幕，两者合用
        webSettings.loadWithOverviewMode = true // 缩放至屏幕的大小

        webSettings.setSupportZoom(true)
//        webSettings.displayZoomControls = false
        webSettings.builtInZoomControls = true

        webSettings.setAppCacheEnabled(false)
        webSettings.cacheMode = WebSettings.LOAD_NO_CACHE
        webSettings.domStorageEnabled = true
        webSettings.supportMultipleWindows()
        webSettings.allowContentAccess = true
        webSettings.layoutAlgorithm = WebSettings.LayoutAlgorithm.NARROW_COLUMNS
        webSettings.useWideViewPort = true
        webSettings.loadWithOverviewMode = true
        webSettings.savePassword = true
//        webSettings.saveFormData = true
        webSettings.javaScriptCanOpenWindowsAutomatically = true
        webSettings.loadsImagesAutomatically = true

        webSettings.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW

        webView.webViewClient = MyWebViewClient()
        webView.loadUrl(webUrl)
    }

    class MyWebViewClient : WebViewClient() {

        override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {

            val tag = "tel:" //tada:
            if (url != null && url.contains(tag)) {

                var mobile: String = url.substring(url.lastIndexOf("/") + 1)

                val uri: Uri = Uri.parse(mobile)//"tel:$mobile"
                val intent = Intent(Intent.ACTION_CALL, uri)
                startActivity(view!!.context, intent, null)

                //这个超连接,java已经处理了，webview不要处理了
                return true
            }

            return super.shouldOverrideUrlLoading(view, url)
        }

//        override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
//
//            //判断用户单击的是那个超连接
//
//            val url: String = request!!.url.path
//
//            val tag: String = "tada:tel"
//
//            if (url.contains(tag)) {
//
//                val mobile: String = url.substring(url.lastIndexOf("/") + 1)
//
//                val uri: Uri = Uri.parse("tel:" + mobile);
//
//                val intent: Intent = Intent(Intent.ACTION_CALL, uri)
//                startActivity(view!!.context, intent)
//
//                //这个超连接,java已经处理了，webview不要处理了
//                return true
//            }
//
//
//            return super.shouldOverrideUrlLoading(view, request)
//        }

        //        @Override
//        public boolean shouldOverrideUrlLoading(WebView view, String url) {
//            Log.i("用户单击超连接", url);
//            //判断用户单击的是那个超连接
//            String tag="tada:tel";
//
//            if (url.contains(tag))
//            {
//                String mobile=url.substring(url.lastIndexOf("/")+1);
//                Uri uri=Uri.parse("tel:"+mobile);
//                Intent intent=new Intent(Intent.ACTION_CALL,uri);
//                startActivity(intent);
//                //这个超连接,java已经处理了，webview不要处理了
//                return true;
//            }
//
//            return super.shouldOverrideUrlLoading(view, url);
//        }

//        public boolean shouldOverrideUrlLoading(WebView view, String url)
//        {
//            view.loadurl(url)//返回true代表在当前webview中打开，返回false表示打开浏览器
//            return super.shouldOverrideUrlLoading(view, url)
//        }
////
//        @Override
//        public void onPageStarted(WebView view, String url, Bitmap favicon)
//        {
//            if (!dialog.isShowing()) {
//                dialog.show()
//            }
//            super.onPageStarted(view, url, favicon)
//        }
//
//        @Override
//        public void onPageFinished(WebView view, String url)
//        {
//            if (dialog.isShowing()) {
//                dialog.dismiss()
//            }
//            super.onPageFinished(view, url)
//        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        if (keyCode == KEYCODE_BACK && webView.canGoBack()) {
            webView.goBack()
            return true
        }
        return super.onKeyDown(keyCode, event)
    }
}

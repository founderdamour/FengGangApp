package com.zkhy.fenggang.community.view.main.am.fragment;

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import com.zkhy.fenggang.community.R
import kotlinx.android.synthetic.main.fragment_web_view.*


/**
 * TabFragment
 * Created by D on 2017/8/27.
 */
class SafeWorkListFragment : Fragment() {

    var webUrl = ""

    fun getInstance(url: String): SafeWorkListFragment {
        val fragment = SafeWorkListFragment()
        val bundle = Bundle()
        bundle.putString("url", url)
        fragment.arguments = bundle
        return fragment
    }

    fun getLocWebView(): WebView = webView

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        val arguments: Bundle? = arguments
        webUrl = arguments?.getString("url").toString()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_web_view, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webSettings.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
        }

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
                ContextCompat.startActivity(view!!.context, intent, null)

                //这个超连接,java已经处理了，webview不要处理了
                return true
            }

            return super.shouldOverrideUrlLoading(view, url)
        }
    }
}

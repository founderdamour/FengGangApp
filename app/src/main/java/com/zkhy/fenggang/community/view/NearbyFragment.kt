package com.zkhy.fenggang.community.view

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebSettings
import android.webkit.WebViewClient
import com.zkhy.fenggang.community.R
import kotlinx.android.synthetic.main.fragment_nearby.*


class NearbyFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_nearby, container, false)
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
        webSettings.displayZoomControls = false

        webSettings.setAppCacheEnabled(true)
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

        webView.webViewClient = MyWebViewClient()
        webView.loadUrl("http://58.16.181.23:7070/site/index.html#/activity")
    }

    class MyWebViewClient : WebViewClient() {
        //        public boolean shouldOverrideUrlLoading(WebView view, String url) {
//            view.loadurl(url)//返回true代表在当前webview中打开，返回false表示打开浏览器
//            return super.shouldOverrideUrlLoading(view,url)       }
////
//        @Override
//        public void onPageStarted(WebView view, String url, Bitmap favicon) {
//            if(!dialog.isShowing()) {
//                dialog.show()
//            }
//            super.onPageStarted(view, url, favicon)
//        }
//
//        @Override
//        public void onPageFinished(WebView view, String url) {
//            if(dialog.isShowing()){
//                dialog.dismiss()
//            }
//            super.onPageFinished(view, url)
//        }
    }

//    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
//        if (keyCode == KEYCODE_BACK && webView.canGoBack()) {
//            webView.goBack()
//            return true
//        }
//        return super.onKeyDown(keyCode, event)
//    }

//    companion object {
//        /**
//         * Use this factory method to create a new instance of
//         * this fragment using the provided parameters.
//         *
//         * @param param1 Parameter 1.
//         * @param param2 Parameter 2.
//         * @return A new instance of fragment HomeFragment.
//         */
//        @JvmStatic
//        fun newInstance(param1: String, param2: String) =
//            HomeFragment().apply {
//                arguments = Bundle().apply {
//                    putString(ARG_PARAM1, param1)
//                    putString(ARG_PARAM2, param2)
//                }
//            }
//    }

//    override fun onResume() {
//        super.onResume()
//
//        view!!.isFocusableInTouchMode = true
//        view!!.requestFocus()
//        view!!.setOnKeyListener(View.OnKeyListener { view, i, keyEvent ->
//            if (keyEvent.action == KeyEvent.ACTION_DOWN && i == KeyEvent.KEYCODE_BACK) {
//                Toast.makeText(activity, "按了返回键", Toast.LENGTH_SHORT).show()
//                return@OnKeyListener true
//            }
//            false
//        })
//    }
}

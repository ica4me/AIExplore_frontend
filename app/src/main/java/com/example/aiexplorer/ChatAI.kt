package com.example.aiexplorer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment

class ChatAI : Fragment() {
    // deklarasikan webview
    private lateinit var webView: WebView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.activity_chat_ai, container, false)

        // Inisialisasi WebView
        webView = view.findViewById(R.id.webView) // Pastikan ID sesuai dengan layout fragment Anda
        webView.webViewClient = WebViewClient()
        webView.loadUrl("https://chat.openai.com/")

        // Pengaturan WebView
        val webSettings = webView.settings
        webSettings.javaScriptEnabled = true
        webSettings.domStorageEnabled = true

        return view
    }

    // Fungsi ketika tombol kembali ditekan
    fun onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack()
        } else {
            activity?.onBackPressed()
        }
    }
}

package com.example.aiexplorer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment

class Explore : Fragment() {
    // Declare the WebView here
    private lateinit var webView: WebView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_explore, container, false)

        // Initialize the WebView
        webView = view.findViewById(R.id.WV)
        webView.webViewClient = WebViewClient()
        webView.loadUrl("http://20.255.62.2/aiexplore/public/viewcontent")

        // Configure WebView settings
        val webSettings = webView.settings
        webSettings.javaScriptEnabled = true
        webSettings.domStorageEnabled = true

        return view
    }

    // Handle back press in WebView
    fun onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack()
        } else {
            // Handle back press for fragment or activity
        }
    }
}

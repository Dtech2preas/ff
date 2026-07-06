package com.orbit.offline

import android.annotation.SuppressLint
import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity

class GameActivity : AppCompatActivity() {
    private lateinit var webView: WebView

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        webView = findViewById(R.id.webView)
        webView.settings.javaScriptEnabled = true
        webView.settings.domStorageEnabled = true
        webView.settings.mediaPlaybackRequiresUserGesture = false
        webView.settings.allowFileAccessFromFileURLs = true
        webView.settings.allowUniversalAccessFromFileURLs = true

        webView.webViewClient = WebViewClient()
        webView.webChromeClient = WebChromeClient()

        val mode = intent.getStringExtra("MODE") ?: "ONLINE"

        if (mode == "ONLINE") {
            webView.loadUrl("https://game.dtech-services.co.za")
        } else {
            webView.addJavascriptInterface(WebAppInterface(this) {
                startServer()
            }, "AndroidBridge")
            webView.loadUrl("file:///android_asset/index.html")
        }
    }

    private var server: LocalWebSocketServer? = null

    private fun startServer() {
        if (server == null) {
            server = LocalWebSocketServer(8080)
            server?.start()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        server?.stop()
        server = null
    }
}
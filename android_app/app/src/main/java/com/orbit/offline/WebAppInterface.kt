package com.orbit.offline

import android.content.Context
import android.net.wifi.WifiManager
import android.webkit.JavascriptInterface
import android.util.Log

class WebAppInterface(private val context: Context, private val onStartHost: () -> Unit) {

    @JavascriptInterface
    fun getLocalIpAddress(): String {
        try {
            val wifiManager = context.applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
            val ipAddress = wifiManager.connectionInfo.ipAddress
            if (ipAddress == 0) return "127.0.0.1" // Fallback if not on Wi-Fi
            return String.format(
                "%d.%d.%d.%d",
                ipAddress and 0xff,
                ipAddress shr 8 and 0xff,
                ipAddress shr 16 and 0xff,
                ipAddress shr 24 and 0xff
            )
        } catch (e: Exception) {
            Log.e("WebAppInterface", "Error getting IP", e)
            return "127.0.0.1"
        }
    }

    @JavascriptInterface
    fun startLocalServer() {
        onStartHost()
    }
}
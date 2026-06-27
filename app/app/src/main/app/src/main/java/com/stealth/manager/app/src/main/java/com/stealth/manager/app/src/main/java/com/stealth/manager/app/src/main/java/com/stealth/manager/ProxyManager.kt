package com.stealth.manager

import android.webkit.WebView
import java.net.Authenticator
import java.net.InetSocketAddress
import java.net.Proxy
import java.util.Base64

object ProxyManager {
    fun setupWebViewProxy(webView: WebView, host: String, port: Int, user: String, pass: String) {
        val proxy = Proxy(Proxy.Type.HTTP, InetSocketAddress(host, port))
        val authenticator = Authenticator { _, _ ->
            val credential = Base64.getEncoder().encodeToString("$user:$pass".toByteArray())
            System.setProperty("http.proxyHost", host)
            System.setProperty("http.proxyPort", port.toString())
            System.setProperty("https.proxyHost", host)
            System.setProperty("https.proxyPort", port.toString())
            null
        }
        Authenticator.setDefault(authenticator)
    }
}

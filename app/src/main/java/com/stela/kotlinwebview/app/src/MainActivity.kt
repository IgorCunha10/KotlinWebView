package com.stela.kotlinwebview.app.src

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.stela.kotlinwebview.R

class MainActivity : AppCompatActivity() {


    private lateinit var webView: WebView
    private lateinit var webAppInterface: WebAppInterface
    private lateinit var fabScanBtn: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        webView = findViewById<WebView>(R.id.web)
        webAppInterface = WebAppInterface(this)

        initView()
        initWebView()
        checkLoginState()
        showButton()
    }

    fun initView() {
        fabScanBtn = findViewById<FloatingActionButton>(R.id.fabNewScan)
    }

    fun initWebView() {
        webView.loadUrl("http://192.168.1.180/browser")
        webView.settings.javaScriptEnabled = true
        webView.webViewClient = WebViewClient()
        webView.addJavascriptInterface(webAppInterface, "Android")

        webView.webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)

                checkLoginState()

            }
        }
    }

    private fun checkLoginState() {
        webView.evaluateJavascript(
            """
        (function() {
            var token = localStorage.getItem('auth_token');
            if (token) {
                Android.onUserLoggedIn(token);
            } else {
                Android.onUserLoggedOut();
            }
        })();
        """.trimIndent(),
            null
        )
    }

    private fun showButton() {
        if(webAppInterface.isLoggedIn) {
            fabScanBtn.show()
        } else {
            fabScanBtn.hide()
        }
    }


}
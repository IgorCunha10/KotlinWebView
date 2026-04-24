package com.stela.kotlinwebview.app.src.ui.view

import android.content.Intent
import android.os.Bundle
import android.webkit.CookieManager
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

        initView()
        webView = findViewById<WebView>(R.id.web)
        initWebView()
        fabScanBtn.hide()
        initListeners()
    }

    private fun initListeners() {
        fabScanBtn.setOnClickListener {
            val intent = Intent(this, ScanScreen::class.java)
            startActivity(intent)
        }
    }

    private fun initWebView() {
        webView.settings.javaScriptEnabled = true

        webView.webViewClient = object : WebViewClient() {

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                checkUrl(url)
            }

            override fun doUpdateVisitedHistory(view: WebView?, url: String?, isReload: Boolean) {
                super.doUpdateVisitedHistory(view, url, isReload)
                checkUrl(url)
            }
        }
        webView.loadUrl("http://192.168.1.180/browser/#/login")

    }

    private fun checkUrl(url: String?) {
        val isLoggedIn = url?.contains("#/patients") == true
        updateScanButton(isLoggedIn)
    }

    private fun updateScanButton(loggedIn: Boolean) {
        runOnUiThread {
            if (loggedIn) {
                fabScanBtn.show()
            } else {
                fabScanBtn.hide()
            }
        }
    }

    fun initView() {
        fabScanBtn = findViewById<FloatingActionButton>(R.id.fabNewScan)
    }

}
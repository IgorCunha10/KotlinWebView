package com.stela.kotlinwebview.app.src.ui.view

import android.os.Bundle
import android.webkit.WebView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.stela.kotlinwebview.R

class WebViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_web_view)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val tag = intent.getStringExtra("PATIENT_ID") ?: return
        val url = "http://http://192.168.1.180/browser/#/patient/$tag"

        val webView = findViewById<WebView>(R.id.web)
        webView.settings.javaScriptEnabled = true
        webView.loadUrl(url)

    }
}
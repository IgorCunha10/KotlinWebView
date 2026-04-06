package com.stela.kotlinwebview.app.src

import android.content.Context
import android.webkit.JavascriptInterface

class WebAppInterface(private val context: Context)  {

   var isLoggedIn = false
       private set

    @JavascriptInterface
    fun onUserLoggedIn(token: String?) {
        isLoggedIn = true
    }

    @JavascriptInterface
    fun onUserLoggedOut() {
        isLoggedIn = false
    }
}

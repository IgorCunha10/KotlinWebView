package com.stela.kotlinwebview.app.src.ui.vmfactory

import android.app.Activity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.stela.kotlinwebview.app.src.ui.viewmodel.RfidViewModel

class RfidVMFactory(private val activity: Activity) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        return RfidViewModel(activity) as T
    }

}
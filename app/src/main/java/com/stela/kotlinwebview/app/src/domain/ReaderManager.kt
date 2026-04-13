package com.stela.kotlinwebview.app.src.domain

import android.app.Activity
import com.grotg.hpp.otglibrary.exception.ReaderException
import com.grotg.hpp.otglibrary.otgreader.OtgReader
import com.grotg.hpp.otglibrary.param.EpcBean

class ReaderManager {

    private lateinit var otgReader : OtgReader

    interface TagCallBack {
        fun onTagRead(epcBean: EpcBean)
    }

    constructor(activity: Activity) {
        otgReader = OtgReader(activity)
    }

    fun connect(callback: OtgReader.ConnectCallback) {
        otgReader.connect(callback)
    }

    fun startScan() {
        try {
            otgReader.scanTags()
        } catch (e: ReaderException) {
            throw RuntimeException(e)
        }
    }

    fun stopScan() {
        try {
            otgReader.stopScan()
        } catch (e: ReaderException) {
            throw RuntimeException(e)
        }
    }

    fun setTagOnRead(callback: TagCallBack) {
        otgReader.setReadTagDataCallback(callback::onTagRead)
    }

    fun release() {
        try {
            otgReader.stopScan()
        } catch (ignored: ReaderException) {

        }
    }

}
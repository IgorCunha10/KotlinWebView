package com.stela.kotlinwebview.app.src.domain

import com.grotg.hpp.otglibrary.param.EpcBean

class Tag constructor() : EpcBean() {

    constructor(
        epc: String, serialNumber: String,
        readCount: Int, rssi: Int,
    ) : this() {

        this.strepc = epc
        this.strNo = serialNumber
        this.strCount = readCount
        this.intRssi = rssi
    }

    constructor(epcBean: EpcBean) : this() {
        this.strepc = epcBean.strepc ?: ""
        this.strNo = epcBean.strNo ?: ""
        this.strCount = epcBean.strCount
        this.intRssi = epcBean.intRssi
    }

    fun hasValidEpc(): Boolean {
        return strepc != null && !strepc.trim().isEmpty()
    }

    override fun toString(): String {
        return "Tag{ " +
                "epc='" + strepc + '\'' +
                ", serialNumber='" + strNo + '\'' +
                ", readCount=" + strCount +
                ", rssi=" + intRssi +
                '}'
    }
}


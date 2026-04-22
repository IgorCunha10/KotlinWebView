package com.stela.kotlinwebview.app.src.data

import com.stela.kotlinwebview.app.src.model.PatientData
import com.stela.kotlinwebview.app.src.network.RetrofitClient

class PatientRepository {
    suspend fun findPatient(tag : String) : PatientData {
        return RetrofitClient.instance.getPatient(tag)
    }

}
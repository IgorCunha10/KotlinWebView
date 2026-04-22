package com.stela.kotlinwebview.app.src.data

import com.stela.kotlinwebview.app.src.model.PatientResponse
import com.stela.kotlinwebview.app.src.network.RetrofitClient

class PatientRepository {
    suspend fun findPatient(tag : String) : PatientResponse {
        return RetrofitClient.instance.getPatient(tag)
    }

}
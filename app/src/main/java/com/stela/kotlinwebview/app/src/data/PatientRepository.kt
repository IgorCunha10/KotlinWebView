package com.stela.kotlinwebview.app.src.data

import com.stela.kotlinwebview.app.src.model.PatientData
import com.stela.kotlinwebview.app.src.network.RetrofitClient

class PatientRepository {
    suspend fun findPatient(tag : String) : PatientData? {
        val response = RetrofitClient.instance.getPatient(tag)
        return response.firstOrNull()
    }

}
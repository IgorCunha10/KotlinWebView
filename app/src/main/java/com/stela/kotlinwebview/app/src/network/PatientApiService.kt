package com.stela.kotlinwebview.app.src.network

import com.stela.kotlinwebview.app.src.model.PatientData
import retrofit2.http.GET
import retrofit2.http.Query

interface PatientApiService {

    @GET("rehab_prontuario/pacienteTag.php")

    suspend fun getPatientByTag(@Query("tag") tag: String) : List<PatientData>

}
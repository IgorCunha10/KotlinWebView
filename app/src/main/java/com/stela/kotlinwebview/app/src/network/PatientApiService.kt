package com.stela.kotlinwebview.app.src.network

import com.stela.kotlinwebview.app.src.model.PatientResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface PatientApiService {

    @GET("rehab_prontuario/pacientes.php")

    suspend fun getPatient(@Query("tag") tag: String) : PatientResponse

}
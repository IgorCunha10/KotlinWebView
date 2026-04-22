package com.stela.kotlinwebview.app.src.model

import com.google.gson.annotations.SerializedName

data class PatientResponse(
    @SerializedName("status") val status: String,
    @SerializedName("pacientes") val patients: List<PatientData>
)
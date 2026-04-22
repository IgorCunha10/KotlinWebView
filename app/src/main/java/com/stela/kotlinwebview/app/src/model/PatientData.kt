package com.stela.kotlinwebview.app.src.model

import com.google.gson.annotations.SerializedName

data class PatientData(
    @SerializedName("Nome") val name: String,
    @SerializedName("Nascimento") val birth: String,
    @SerializedName("Tag") val tag: String,
    @SerializedName("Convenio") val covenant: String
)

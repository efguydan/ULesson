package com.efedaniel.ulesson.ulessonapp.models.api.reponse

import com.efedaniel.ulesson.ulessonapp.models.api.ApiSubject
import com.google.gson.annotations.SerializedName

data class SubjectResponse(
    @SerializedName("status")
    val status: String,
    @SerializedName("message")
    val message: String,
    @SerializedName("subjects")
    val subjects: List<ApiSubject>
)

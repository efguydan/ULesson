package com.efedaniel.ulesson.ulessonapp.models.api

import com.google.gson.annotations.SerializedName

data class ApiSubject(
    @SerializedName("name")
    val name: String,
)
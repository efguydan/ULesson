package com.efedaniel.ulesson.ulessonapp.models.api

import com.efedaniel.ulesson.extensions.EMPTY
import com.google.gson.annotations.SerializedName

data class ApiTutor(
    @SerializedName("firstname")
    val firstName: String? = String.EMPTY,
    @SerializedName("lastname")
    val lastName: String? = String.EMPTY
)

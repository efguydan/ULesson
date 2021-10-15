package com.efedaniel.ulesson.networkutils

import retrofit2.Response

const val GENERIC_ERROR_MESSAGE = "An error occurred, Please try again"
const val GENERIC_ERROR_CODE = "-1"

fun <T : Any> getAPIResult(response: Response<BaseApiResponse<T>>): Result<T> {
    if (response.isSuccessful) {
        val body = response.body()
        if (body?.data != null) {
            return Result.Success(body.data!!)
        }
    }
    // No sample error json found. so...
    // Fallback to regular status code and message
    return Result.Error(GENERIC_ERROR_CODE, GENERIC_ERROR_MESSAGE)
}

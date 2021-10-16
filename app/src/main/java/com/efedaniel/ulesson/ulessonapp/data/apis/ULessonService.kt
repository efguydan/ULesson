package com.efedaniel.ulesson.ulessonapp.data.apis

import com.efedaniel.ulesson.networkutils.BaseApiResponse
import com.efedaniel.ulesson.ulessonapp.models.api.ApiLesson
import retrofit2.Response
import retrofit2.http.GET

interface ULessonService {

    @GET("/api/v1/promoted")
    suspend fun getPromotedLessons(): Response<BaseApiResponse<List<ApiLesson>>>

    @GET("/api/v1/lessons")
    suspend fun getLiveLessons(): Response<BaseApiResponse<List<ApiLesson>>>

    @GET("/api/v1/lessons/me")
    suspend fun getMyLessons(): Response<BaseApiResponse<List<ApiLesson>>>
}

package com.efedaniel.ulesson.ulessonapp.data.apis

import com.efedaniel.ulesson.networkutils.BaseApiResponse
import com.efedaniel.ulesson.ulessonapp.models.api.reponse.SubjectResponse
import retrofit2.Response
import retrofit2.http.GET

interface ULessonService {

    @GET("/3p/api/content/grade")
    suspend fun getSubjects(): Response<BaseApiResponse<SubjectResponse>>
}

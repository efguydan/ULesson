package com.efedaniel.ulesson.ulessonapp.data.repositories

import com.efedaniel.ulesson.networkutils.GENERIC_ERROR_CODE
import com.efedaniel.ulesson.networkutils.GENERIC_ERROR_MESSAGE
import com.efedaniel.ulesson.networkutils.Result
import com.efedaniel.ulesson.networkutils.getAPIResult
import com.efedaniel.ulesson.ulessonapp.data.apis.ULessonService
import javax.inject.Inject

class ULessonRepository @Inject constructor(
    private val uLessonService: ULessonService,
    private val localRepository: LocalRepository
) {

    suspend fun getSubjects(): Result<Boolean> {
        return try {
            when (val result = getAPIResult(uLessonService.getSubjects())) {
                is Result.Success -> {
                    localRepository.insertSubjects(result.data.subjects)
                    Result.Success(true)
                }
                is Result.Error -> Result.Error(result.errorCode, result.errorMessage)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Result.Error(GENERIC_ERROR_CODE, GENERIC_ERROR_MESSAGE)
        }
    }
}

package com.efedaniel.ulesson.ulessonapp.data.repositories

import com.efedaniel.ulesson.networkutils.GENERIC_ERROR_CODE
import com.efedaniel.ulesson.networkutils.GENERIC_ERROR_MESSAGE
import com.efedaniel.ulesson.networkutils.Result
import com.efedaniel.ulesson.networkutils.getAPIResult
import com.efedaniel.ulesson.ulessonapp.data.apis.ULessonService
import com.efedaniel.ulesson.ulessonapp.models.api.toLessonModel
import com.efedaniel.ulesson.ulessonapp.models.api.toLocalLessonModel
import com.efedaniel.ulesson.ulessonapp.models.general.Lesson
import javax.inject.Inject

class ULessonRepository @Inject constructor(
    private val uLessonService: ULessonService,
    private val localRepository: LocalRepository
) {

    suspend fun getMyLessons(): Result<Boolean> {
        return try {
            when (val result = getAPIResult(uLessonService.getMyLessons())) {
                is Result.Success -> {
                    localRepository.cacheMyLessons(result.data.map { it.toLocalLessonModel() })
                    Result.Success(true)
                }
                is Result.Error -> Result.Error(result.errorCode, result.errorMessage)
            }
        } catch(e: Exception) {
            e.printStackTrace()
            Result.Error(GENERIC_ERROR_CODE, GENERIC_ERROR_MESSAGE)
        }
    }

    suspend fun getPromotedLessons(): Result<List<Lesson>> {
        return try {
            when (val result = getAPIResult(uLessonService.getPromotedLessons())) {
                is Result.Success -> Result.Success(result.data.map { it.toLessonModel() })
                is Result.Error -> Result.Error(result.errorCode, result.errorMessage)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Result.Error(GENERIC_ERROR_CODE, GENERIC_ERROR_MESSAGE)
        }
    }

    suspend fun getLiveLessons(): Result<List<Lesson>> {
        return try {
            when (val result = getAPIResult(uLessonService.getLiveLessons())) {
                is Result.Success -> Result.Success(result.data.map { it.toLessonModel() })
                is Result.Error -> Result.Error(result.errorCode, result.errorMessage)
            }
        } catch(e: Exception) {
            e.printStackTrace()
            Result.Error(GENERIC_ERROR_CODE, GENERIC_ERROR_MESSAGE)
        }
    }

}

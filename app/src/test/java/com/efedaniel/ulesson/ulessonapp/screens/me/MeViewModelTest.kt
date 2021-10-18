package com.efedaniel.ulesson.ulessonapp.screens.me

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.efedaniel.ulesson.networkutils.GENERIC_ERROR_CODE
import com.efedaniel.ulesson.networkutils.LoadingStatus
import com.efedaniel.ulesson.networkutils.Result
import com.efedaniel.ulesson.ulessonapp.data.repositories.ULessonRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.isA
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.verifyNoMoreInteractions
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class MeViewModelTest {

    private val testDispatcher = TestCoroutineDispatcher()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Mock private lateinit var repository: ULessonRepository

    private lateinit var viewModel: MeViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(testDispatcher)
    }

    @Test
    fun `Should post loading status success when load lessons call is successful`() {
        runBlockingTest {
            // Given
            val mockObserver: Observer<LoadingStatus> = mock()
            val expectedResult = true
            whenever(repository.getMyLessons()).thenReturn(Result.Success(expectedResult))

            // when
            viewModel = MeViewModel(repository)
            viewModel.loadingStatus.observeForever(mockObserver)

            // Then
            verify(mockObserver).onChanged(isA(LoadingStatus.Success::class.java))
            verifyNoMoreInteractions(mockObserver)
        }
    }

    @Test
    fun `Should show offline caching message when load lessons call is not successful`() {
        runBlockingTest {
            val mockObserver: Observer<LoadingStatus> = mock()
            val expectedResult = "No Internet Connection. Displaying Cached Lessons"
            whenever(repository.getMyLessons()).thenReturn(Result.Error(GENERIC_ERROR_CODE, expectedResult))

            // when
            viewModel = MeViewModel(repository)
            viewModel.loadingStatus.observeForever(mockObserver)

            // Then
            verify(mockObserver).onChanged(LoadingStatus.Error(expectedResult))
            verifyNoMoreInteractions(mockObserver)
        }
    }
}

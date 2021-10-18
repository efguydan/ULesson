package com.efedaniel.ulesson.ulessonapp.screens.live

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.efedaniel.ulesson.networkutils.Result
import com.efedaniel.ulesson.ulessonapp.data.repositories.ULessonRepository
import com.efedaniel.ulesson.ulessonapp.models.general.EMPTY
import com.efedaniel.ulesson.ulessonapp.models.general.Lesson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.verifyNoMoreInteractions
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class LiveViewModelTest {

    companion object {
        val lesson = Lesson.EMPTY
    }

    private val testDispatcher = TestCoroutineDispatcher()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Mock private lateinit var repository: ULessonRepository

    private lateinit var viewModel: LiveViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        Dispatchers.setMain(testDispatcher)
    }

    @Test
    fun `Should set live lessons to all gotten Live Lessons when Get Live Lessons API call is successful`() {
        runBlockingTest {
            // Given
            val expectedResult = listOf(lesson)
            val mockObserver: Observer<List<Lesson>> = mock()
            whenever(repository.getLiveLessons()).thenReturn(Result.Success(expectedResult))
            whenever(repository.getPromotedLessons()).thenReturn(Result.Success(expectedResult))

            // When
            viewModel = LiveViewModel(repository)
            viewModel.liveLessons.observeForever(mockObserver)

            // Then
            verify(mockObserver).onChanged(expectedResult)
            verifyNoMoreInteractions(mockObserver)
        }
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

}
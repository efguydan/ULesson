package com.efedaniel.ulesson.ulessonapp.screens.live

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.efedaniel.ulesson.networkutils.GENERIC_ERROR_CODE
import com.efedaniel.ulesson.networkutils.GENERIC_ERROR_MESSAGE
import com.efedaniel.ulesson.networkutils.LoadingStatus
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
import org.mockito.Mockito
import org.mockito.Mockito.isA
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.isA
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.verifyNoInteractions
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
            whenever(repository.getPromotedLessons()).thenReturn(Result.Success(emptyList()))

            // When
            viewModel = LiveViewModel(repository)
            viewModel.liveLessons.observeForever(mockObserver)

            // Then
            verify(mockObserver).onChanged(expectedResult)
            verifyNoMoreInteractions(mockObserver)
        }
    }

    @Test
    fun `Should set promoted lessons to all gotten Promoted Lessons when Get Promoted Lessons API call is successful`() {
        runBlockingTest {
            // Given
            val expectedResult = listOf(lesson)
            val mockObserver: Observer<List<Lesson>> = mock()
            whenever(repository.getLiveLessons()).thenReturn(Result.Success(emptyList()))
            whenever(repository.getPromotedLessons()).thenReturn(Result.Success(expectedResult))

            // When
            viewModel = LiveViewModel(repository)
            viewModel.promotedLessons.observeForever(mockObserver)

            // Then
            verify(mockObserver).onChanged(expectedResult)
            verifyNoMoreInteractions(mockObserver)
        }
    }

    @Test
    fun `Should post Error when Either of get Live and get Promoted Lessons is unsuccessful`() {
        runBlockingTest {
            // Given
            val expectedResult = listOf(lesson)
            val lessonsObserver: Observer<List<Lesson>> = mock()
            val loadingObserver: Observer<LoadingStatus> = mock()

            whenever(repository.getLiveLessons()).thenReturn(Result.Error(GENERIC_ERROR_CODE, GENERIC_ERROR_MESSAGE))
            whenever(repository.getPromotedLessons()).thenReturn(Result.Success(expectedResult))

            // When
            viewModel = LiveViewModel(repository)
            viewModel.promotedLessons.observeForever(lessonsObserver)
            viewModel.loadingStatus.observeForever(loadingObserver)

            // Then
            verifyNoInteractions(lessonsObserver)
            verify(loadingObserver).onChanged(isA(LoadingStatus.Error::class.java))
        }
    }

    @Test
    fun `Should filter Subjects when position is selected from spinner`() {
        runBlockingTest {
            // Given
            val lesson1 = lesson.copy(subjectName = "Biology")
            val lesson2 = lesson.copy(subjectName = "Physics")
            val lesson3 = lesson.copy(subjectName = "Biology")

            val expectedResult = listOf(lesson1, lesson2, lesson3)

            val mockObserver: Observer<List<Lesson>> = mock()

            whenever(repository.getLiveLessons()).thenReturn(Result.Success(expectedResult))
            whenever(repository.getPromotedLessons()).thenReturn(Result.Success(emptyList()))

            // When
            viewModel = LiveViewModel(repository)
            viewModel.liveLessons.observeForever(mockObserver)
            viewModel.onSubjectSelected(4)
            viewModel.onSubjectSelected(5)
            viewModel.onSubjectSelected(2)
            viewModel.onSubjectSelected(0)

            // Then
            verify(mockObserver, times(2)).onChanged(expectedResult)
            verify(mockObserver).onChanged(listOf(lesson1, lesson3))
            verify(mockObserver).onChanged(listOf(lesson2))
            verify(mockObserver).onChanged(emptyList())
            verifyNoMoreInteractions(mockObserver)
        }
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

}
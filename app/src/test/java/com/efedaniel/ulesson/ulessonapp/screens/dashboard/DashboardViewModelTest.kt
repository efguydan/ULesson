package com.efedaniel.ulesson.ulessonapp.screens.dashboard

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.efedaniel.ulesson.networkutils.LoadingStatus
import com.efedaniel.ulesson.networkutils.Result
import com.efedaniel.ulesson.ulessonapp.data.repositories.LocalRepository
import com.efedaniel.ulesson.ulessonapp.data.repositories.ULessonRepository
import com.efedaniel.ulesson.ulessonapp.models.local.LocalSubject
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import com.nhaarman.mockitokotlin2.verifyZeroInteractions
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
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class DashboardViewModelTest {

    @Mock private lateinit var uLessonRepository: ULessonRepository
    @Mock private lateinit var localRepository: LocalRepository
    @Mock private lateinit var mockObserver: Observer<LoadingStatus>
    @Mock private lateinit var localSubject: LocalSubject

    private val testDispatcher = TestCoroutineDispatcher()
    private lateinit var viewModel: DashboardViewModel

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        Dispatchers.setMain(testDispatcher)
        viewModel = DashboardViewModel(uLessonRepository, localRepository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    /**
     * This test tests the case when the viewModel.getSubjects method is called, the database is empty and
     * the API call was successful.
     *
     * In this situation, loading status should go through the loading state, and when the API returns a
     * success response, the loading status should go to the Success State.
     *
     * runBlockingTest is used for the suspend functions to avoid any delays and returns response from
     * any suspend function immediately
     */
    @Test
    fun getSubjects_whenThereIsNothingInTheDBAndAPICallWasSuccessful_ShouldShowLoadingAndDismissIt() = runBlockingTest {
        // Mock an empty list response from the db.
        Mockito.`when`(localRepository.getAllSubjects()).thenReturn(emptyList())
        // Mock a successful API call
        Mockito.`when`(uLessonRepository.getSubjects()).thenReturn(Result.Success(true))
        // Observe the loading status
        viewModel.loadingStatus.observeForever(mockObserver)

        viewModel.getSubjects()
        // Verify the observer went through the loading state
        verify(mockObserver).onChanged(Mockito.isA(LoadingStatus.Loading::class.java))
        // Verify the observer went through the Success state
        verify(mockObserver).onChanged(Mockito.isA(LoadingStatus.Success::class.java))
        // Verify no more interactions with the loading status observer
        verifyNoMoreInteractions(mockObserver)
    }

    /**
     * This test tests the case when the viewModel.getSubjects method is called, the database is empty and
     * the API call was not successful.
     *
     * In this situation, loading status should go through the loading state, but then go to the error state
     * instead of success state
     *
     * runBlockingTest is used for the suspend functions to avoid any delays and returns response from
     * any suspend function immediately
     */
    @Test
    fun getSubjects_whenThereIsNothingInTheDBAndAPICallWasNotSuccessful_ShouldShowLoadingAndShowError() = runBlockingTest {
        // Mock an empty list response from the db.
        Mockito.`when`(localRepository.getAllSubjects()).thenReturn(emptyList())
        // Mock a failed API call
        Mockito.`when`(uLessonRepository.getSubjects()).thenReturn(Result.Error("", ""))
        // Observe the loading status
        viewModel.loadingStatus.observeForever(mockObserver)

        viewModel.getSubjects()
        // Verify the observer went through the loading state
        verify(mockObserver).onChanged(Mockito.isA(LoadingStatus.Loading::class.java))
        // Verify the observer went through the Error state
        verify(mockObserver).onChanged(Mockito.isA(LoadingStatus.Error::class.java))
        // Verify no more interactions with the loading status observer
        verifyNoMoreInteractions(mockObserver)
    }

    /**
     * This test tests the case when the viewModel.getSubjects method is called, the database is not empty and
     * the API call was successful.
     *
     * In this situation, loading status should NOT go through the loading state as the data in the db
     * will already be shown to the user. It should however go to the success state
     *
     * runBlockingTest is used for the suspend functions to avoid any delays and returns response from
     * any suspend function immediately
     */
    @Test
    fun getSubjects_whenThereIsDataInTheDBAndAPICallWasSuccessful_ShouldLoadDataWithoutShowingLoading() = runBlockingTest {
        // Mock a non empty list response from the db.
        Mockito.`when`(localRepository.getAllSubjects()).thenReturn(listOf(localSubject))
        // Mock a successful API call
        Mockito.`when`(uLessonRepository.getSubjects()).thenReturn(Result.Success(true))
        // Observe the loading status
        viewModel.loadingStatus.observeForever(mockObserver)

        viewModel.getSubjects()
        // Verify the observer went through the loading state
        verify(mockObserver).onChanged(Mockito.isA(LoadingStatus.Success::class.java))
        // Verify no more interactions with the loading status observer
        verifyNoMoreInteractions(mockObserver)
    }

    /**
     * This test tests the case when the viewModel.getSubjects method is called, the database is not empty and
     * the API call was not successful.
     *
     * In this situation, the api call is expected to fail silently as local data is already being
     * displayed to the user
     *
     * runBlockingTest is used for the suspend functions to avoid any delays and returns response from
     * any suspend function immediately
     */
    @Test
    fun getSubjects_whenThereIsDataInTheDBAndAPICallWasNotSuccessful_ShouldFailSilently() = runBlockingTest {
        // Mock a non empty list response from the db.
        Mockito.`when`(localRepository.getAllSubjects()).thenReturn(listOf(localSubject))
        // Mock a successful API call
        Mockito.`when`(uLessonRepository.getSubjects()).thenReturn(Result.Error("", ""))
        // Observe the loading status
        viewModel.loadingStatus.observeForever(mockObserver)

        viewModel.getSubjects()
        // Verify zero interactions with the loading status observer
        verifyZeroInteractions(mockObserver)
    }
}

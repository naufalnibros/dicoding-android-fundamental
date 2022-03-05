package com.naufalnibros.submission_fundamental.ui.main.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.naufalnibros.submission_fundamental.repository.user.User
import com.naufalnibros.submission_fundamental.utils.DummyData
import com.naufalnibros.submission_fundamental.utils.ResourceHelper
import com.naufalnibros.submission_fundamental.utils.RxImmediateSchedulerRule
import com.nhaarman.mockitokotlin2.verify
import io.reactivex.Observable
import junit.framework.TestCase.assertTrue
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class HomeViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var rxImmediateSchedulerRule = RxImmediateSchedulerRule()

    @Mock
    private lateinit var observer: Observer<HomeState>

    @Mock
    private lateinit var useCase: HomeUseCase

    private val resource = ResourceHelper.loadJson<List<User>>(DummyData.resourceListUser)

    private lateinit var viewModel: HomeViewModel

    @Before
    fun setup() {
        resource?.let {
            `when`(useCase.list()).thenReturn(Observable.just(it))

            viewModel = HomeViewModel(useCase)

            verify(useCase).list()
        }
    }

    @Test
    fun `make sure observer UseCase list user yg diterima sesuai tanpa error`() {
        resource?.let {
            useCase.list()
                .test()
                .assertSubscribed()
                .assertValues(it)
                .assertComplete()
                .assertNoErrors()
        }
    }

    @Test
    fun `make sure OnSuccess state, getting data from UseCase and observe to livedata`() {
        val homeState = MutableLiveData<HomeState>()
        resource?.let {
            homeState.value = HomeState.OnSuccess(list = it)
            val users = viewModel.stateList.value

            assertNotNull(users)
            assertTrue(users is HomeState.OnSuccess)

            viewModel.stateList.observeForever(observer)
            Mockito.verify(observer).onChanged(homeState.value)
        }
    }

}
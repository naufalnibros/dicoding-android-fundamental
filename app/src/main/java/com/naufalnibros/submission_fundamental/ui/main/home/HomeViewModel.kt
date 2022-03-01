package com.naufalnibros.submission_fundamental.ui.main.home

import androidx.lifecycle.MutableLiveData
import com.naufalnibros.submission_fundamental.base.BaseViewModel
import com.naufalnibros.submission_fundamental.repository.user.User
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import java.util.concurrent.TimeUnit

/**
 * Created by @naufalnibros on 26/02/22.
 */
class HomeViewModel(private val useCase: HomeUseCase): BaseViewModel() {

    private val _stateList = MutableLiveData<HomeState>()

    val stateList get() = _stateList

    private val subscribeSearch: PublishSubject<String> = PublishSubject.create()

    init {
        getList()

        subscribeSearch
            .debounce(800, TimeUnit.MILLISECONDS)
            .distinct()
            .filter { text -> text.isNotBlank() }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { getSearch(it) }.disposeOnCleared()
    }

    fun refresh() {
        getList()
    }

    fun search(keyword: String) {
        subscribeSearch.onNext(keyword)
    }

    private fun getSearch(keyword: String) {
        useCase.search(keyword)
            .subscribeOn(Schedulers.io())
            .doOnSubscribe {
                _stateList.postValue(HomeState.OnLoading)
            }.subscribe({
                _stateList.postValue(HomeState.OnSuccess(it))
            }, {
                _stateList.postValue(HomeState.OnError(it?.message ?: "Terjadi Kesalahan"))
            }).disposeOnCleared()
    }

    private fun getList() {
        useCase.list()
            .subscribeOn(Schedulers.io())
            .doOnSubscribe {
                _stateList.postValue(HomeState.OnLoading)
            }.subscribe({
                _stateList.postValue(HomeState.OnSuccess(it))
            }, {
                _stateList.postValue(HomeState.OnError(it?.message ?: "Terjadi Kesalahan"))
            }).disposeOnCleared()
    }

}

sealed class HomeState {
    object OnLoading : HomeState()
    data class OnSuccess(val list: List<User>) : HomeState()
    data class OnError(val message: String) : HomeState()
}
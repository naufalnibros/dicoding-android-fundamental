package com.naufalnibros.submission_fundamental.ui.main.profile

import androidx.lifecycle.MutableLiveData
import com.naufalnibros.submission_fundamental.base.BaseViewModel
import com.naufalnibros.submission_fundamental.repository.user.User
import io.reactivex.schedulers.Schedulers

/**
 * Created by @naufalnibros on 26/02/22.
 */
class ProfileViewModel(private val useCase: ProfileUseCase): BaseViewModel() {

    private val _state = MutableLiveData<ProfileState>()

    val state get() = _state

    fun detail(username: String) {
        useCase.detail(username)
            .subscribeOn(Schedulers.io())
            .doOnSubscribe {
                _state.postValue(ProfileState.OnLoading)
            }.subscribe({
                _state.postValue(ProfileState.OnSuccess(it))
            }, {
                _state.postValue(ProfileState.OnError(it?.message ?: "Terjadi Kesalahan"))
            }).disposeOnCleared()

    }

}

sealed class ProfileState {
    object OnLoading : ProfileState()
    data class OnSuccess(val user: User) : ProfileState()
    data class OnError(val message: String) : ProfileState()
}
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

    private val _favoriteState = MutableLiveData<FavoriteState>()

    val state get() = _state

    val favoriteState get() = _favoriteState

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

    fun favorite(user: User) {
        useCase.favorite(user)
            .subscribeOn(Schedulers.io())
            .subscribe({
                _favoriteState.postValue(FavoriteState.OnSaved)
            }, {
                _favoriteState.postValue(FavoriteState.OnError(it.message ?: ""))
            })
            .disposeOnCleared()
    }

    fun find(username: String) {
        useCase.find(username)
            .subscribeOn(Schedulers.io())
            .doOnSubscribe {
                _favoriteState.postValue(FavoriteState.NotFound)
            }
            .subscribe({
                _favoriteState.postValue(FavoriteState.OnSaved)
            }, {
                _favoriteState.postValue(FavoriteState.OnError(it.message ?: ""))
            })
            .disposeOnCleared()
    }

    fun delete(username: String) {
        useCase.delete(username)
            .subscribeOn(Schedulers.io())
            .subscribe({
                _favoriteState.postValue(FavoriteState.NotFound)
            }, {
                _favoriteState.postValue(FavoriteState.OnError(it.message ?: ""))
            })
            .disposeOnCleared()
    }

}

sealed class ProfileState {
    object OnLoading : ProfileState()
    data class OnSuccess(val user: User) : ProfileState()
    data class OnError(val message: String) : ProfileState()
}

sealed class FavoriteState {
    object NotFound : FavoriteState()
    object OnSaved : FavoriteState()
    data class OnError(val message: String) : FavoriteState()
}
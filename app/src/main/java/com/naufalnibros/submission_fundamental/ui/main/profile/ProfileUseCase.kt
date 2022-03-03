package com.naufalnibros.submission_fundamental.ui.main.profile

import com.naufalnibros.submission_fundamental.core.local.user.UserFavoriteScema
import com.naufalnibros.submission_fundamental.repository.user.User
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable

interface ProfileUseCase {

    fun detail(username: String): Observable<User>

    fun favorite(user: User): Completable

    fun find(username: String): Flowable<UserFavoriteScema>

    fun delete(username: String): Completable

}
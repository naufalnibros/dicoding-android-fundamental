package com.naufalnibros.submission_fundamental.ui.main.favorite

import com.naufalnibros.submission_fundamental.repository.user.User
import io.reactivex.Completable
import io.reactivex.Flowable

interface FavoriteUseCase {

    fun list(): Flowable<List<User>>

    fun truncate(): Completable

}
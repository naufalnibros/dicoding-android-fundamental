package com.naufalnibros.submission_fundamental.ui.main.favorite

import com.naufalnibros.submission_fundamental.repository.user.User
import com.naufalnibros.submission_fundamental.repository.user.UserRepository
import com.naufalnibros.submission_fundamental.repository.user.toListUser
import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers

class FavoriteUseCaseImplement(val repository: UserRepository) : FavoriteUseCase {

    override fun list(): Flowable<List<User>> = repository.favorites()
        .subscribeOn(Schedulers.io())
        .flatMap { database -> database.toListUser() }

}
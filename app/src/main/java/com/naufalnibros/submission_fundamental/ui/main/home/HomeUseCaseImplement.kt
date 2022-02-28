package com.naufalnibros.submission_fundamental.ui.main.home

import com.naufalnibros.submission_fundamental.core.remote.response.mapToList
import com.naufalnibros.submission_fundamental.repository.user.User
import com.naufalnibros.submission_fundamental.repository.user.UserRepository
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

class HomeUseCaseImplement(private val repository: UserRepository): HomeUseCase {

    override fun search(keyword: String): Observable<List<User>> {
        return repository.search(keyword)
            .subscribeOn(Schedulers.io())
            .flatMap { response -> response.mapToList()  }
    }

    override fun list(): Observable<List<User>> = repository.list()

}
package com.naufalnibros.submission_fundamental.ui.main.home

import com.naufalnibros.submission_fundamental.repository.user.User
import io.reactivex.Observable

interface HomeUseCase {

    fun search(keyword: String): Observable<List<User>>

    fun list(): Observable<List<User>>

}
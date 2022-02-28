package com.naufalnibros.submission_fundamental.ui.main.profile

import com.naufalnibros.submission_fundamental.repository.user.User
import io.reactivex.Observable

interface ProfileUseCase {
    fun detail(username: String): Observable<User>
}
package com.naufalnibros.submission_fundamental.ui.main.profile.tab

import com.naufalnibros.submission_fundamental.repository.user.User
import io.reactivex.Observable

interface ProfileTabUseCase {
    fun follower(username: String): Observable<List<User>>
    fun following(username: String): Observable<List<User>>
}
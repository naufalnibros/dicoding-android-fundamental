package com.naufalnibros.submission_fundamental.ui.main.profile.tab

import com.naufalnibros.submission_fundamental.repository.user.UserRepository

class ProfileTabUseCaseImplement(private val repository: UserRepository): ProfileTabUseCase {

    override fun follower(username: String) = repository.followers(username)

    override fun following(username: String) = repository.following(username)

}
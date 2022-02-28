package com.naufalnibros.submission_fundamental.ui.main.profile

import com.naufalnibros.submission_fundamental.repository.user.UserRepository

class ProfileUseCaseImplement(private val repository: UserRepository): ProfileUseCase {

    override fun detail(username: String) = repository.detail(username)

}
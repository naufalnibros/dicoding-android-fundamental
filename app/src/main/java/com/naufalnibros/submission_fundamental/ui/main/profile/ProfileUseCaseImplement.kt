package com.naufalnibros.submission_fundamental.ui.main.profile

import com.naufalnibros.submission_fundamental.repository.user.User
import com.naufalnibros.submission_fundamental.repository.user.UserRepository

class ProfileUseCaseImplement(private val repository: UserRepository): ProfileUseCase {

    override fun detail(username: String) = repository.detail(username)

    override fun favorite(user: User) = repository.favorite(user)

    override fun find(username: String) = repository.favorite(username)

    override fun delete(username: String) = repository.deleteFromFavorite(username)
}
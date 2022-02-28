package com.naufalnibros.submission_fundamental.repository.user

import com.naufalnibros.submission_fundamental.core.local.dao.UserDao
import com.naufalnibros.submission_fundamental.core.remote.services.UserServices

class UserRepository(
    private val service: UserServices,
    private val dao: UserDao
) {
    /** https://api.github.com/users */
    fun list() = service.users()

    /** https://api.github.com/search/users?q=keyword */
    fun search(keyword: String) = service.users(keyword)

    /** https://api.github.com/users/username */
    fun detail(username: String) = service.user(username)

}
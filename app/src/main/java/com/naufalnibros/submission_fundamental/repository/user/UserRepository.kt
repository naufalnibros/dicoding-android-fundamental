package com.naufalnibros.submission_fundamental.repository.user

import com.naufalnibros.submission_fundamental.core.remote.services.UserServices

class UserRepository(
    private val service: UserServices) {
    /** https://api.github.com/users */
    fun list() = service.users()

    /** https://api.github.com/users/naufalnibros/followers **/
    fun followers(username: String) = service.followers(username)

    /** https://api.github.com/users/naufalnibros/following **/
    fun following(username: String) = service.following(username)

    /** https://api.github.com/search/users?q=keyword */
    fun search(keyword: String) = service.search(keyword)

    /** https://api.github.com/users/username */
    fun detail(username: String) = service.user(username)
}
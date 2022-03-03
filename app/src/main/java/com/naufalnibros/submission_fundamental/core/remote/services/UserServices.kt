package com.naufalnibros.submission_fundamental.core.remote.services

import com.naufalnibros.submission_fundamental.core.remote.response.UserSearchResponse
import com.naufalnibros.submission_fundamental.repository.user.User
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface UserServices {

    @GET("users")
    fun users(): Observable<List<User>>

    @GET("users/{username}/followers")
    fun followers(@Path("username") hateoas: String): Observable<List<User>>

    @GET("users/{username}/following")
    fun following(@Path("username") hateoas: String): Observable<List<User>>

    @GET("search/users")
    fun search(@Query("q") keyword: String): Observable<UserSearchResponse>

    @GET("users/{username}")
    fun user(@Path("username") username: String): Observable<User>

}
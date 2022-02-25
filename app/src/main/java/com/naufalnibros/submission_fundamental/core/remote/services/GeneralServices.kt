package com.naufalnibros.submission_fundamental.core.remote.services

import com.google.gson.JsonObject
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Path

interface GeneralServices {

    @GET("{path}")
    fun getService(@Path(value = "path", encoded = true) path: String,
                   @Body body: JsonObject): Observable<JsonObject>

}
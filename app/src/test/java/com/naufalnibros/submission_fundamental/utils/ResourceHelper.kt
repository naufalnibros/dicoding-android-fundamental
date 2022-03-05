package com.naufalnibros.submission_fundamental.utils

import com.google.gson.GsonBuilder


object ResourceHelper {

    inline fun <reified T> loadJson(resource: String): T? {
        return try {
            GsonBuilder().create().fromJson(resource, T::class.java)
        } catch (e: Exception) {
            null
        }
    }
}
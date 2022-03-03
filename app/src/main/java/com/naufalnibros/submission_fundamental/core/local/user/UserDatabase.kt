package com.naufalnibros.submission_fundamental.core.local.user

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [
        UserFavoriteScema::class
    ], version = 1, exportSchema = false
)
abstract class UserDatabase: RoomDatabase()  {
    abstract fun user(): UserDao
}
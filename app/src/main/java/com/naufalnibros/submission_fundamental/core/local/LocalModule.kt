package com.naufalnibros.submission_fundamental.core.local

import androidx.room.Room
import com.naufalnibros.submission_fundamental.core.local.user.UserDatabase
import com.naufalnibros.submission_fundamental.core.local.user.UserLocalConfig
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val daoModule = module {
    factory { get<UserDatabase>().user() }
}

val localModule = module {
    single {
        Room.databaseBuilder(androidApplication(), UserDatabase::class.java, UserLocalConfig.DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }
}
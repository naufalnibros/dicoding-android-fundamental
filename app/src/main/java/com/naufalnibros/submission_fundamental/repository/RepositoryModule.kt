package com.naufalnibros.submission_fundamental.repository

import com.naufalnibros.submission_fundamental.core.local.dao.UserDao
import com.naufalnibros.submission_fundamental.repository.user.UserRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { UserRepository(get(), UserDao()) }
}
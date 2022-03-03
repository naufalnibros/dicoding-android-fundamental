package com.naufalnibros.submission_fundamental.base

import android.app.Application
import com.naufalnibros.submission_fundamental.BuildConfig
import com.naufalnibros.submission_fundamental.core.local.localModule
import com.naufalnibros.submission_fundamental.core.remote.errorHandleModule
import com.naufalnibros.submission_fundamental.core.remote.remoteModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.core.module.Module

abstract class BaseApplication: Application() {

    abstract fun defineDependencies(): List<Module>

    override fun onCreate() {
        super.onCreate()
        dependenciesInjection()
    }

    private fun dependenciesInjection() {
        startKoin {

            androidLogger( if (BuildConfig.DEBUG) Level.ERROR else Level.NONE )

            androidContext(this@BaseApplication)

            modules(
                mutableListOf(remoteModule, localModule, errorHandleModule)
                    .apply { addAll(defineDependencies()) }
            )
        }
    }

}
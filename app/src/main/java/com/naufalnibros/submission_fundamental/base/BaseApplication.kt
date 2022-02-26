package com.naufalnibros.submission_fundamental.base

import android.app.Application
import android.content.SharedPreferences
import com.naufalnibros.submission_fundamental.BuildConfig
import com.naufalnibros.submission_fundamental.core.remote.errorHandleModule
import com.naufalnibros.submission_fundamental.core.remote.remoteModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.core.module.Module

abstract class BaseApplication: Application() {

    abstract fun defineDependencies(): List<Module>

    companion object {
        lateinit var gantiDataStore: SharedPreferences
    }

    override fun onCreate() {
        super.onCreate()
        dependenciesInjection()
        initLogging()
    }

    private fun dependenciesInjection() {
        startKoin {

            androidLogger( if (BuildConfig.DEBUG) Level.DEBUG else Level.NONE )

            androidContext(this@BaseApplication)

            modules(
                mutableListOf(remoteModule, errorHandleModule)
                    .apply { addAll(defineDependencies()) }
            )
        }
    }

    /**
     * TODO Init logging
     */
    private fun initLogging() {
        if (BuildConfig.DEBUG) {
            // Timber.plant(get())
        } else {
            // Do something Init crashlytics
        }
    }

}
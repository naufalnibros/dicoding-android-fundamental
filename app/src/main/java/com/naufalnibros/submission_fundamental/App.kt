package com.naufalnibros.submission_fundamental

import com.naufalnibros.submission_fundamental.base.BaseApplication
import com.naufalnibros.submission_fundamental.core.local.daoModule
import com.naufalnibros.submission_fundamental.core.preference.preferencesModul
import com.naufalnibros.submission_fundamental.core.remote.serviceModule
import com.naufalnibros.submission_fundamental.repository.repositoryModule
import com.naufalnibros.submission_fundamental.ui.viewModelModule
import org.koin.core.module.Module

/**
 * Created by @naufalnibros on 26/02/22.
 */
class App: BaseApplication() {
    override fun defineDependencies(): List<Module> {
        return listOf(
            daoModule,
            serviceModule,
            preferencesModul,
            repositoryModule,
            viewModelModule
        )
    }
}
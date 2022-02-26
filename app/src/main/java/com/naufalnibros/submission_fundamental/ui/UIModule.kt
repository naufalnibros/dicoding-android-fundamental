package com.naufalnibros.submission_fundamental.ui

import com.naufalnibros.submission_fundamental.ui.main.home.HomeViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.component.KoinApiExtension
import org.koin.dsl.module

/**
 * Created by @naufalnibros on 26/02/22.
 */
@OptIn(KoinApiExtension::class)
val viewModelModule = module {
    viewModel { HomeViewModel() }
}
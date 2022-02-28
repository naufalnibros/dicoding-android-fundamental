package com.naufalnibros.submission_fundamental.ui

import com.naufalnibros.submission_fundamental.ui.main.home.HomeUseCase
import com.naufalnibros.submission_fundamental.ui.main.home.HomeUseCaseImplement
import com.naufalnibros.submission_fundamental.ui.main.home.HomeViewModel
import com.naufalnibros.submission_fundamental.ui.main.profile.ProfileUseCase
import com.naufalnibros.submission_fundamental.ui.main.profile.ProfileUseCaseImplement
import com.naufalnibros.submission_fundamental.ui.main.profile.ProfileViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.component.KoinApiExtension
import org.koin.dsl.module

/**
 * Created by @naufalnibros on 26/02/22.
 */
@OptIn(KoinApiExtension::class)
val viewModelModule = module {
    single<HomeUseCase> { HomeUseCaseImplement(get()) }
    viewModel { HomeViewModel(get()) }

    single<ProfileUseCase> { ProfileUseCaseImplement(get()) }
    viewModel { ProfileViewModel(get()) }
}
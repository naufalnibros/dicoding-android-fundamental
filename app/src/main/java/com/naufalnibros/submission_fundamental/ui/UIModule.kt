package com.naufalnibros.submission_fundamental.ui

import com.naufalnibros.submission_fundamental.ui.main.home.HomeUseCase
import com.naufalnibros.submission_fundamental.ui.main.home.HomeUseCaseImplement
import com.naufalnibros.submission_fundamental.ui.main.home.HomeViewModel
import com.naufalnibros.submission_fundamental.ui.main.profile.ProfileUseCase
import com.naufalnibros.submission_fundamental.ui.main.profile.ProfileUseCaseImplement
import com.naufalnibros.submission_fundamental.ui.main.profile.ProfileViewModel
import com.naufalnibros.submission_fundamental.ui.main.profile.tab.ProfileTabUseCase
import com.naufalnibros.submission_fundamental.ui.main.profile.tab.ProfileTabUseCaseImplement
import com.naufalnibros.submission_fundamental.ui.main.profile.tab.ProfileTabViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Created by @naufalnibros on 26/02/22.
 */
val viewModelModule = module {
    single<HomeUseCase> { HomeUseCaseImplement(get()) }
    viewModel { HomeViewModel(get()) }

    single<ProfileUseCase> { ProfileUseCaseImplement(get()) }
    viewModel { ProfileViewModel(get()) }

    single<ProfileTabUseCase> { ProfileTabUseCaseImplement(get()) }
    viewModel { ProfileTabViewModel(get()) }
}
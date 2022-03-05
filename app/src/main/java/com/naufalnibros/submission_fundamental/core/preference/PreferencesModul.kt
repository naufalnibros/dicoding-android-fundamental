package com.naufalnibros.submission_fundamental.core.preference

import android.content.Context
import androidx.datastore.preferences.preferencesDataStore
import com.naufalnibros.submission_fundamental.core.preference.datastores.DataStoreThemeUI
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

private val Context.dataStoreThemeUI by preferencesDataStore(name = "setting_theme_ui.pref")

val preferencesModul = module {

    single {
        DataStoreThemeUI(androidApplication().dataStoreThemeUI)
    }

}
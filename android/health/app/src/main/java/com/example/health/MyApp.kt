package com.example.health

import android.app.Application
import com.example.health.network.ApiModule
import com.example.health.view.calendar.CalendarViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class MyApp: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyApp)
            modules(appModule)
        }
    }
}

val appModule = module {
    single { ApiModule.provideApiService() }

    viewModel { CalendarViewModel() }
}
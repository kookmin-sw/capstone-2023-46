package com.example.health

import android.app.Application
import com.example.health.network.ApiModule
import com.example.health.view.calendar.CalendarViewModel
import com.example.health.view.calendar.record.RecordViewModel
import com.example.health.view.routine.RoutineViewModel
import com.example.health.view.signin.SignInViewModel
import com.example.health.view.signup.SignUpViewModel
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
    viewModel { RecordViewModel() }
    viewModel { RoutineViewModel() }
    viewModel { SignUpViewModel(get()) }
    viewModel { SignInViewModel(get()) }
}
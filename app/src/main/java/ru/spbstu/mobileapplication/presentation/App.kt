package ru.spbstu.mobileapplication.presentation

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import ru.spbstu.mobileapplication.di.ApplicationComponent
import ru.spbstu.mobileapplication.di.DaggerApplicationComponent


@HiltAndroidApp
class App : Application() {

    val component : ApplicationComponent by lazy {
        DaggerApplicationComponent.factory().create(this)
    }
}


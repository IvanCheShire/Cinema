package ru.geekbrains.cinema.ui

import android.app.Application
import ru.geekbrains.cinema.di.AppComponent
import ru.geekbrains.cinema.di.DaggerAppComponent
import ru.geekbrains.cinema.di.modules.AppModule

class App: Application() {

    companion object {
        lateinit var instance: App
    }

    lateinit var appComponent: AppComponent
        private set

    override fun onCreate() {
        super.onCreate()
        instance = this
        appComponent =  DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }

}
package ru.geekbrains.cinema.di.modules

import dagger.Module
import dagger.Provides
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import ru.geekbrains.cinema.ui.App


@Module
class AppModule(val app: App) {

    @Provides
    fun app(): App {
        return app
    }

    @Provides
    fun uiScheduler(): Scheduler = AndroidSchedulers.mainThread()
}
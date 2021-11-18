package ru.geekbrains.cinema.di.modules

import dagger.Module
import dagger.Provides
import ru.geekbrains.cinema.mvp.model.api.IDataSource
import ru.geekbrains.cinema.mvp.model.repository.IMoviesRepository
import ru.geekbrains.cinema.mvp.model.repository.retrofit.RetrofitMoviesRepository

@Module
class MoviesScreenModule {

    @Provides
    fun moviesRepository(api: IDataSource): IMoviesRepository =
        RetrofitMoviesRepository(api)

}
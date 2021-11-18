package ru.geekbrains.cinema.mvp.model.repository.retrofit

import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.geekbrains.cinema.mvp.model.api.IDataSource
import ru.geekbrains.cinema.mvp.model.entity.Movie
import ru.geekbrains.cinema.mvp.model.repository.IMoviesRepository

class RetrofitMoviesRepository(val api: IDataSource): IMoviesRepository {

    override fun getMovies(page: Int): Single<List<Movie>> =
        api.getPopularMovies(MOVIE_DB_API_KEY, page).flatMap { response ->
            Single.just(response.results)
        }.subscribeOn(Schedulers.io())

}
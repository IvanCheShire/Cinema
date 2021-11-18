package ru.geekbrains.cinema.mvp.model.repository

import io.reactivex.rxjava3.core.Single
import ru.geekbrains.cinema.mvp.model.entity.Movie

interface IMoviesRepository {
    fun getMovies(page: Int): Single<List<Movie>>
}
package ru.geekbrains.cinema.mvp.model.api

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import ru.geekbrains.cinema.mvp.model.entity.MovieDBPopularMoviesResponse
import ru.geekbrains.cinema.mvp.model.entity.MovieDetails

interface IDataSource {

    fun getPopularMovies(@Query("api_key") apiKey: String, @Query("page") page: Int): Single<MovieDBPopularMoviesResponse>

    @GET(value = "movie/{movieId}")
    fun getMovieDetails(@Path("movieId") movieId: Int, @Query("api_key") apiKey: String): Single<MovieDetails>

}
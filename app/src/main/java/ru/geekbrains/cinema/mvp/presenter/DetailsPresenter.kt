package ru.geekbrains.cinema.mvp.presenter

import moxy.MvpPresenter
import ru.geekbrains.cinema.mvp.model.entity.Movie
import ru.geekbrains.cinema.mvp.view.DetailsView


class DetailsPresenter(val movie: Movie): MvpPresenter<DetailsView>()  {


    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        viewState.setTitle(movie.title)
        movie.overview?.let { viewState.setAbout(movie.overview) }
        movie.posterPath?.let { viewState.loadImage(movie.posterPath) }
        if (movie.backdropPath != null)  viewState.loadBackdropImage(movie.backdropPath)
        else viewState.loadBackdropImage(movie.posterPath)
    }

}
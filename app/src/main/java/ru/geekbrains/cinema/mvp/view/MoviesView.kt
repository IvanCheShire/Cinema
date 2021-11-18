package ru.geekbrains.cinema.mvp.view

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle
import moxy.viewstate.strategy.alias.Skip
import ru.geekbrains.cinema.mvp.model.entity.Movie

@AddToEndSingle
interface MoviesView: MvpView {
    fun init()
    fun updateMoviesList()
    fun scrollListToCurrentPosition(currentItem: Int)
    @Skip
    fun openDetailsFragment(movie: Movie)
}
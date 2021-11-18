package ru.geekbrains.cinema.mvp.presenter

import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.CompositeDisposable
import moxy.MvpPresenter
import ru.geekbrains.cinema.mvp.model.entity.Movie
import ru.geekbrains.cinema.mvp.model.repository.IMoviesRepository
import ru.geekbrains.cinema.mvp.presenter.list.IMoviesListPresenter
import ru.geekbrains.cinema.mvp.view.MoviesView
import ru.geekbrains.cinema.mvp.view.list.MovieItemView
import javax.inject.Inject

class MoviesPresenter(): MvpPresenter<MoviesView>() {

    @Inject lateinit var moviesRepository: IMoviesRepository
    @Inject lateinit var uiScheduler: Scheduler

    class MoviesListPresenter : IMoviesListPresenter {
        override var itemClickListener: ((MovieItemView) -> Unit)? = null

        var movies = mutableListOf<Movie>()

        override fun bindView(view: MovieItemView) {
            val movie = movies[view.pos]
            view.setTitle(movie.title)
            println("FILM: ${movie.title}")
            movie.posterPath?.let { view.loadImage(it) }
        }
        override fun getCount() = movies.size

        var currentItem: Int = 0
    }

    val moviesListPresenter = MoviesListPresenter()
    var disposables = CompositeDisposable()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadMovies(1)

        moviesListPresenter.itemClickListener = { movieItemView ->
            moviesListPresenter.currentItem = movieItemView.pos
            val movie = moviesListPresenter.movies[movieItemView.pos]
            viewState.openDetailsFragment(movie)
        }
    }

    fun loadMovies(page: Int) {
        disposables.add(moviesRepository.getMovies(page)
            .retry(3)
            .observeOn(uiScheduler)
            .subscribe(
                {
                    moviesListPresenter.movies.clear()
                    moviesListPresenter.movies.addAll(it)
                    println("FIRST FILM: ${moviesListPresenter.movies.first().title}")
                    viewState.updateMoviesList()
                },
                { println("onError: ${it.message}") }))
    }


    override fun onDestroy() {
        super.onDestroy()
        disposables.dispose()
    }

    fun onViewCreated() {
        viewState.scrollListToCurrentPosition(moviesListPresenter.currentItem)
    }
}
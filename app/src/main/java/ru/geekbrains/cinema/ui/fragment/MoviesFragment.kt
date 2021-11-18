package ru.geekbrains.cinema.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.fragment_movies.*
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.geekbrains.cinema.R
import ru.geekbrains.cinema.mvp.model.entity.Movie
import ru.geekbrains.cinema.mvp.presenter.MoviesPresenter
import ru.geekbrains.cinema.mvp.view.MoviesView
import ru.geekbrains.cinema.ui.App
import ru.geekbrains.cinema.ui.adapter.EndlessRecyclerViewScrollListener
import ru.geekbrains.cinema.ui.adapter.MoviesRvAdapter

class MoviesFragment: MvpAppCompatFragment(), MoviesView {

    val presenter: MoviesPresenter by moxyPresenter {
        MoviesPresenter().apply{ App.instance.appComponent.inject(this)}
    }

    private var adapter: MoviesRvAdapter? = null
    lateinit var scrollListener: EndlessRecyclerViewScrollListener

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_movies, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.onViewCreated()
    }

    override fun init() {
        if (adapter == null) adapter = MoviesRvAdapter(presenter.moviesListPresenter).apply { App.instance.appComponent.inject(
            this
        )}
        val gridLayoutManager = GridLayoutManager(context, 2)
        movies_recyclerview.layoutManager = gridLayoutManager
        scrollListener = EndlessRecyclerViewScrollListener(
            (presenter::loadMovies),
            gridLayoutManager
        )
        movies_recyclerview.addOnScrollListener(scrollListener)
        movies_recyclerview.adapter = adapter

    }

    override fun updateMoviesList() {
        println("NEW FILM LIST, first: ${presenter.moviesListPresenter.movies.first().title}")
        adapter?.notifyDataSetChanged()
    }
    override fun scrollListToCurrentPosition(currentItem: Int) {
        movies_recyclerview?.layoutManager?.scrollToPosition(currentItem)
    }
    override fun openDetailsFragment(movie: Movie) {
        val action = MoviesFragmentDirections.actionMoviesFragmentToDetailsFragment(movie)
        findNavController().navigate(action)
    }

}
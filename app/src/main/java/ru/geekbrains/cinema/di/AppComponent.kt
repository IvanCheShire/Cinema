package ru.geekbrains.cinema.di

import dagger.Component
import ru.geekbrains.cinema.di.modules.*
import ru.geekbrains.cinema.mvp.presenter.DetailsPresenter
import ru.geekbrains.cinema.mvp.presenter.MainPresenter
import ru.geekbrains.cinema.mvp.presenter.MoviesPresenter
import ru.geekbrains.cinema.ui.activity.MainActivity
import ru.geekbrains.cinema.ui.adapter.MoviesRvAdapter
import ru.geekbrains.cinema.ui.fragment.DetailsFragment
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AppModule::class,
    NavigationModule::class,
    ApiModule::class,
    ImageLoaderModule::class,
    MoviesScreenModule::class,
    DetailsScreenModule::class
])
interface AppComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(mainPresenter: MainPresenter)
    fun inject(moviesPresenter: MoviesPresenter)
    fun inject(moviesRvAdapter: MoviesRvAdapter)
    fun inject(detailsFragment: DetailsFragment)
    fun inject(detailsPresenter: DetailsPresenter)
}
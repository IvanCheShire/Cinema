package ru.geekbrains.cinema.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.navigation.fragment.navArgs
import kotlinx.android.synthetic.main.fragment_details.*
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.geekbrains.cinema.R
import ru.geekbrains.cinema.mvp.model.imageloader.IImageLoader
import ru.geekbrains.cinema.mvp.presenter.DetailsPresenter
import ru.geekbrains.cinema.mvp.view.DetailsView
import ru.geekbrains.cinema.ui.App
import javax.inject.Inject

class DetailsFragment: MvpAppCompatFragment(), DetailsView {

    private val args: DetailsFragmentArgs  by navArgs()

    @Inject
    lateinit var imageLoader: IImageLoader<ImageView>


    val presenter: DetailsPresenter by moxyPresenter {
        val movie = args.movie
        DetailsPresenter(movie).apply { App.instance.appComponent.inject(this) }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
        View.inflate(context, R.layout.fragment_details, null)

    override fun init() {
        App.instance.appComponent.inject(this)
    }

    override fun setTitle (title: String) { tv_name.text = title }
    override fun setAbout(overview: String) { tv_about.text = overview }
    override fun loadImage(posterPath: String) = imageLoader.loadWithRoundCornersInto(posterPath, iv_image)
    override fun loadBackdropImage(backdropPath: String?) {
        imageLoader.loadInto(backdropPath, iv_backdrop)
    }
}
package ru.geekbrains.cinema.di.modules

import android.widget.ImageView
import dagger.Module
import dagger.Provides
import ru.geekbrains.cinema.mvp.model.imageloader.IImageLoader
import ru.geekbrains.cinema.ui.imageloader.GlideImageLoader
import javax.inject.Named
import javax.inject.Singleton

@Module
class ImageLoaderModule {

    private val _imageBaseUrl = "https://image.tmdb.org/t/p/w500"

    @Named("imageBaseUrl")
    @Provides
    fun baseUrl(): String {
        return _imageBaseUrl
    }

    @Singleton
    @Provides
    fun imageLoader(@Named("imageBaseUrl") imageBaseUrl: String): IImageLoader<ImageView> = GlideImageLoader(imageBaseUrl)
}
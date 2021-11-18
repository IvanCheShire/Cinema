package ru.geekbrains.cinema.mvp.view.list

interface MovieItemView: IItemView {
    fun setTitle(title: String)
    fun loadImage(imageUrl: String)
}
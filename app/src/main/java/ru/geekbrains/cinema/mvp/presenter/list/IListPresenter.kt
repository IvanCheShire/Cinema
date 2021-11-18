package ru.geekbrains.cinema.mvp.presenter.list

import android.view.View
import ru.geekbrains.cinema.mvp.view.list.IItemView

interface IListPresenter<V: IItemView> {
    var itemClickListener: ((V) -> Unit)?
    fun bindView(view: V)
    fun getCount(): Int
}
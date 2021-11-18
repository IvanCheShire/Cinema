package ru.geekbrains.cinema.ui.activity

import androidx.navigation.NavController
import androidx.navigation.Navigation
import android.os.Bundle
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter
import ru.geekbrains.cinema.R
import ru.geekbrains.cinema.mvp.presenter.MainPresenter
import ru.geekbrains.cinema.mvp.view.MainView
import ru.geekbrains.cinema.ui.App


class MainActivity : MvpAppCompatActivity(), MainView {

    val presenter by moxyPresenter {
        App.instance.appComponent.inject(this)

        MainPresenter().apply {
            App.instance.appComponent.inject(this)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        App.instance.appComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
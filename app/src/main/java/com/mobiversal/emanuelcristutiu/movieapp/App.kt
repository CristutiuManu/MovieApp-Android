package com.mobiversal.emanuelcristutiu.movieapp

import android.app.Application
import com.mobiversal.emanuelcristutiu.movieapp.database.Database

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        Database.instance.initialize(this)
    }

}
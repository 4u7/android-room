package org.example.kotlin.room

import android.app.Application


class App : Application() {
    override fun onCreate() {
        super.onCreate()
        AppDatabase.initialize(this)
    }
}
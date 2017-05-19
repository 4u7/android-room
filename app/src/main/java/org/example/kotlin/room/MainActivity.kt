package org.example.kotlin.room

import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        AsyncTask.execute {
            AppDatabase.instance.userDao().insertAll(
                    User(0, "Rick", "Sanchez"),
                    User(1, "Morty", "Smith")
            )

            val users = AppDatabase.instance.userDao().all
        }
    }
}

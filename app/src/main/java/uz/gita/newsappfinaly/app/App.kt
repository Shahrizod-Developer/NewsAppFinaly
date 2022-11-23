package uz.gita.newsappfinaly.app

import android.app.Application
import androidx.viewbinding.BuildConfig
import dagger.hilt.android.HiltAndroidApp
import uz.gita.newsappfinaly.data.local.shp.MySharedPreference

@HiltAndroidApp
class App : Application() {

    companion object {
        lateinit var instance: App
            private set
    }

    override fun onCreate() {
        super.onCreate()
        MySharedPreference.init(this)
        instance = this
    }
}
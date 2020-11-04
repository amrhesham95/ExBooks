package ex.devs.exbooks

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex

class AppDelegate : Application() {
    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }
}
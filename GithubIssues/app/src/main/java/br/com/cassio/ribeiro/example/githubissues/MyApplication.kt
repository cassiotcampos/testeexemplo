package br.com.cassio.ribeiro.example.githubissues


import android.app.Application
import br.com.cassio.ribeiro.example.githubissues.module.mModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidLogger()
            androidContext(this@MyApplication)
            modules(mModules)
        }
    }
}
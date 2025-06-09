package sco.carlukesoftware.countryflags.app

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import sco.carlukesoftware.countryflags.di.countryModule
import sco.carlukesoftware.countryflags.di.networkModule
import sco.carlukesoftware.countryflags.di.scopeModule

class CountryFlagsApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@CountryFlagsApp)
            androidLogger()

            modules(
                networkModule,
                countryModule,
                scopeModule
            )
        }
    }
}

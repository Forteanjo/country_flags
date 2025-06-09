package sco.carlukesoftware.countryflags.di

import org.koin.core.qualifier.named
import org.koin.dsl.module
import sco.carlukesoftware.countryflags.MainActivity

val scopeModule = module {
    //scope a variable to an activity not to whole application.
    // so when the activity is destroyed the scope is destroyed

    scope<MainActivity> {
        scoped(qualifier = named("Hello")){"hello from main activity"}
        scoped(qualifier = named("Bye")){"Bye from main activity"}
    }

}

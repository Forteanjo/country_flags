package sco.carlukesoftware.countryflags.di

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module
import sco.carlukesoftware.countryflags.ktor.KtorClient

const val BASE_URL = "https://www.apicountries.com/"

fun provideKtorClient(): KtorClient = KtorClient(BASE_URL)

fun provideIoDispatcher(): CoroutineDispatcher = Dispatchers.IO

val networkModule = module {

    single<KtorClient> { provideKtorClient() }

    single { provideIoDispatcher() }

}

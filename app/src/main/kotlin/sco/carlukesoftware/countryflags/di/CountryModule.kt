package sco.carlukesoftware.countryflags.di

import kotlinx.coroutines.CoroutineDispatcher
import org.koin.dsl.module
import org.koin.core.module.dsl.viewModel
import sco.carlukesoftware.countryflags.ktor.KtorClient
import sco.carlukesoftware.countryflags.repository.CountryRepository
import sco.carlukesoftware.countryflags.repository.ICountryRepository
import sco.carlukesoftware.countryflags.usecase.GetCountriesUseCase
import sco.carlukesoftware.countryflags.usecase.IGetCountriesUseCase
import sco.carlukesoftware.countryflags.viewmodel.CountryViewModel


fun provideCountryRepository(
    ktorClient: KtorClient,
    ioDispatcher: CoroutineDispatcher
): ICountryRepository = CountryRepository(ktorClient, ioDispatcher)

fun provideCountryUseCase(repository: ICountryRepository) = GetCountriesUseCase(repository)

val countryModule = module {
    factory<ICountryRepository> { provideCountryRepository(get(), get()) }
    factory<IGetCountriesUseCase> { provideCountryUseCase(get()) }

    viewModel<CountryViewModel> { CountryViewModel(get()) }
}

package sco.carlukesoftware.countryflags.usecase

import sco.carlukesoftware.countryflags.ktor.NetworkResult
import sco.carlukesoftware.countryflags.models.Country
import sco.carlukesoftware.countryflags.repository.CountryRepository
import sco.carlukesoftware.countryflags.repository.CountryResponse
import sco.carlukesoftware.countryflags.repository.ICountryRepository

class GetCountriesUseCase(
    private val countryRepository: ICountryRepository
) : IGetCountriesUseCase {

    override suspend fun invoke(): NetworkResult<CountryResponse> = countryRepository.getCountries()

}

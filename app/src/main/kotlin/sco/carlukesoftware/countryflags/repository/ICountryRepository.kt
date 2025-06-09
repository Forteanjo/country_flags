package sco.carlukesoftware.countryflags.repository

import sco.carlukesoftware.countryflags.ktor.NetworkResult
import sco.carlukesoftware.countryflags.models.Country

interface ICountryRepository {

    suspend fun getCountries(): NetworkResult<CountryResponse>

}

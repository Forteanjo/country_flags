package sco.carlukesoftware.countryflags.usecase

import sco.carlukesoftware.countryflags.ktor.NetworkResult
import sco.carlukesoftware.countryflags.repository.CountryResponse

interface IGetCountriesUseCase {

    suspend operator fun invoke(): NetworkResult<CountryResponse>

}

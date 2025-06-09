package sco.carlukesoftware.countryflags.repository

import kotlinx.serialization.Serializable
import sco.carlukesoftware.countryflags.models.Country

@Serializable
data class  CountryResponse(
    val countries: List<Country>
)

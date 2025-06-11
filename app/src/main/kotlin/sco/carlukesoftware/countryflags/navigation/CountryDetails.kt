package sco.carlukesoftware.countryflags.navigation

import androidx.navigation3.runtime.NavKey
import sco.carlukesoftware.countryflags.models.Country

data class CountryDetails(
    val country: Country
): NavKey

package sco.carlukesoftware.countryflags.models

import kotlinx.serialization.Serializable

@Serializable
data class Language(
    val iso639_1: String? = null,
    val iso639_2: String? = null,
    val name: String? = null,
    val nativeName: String? = null
)

package sco.carlukesoftware.countryflags.models

import kotlinx.serialization.Serializable

@Serializable
data class Translations(
    val br: String? = null,
    val de: String? = null,
    val es: String? = null,
    val fa: String? = null,
    val fr: String? = null,
    val hr: String? = null,
    val hu: String? = null,
    val `it`: String? = null,
    val ja: String? = null,
    val nl: String? = null,
    val pt: String? = null
)

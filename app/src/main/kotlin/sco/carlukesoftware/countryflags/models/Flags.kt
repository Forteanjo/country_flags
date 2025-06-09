package sco.carlukesoftware.countryflags.models

import kotlinx.serialization.Serializable

@Serializable
data class Flags(
    val png: String,
    val svg: String
)

package sco.carlukesoftware.countryflags.models

import kotlinx.serialization.EncodeDefault
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable

@OptIn(ExperimentalSerializationApi::class)
@Serializable
data class RegionalBloc(
    val acronym: String,
    val name: String,
//    val otherAcronyms: List<String>? = null,
//    val otherNames: List<String>? = null

    // If you want them to be non-nullable List<String> in your code,
    // but the API might omit them (defaulting to an empty list).
    @EncodeDefault // Ensures this default is used if the key is missing from JSON
    val otherAcronyms: List<String> = emptyList(),

    @EncodeDefault
    val otherNames: List<String> = emptyList()
)

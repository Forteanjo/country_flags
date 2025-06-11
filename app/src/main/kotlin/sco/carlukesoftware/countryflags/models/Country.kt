package sco.carlukesoftware.countryflags.models

import kotlinx.serialization.Serializable

@Serializable
data class Country(
    val alpha2Code: String,
    val alpha3Code: String,
    val altSpellings: List<String>? = null,
    val area: Double? = null,
    val borders: List<String>? = null,
    val callingCodes: List<String>,
    val capital: String? = null,
    val cioc: String? = null,
    val currencies: List<Currency>? = null,
    val demonym: String,
    val flag: String,
    val flags: Flags,
    val gini: Double? = null,
    val independent: Boolean,
    val languages: List<Language>,
    val latlng: List<Double>? = null,
    val name: String,
    val nativeName: String? = null,
    val numericCode: String,
    val population: Int,
    val region: String,
    val regionalBlocs: List<RegionalBloc>? = null,
    val subregion: String,
    val timezones: List<String>,
    val topLevelDomain: List<String>,
    val translations: Translations
)

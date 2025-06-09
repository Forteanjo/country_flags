package sco.carlukesoftware.countryflags.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import sco.carlukesoftware.countryflags.ktor.NetworkResult
import sco.carlukesoftware.countryflags.models.Country
import sco.carlukesoftware.countryflags.usecase.IGetCountriesUseCase

class CountryViewModel(
    val useCase: IGetCountriesUseCase
) : ViewModel() {

    private val _listOfCountries: MutableStateFlow<List<Country>> = MutableStateFlow(emptyList())
    val countryList: StateFlow<List<Country>> = _listOfCountries

    init {
        viewModelScope.launch {
            when (val result = useCase()) {
                is NetworkResult.Success -> {
                    _listOfCountries.value = result.data.countries
                }
                is NetworkResult.Error -> {
                /* Show error message result.message */
                    _listOfCountries.value = emptyList()
                }
                is NetworkResult.Loading -> { /* Show loading indicator */ }
            }
        }
    }

}

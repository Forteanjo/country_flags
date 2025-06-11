package sco.carlukesoftware.countryflags.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import sco.carlukesoftware.countryflags.navigation.CountryDetails
import sco.carlukesoftware.countryflags.navigation.CountryList

@Composable
fun MainScreen(
    modifier: Modifier = Modifier
) {
    val backStack = rememberNavBackStack(CountryList)

    NavDisplay(
        backStack = backStack,
    ) { route ->
        when (route) {
            is CountryList -> NavEntry(route) {
                //backStack.add(CountryList)
                CountryScreen(
                    onShowDetailsClick = { country ->
                        backStack.add(
                            CountryDetails(
                                country
                            )
                        )
                    },
                    modifier = modifier
                )
            }

            is CountryDetails -> NavEntry(route) {
                CountryDetailsScreen(
                    country = route.country,
                    onBackClick = { backStack.removeLastOrNull() },
                    modifier = modifier,
                )
            }

            else -> NavEntry(route)  {

            }
        }

    }
}

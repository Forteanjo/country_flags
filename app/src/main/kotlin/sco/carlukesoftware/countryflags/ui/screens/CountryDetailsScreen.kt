package sco.carlukesoftware.countryflags.ui.screens

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import sco.carlukesoftware.countryflags.models.Country

@Composable
fun CountryDetailsScreen(
    country: Country,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Text(
        text = country.name,
        modifier = modifier
            .fillMaxWidth()
    )
}

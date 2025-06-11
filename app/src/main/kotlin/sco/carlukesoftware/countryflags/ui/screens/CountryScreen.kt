package sco.carlukesoftware.countryflags.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import org.koin.compose.koinInject
import sco.carlukesoftware.countryflags.R
import sco.carlukesoftware.countryflags.models.Country
import sco.carlukesoftware.countryflags.viewmodel.CountryViewModel
import java.text.DecimalFormat

@Composable
fun CountryScreen(
    modifier: Modifier = Modifier,
    countryViewModel: CountryViewModel = koinInject(),
    onShowDetailsClick: (Country) -> Unit
) {
    val countries by countryViewModel.countryList.collectAsState()

    // Simple decimal formatting
    val decimalFormatter = remember {
        // Pattern for grouping without any decimal places
        DecimalFormat("#,##0")
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(
                horizontal = 16.dp,
            )
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .height(74.dp),
            elevation = CardDefaults
                .cardElevation(8.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(R.string.app_name),
                    fontWeight = FontWeight.Bold
                )
            }

        }

        LazyColumn(
            modifier = Modifier
        ) {
            items(countries) {country ->
                CountryItem(
                    country = country,
                    decimalFormatter = decimalFormatter,
                    onDetailsClick =  {
                        onShowDetailsClick(country)
                    }
                )
            }
        }
    }
}

const val CARD_PADDING = 8
const val CARD_ELEVATION = 4

@Composable
fun CountryItem(
    country: Country,
    decimalFormatter: DecimalFormat, // Pass it in
    modifier: Modifier = Modifier,
    onDetailsClick: (Country) -> Unit = {}
) {
    Card(
        onClick = {
            onDetailsClick(country)
        },
        modifier = modifier
            .padding(CARD_PADDING.dp),
        elevation = CardDefaults
            .cardElevation(CARD_ELEVATION.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            AsyncImage(
                model = country.flags.png,
                contentDescription = stringResource(
                    R.string.country_flag_description, // Use stringResource
                    country.name
                ),
                modifier = Modifier
                    .size(80.dp)
            )
            Column(
                modifier = Modifier
                    .padding(
                        start = 20.dp,
                    )
            ) {
                Text(
                    text = country.name,
                    style = MaterialTheme.typography.titleLarge, // Example style
                )

                Text(
                    text = stringResource(
                        R.string.country_capital_label,
                        country.capital?: stringResource(R.string.unknown) // Use stringResource
                    ), // Use stringResource
                    style = MaterialTheme.typography.bodyLarge
                )
                Text(
                    text = stringResource(
                        R.string.country_population_label,
                        decimalFormatter.format(country.population)
                    ), // Use stringResource
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    }
}

@Composable
fun CountryItemShimmer(
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxSize()
            .padding(CARD_PADDING.dp),
        elevation = CardDefaults
            .cardElevation(CARD_ELEVATION.dp)
    ) {

    }
}

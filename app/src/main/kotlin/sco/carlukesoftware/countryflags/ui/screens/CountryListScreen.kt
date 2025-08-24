package sco.carlukesoftware.countryflags.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import org.koin.compose.viewmodel.koinViewModel
import sco.carlukesoftware.countryflags.R
import sco.carlukesoftware.countryflags.models.Country
import sco.carlukesoftware.countryflags.utils.CARD_ELEVATION
import sco.carlukesoftware.countryflags.utils.CARD_PADDING
import sco.carlukesoftware.countryflags.utils.COLUMN_HORIZONTAL_PADDING
import sco.carlukesoftware.countryflags.viewmodel.CountryViewModel
import java.text.DecimalFormat

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CountryListScreen(
    modifier: Modifier = Modifier,
    countryViewModel: CountryViewModel = koinViewModel(),
    onShowDetailsClick: (Country) -> Unit
) {
    val countries by countryViewModel.countryList.collectAsStateWithLifecycle(emptyList())

    // Simple decimal formatting
    val decimalFormatter = remember {
        // Pattern for grouping without any decimal places
        DecimalFormat("#,##0")
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(
                horizontal = COLUMN_HORIZONTAL_PADDING.dp,
            )
    ) {
        TopAppBar(
            title = {
                Text("Countries")
            },
            actions = {
                IconButton(onClick = {  }) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search"
                    )
                }
            },
            windowInsets = WindowInsets(0),
        )

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(16.dp),
            modifier = Modifier.fillMaxSize()
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
            .padding(
                vertical = CARD_PADDING.dp
            ),
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
                        start = 20.dp
                    )
            ) {
                Text(
                    text = country.name,
                    style = MaterialTheme.typography.titleLarge, // Example style
                )

                Text(
                    text = stringResource(
                        R.string.country_capital_label,
                        country.capital?: stringResource(R.string.not_applicable) // Use stringResource
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

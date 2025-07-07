package sco.carlukesoftware.countryflags.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import sco.carlukesoftware.countryflags.R
import sco.carlukesoftware.countryflags.models.Country
import sco.carlukesoftware.countryflags.utils.CARD_ELEVATION
import sco.carlukesoftware.countryflags.utils.CARD_PADDING
import sco.carlukesoftware.countryflags.utils.COLUMN_HORIZONTAL_PADDING
import java.text.DecimalFormat

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CountryDetailsScreen(
    country: Country,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
    showBackButton: Boolean = true
) {
    // Simple decimal formatting
    val decimalFormatter = remember {
        // Pattern for grouping without any decimal places
        DecimalFormat("#,##0")
    }

    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier
            .verticalScroll(rememberScrollState())
            .fillMaxSize()
            .padding(
                horizontal = COLUMN_HORIZONTAL_PADDING.dp,
            )
    ) {
        TopAppBar(
            title = {
                Text(country.name)
            },
            navigationIcon = {
                if (showBackButton) {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Default.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            },
            windowInsets = WindowInsets(0),
        )

        CountryDetailsSummary(
            country = country,
            decimalFormatter = decimalFormatter,
            modifier = Modifier
        )

        Row {
            Column(
                modifier = Modifier
                    .fillMaxWidth(fraction = 0.5f)
            ) {
                CountryRegionBlock(
                    country = country,
                    modifier = Modifier
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {

            }
        }
    }
}

@Composable
fun CountryDetailsSummary(
    country: Country,
    decimalFormatter: DecimalFormat, // Pass it in
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .padding(CARD_PADDING.dp),
        elevation = CardDefaults
            .cardElevation(CARD_ELEVATION.dp)
    ) {
        Row(
            modifier = modifier
                //.background(color = MaterialTheme.colorScheme.surface)
                .padding(10.dp)
                .clip(
                    shape = MaterialTheme.shapes.medium
                ),
            verticalAlignment = Alignment
                .CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(fraction = 0.3f)
            ) {
                AsyncImage(
                    model = country.flags.png,
                    contentDescription = stringResource(
                        R.string.country_flag_description, // Use stringResource
                        country.name
                    ),
                    modifier = Modifier
                        .fillMaxSize()
                )
            }

            Spacer(
                modifier = Modifier
                    .width(COLUMN_HORIZONTAL_PADDING.dp)
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Text(
                    text = stringResource(
                        R.string.country_capital_label,
                        country.capital
                            ?: stringResource(R.string.not_applicable) // Use stringResource
                    ),
                    style = MaterialTheme.typography.bodyLarge
                )

                Text(
                    text = stringResource(
                        R.string.country_population_label,
                        decimalFormatter.format(country.population)
                    ), // Use stringResource
                    style = MaterialTheme.typography.bodyLarge
                )

                Text(
                    text = stringResource(
                        R.string.country_native_name_label,
                        country.nativeName ?: stringResource(R.string.unknown) // Use stringResource
                    ),
                    style = MaterialTheme.typography.bodyLarge
                )

                Text(
                    text = stringResource(
                        R.string.country_area_label,
                        decimalFormatter.format(country.area)
                            ?: stringResource(R.string.unknown) // Use stringResource
                    ),
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    }
}

@Composable
fun CountryRegionBlock(
    country: Country,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = stringResource(
                R.string.country_region_label,
                country.region
            ),
            style = MaterialTheme.typography.bodyLarge
        )

        Text(
            text = stringResource(
                R.string.country_subregion_label,
                country.subregion
            ),
            style = MaterialTheme.typography.bodyMedium
        )

        country.regionalBlocs
            ?.forEach { regionalBloc ->
                Text(
                    text = stringResource(
                        R.string.country_acronym_label,
                        regionalBloc.acronym
                    ),
                    style = MaterialTheme.typography.bodyMedium
                )

                Text(
                    text = stringResource(
                        R.string.country_other_acronyms_label,
                        regionalBloc.otherAcronyms.joinToString("; ")
                    ),
                    style = MaterialTheme.typography.bodyMedium
                )

                Text(
                    text = stringResource(
                        R.string.country_other_names_label,
                        regionalBloc.otherNames.joinToString("; ")
                    ),
                    style = MaterialTheme.typography.bodyMedium
                )
            }
    }
}

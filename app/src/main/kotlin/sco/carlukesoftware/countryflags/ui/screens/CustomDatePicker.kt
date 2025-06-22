package sco.carlukesoftware.countryflags.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DisplayMode
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SelectableDates
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.atStartOfDayIn
import kotlinx.datetime.format.DayOfWeekNames
import kotlinx.datetime.format.MonthNames
import kotlinx.datetime.format.Padding
import kotlinx.datetime.format.char
import kotlinx.datetime.toLocalDateTime

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomDatePicker(modifier: Modifier = Modifier) {

    var isDatePickerOpen by rememberSaveable {
        mutableStateOf(false)
    }

    val initialDateMillis = LocalDate(2024,10,6)
        .atStartOfDayIn(TimeZone.UTC)
        .toEpochMilliseconds()

    val datePickerState = rememberDatePickerState(
        initialSelectedDateMillis = initialDateMillis, // System.currentTimeMillis()
        initialDisplayMode = DisplayMode.Picker,
        selectableDates = HistoricalSelectableDates,
    )

    if (isDatePickerOpen) {
        DatePickerDialog(
            modifier = Modifier
                .verticalScroll(rememberScrollState()),
            onDismissRequest = {
                // Dismiss the dialog when the user clicks outside the dialog or on the back
                // button. If you want to disable that functionality, simply use an empty
                // onDismissRequest.
                isDatePickerOpen = false
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        // Do something with the date chosen by the user
                        isDatePickerOpen = false
                    }
                ) {
                    Text("Select")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        // Dismiss the dialog when the user clicks the dismiss button
                        isDatePickerOpen = false
                    }
                ) {
                    Text("Cancel")
                }
            },
        ) {
            DatePicker(
                state = datePickerState,
                modifier = modifier,
                showModeToggle = true,
                title = {
                    Text(text = "When was your last birthday?")
                }
            )
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment
            .CenterHorizontally,
        verticalArrangement = Arrangement
            .Center
    ) {
        Button(
            onClick = {
                isDatePickerOpen = true
            }
        ) {
            Text(text = "Open date picker")
        }

        Text(
            text = "Selected date: ${datePickerState.selectedDateMillis.toFormattedDateString()}"
        )
    }
}

fun Long?.toFormattedDateString(): String {
    val instant = Instant.fromEpochMilliseconds(this ?: System.currentTimeMillis())
    val date = instant.toLocalDateTime(TimeZone.currentSystemDefault()).date
    val formatter = LocalDate.Format {
        // dd/mm/yyyy
        //dayOfMonth(); char('/'); monthNumber(); char('/'); year()

        // Jan 02, 2025
//        monthName(MonthNames.ENGLISH_ABBREVIATED)
//        char(' ')
//        dayOfMonth()
//        char(',')
//        char(' ')
//        year()

        dayOfWeek(names = DayOfWeekNames.ENGLISH_FULL); char(' ')
        dayOfMonth(padding = Padding.NONE); char(' ')
        monthName(MonthNames.ENGLISH_FULL); char(' ')
        year()
    }
    return formatter.format(date)
}

@OptIn(ExperimentalMaterial3Api::class)
object HistoricalSelectableDates : SelectableDates {

    private val timeZone = TimeZone.currentSystemDefault()
    private val today = Clock.System.now().toLocalDateTime(timeZone).date

    override fun isSelectableDate(utcTimeMillis: Long): Boolean {
        val date = Instant.fromEpochMilliseconds(utcTimeMillis)
            .toLocalDateTime(timeZone).date
        return date < today
    }

    override fun isSelectableYear(year: Int): Boolean {
        return year < today.year
    }
}

@Preview
@Composable
private fun CustomDatePickerPreview() {
    CustomDatePicker()
}

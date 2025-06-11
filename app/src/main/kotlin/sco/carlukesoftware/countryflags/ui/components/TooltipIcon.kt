package sco.carlukesoftware.countryflags.ui.components

import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TooltipIcon() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(30.dp)
    ) {
        Text("Long press the icons:")

//        // Option 1: Using Material 3 PlainTooltip (Recommended for M3)
//        val tooltipStateM3 = rememberTooltipState(isPersistent = true) // isPersistent for long press
//        // val scope = rememberCoroutineScope() // To control tooltip programmatically if needed
//
//        PlainTooltipBox(
//            tooltip = { Text("This is an M3 PlainTooltip!") },
//            tooltipState = tooltipStateM3
//        ) {
//            Icon(
//                imageVector = Icons.Filled.Help,
//                contentDescription = "Help Icon with M3 Tooltip",
//                modifier = Modifier
//                    .size(48.dp)
//                    .tooltipAnchor() // Connects the icon to the tooltip box
//                // For long press, TooltipArea handles it.
//                // If you need more control or to trigger programmatically:
//                // .combinedClickable(
//                //     onLongClick = { scope.launch { tooltipStateM3.show() } },
//                //     onClick = {}
//                // )
//            )
//        }


        // Option 2: Custom Tooltip with combinedClickable and Popup
        var showCustomTooltip by remember { mutableStateOf(false) }
        val interactionSource = remember { MutableInteractionSource() }
        // val isPressed by interactionSource.collectIsPressedAsState() // Can be used too

        Box { // Box to anchor the Popup
            Icon(
                imageVector = Icons.Filled.Info,
                contentDescription = "Help Icon with Custom Tooltip",
                modifier = Modifier
                    .size(48.dp)
                    .combinedClickable(
                        interactionSource = interactionSource,
                        indication = null, // Or your custom indication
                        onClick = { /* Optional: handle regular click */ },
                        onLongClick = {
                            showCustomTooltip = true
                        }
                    )
            )

            if (showCustomTooltip) {
                Popup(
                    alignment = Alignment.TopCenter, // Adjust as needed, e.g., above the icon
                    offset = IntOffset(0, -56), // Offset to position above a 48.dp icon
                    onDismissRequest = {
                        showCustomTooltip = false
                    }
                ) {
                    Surface(
                        modifier = Modifier.shadow(4.dp),
                        color = Color.DarkGray, // Or MaterialTheme.colorScheme.surfaceVariant
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text(
                            text = "Custom tooltip on long press!",
                            modifier = Modifier.padding(10.dp),
                            color = Color.White // Or MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }
        }

//        // Android's default long-press tooltip behavior (Accessibility)
//        // This doesn't show a custom popup, but uses the system's tooltip mechanism.
//        // It's good for accessibility if you don't need a fancy visual tooltip.
//        val view = LocalView.current
//        Icon(
//            imageVector = Icons.Filled.Help,
//            contentDescription = "Help Icon with System Tooltip", // This becomes the tooltip text
//            modifier = Modifier
//                .size(48.dp)
//                .combinedClickable(
//                    onLongClick = {
//                        // For Android P and above, the contentDescription can act as a tooltip on long press
//                        // if TalkBack or other accessibility services are enabled.
//                        // For a more explicit system tooltip:
//                        view.performLongClick() // This might trigger the system tooltip if configured
//                        // You can also use view.tooltipText = "My Tooltip Text" but it's less Compose-idiomatic.
//                    },
//                    onClick = {}
//                )
//            // More explicit way for system tooltip:
//            // .semantics {
//            //    this.longClick("Show tooltip for Help") {
//            //        // view.tooltipText = "This is system help text" // API 26+
//            //        // For a visual cue without a custom popup, you might just log or set a state.
//            //        // The contentDescription is often the primary source for system tooltips.
//            //        true
//            //    }
//            // }
//        )
    }
}

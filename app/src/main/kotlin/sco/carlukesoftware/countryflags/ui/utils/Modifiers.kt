package sco.carlukesoftware.countryflags.ui.utils

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.IntSize

/**
 * Applies a shimmer loading effect to the modifier by animating a gradient brush horizontally
 *
 * This is used during the loading of the country information
 *
 * @param shimmerColors A list of colors used in the shimmer gradient
 * @param shimmerDurationMd The duration of one full shimmer animation cycle in milliseconds
 *
 * @return A [Modifier] with an animated shimmer background
 */
fun Modifier.shimmerEffect(
    shimmerColors: List<Color> = listOf(
        Color.LightGray.copy(alpha = 0.6f),
        Color.LightGray.copy(alpha = 0.2f),
        Color.LightGray.copy(alpha = 0.6f),
    ),
    shimmerDurationMs: Int = 1000
): Modifier = composed {
    var size by remember {
        mutableStateOf(IntSize.Zero)
    }

    val transition = rememberInfiniteTransition(
        label = "shimmer_transition"
    )

    val translateX by transition.animateFloat(
        initialValue = -size.width.toFloat(),
        targetValue = size.width.toFloat() * 2,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = shimmerDurationMs,
                easing = LinearEasing
            ),
            repeatMode = RepeatMode.Restart,
        ),
        label = "shimmer_animation"
    )

    val brush = Brush.linearGradient(
        colors = shimmerColors,
        start = Offset(
            x = translateX,
            y = 0f
        ),
        end = Offset(
            x = translateX + size.width.toFloat(),
            y = 0f
        )
    )

    this
        .onGloballyPositioned {
            size = it.size
        }
        .background(
            brush = brush
        )
}

package sco.carlukesoftware.countryflags.ui.components

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import sco.carlukesoftware.countryflags.ui.theme.CountryFlagsTheme
import sco.carlukesoftware.countryflags.ui.utils.shimmerEffect

@Composable
fun ShimmeringImage(
    modifier: Modifier = Modifier,
    isLoading: Boolean = true
) {
    Crossfade(
        modifier = modifier,
        targetState = isLoading,
        label = "shimmering_image"
    ) { loading ->
        if (loading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .shimmerEffect()
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ShimmerImagePreview() {
    CountryFlagsTheme {
        ShimmeringImage(
            isLoading = true,
            modifier = Modifier
                .width(70.dp)
                .height(70.dp)
                .clip(RoundedCornerShape(12.dp))
        )
    }
}

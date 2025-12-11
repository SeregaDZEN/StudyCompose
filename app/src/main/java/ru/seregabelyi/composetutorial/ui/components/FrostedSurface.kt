package ru.seregabelyi.composetutorial.ui.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp

@Composable
fun FrostedSurface(
    modifier: Modifier = Modifier, content: @Composable () -> Unit
) {
    Surface(
        modifier = modifier.clip(RoundedCornerShape(20.dp)), tonalElevation = 4.dp
    ) {
        content()
    }
}

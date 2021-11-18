package com.powilliam.composefundamentals.composables

import android.content.res.Configuration
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.powilliam.composefundamentals.ui.ComposeFundamentalsTheme

typealias ActionCallback = () -> Unit

@Composable
fun ActionButtons(
    modifier: Modifier = Modifier,
    shape: Shape = RoundedCornerShape(percent = 50),
    tonalElevation: Dp = 2.dp,
    onDecrement: ActionCallback = {},
    onReset: ActionCallback = {},
    onIncrement: ActionCallback = {}
) {
    Surface(
        shape = shape,
        tonalElevation = tonalElevation
    ) {
        Row(
            modifier = modifier
                .padding(horizontal = ButtonDefaults.ContentPadding.calculateTopPadding())
        ) {
            TextButton(onClick = onDecrement) {
                Text(text = "Decrement")
            }
            Spacer(modifier = modifier.width(8.dp))
            TextButton(onClick = onReset) {
                Text(text = "Reset")
            }
            Spacer(modifier = modifier.width(8.dp))
            TextButton(onClick = onIncrement) {
                Text(text = "Increment")
            }
        }
    }
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun ActionButtons_Preview() {
    ComposeFundamentalsTheme {
        ActionButtons()
    }
}
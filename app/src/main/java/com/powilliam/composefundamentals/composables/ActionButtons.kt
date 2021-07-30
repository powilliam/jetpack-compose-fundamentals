package com.powilliam.composefundamentals.composables

import android.content.res.Configuration
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.powilliam.composefundamentals.ui.ComposeFundamentalsTheme

typealias ActionCallback = () -> Unit

@Composable
fun ActionButtons(
    onDecrement: ActionCallback = {},
    onReset: ActionCallback = {},
    onIncrement: ActionCallback = {}
) {
    Row {
        TextButton(onClick = onDecrement) {
            Text(text = "Decrement")
        }
        Spacer(modifier = Modifier.width(8.dp))
        TextButton(onClick = onReset) {
            Text(text = "Reset")
        }
        Spacer(modifier = Modifier.width(8.dp))
        TextButton(onClick = onIncrement) {
            Text(text = "Increment")
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ActionButtons_LightPreview() {
    ComposeFundamentalsTheme {
        ActionButtons()
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun ActionButtons_DarkPreview() {
    ComposeFundamentalsTheme {
        ActionButtons()
    }
}
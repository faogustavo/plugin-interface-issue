package org.example.project

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.PopupPositionProviderAtPosition
import androidx.compose.ui.window.PopupProperties
import org.jetbrains.jewel.ui.component.OutlinedButton
import org.jetbrains.jewel.ui.component.Text

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun App() {
    var renderPopup by remember { mutableStateOf(false) }

    Column(
        modifier =
            Modifier
                .safeContentPadding()
                .fillMaxSize()
                .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            OutlinedButton(onClick = { renderPopup = !renderPopup }) {
                Text("Show popup!")
            }
        }

        if (renderPopup) {
            LocalPopupRenderer.current.Popup(
                properties = PopupProperties(),
                popupPositionProvider =
                    PopupPositionProviderAtPosition(
                        positionPx = Offset.Zero,
                        isRelativeToAnchor = true,
                        offsetPx = Offset.Zero,
                        windowMarginPx = 0,
                    ),
                onDismissRequest = { renderPopup = false },
                onPreviewKeyEvent = null,
                onKeyEvent = null,
            ) {
                Text("Hello !!")
            }
        }
    }
}

@Composable
private fun DemoBox(color: Color) {
    Box(
        Modifier
            .size(64.dp)
            .background(color, RoundedCornerShape(8.dp))
            .padding(12.dp),
    )
}

package org.example.project

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.KeyEvent
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.PopupPositionProvider
import androidx.compose.ui.window.PopupProperties

interface PopupRenderer {
    // Notice that the declaration with default parameters is crashing the plugin
    // While the one without default parameters works fine
    @Composable
    fun Popup(
        popupPositionProvider: PopupPositionProvider,
        onDismissRequest: (() -> Unit)? = null,
        properties: PopupProperties = PopupProperties(),
        onPreviewKeyEvent: ((KeyEvent) -> Boolean)? = null,
        onKeyEvent: ((KeyEvent) -> Boolean)? = null,
        content: @Composable () -> Unit,
    )

//    @Composable
//    fun Popup(
//        popupPositionProvider: PopupPositionProvider,
//        onDismissRequest: (() -> Unit)?,
//        properties: PopupProperties,
//        onPreviewKeyEvent: ((KeyEvent) -> Boolean)?,
//        onKeyEvent: ((KeyEvent) -> Boolean)?,
//        content: @Composable () -> Unit
//    )
}

private object DefaultPopupRenderer : PopupRenderer {
    @Composable
    override fun Popup(
        popupPositionProvider: PopupPositionProvider,
        onDismissRequest: (() -> Unit)?,
        properties: PopupProperties,
        onPreviewKeyEvent: ((KeyEvent) -> Boolean)?,
        onKeyEvent: ((KeyEvent) -> Boolean)?,
        content: @Composable () -> Unit,
    ) {
        androidx.compose.ui.window.Popup(
            popupPositionProvider = popupPositionProvider,
            onDismissRequest = onDismissRequest,
            properties = properties,
            onPreviewKeyEvent = onPreviewKeyEvent,
            onKeyEvent = onKeyEvent,
        ) {
            Box(modifier = Modifier.background(Color.Black, RoundedCornerShape(8.dp)).padding(8.dp)) {
                content()
            }
        }
    }
}

val LocalPopupRenderer: ProvidableCompositionLocal<PopupRenderer> =
    staticCompositionLocalOf { DefaultPopupRenderer }

package org.example.project.plugin

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.KeyEvent
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.PopupPositionProvider
import androidx.compose.ui.window.PopupProperties
import com.intellij.openapi.project.DumbAware
import com.intellij.openapi.project.Project
import com.intellij.openapi.wm.ToolWindow
import com.intellij.openapi.wm.ToolWindowFactory
import org.example.project.App
import org.example.project.LocalPopupRenderer
import org.example.project.PopupRenderer
import org.jetbrains.jewel.bridge.addComposeTab
import org.jetbrains.jewel.ui.component.Text

class ExampleToolWindow :
    ToolWindowFactory,
    DumbAware {
    override fun createToolWindowContent(
        project: Project,
        toolWindow: ToolWindow,
    ) {
        toolWindow.addComposeTab {
            Row(modifier = Modifier.padding(top = 20.dp)) {
                Column(modifier = Modifier.weight(1f), horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("Without custom PopupRenderer")
                    App()
                }

                Column(modifier = Modifier.weight(1f), horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("With custom PopupRenderer")
                    CompositionLocalProvider(LocalPopupRenderer provides AlternativePopupRenderer) {
                        App()
                    }
                }
            }
        }
    }
}

private object AlternativePopupRenderer : PopupRenderer {
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
            Box(modifier = Modifier.background(Color.DarkGray, RoundedCornerShape(8.dp)).padding(8.dp)) {
                content()
            }
        }
    }
}

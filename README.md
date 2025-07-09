# IntelliJ Plugin + Compose issue reproducer

This is a minimal project to reproduce an issue with IntelliJ plugins and Compose for Desktop.

### Problem

The problem happens when you have an interface with a composable property and default values.

Example:

```kotlin
interface PopupRenderer {
    @Composable
    fun Popup(
        popupPositionProvider: PopupPositionProvider,
        onDismissRequest: (() -> Unit)? = null,
        properties: PopupProperties = PopupProperties(),
        onPreviewKeyEvent: ((KeyEvent) -> Boolean)? = null,
        onKeyEvent: ((KeyEvent) -> Boolean)? = null,
        content: @Composable () -> Unit,
    )
}
```

Removing the default values for all arguments make the issue go away.

```kotlin
interface PopupRenderer {
    @Composable
    fun Popup(
        popupPositionProvider: PopupPositionProvider,
        onDismissRequest: (() -> Unit)?,
        properties: PopupProperties,
        onPreviewKeyEvent: ((KeyEvent) -> Boolean)?,
        onKeyEvent: ((KeyEvent) -> Boolean)?,
        content: @Composable () -> Unit
    )
}
```

### Stacktrace

```stacktrace
Caused by: java.lang.AbstractMethodError: Receiver class org.example.project.plugin.AlternativePopupRenderer does not define or inherit an implementation of the resolved method 'abstract void Popup(androidx.compose.ui.window.PopupPositionProvider, kotlin.jvm.functions.Function0, androidx.compose.ui.window.PopupProperties, kotlin.jvm.functions.Function1, kotlin.jvm.functions.Function1, kotlin.jvm.functions.Function2, androidx.compose.runtime.Composer, int)' of interface org.example.project.PopupRenderer.
	at org.example.project.PopupRenderer$ComposeDefaultImpls.Popup$default(PopupRenderer.kt:26)
	at org.example.project.AppKt.App(App.kt:49)
	at org.example.project.AppKt.App$lambda$9(App.kt)
	at androidx.compose.runtime.RecomposeScopeImpl.compose(RecomposeScopeImpl.kt:235)
	at androidx.compose.runtime.ComposerImpl.recomposeToGroupEnd(Composer.kt:2838)
	at androidx.compose.runtime.ComposerImpl.skipCurrentGroup(Composer.kt:3158)
	at androidx.compose.runtime.ComposerImpl.doCompose-aFTiNEg(Composer.kt:3706)
	at androidx.compose.runtime.ComposerImpl.recompose-aFTiNEg$runtime(Composer.kt:3648)
```

### Running

```bash
./gradlew :pluginDemo:runIde
```
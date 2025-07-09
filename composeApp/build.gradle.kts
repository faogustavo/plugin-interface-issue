plugins {
    alias(libs.plugins.kotlinJvm)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
}

dependencies {
    compileOnly("org.jetbrains.jewel:jewel-ui:0.28.0-252.15920")
}

plugins {
    alias(libs.plugins.kotlinJvm)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
}

dependencies {
    compileOnly(compose.runtime)
    compileOnly(compose.foundation)
    compileOnly(compose.ui)
}

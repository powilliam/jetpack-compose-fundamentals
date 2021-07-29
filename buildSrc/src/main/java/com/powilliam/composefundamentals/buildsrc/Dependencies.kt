package com.powilliam.composefundamentals.buildsrc

object Dependencies {
    const val kotlinVersion = "1.5.10"
    const val composeVersion = "1.0.0"

    const val androidGradlePlugin = "com.android.tools.build:gradle:7.0.0"
    const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion"

    object Google {
        const val materialDesign = "com.google.android.material:material:1.4.0"
    }

    object AndroidX {
        const val core = "androidx.core:core-ktx:1.6.0"
        const val appcompat = "androidx.appcompat:appcompat:1.3.1"

        object Activity {
            const val activityCompose = "androidx.activity:activity-compose:1.3.0"
        }

        object Lifecycle {
            const val viewModelCompose =
                "androidx.lifecycle:lifecycle-viewmodel-compose:$composeVersion"
        }

        object Compose {
            const val ui = "androidx.compose.ui:ui:$composeVersion"
            const val tooling = "androidx.compose.ui:ui-tooling:$composeVersion"
            const val foundation = "androidx.compose.foundation:foundation:$composeVersion"
            const val material = "androidx.compose.material:material:$composeVersion"
            const val icons = "androidx.compose.material:material-icons-core:$composeVersion"
            const val iconsExtended =
                "androidx.compose.material:material-icons-extended:$composeVersion"
            const val runtime = "androidx.compose.runtime:runtime:$composeVersion"
            const val compiler = "androidx.compose.compiler:compiler:$composeVersion"
        }
    }
}
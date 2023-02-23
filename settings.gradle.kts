pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

rootProject.name = "Cashew"
include(":androidApp")
include(":core")

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }

    versionCatalogs {
        create("libs") {
            version("ktor", "2.1.0")
            library("ktor-client-core", "io.ktor", "ktor-client-core").versionRef("ktor")
            library("ktor-client-cio", "io.ktor", "ktor-client-cio").versionRef("ktor")
            library("ktor-client-serialization", "io.ktor", "ktor-client-serialization").versionRef(
                "ktor"
            )
            library("ktor-client-logging", "io.ktor", "ktor-client-logging").versionRef("ktor")
            library("ktor-client-contentNegotiation", "io.ktor", "ktor-client-content-negotiation").versionRef("ktor")
            library("ktor-serialization-kotlinx-json", "io.ktor", "ktor-serialization-kotlinx-json").versionRef("ktor")
            library("ktor-client-auth", "io.ktor", "ktor-client-auth").versionRef("ktor")
            bundle(
                "ktor-client",
                listOf(
                    "ktor-client-core",
                    "ktor-client-serialization",
                    "ktor-client-logging",
                    "ktor-client-contentNegotiation",
                    "ktor-client-auth",
                    "ktor-serialization-kotlinx-json"
                )
            )

            library("ktor-client-cio", "io.ktor", "ktor-client-cio").versionRef("ktor")
            library("ktor-client-okhttp", "io.ktor", "ktor-client-okhttp").versionRef("ktor")

            version("decompose", "1.0.0")
            library("decompose-core", "com.arkivanov.decompose", "decompose").versionRef("decompose")
            library("decompose-compose", "com.arkivanov.decompose", "extensions-compose-jetpack").versionRef("decompose")

            version("koin", "3.3.0")
            library("koin-core", "io.insert-koin", "koin-core").versionRef("koin")

            // Replica
            version("replica", "1.0.0-alpha10")
            library("replica-core", "com.github.aartikov", "replica-core").versionRef("replica")
            library("replica-algebra", "com.github.aartikov", "replica-algebra").versionRef("replica")
            library("replica-android-network", "com.github.aartikov", "replica-android-network").versionRef("replica")
            library("replica-decompose", "com.github.aartikov", "replica-decompose").versionRef("replica")
            library("replica-devtools", "com.github.aartikov", "replica-devtools").versionRef("replica")
            bundle(
                "replica",
                listOf(
                    "replica-core",
                    "replica-algebra",
                    "replica-decompose",
                    "replica-devtools"
                )
            )

            // Storage
            library("multiplatform-settings", "com.russhwolf", "multiplatform-settings").version("1.0.0")

            // String resources
            version("moko", "0.20.1")
            library("moko-resources-core", "dev.icerock.moko","resources").versionRef("moko")
            library("moko-resources-compose", "dev.icerock.moko", "resources-compose").versionRef("moko")
            library("moko-resources-test", "dev.icerock.moko", "resources-test").versionRef("moko")

            // ---ANDROID---

            // Compose
            version("compose", "1.3.1")
            library("compose-ui", "androidx.compose.ui", "ui").versionRef("compose")
            library("compose-tooling", "androidx.compose.ui", "ui-tooling").versionRef("compose")
            library("compose-preview", "androidx.compose.ui", "ui-tooling-preview").versionRef("compose")
            library("compose-foundation", "androidx.compose.foundation", "foundation").versionRef("compose")
            library("compose-material", "androidx.compose.material", "material").versionRef("compose")
            library("compose-activity", "androidx.activity", "activity-compose").version("1.5.1")
            bundle(
                "compose",
                listOf(
                    "compose-ui",
                    "compose-tooling",
                    "compose-preview",
                    "compose-foundation",
                    "compose-material",
                    "compose-activity"
                )
            )

            // Excrypted shared spref
            library("android-security", "androidx.security", "security-crypto").version("1.1.0-alpha04")

            // Image loader
            version("coil", "2.2.2")
            library("coil-core", "io.coil-kt", "coil").versionRef("coil")
            library("coil-compose", "io.coil-kt", "coil-compose").versionRef("coil")
        }
    }
}
include(":features")

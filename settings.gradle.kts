pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

rootProject.name = "Cashew"
include(":androidApp")
include(":shared")

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }

    versionCatalogs {
        create("libs") {
            version("ktor", "2.1.0")
            library("ktor-client-core", "io.ktor", "ktor-client-core").versionRef("ktor")
            library("ktor-client-cio", "io.ktor", "ktor-client-cio").versionRef("ktor")
            library("ktor-client-serialization", "io.ktor", "ktor-client-serialization").versionRef("ktor")
            library("ktor-client-logging", "io.ktor", "ktor-client-logging").versionRef("ktor")
            library("ktor-client-contentNegotiation", "io.ktor", "ktor-client-content-negotiation").versionRef("ktor")
            library(
                "ktor-serialization-kotlinx-json",
                "io.ktor",
                "ktor-serialization-kotlinx-json"
            ).versionRef("ktor")
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
            library(
                "decompose-compose",
                "com.arkivanov.decompose",
                "extensions-compose-jetpack"
            ).versionRef("decompose")

            version("koin", "3.3.0")
            library("koin-core", "io.insert-koin", "koin-core").versionRef("koin")

            library("loader", "io.github.kursor1337", "kmm-data-loading-automation").version("0.1")

            // Storage
            library("multiplatform-settings", "com.russhwolf", "multiplatform-settings").version("1.0.0")

            // Excrypted shared spref
            library("android-security", "androidx.security", "security-crypto").version("1.1.0-alpha04")
        }
    }
}
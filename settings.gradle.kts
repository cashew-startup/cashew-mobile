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
            bundle(
                "ktor-client",
                listOf(
                    "ktor-client-core",
                    "ktor-client-cio",
                    "ktor-client-serialization",
                    "ktor-client-logging",
                    "ktor-client-contentNegotiation",
                    "ktor-serialization-kotlinx-json"
                )
            )

            version("decompose", "0.8.0")
            library("decompose", "com.arkivanov.decompose", "decompose").versionRef("decompose")

            version("koin", "3.3.0")
            library("koin-core", "io.insert-koin", "koin-core").versionRef("koin")

            library("loader", "io.github.kursor1337", "kmm-data-loading-automation").version("0.1")
        }
    }
}
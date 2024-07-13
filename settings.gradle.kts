pluginManagement {
    repositories {
        maven {
            url=uri("https://inexus.samentic.com/repository/samentic-android/")
            artifactUrls("https://inexus.samentic.com/repository/samentic-android/")
            credentials{
                username="college-student"
                password="=mq)CTV.8NTu#j4"
            }
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        maven {
            url=uri("https://inexus.samentic.com/repository/samentic-android/")
            artifactUrls("https://inexus.samentic.com/repository/samentic-android/")
            credentials{
                username="college-student"
                password="=mq)CTV.8NTu#j4"
            }
    }
        google()
        mavenCentral()
    }
}

rootProject.name = "My Application"
include(":app")}
 
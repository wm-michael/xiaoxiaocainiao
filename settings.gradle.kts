pluginManagement {
    repositories {
        google()
        //mavenCentral()
        maven {
            url = uri("https://maven.aliyun.com/repository/public")
            isAllowInsecureProtocol = true
        }
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        //mavenCentral()
        maven {
            url = uri("https://maven.aliyun.com/repository/public")
            isAllowInsecureProtocol = true
        }
    }
}

rootProject.name = "XiaoXiaoCaiNiao"
include(":app")
include(":lib-network")
include(":module-retrofit")
include(":lib-room")
include(":module-room")
include(":lib-room-flow")
include(":module-ui-demo")

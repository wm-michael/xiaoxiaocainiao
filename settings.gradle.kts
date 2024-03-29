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
include(":lib-apollographql")
include(":module-apollographql")
include(":lib-glide")
include(":module-glide")
include(":lib-localstore")
include(":module-localstore")
include(":lib-xlog")
include(":module-xlog")
include(":lib-framework")
include(":module-workmanager")
include(":module-exoplayer")
include(":lib-common")
include(":module-compose")
include(":module-test-compose")
include(":module-file-system")
include(":module-service")
include(":module-content-provider")
include(":module-livedata")
include(":module-paging")

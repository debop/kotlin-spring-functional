pluginManagement {
    repositories {
        gradlePluginPortal()
        maven("https://repo.spring.io/milestone")
        maven("https://repo.spring.io/snapshot")
        jcenter()
    }

    resolutionStrategy {
        eachPlugin {
            if(requested.id.id == "org.springframework.boot") {
                useModule("org.springframework.boot:spring-boot-gradle-plugin:${requested.version}")
            }
        }
    }
}

rootProject.name = "kotlin-spring-functional"

include("kotlin-testcontainers")
include("springfu:kofu-coroutines-mongodb")

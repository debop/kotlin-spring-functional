package io.github.debop.springfu.coroutines

import io.github.debop.kotlin.testcontainers.MongoDBContainer
import kotlinx.coroutines.runBlocking
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.fu.kofu.configuration
import org.springframework.fu.kofu.mongo.reactiveMongodb
import org.springframework.fu.kofu.webflux.webFlux


val dataConfig = configuration {
    beans {
        bean<UserRepository>()
    }
    listener<ApplicationReadyEvent> {
        runBlocking {
            ref<UserRepository>().init()
        }
    }
    reactiveMongodb {
        uri = MongoDBContainer.instance.connectionString + "/test"
    }
}

val webConfig = configuration {
    beans {
        bean<UserHandler>()
        bean(::routes)
    }
    webFlux {
        port = if(profiles.contains("test")) 8181 else 8080
        codecs {
            string()
            jackson()
        }
    }
}
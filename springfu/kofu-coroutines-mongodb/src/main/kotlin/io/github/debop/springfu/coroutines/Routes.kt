package io.github.debop.springfu.coroutines

import org.springframework.web.reactive.function.server.coRouter


fun routes(userHandler: UserHandler) = coRouter {

    GET("/", userHandler::listApi)
    GET("/api/user", userHandler::listApi)
    GET("/conf", userHandler::conf)

}
package io.github.debop.springfu.coroutines

import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse.ok
import org.springframework.web.reactive.function.server.bodyAndAwait

@Suppress("UNUSED_PARAMETER")
class UserHandler(private val repository: UserRepository,
                  private val configuration: SampleProperties) {


    suspend fun listApi(request: ServerRequest) =
        ok().contentType(MediaType.APPLICATION_JSON).bodyAndAwait(repository.findAll())

    suspend fun conf(request: ServerRequest) =
        ok().bodyAndAwait(configuration.message)
}
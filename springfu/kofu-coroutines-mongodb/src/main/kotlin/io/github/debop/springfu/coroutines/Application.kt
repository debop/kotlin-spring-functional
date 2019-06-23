package io.github.debop.springfu.coroutines

import org.springframework.boot.WebApplicationType
import org.springframework.fu.kofu.application

/**
 * Application
 *
 * @author debop (Sunghyouk Bae)
 * @since 19. 6. 22
 */

val app = application(WebApplicationType.REACTIVE) {
    configurationProperties<SampleProperties>(prefix = "sample")
    enable(dataConfig)
    enable(webConfig)
}

fun main() {
    // TestContainers 로 작업하기
    app.run()
}
package cz.boris.reactive.reactivedemo.redirects

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.router

@Configuration
class RedirectRoutes(val redirectHandler: RedirectHandler) {
    @Bean
    fun redirectsApi() = router {
        accept(MediaType.APPLICATION_JSON).nest {
            "/redirects".nest {
                GET("/", redirectHandler::allRedirects)
                GET("/{id}", redirectHandler::oneRedirect)
            }
        }
    }
}
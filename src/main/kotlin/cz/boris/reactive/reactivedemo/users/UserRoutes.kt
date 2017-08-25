package cz.boris.reactive.reactivedemo.users

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.router

@Configuration
class UserRoutes(val userHandler: UserHandler) {
    @Bean
    fun usersApi() = router {
        accept(MediaType.APPLICATION_JSON).nest {
            "/users".nest {
                GET("/", userHandler::allUsers)
                GET("/{id}", userHandler::oneUser)
            }
        }
    }
}
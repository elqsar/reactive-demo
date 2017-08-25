package cz.boris.reactive.reactivedemo.users

import cz.boris.reactive.reactivedemo.bodyWrapper
import cz.boris.reactive.reactivedemo.json
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Component
class UserHandler {
    fun allUsers(req: ServerRequest): Mono<ServerResponse> = ServerResponse.ok()
            .json()
            .body(bodyWrapper(Flux.just(User(1L, "Boris"), User(2L, "Sebastian")), User::class.java))

    fun oneUser(req: ServerRequest): Mono<ServerResponse> = ServerResponse.ok()
            .json()
            .body(bodyWrapper(Mono.just(User(req.pathVariable("id").toLong(), "Ilona")), User::class.java))
}


data class User(val id: Long, val name: String)
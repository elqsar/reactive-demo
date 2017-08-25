package cz.boris.reactive.reactivedemo.redirects

import cz.boris.reactive.reactivedemo.bodyWrapper
import cz.boris.reactive.reactivedemo.json
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Component
class RedirectHandler(val redirectRepository: RedirectRepository) {
    fun allRedirects(req: ServerRequest): Mono<ServerResponse> =
            ServerResponse.ok()
                    .json()
                    .body(bodyWrapper(Flux.just(Redirect("from", "to"), Redirect("from", "to")), Redirect::class.java))


    fun oneRedirect(req: ServerRequest): Mono<ServerResponse> =
            ServerResponse.ok()
                    .json()
                    .body(bodyWrapper(Mono.just(Redirect("from", "to")), Redirect::class.java))


}
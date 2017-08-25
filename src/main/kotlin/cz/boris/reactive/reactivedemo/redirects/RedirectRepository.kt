package cz.boris.reactive.reactivedemo.redirects

import org.springframework.data.redis.core.ReactiveRedisTemplate
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono

@Repository
class RedirectRepository(val reactiveRedisTemplate: ReactiveRedisTemplate<String, String>, val redisContentService: RedirectContentService) {

    fun save(redirect: Redirect): Mono<Boolean> {
        val (from, to) = redirect
        if (redisContentService.isExternalUrl(from)) {
            return reactiveRedisTemplate.opsForValue().set(
                    redisContentService.createKey(redisContentService.addLeadingSlash(from)), to)
        }
        return reactiveRedisTemplate.opsForValue().set(redisContentService.createKey(from), to)
    }

}
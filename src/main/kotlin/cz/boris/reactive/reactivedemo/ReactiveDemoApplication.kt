package cz.boris.reactive.reactivedemo

import org.reactivestreams.Publisher
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory
import org.springframework.data.redis.core.ReactiveRedisTemplate
import org.springframework.data.redis.serializer.RedisSerializationContext
import org.springframework.data.redis.serializer.StringRedisSerializer
import org.springframework.http.MediaType
import org.springframework.http.ReactiveHttpOutputMessage
import org.springframework.web.reactive.function.BodyInserter
import org.springframework.web.reactive.function.BodyInserters
import org.springframework.web.reactive.function.server.ServerResponse


@SpringBootApplication
class ReactiveDemoApplication {

    @Bean
    fun reactiveRedisTemplate(reactiveRedisConnectionFactory: ReactiveRedisConnectionFactory): ReactiveRedisTemplate<String, String> {
        val serializer = StringRedisSerializer()
        val serializationContext = RedisSerializationContext.newSerializationContext<String, String>()
                .key(serializer)
                .value(serializer)
                .hashKey(serializer)
                .hashValue(serializer)
                .build()
        return ReactiveRedisTemplate(reactiveRedisConnectionFactory, serializationContext)
    }
}

fun ServerResponse.BodyBuilder.json(): ServerResponse.BodyBuilder = contentType(MediaType.APPLICATION_JSON)

fun<T> bodyWrapper(p: Publisher<T>, clazz: Class<T>): BodyInserter<Publisher<T>, ReactiveHttpOutputMessage> = BodyInserters.fromPublisher(p, clazz)


fun main(args: Array<String>) {
    SpringApplication.run(ReactiveDemoApplication::class.java, *args)
}

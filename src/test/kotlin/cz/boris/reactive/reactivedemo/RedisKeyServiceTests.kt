package cz.boris.reactive.reactivedemo

import cz.boris.reactive.reactivedemo.redirects.RedirectContentService
import io.kotlintest.matchers.shouldBe
import io.kotlintest.properties.forAll
import io.kotlintest.properties.headers
import io.kotlintest.properties.row
import io.kotlintest.properties.table
import org.junit.Test

class RedisKeyServiceTests {

    private val redisKeyService: RedirectContentService = RedirectContentService()

    @Test
    fun addSlashesTest() {
        val data = table(
                headers("url", "result"),
                row("/seznam.cz/beauty", "/seznam.cz/beauty"),
                row("seznam.cz", "/seznam.cz")
        )

        forAll(data) { input, output ->
            redisKeyService.addLeadingSlash(input) shouldBe output
        }
    }

    @Test
    fun externalUrlTest() {
        val data = table(
                headers("url", "result"),
                row("http://seznam.cz/beauty", true),
                row("www.seznam.cz", true),
                row("/internal.html", false)
        )

        forAll(data) { url, result ->
            redisKeyService.isExternalUrl(url) shouldBe result
        }
    }

    @Test
    fun removeNamespaceTest() {
        val data = table(
                headers("from", "to"),
                row("source:/beauty", "/beauty"),
                row("source:/beauty/data", "/beauty/data")
        )

        forAll(data) { from, to ->
            redisKeyService.removeNamespace(from) shouldBe to
        }
    }

    @Test
    fun addHttpPrefixTest() {
        val data = table(
                headers("input", "output"),
                row("http://seznam.cz", "http://seznam.cz"),
                row("www.seznam.cz", "http://www.seznam.cz")
        )

        forAll(data) { input, output ->
            redisKeyService.addHttpPrefix(input) shouldBe output
        }
    }
}
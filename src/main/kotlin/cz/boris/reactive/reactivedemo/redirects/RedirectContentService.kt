package cz.boris.reactive.reactivedemo.redirects

import org.springframework.stereotype.Service

@Service
class RedirectContentService {

    val namespace = "source:"
    val externalUrlPattern = Regex("^(http://www\\.|https://www\\.|http://|https://)?[a-z0-9]+([\\-.][a-z0-9]+)*\\.[a-z]{2,5}(:[0-9]{1,5})?(/.*)?\$")

    fun createKey(content: String): String {
        return "$namespace/$content"
    }

    fun addLeadingSlash(source: String): String {
        return startsWith(source, "/")
    }

    fun addHttpPrefix(source: String): String {
        return startsWith(source, "http://")
    }

    fun isExternalUrl(url: String): Boolean {
        return url.matches(externalUrlPattern)
    }

    fun removeNamespace(source: String): String {
        return source.substring(namespace.length)
    }

    private fun startsWith(source: String, prefix: String): String {
        return when {
            source.startsWith(prefix) -> source
            else -> "$prefix$source"
        }
    }
}
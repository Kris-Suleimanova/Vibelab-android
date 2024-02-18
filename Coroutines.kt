import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import java.net.HttpURLConnection
import java.net.URL

val websites = listOf(
    "https://www.google.com",
    "https://www.facebook.com",
    "https://www.github.com",
    "https://www.twitter.com",
    "https://www.instagram.com"
)

fun main() {
    runBlocking {
        val jobs = websites.map { url ->
            async(Dispatchers.IO) {
                val isAvailable = checkWebsite(url)
                if (isAvailable) {
                    "Сайт $url доступен"
                } else {
                    "Сайт $url недоступен"
                }
            }
        }
        
        jobs.forEach { println(it.await()) }
    }
}

suspend fun checkWebsite(url: String): Boolean {
    return try {
        val connection = URL(url).openConnection() as HttpURLConnection
        connection.requestMethod = "HEAD"
        connection.connectTimeout = 5000
        connection.readTimeout = 5000
        connection.responseCode == HttpURLConnection.HTTP_OK
    } catch (e: Exception) {
        false
    }
}

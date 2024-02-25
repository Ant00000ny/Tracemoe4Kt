import com.isekaiofficial.tracemoe4kt.TracemoeClient
import com.isekaiofficial.tracemoe4kt.client
import com.isekaiofficial.tracemoe4kt.objectMapper
import io.ktor.client.call.*
import io.ktor.client.request.*
import kotlinx.coroutines.runBlocking
import java.io.File
import kotlin.test.Test

class Test {
    private val apiKey = object {}
        .javaClass
        .classLoader
        .getResource("properties.json")
        ?.toURI()
        ?.let { File(it) }
        ?.readText(Charsets.UTF_8)
        ?.let { objectMapper.readTree(it) }
        ?.at("/api_key")
        ?.asText()

    private val tracemoeClient = TracemoeClient(apiKey = apiKey)

    @Test
    fun testUrlSearch() = runBlocking {
        val searchResult = tracemoeClient.searchAnime(imgUrl = "https://avatars.githubusercontent.com/u/20972206?v=4")
        println(searchResult)
    }

    @Test
    fun testImageBytesSearch()  = runBlocking {
        val image = client.get("https://avatars.githubusercontent.com/u/20972206?v=4").body<ByteArray>()

        val searchResult = tracemoeClient.searchAnime(imgBytes = image)
        println(searchResult)
    }

    @Test
    fun testGetMe() = runBlocking {
        val me = tracemoeClient.getMe()
        println(me)
    }
}

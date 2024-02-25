package com.isekaiofficial.tracemoe4kt

import com.fasterxml.jackson.module.kotlin.readValue
import com.isekaiofficial.tracemoe4kt.entity.MeResult
import com.isekaiofficial.tracemoe4kt.entity.TracemoeResponse
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.request.forms.*
import io.ktor.http.*

class TracemoeClient(
    private val apiKey: String? = null,
    private val cutBorders: Boolean = false,
) {
    suspend fun searchAnime(imgUrl: String? = null, imgBytes: ByteArray? = null): TracemoeResponse {
        require(listOfNotNull(imgUrl, imgBytes).size == 1) { "Only one of imgUrl or imgBytes should be provided" }

        val url = URLBuilder()
            .apply {
                takeFrom("$TRACEMOE_API/search")
                listOf(
                    "cutBorders" to if (cutBorders) "1" else null,
                    "x-trace-key" to apiKey,
                    "url" to imgUrl,
                )
                    .filter { it.second != null && it.second != "null" }
                    .forEach { parameters.append(it.first, it.second!!) }
            }
            .buildString()

        val responseJson = if (imgUrl != null) {
            client.get(url)
        } else {
            client.submitFormWithBinaryData(
                url = url,
                formData = formData {
                    append(
                        "image", imgBytes!!,
                        Headers.build {
                            if (!apiKey.isNullOrEmpty()) append("x-trace-key", apiKey)
                            append(HttpHeaders.ContentType, ContentType.Image.Any)
                            append(HttpHeaders.ContentDisposition, "filename=image.png")
                        }
                    )
                }
            )
        }
            .body<String>()

        return objectMapper.readValue<TracemoeResponse>(responseJson)
    }

    suspend fun getMe(): MeResult {
        val responseJson = client.get("$TRACEMOE_API/me").body<String>()
        return objectMapper.readValue<MeResult>(responseJson)
    }
}

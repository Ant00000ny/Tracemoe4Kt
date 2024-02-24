package com.isekaiofficial.tracemoe4kt

import com.fasterxml.jackson.module.kotlin.convertValue
import com.isekaiofficial.tracemoe4kt.entity.TracemoeResponse
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.request.forms.*
import io.ktor.http.*

class TracemoeClient(private val apiKey: String? = null) {
    suspend fun searchAnime(imgUrl: String): TracemoeResponse {
        val responseJson = client
            .get {
                url {
                    takeFrom(imgUrl)
                    parameters {
                        append("cutBorders", "")
                    }
                }
                if (!apiKey.isNullOrEmpty()) header("x-trace-key", apiKey)
            }
            .body<String>()
        return objectMapper.convertValue<TracemoeResponse>(responseJson)
    }

    suspend fun searchAnime(imgBytes: ByteArray): TracemoeResponse {
        val responseJson = client
            .submitFormWithBinaryData(
                url = "https://api.trace.moe/search",
                formData = formData {
                    append(
                        "image", imgBytes,
                        Headers.build {
                            if (!apiKey.isNullOrEmpty()) append("x-trace-key", apiKey)
                            append(HttpHeaders.ContentType, ContentType.Image.Any)
                            append(HttpHeaders.ContentDisposition, "filename=image.png")
                        }
                    )
                }
            )
            .body<String>()

        return objectMapper.convertValue<TracemoeResponse>(responseJson)
    }
}

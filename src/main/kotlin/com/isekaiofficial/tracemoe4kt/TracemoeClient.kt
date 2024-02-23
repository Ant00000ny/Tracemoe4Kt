package com.isekaiofficial.tracemoe4kt

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.module.kotlin.convertValue
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.request.forms.*
import io.ktor.http.*

class TracemoeClient(private val apiKey: String? = null) {
    suspend fun searchAnime(imgUrl: String): List<TracemoeResult> {
        val safeUrl = if (!imgUrl.startsWith("http")) {
            "https//$imgUrl"
        } else {
            imgUrl
        }

        val responseJsonNode = client
            .get(safeUrl) {
                if (!apiKey.isNullOrEmpty()) header("x-trace-key", apiKey)
            }
            .body<String>()
            .let { objectMapper.readTree(it) }
        return responseJsonToList(responseJsonNode)
    }

    suspend fun searchAnime(imgBytes: ByteArray): List<TracemoeResult> {
        val responseJsonNode = client
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
            .let { objectMapper.readTree(it) }

        return responseJsonToList(responseJsonNode)
    }

    private fun responseJsonToList(jsonNode: JsonNode): List<TracemoeResult> {
        return jsonNode.at("/result")
            .map { objectMapper.convertValue<TracemoeResult>(it) }
    }
}

package com.isekaiofficial.tracemoe4kt

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import io.ktor.client.*
import io.ktor.client.engine.okhttp.*

internal val client = HttpClient(OkHttp) {
    engine {
        config {
            followRedirects(true)
        }
    }
}

internal val objectMapper = jacksonObjectMapper()

package com.isekaiofficial.tracemoe4kt

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import io.ktor.client.*
import io.ktor.client.engine.cio.*

internal val client = HttpClient(CIO) {
    expectSuccess = true
}

internal val objectMapper = jacksonObjectMapper()

internal const val TRACEMOE_API = "https://api.trace.moe"

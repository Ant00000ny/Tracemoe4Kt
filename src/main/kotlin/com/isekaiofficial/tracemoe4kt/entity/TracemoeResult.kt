package com.isekaiofficial.tracemoe4kt.entity

data class TracemoeResult(
    val anilist: Long,
    val filename: String,
    val episode: String,
    val from: Double,
    val to: Double,
    val similarity: Double,
    val video: String,
    val image: String,
)

data class TracemoeResponse(
    val frameCount: Long,
    val error: String,
    val result: List<TracemoeResult>,
)

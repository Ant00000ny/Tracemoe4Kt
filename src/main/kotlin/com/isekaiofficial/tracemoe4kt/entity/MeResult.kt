package com.isekaiofficial.tracemoe4kt.entity

/**
 * {
 *   "id": "127.0.0.1",
 *   "priority": 0,
 *   "concurrency": 1,
 *   "quota": 1000,
 *   "quotaUsed": 43
 * }
 */
data class MeResult(
    val id: String,
    val priority: Int,
    val concurrency: Int,
    val quota: Int,
    val quotaUsed: Int,
)

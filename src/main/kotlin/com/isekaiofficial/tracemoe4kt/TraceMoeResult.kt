package com.isekaiofficial.tracemoe4kt

/**
 * ```json
 * {
 *     "anilist" : 19157,
 *     "filename" : "[Dymy][Youkai Watch][46][BIG5][1280X720].mp4",
 *     "episode" : 46,
 *     "from" : 152.42,
 *     "to" : 152.83,
 *     "similarity" : 0.7684002748051902,
 *     "video" : "https://media.trace.moe/video/19157/%5BDymy%5D%5BYoukai%20Watch%5D%5B46%5D%5BBIG5%5D%5B1280X720%5D.mp4?t=152.625&now=1708678800&token=316rjQcafiuOe93rsWY3jpYqiA0",
 *     "image" : "https://media.trace.moe/image/19157/%5BDymy%5D%5BYoukai%20Watch%5D%5B46%5D%5BBIG5%5D%5B1280X720%5D.mp4.jpg?t=152.625&now=1708678800&token=Q24U9fg8yn4FxLNXYmopE3KWw"
 *   }
 *   ```
 *
 *   [ref](https://soruly.github.io/trace.moe-api/#/docs)
 */
data class TraceMoeResult(
    val anilist: Long,
    val filename: String,
    val episode: Int,
    val from: Double,
    val to: Double,
    val similarity: Double,
    val video: String,
    val image: String,
)

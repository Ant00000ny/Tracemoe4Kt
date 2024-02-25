## Tracemoe4Kt

### Description

Tracemoe4Kt is a Kotlin library for [Trace.moe](https://trace.moe) API.

This project is WIP.

### Usage

```kotlin
// apiKey can be null
val apiKey = "YOUR_API_KEY"

val tracemoeClient = TracemoeClient(apiKey)

// search by image url
val results = tracemoeClient.searchAnime(url)

// or search by upload image bytes
val results = tracemoeClient.searchAnime(bytes)
```
See [Test.kt](src%2Ftest%2Fkotlin%2FTest.kt) for more example.

The return value above is a list of `TracemoeResponse`.

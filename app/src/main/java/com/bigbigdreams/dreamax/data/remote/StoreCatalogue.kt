package com.bigbigdreams.dreamax.data.remote

import com.bigbigdreams.dreamax.domain.model.Game
import com.bigbigdreams.dreamax.domain.model.GameSource

object StoreCatalogue {
    val categories = listOf("Action", "Adventure", "Fighting", "Racing", "Sports", "Offline")

    val games: List<Game> = listOf(
        Game(
            id = "sample_racing_1",
            packageName = "com.example.racinglegends",
            title = "Racing Legends",
            versionName = "1.2.0",
            versionCode = 12,
            category = "Racing",
            description = "High-speed offline racing. Sample store entry.",
            fileSizeBytes = 48_000_000,
            minSdk = 24,
            source = GameSource.STORE,
            lastUpdatedAt = System.currentTimeMillis()
        ),
        Game(
            id = "sample_fight_1",
            packageName = "com.example.arenafighters",
            title = "Arena Fighters",
            versionName = "3.0.1",
            versionCode = 301,
            category = "Fighting",
            description = "Classic 2D fighting. Sample store entry.",
            fileSizeBytes = 72_000_000,
            minSdk = 26,
            source = GameSource.STORE,
            lastUpdatedAt = System.currentTimeMillis() - 86_400_000
        ),
        Game(
            id = "sample_adventure_1",
            packageName = "com.example.shadowquest",
            title = "Shadow Quest",
            versionName = "2.5.0",
            versionCode = 250,
            category = "Adventure",
            description = "Explore dungeons offline. Sample store entry.",
            fileSizeBytes = 95_000_000,
            minSdk = 26,
            source = GameSource.STORE,
            lastUpdatedAt = System.currentTimeMillis() - 172_800_000
        ),
        Game(
            id = "sample_sports_1",
            packageName = "com.example.streetfootball",
            title = "Street Football",
            versionName = "1.0.4",
            versionCode = 104,
            category = "Sports",
            description = "Arcade football. Sample store entry.",
            fileSizeBytes = 55_000_000,
            minSdk = 24,
            source = GameSource.STORE,
            lastUpdatedAt = System.currentTimeMillis() - 259_200_000
        ),
        Game(
            id = "sample_offline_1",
            packageName = "com.example.puzzlemax",
            title = "Puzzle Max",
            versionName = "4.1.0",
            versionCode = 410,
            category = "Offline",
            description = "Brain teasers that work offline. Sample store entry.",
            fileSizeBytes = 28_000_000,
            minSdk = 23,
            source = GameSource.STORE,
            lastUpdatedAt = System.currentTimeMillis() - 345_600_000
        )
    )

    fun getById(id: String): Game? = games.find { it.id == id }

    fun downloadUrlFor(gameId: String): String = ""
}

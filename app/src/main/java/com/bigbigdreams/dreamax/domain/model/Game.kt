package com.bigbigdreams.dreamax.domain.model

data class Game(
    val id: String,
    val packageName: String,
    val title: String,
    val versionName: String?,
    val versionCode: Long?,
    val category: String? = null,
    val description: String? = null,
    val iconPath: String? = null,
    val apkPath: String? = null,
    val fileSizeBytes: Long? = null,
    val minSdk: Int? = null,
    val targetSdk: Int? = null,
    val source: GameSource = GameSource.IMPORTED,
    val isInstalled: Boolean = false,
    val installedVersionName: String? = null,
    val installedVersionCode: Long? = null,
    val addedAt: Long = System.currentTimeMillis(),
    val lastUpdatedAt: Long = System.currentTimeMillis(),
    val sha256: String? = null
)

enum class GameSource {
    IMPORTED,
    DOWNLOADED,
    STORE
}

enum class GameFilter {
    ALL,
    INSTALLED,
    NOT_INSTALLED,
    IMPORTED,
    DOWNLOADED
}

enum class GameSort {
    RECENT,
    NAME,
    SIZE,
    INSTALLED_FIRST
}

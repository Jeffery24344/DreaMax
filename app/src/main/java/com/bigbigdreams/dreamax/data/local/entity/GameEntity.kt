package com.bigbigdreams.dreamax.data.local.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.bigbigdreams.dreamax.domain.model.Game
import com.bigbigdreams.dreamax.domain.model.GameSource

@Entity(
    tableName = "games",
    indices = [
        Index(value = ["packageName"], unique = true),
        Index(value = ["source"]),
        Index(value = ["addedAt"])
    ]
)
data class GameEntity(
    @PrimaryKey
    val id: String,
    val packageName: String,
    val title: String,
    val versionName: String?,
    val versionCode: Long?,
    val category: String?,
    val description: String?,
    val iconPath: String?,
    val apkPath: String?,
    val fileSizeBytes: Long?,
    val minSdk: Int?,
    val targetSdk: Int?,
    val source: String,
    val isInstalled: Boolean,
    val installedVersionName: String?,
    val installedVersionCode: Long?,
    val addedAt: Long,
    val lastUpdatedAt: Long,
    val sha256: String?
) {
    fun toDomain(): Game = Game(
        id = id,
        packageName = packageName,
        title = title,
        versionName = versionName,
        versionCode = versionCode,
        category = category,
        description = description,
        iconPath = iconPath,
        apkPath = apkPath,
        fileSizeBytes = fileSizeBytes,
        minSdk = minSdk,
        targetSdk = targetSdk,
        source = runCatching { GameSource.valueOf(source) }.getOrDefault(GameSource.IMPORTED),
        isInstalled = isInstalled,
        installedVersionName = installedVersionName,
        installedVersionCode = installedVersionCode,
        addedAt = addedAt,
        lastUpdatedAt = lastUpdatedAt,
        sha256 = sha256
    )

    companion object {
        fun fromDomain(game: Game): GameEntity = GameEntity(
            id = game.id,
            packageName = game.packageName,
            title = game.title,
            versionName = game.versionName,
            versionCode = game.versionCode,
            category = game.category,
            description = game.description,
            iconPath = game.iconPath,
            apkPath = game.apkPath,
            fileSizeBytes = game.fileSizeBytes,
            minSdk = game.minSdk,
            targetSdk = game.targetSdk,
            source = game.source.name,
            isInstalled = game.isInstalled,
            installedVersionName = game.installedVersionName,
            installedVersionCode = game.installedVersionCode,
            addedAt = game.addedAt,
            lastUpdatedAt = game.lastUpdatedAt,
            sha256 = game.sha256
        )
    }
}

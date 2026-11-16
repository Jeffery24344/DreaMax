package com.bigbigdreams.dreamax.data.local.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.bigbigdreams.dreamax.domain.model.DownloadItem
import com.bigbigdreams.dreamax.domain.model.DownloadStatus

@Entity(
    tableName = "downloads",
    indices = [
        Index(value = ["gameId"]),
        Index(value = ["status"]),
        Index(value = ["createdAt"])
    ]
)
data class DownloadEntity(
    @PrimaryKey
    val id: String,
    val gameId: String,
    val packageName: String,
    val title: String,
    val versionName: String?,
    val downloadUrl: String,
    val expectedSha256: String?,
    val status: String,
    val progressPercent: Int,
    val bytesDownloaded: Long,
    val totalBytes: Long,
    val localPath: String?,
    val errorMessage: String?,
    val createdAt: Long,
    val updatedAt: Long
) {
    fun toDomain(): DownloadItem = DownloadItem(
        id = id,
        gameId = gameId,
        packageName = packageName,
        title = title,
        versionName = versionName,
        downloadUrl = downloadUrl,
        expectedSha256 = expectedSha256,
        status = runCatching { DownloadStatus.valueOf(status) }.getOrDefault(DownloadStatus.QUEUED),
        progressPercent = progressPercent,
        bytesDownloaded = bytesDownloaded,
        totalBytes = totalBytes,
        localPath = localPath,
        errorMessage = errorMessage,
        createdAt = createdAt,
        updatedAt = updatedAt
    )

    companion object {
        fun fromDomain(item: DownloadItem): DownloadEntity = DownloadEntity(
            id = item.id,
            gameId = item.gameId,
            packageName = item.packageName,
            title = item.title,
            versionName = item.versionName,
            downloadUrl = item.downloadUrl,
            expectedSha256 = item.expectedSha256,
            status = item.status.name,
            progressPercent = item.progressPercent,
            bytesDownloaded = item.bytesDownloaded,
            totalBytes = item.totalBytes,
            localPath = item.localPath,
            errorMessage = item.errorMessage,
            createdAt = item.createdAt,
            updatedAt = item.updatedAt
        )
    }
}

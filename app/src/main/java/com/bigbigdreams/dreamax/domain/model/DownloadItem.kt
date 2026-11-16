package com.bigbigdreams.dreamax.domain.model

data class DownloadItem(
    val id: String,
    val gameId: String,
    val packageName: String,
    val title: String,
    val versionName: String?,
    val downloadUrl: String,
    val expectedSha256: String? = null,
    val status: DownloadStatus = DownloadStatus.QUEUED,
    val progressPercent: Int = 0,
    val bytesDownloaded: Long = 0,
    val totalBytes: Long = 0,
    val localPath: String? = null,
    val errorMessage: String? = null,
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis()
)

enum class DownloadStatus {
    QUEUED,
    DOWNLOADING,
    PAUSED,
    COMPLETED,
    FAILED,
    CANCELLED
}

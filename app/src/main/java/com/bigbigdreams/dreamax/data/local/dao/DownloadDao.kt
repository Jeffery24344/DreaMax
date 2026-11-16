package com.bigbigdreams.dreamax.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.bigbigdreams.dreamax.data.local.entity.DownloadEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface DownloadDao {

    @Query("SELECT * FROM downloads ORDER BY createdAt DESC")
    fun observeAll(): Flow<List<DownloadEntity>>

    @Query("SELECT * FROM downloads WHERE status IN ('QUEUED', 'DOWNLOADING', 'PAUSED') ORDER BY createdAt DESC")
    fun observeActive(): Flow<List<DownloadEntity>>

    @Query("SELECT * FROM downloads WHERE id = :id LIMIT 1")
    suspend fun getById(id: String): DownloadEntity?

    @Query("SELECT * FROM downloads WHERE gameId = :gameId ORDER BY createdAt DESC LIMIT 1")
    suspend fun getLatestForGame(gameId: String): DownloadEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: DownloadEntity)

    @Update
    suspend fun update(item: DownloadEntity)

    @Query("""
        UPDATE downloads SET
            status = :status,
            progressPercent = :progress,
            bytesDownloaded = :bytesDownloaded,
            totalBytes = :totalBytes,
            localPath = :localPath,
            errorMessage = :error,
            updatedAt = :updatedAt
        WHERE id = :id
        """)
    suspend fun updateProgress(
        id: String,
        status: String,
        progress: Int,
        bytesDownloaded: Long,
        totalBytes: Long,
        localPath: String?,
        error: String?,
        updatedAt: Long = System.currentTimeMillis()
    )

    @Query("DELETE FROM downloads WHERE id = :id")
    suspend fun deleteById(id: String)

    @Query("DELETE FROM downloads WHERE status IN ('COMPLETED', 'FAILED', 'CANCELLED')")
    suspend fun clearFinished()
}

package com.bigbigdreams.dreamax.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.bigbigdreams.dreamax.data.local.entity.GameEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface GameDao {

    @Query("SELECT * FROM games ORDER BY lastUpdatedAt DESC")
    fun observeAll(): Flow<List<GameEntity>>

    @Query("SELECT * FROM games ORDER BY lastUpdatedAt DESC")
    suspend fun getAll(): List<GameEntity>

    @Query("SELECT * FROM games WHERE isInstalled = 1 ORDER BY title ASC")
    fun observeInstalled(): Flow<List<GameEntity>>

    @Query("SELECT * FROM games WHERE isInstalled = 0 ORDER BY lastUpdatedAt DESC")
    fun observeNotInstalled(): Flow<List<GameEntity>>

    @Query("SELECT * FROM games WHERE source = :source ORDER BY lastUpdatedAt DESC")
    fun observeBySource(source: String): Flow<List<GameEntity>>

    @Query("SELECT * FROM games WHERE id = :id LIMIT 1")
    suspend fun getById(id: String): GameEntity?

    @Query("SELECT * FROM games WHERE packageName = :packageName LIMIT 1")
    suspend fun getByPackageName(packageName: String): GameEntity?

    @Query("SELECT * FROM games WHERE packageName = :packageName LIMIT 1")
    fun observeByPackageName(packageName: String): Flow<GameEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(game: GameEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(games: List<GameEntity>)

    @Update
    suspend fun update(game: GameEntity)

    @Query("DELETE FROM games WHERE id = :id")
    suspend fun deleteById(id: String)

    @Query("DELETE FROM games WHERE packageName = :packageName")
    suspend fun deleteByPackageName(packageName: String)

    @Query("UPDATE games SET isInstalled = :installed, installedVersionName = :versionName, installedVersionCode = :versionCode, lastUpdatedAt = :updatedAt WHERE packageName = :packageName")
    suspend fun updateInstallStatus(
        packageName: String,
        installed: Boolean,
        versionName: String?,
        versionCode: Long?,
        updatedAt: Long = System.currentTimeMillis()
    )

    @Query("SELECT COUNT(*) FROM games")
    suspend fun count(): Int

    @Query("SELECT COALESCE(SUM(fileSizeBytes), 0) FROM games WHERE apkPath IS NOT NULL")
    suspend fun totalApkStorageBytes(): Long
}

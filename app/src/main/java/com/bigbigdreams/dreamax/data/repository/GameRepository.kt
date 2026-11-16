package com.bigbigdreams.dreamax.data.repository

import android.content.Context
import android.net.Uri
import com.bigbigdreams.dreamax.data.local.DreaMaxDatabase
import com.bigbigdreams.dreamax.data.local.entity.GameEntity
import com.bigbigdreams.dreamax.domain.model.Game
import com.bigbigdreams.dreamax.domain.model.GameFilter
import com.bigbigdreams.dreamax.domain.model.GameSource
import com.bigbigdreams.dreamax.util.ApkInfoExtractor
import com.bigbigdreams.dreamax.util.InstalledGamesHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import java.io.File
import java.util.UUID

class GameRepository(private val context: Context) {

    private val dao = DreaMaxDatabase.getInstance(context).gameDao()

    fun observeGames(filter: GameFilter = GameFilter.ALL): Flow<List<Game>> {
        return dao.observeAll().map { list -> list.map { it.toDomain() } }
    }

    suspend fun importApk(uri: Uri): Result<Game> = withContext(Dispatchers.IO) {
        runCatching {
            val (meta, tempFile) = ApkInfoExtractor.fromUri(context, uri).getOrThrow()
            val permanentDir = File(context.filesDir, "apks").apply { mkdirs() }
            val dest = File(permanentDir, "${meta.packageName}_${meta.versionCode}.apk")
            tempFile.copyTo(dest, overwrite = true)
            tempFile.delete()

            val installed = InstalledGamesHelper.getInstalledInfo(context, meta.packageName)
            val entity = GameEntity(
                id = UUID.randomUUID().toString(),
                packageName = meta.packageName,
                title = meta.title,
                versionName = meta.versionName,
                versionCode = meta.versionCode,
                category = null,
                description = null,
                iconPath = null,
                apkPath = dest.absolutePath,
                fileSizeBytes = meta.fileSizeBytes,
                minSdk = meta.minSdk,
                targetSdk = meta.targetSdk,
                source = GameSource.IMPORTED.name,
                isInstalled = installed != null,
                installedVersionName = installed?.versionName,
                installedVersionCode = installed?.versionCode,
                addedAt = System.currentTimeMillis(),
                lastUpdatedAt = System.currentTimeMillis(),
                sha256 = meta.sha256
            )
            dao.insert(entity)
            entity.toDomain()
        }
    }
}

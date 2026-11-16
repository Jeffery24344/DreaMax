package com.bigbigdreams.dreamax.util

import android.content.Context
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import java.io.File
import java.io.FileOutputStream
import java.security.MessageDigest
import java.util.UUID

data class ApkMeta(
    val packageName: String,
    val title: String,
    val versionName: String?,
    val versionCode: Long,
    val minSdk: Int?,
    val targetSdk: Int?,
    val fileSizeBytes: Long,
    val sha256: String?
)

object ApkInfoExtractor {

    fun fromUri(context: Context, uri: Uri): Result<Pair<ApkMeta, File>> = runCatching {
        val cacheDir = File(context.cacheDir, "import").apply { mkdirs() }
        val out = File(cacheDir, "apk_${UUID.randomUUID()}.apk")
        context.contentResolver.openInputStream(uri)?.use { input ->
            FileOutputStream(out).use { output -> input.copyTo(output) }
        } ?: error("Could not read selected file")

        require(out.length() > 0) { "APK file is empty" }

        val pm = context.packageManager
        val info = if (Build.VERSION.SDK_INT >= 33) {
            pm.getPackageArchiveInfo(out.absolutePath, PackageManager.PackageInfoFlags.of(0))
        } else {
            @Suppress("DEPRECATION")
            pm.getPackageArchiveInfo(out.absolutePath, 0)
        } ?: error("Not a valid APK")

        val appInfo = info.applicationInfo ?: error("Missing application info")
        appInfo.sourceDir = out.absolutePath
        appInfo.publicSourceDir = out.absolutePath
        val title = appInfo.loadLabel(pm).toString()
        val versionCode = if (Build.VERSION.SDK_INT >= 28) info.longVersionCode else {
            @Suppress("DEPRECATION")
            info.versionCode.toLong()
        }

        val sha = runCatching {
            val md = MessageDigest.getInstance("SHA-256")
            out.inputStream().use { stream ->
                val buf = ByteArray(8192)
                while (true) {
                    val n = stream.read(buf)
                    if (n <= 0) break
                    md.update(buf, 0, n)
                }
            }
            md.digest().joinToString("") { "%02x".format(it) }
        }.getOrNull()

        val meta = ApkMeta(
            packageName = info.packageName,
            title = title,
            versionName = info.versionName,
            versionCode = versionCode,
            minSdk = if (Build.VERSION.SDK_INT >= 24) appInfo.minSdkVersion else null,
            targetSdk = appInfo.targetSdkVersion,
            fileSizeBytes = out.length(),
            sha256 = sha
        )
        meta to out
    }
}

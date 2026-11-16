package com.bigbigdreams.dreamax.util

import android.content.Context
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.os.Build

object InstalledGamesHelper {

    data class InstalledInfo(
        val packageName: String,
        val title: String,
        val versionName: String?,
        val versionCode: Long,
        val isSystemApp: Boolean
    )

    fun isInstalled(context: Context, packageName: String): Boolean {
        return runCatching {
            context.packageManager.getPackageInfo(packageName, 0)
            true
        }.getOrDefault(false)
    }

    fun getInstalledInfo(context: Context, packageName: String): InstalledInfo? {
        return runCatching {
            val pm = context.packageManager
            val flags = if (Build.VERSION.SDK_INT >= 28) PackageManager.GET_META_DATA else 0
            val info = pm.getPackageInfo(packageName, flags)
            val appInfo = info.applicationInfo ?: return null
            val versionCode = if (Build.VERSION.SDK_INT >= 28) info.longVersionCode else {
                @Suppress("DEPRECATION")
                info.versionCode.toLong()
            }
            InstalledInfo(
                packageName = packageName,
                title = appInfo.loadLabel(pm).toString(),
                versionName = info.versionName,
                versionCode = versionCode,
                isSystemApp = (appInfo.flags and ApplicationInfo.FLAG_SYSTEM) != 0
            )
        }.getOrNull()
    }

    fun launch(context: Context, packageName: String): Boolean {
        val intent = context.packageManager.getLaunchIntentForPackage(packageName) ?: return false
        intent.addFlags(android.content.Intent.FLAG_ACTIVITY_NEW_TASK)
        return runCatching {
            context.startActivity(intent)
            true
        }.getOrDefault(false)
    }

    fun canLaunch(context: Context, packageName: String): Boolean {
        return context.packageManager.getLaunchIntentForPackage(packageName) != null
    }
}

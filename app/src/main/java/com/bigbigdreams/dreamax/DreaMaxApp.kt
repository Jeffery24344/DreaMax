package com.bigbigdreams.dreamax

import android.app.Application
import coil.ImageLoader
import coil.ImageLoaderFactory
import coil.disk.DiskCache
import coil.memory.MemoryCache
import coil.request.CachePolicy
import com.bigbigdreams.dreamax.data.local.DreaMaxDatabase

/**
 * Application entry. Configures Coil for efficient icon loading on low-RAM devices.
 */
class DreaMaxApp : Application(), ImageLoaderFactory {

    override fun onCreate() {
        super.onCreate()
        runCatching { DreaMaxDatabase.getInstance(this) }
    }

    override fun newImageLoader(): ImageLoader {
        return ImageLoader.Builder(this)
            .memoryCache {
                MemoryCache.Builder(this)
                    .maxSizePercent(0.15)
                    .build()
            }
            .diskCache {
                DiskCache.Builder()
                    .directory(cacheDir.resolve("image_cache"))
                    .maxSizeBytes(32L * 1024 * 1024)
                    .build()
            }
            .respectCacheHeaders(false)
            .diskCachePolicy(CachePolicy.ENABLED)
            .memoryCachePolicy(CachePolicy.ENABLED)
            .crossfade(false)
            .build()
    }
}

package com.bigbigdreams.dreamax.ui.components

import android.graphics.BitmapFactory
import android.util.Base64
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

/**
 * Official DreaMax logo — loads from assets/logo_dreamax.b64
 */
@Composable
fun DreamaxLogo(
    modifier: Modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 24.dp)
) {
    val context = LocalContext.current
    val bitmap = remember {
        runCatching {
            val b64 = context.assets.open("logo_dreamax.b64").bufferedReader().readText()
            val bytes = Base64.decode(b64, Base64.DEFAULT)
            BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
        }.getOrNull()
    }
    if (bitmap != null) {
        Image(
            bitmap = bitmap.asImageBitmap(),
            contentDescription = "DreaMax — Powered by Big Big Dreams",
            modifier = modifier,
            contentScale = ContentScale.Fit
        )
    }
}

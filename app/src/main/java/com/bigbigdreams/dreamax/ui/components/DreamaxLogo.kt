package com.bigbigdreams.dreamax.ui.components

import android.graphics.BitmapFactory
import android.util.Base64
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.bigbigdreams.dreamax.R

/**
 * Official DreaMax logo.
 * Order: drawable resource → split assets → vector fallback.
 */
@Composable
fun DreamaxLogo(
    modifier: Modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 24.dp)
) {
    val context = LocalContext.current

    // 1) Prefer PNG/WebP placed at res/drawable/logo_dreamax
    val drawableId = remember {
        context.resources.getIdentifier("logo_dreamax", "drawable", context.packageName)
    }
    if (drawableId != 0) {
        Image(
            painter = painterResource(id = drawableId),
            contentDescription = "DreaMax — Powered by Big Big Dreams",
            modifier = modifier.aspectRatio(1.5f),
            contentScale = ContentScale.Fit
        )
        return
    }

    // 2) Split base64 assets (if complete)
    val bitmap = remember {
        runCatching {
            val b64 = (0..3).joinToString("") { i ->
                context.assets.open("logo_dreamax_$i.b64").bufferedReader().readText().trim()
            }
            val bytes = Base64.decode(b64, Base64.DEFAULT)
            BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
        }.getOrNull()
    }
    if (bitmap != null) {
        Image(
            bitmap = bitmap.asImageBitmap(),
            contentDescription = "DreaMax — Powered by Big Big Dreams",
            modifier = modifier.aspectRatio(1.5f),
            contentScale = ContentScale.Fit
        )
        return
    }

    // 3) Vector fallback (D + gamepad + orbit)
    Canvas(modifier = modifier.aspectRatio(1.5f)) {
        val w = size.width
        val h = size.height
        val cx = w * 0.5f
        val cy = h * 0.42f
        val red = Color(0xFFE53935)
        val white = Color.White

        // Orbit ring
        drawArc(
            color = red,
            startAngle = -30f,
            sweepAngle = 240f,
            useCenter = false,
            topLeft = Offset(w * 0.12f, h * 0.08f),
            size = Size(w * 0.76f, h * 0.7f),
            style = Stroke(width = w * 0.035f)
        )

        // D body
        val path = Path().apply {
            moveTo(w * 0.28f, h * 0.18f)
            lineTo(w * 0.28f, h * 0.68f)
            quadraticTo(w * 0.72f, h * 0.68f, w * 0.72f, h * 0.43f)
            quadraticTo(w * 0.72f, h * 0.18f, w * 0.28f, h * 0.18f)
            close()
        }
        drawPath(path, color = white)

        // Inner cut
        val inner = Path().apply {
            moveTo(w * 0.38f, h * 0.28f)
            lineTo(w * 0.38f, h * 0.58f)
            quadraticTo(w * 0.58f, h * 0.58f, w * 0.58f, h * 0.43f)
            quadraticTo(w * 0.58f, h * 0.28f, w * 0.38f, h * 0.28f)
            close()
        }
        drawPath(inner, color = Color.Black)

        // Gamepad body
        drawRoundRect(
            color = red,
            topLeft = Offset(w * 0.36f, h * 0.36f),
            size = Size(w * 0.22f, h * 0.14f),
            cornerRadius = CornerRadius(w * 0.04f, w * 0.04f)
        )
        // D-pad
        drawCircle(white, radius = w * 0.012f, center = Offset(w * 0.41f, h * 0.43f))
        // Buttons
        drawCircle(white, radius = w * 0.008f, center = Offset(w * 0.52f, h * 0.40f))
        drawCircle(white, radius = w * 0.008f, center = Offset(w * 0.55f, h * 0.43f))
        drawCircle(white, radius = w * 0.008f, center = Offset(w * 0.52f, h * 0.46f))
    }
}

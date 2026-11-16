package com.bigbigdreams.dreamax.ui.downloads

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.bigbigdreams.dreamax.ui.theme.DreamaxBlack
import com.bigbigdreams.dreamax.ui.theme.DreamaxWhite

@Composable
fun DownloadsScreen(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(DreamaxBlack),
        contentAlignment = Alignment.Center,
        content = {
            Text(text = "Downloads", color = DreamaxWhite)
        }
    )
}

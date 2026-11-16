package com.bigbigdreams.dreamax.ui.mygames

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.bigbigdreams.dreamax.ui.theme.DreamaxBlack
import com.bigbigdreams.dreamax.ui.theme.DreamaxLightGrey
import com.bigbigdreams.dreamax.ui.theme.DreamaxWhite

@Composable
fun MyGamesScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DreamaxBlack)
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("My Games", color = DreamaxWhite, style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(12.dp))
        Text("Import APKs and manage your library", color = DreamaxLightGrey, style = MaterialTheme.typography.bodyMedium)
        Spacer(modifier = Modifier.height(8.dp))
        Text("Full library UI is rolling out next.", color = DreamaxLightGrey, style = MaterialTheme.typography.bodySmall)
    }
}

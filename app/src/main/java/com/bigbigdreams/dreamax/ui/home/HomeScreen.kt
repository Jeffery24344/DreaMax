package com.bigbigdreams.dreamax.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.bigbigdreams.dreamax.R
import com.bigbigdreams.dreamax.ui.components.DreamaxLogo
import com.bigbigdreams.dreamax.ui.navigation.Screen
import com.bigbigdreams.dreamax.ui.theme.DreamaxBlack
import com.bigbigdreams.dreamax.ui.theme.DreamaxLightGrey
import com.bigbigdreams.dreamax.ui.theme.DreamaxRed
import com.bigbigdreams.dreamax.ui.theme.DreamaxWhite

@Composable
fun HomeScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DreamaxBlack)
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 20.dp, vertical = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(24.dp))
        DreamaxLogo(modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp))
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = stringResource(R.string.brand_slogan),
            color = DreamaxLightGrey,
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(32.dp))
        Text(
            text = "Welcome to DreaMax",
            color = DreamaxWhite,
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Your independent game library and APK store.\nImport APKs, install with the system installer, and keep everything organized.",
            color = DreamaxLightGrey,
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(28.dp))
        HomeActionButton("Open Store") {
            navController.navigate(Screen.Store.route)
        }
        Spacer(modifier = Modifier.height(12.dp))
        HomeActionButton("My Games") {
            navController.navigate(Screen.MyGames.route)
        }
        Spacer(modifier = Modifier.height(12.dp))
        HomeActionButton("Downloads") {
            navController.navigate(Screen.Downloads.route)
        }
        Spacer(modifier = Modifier.height(40.dp))
    }
}

@Composable
private fun HomeActionButton(label: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(
            containerColor = DreamaxRed,
            contentColor = DreamaxWhite
        )
    ) {
        Text(label)
    }
}

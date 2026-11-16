package com.bigbigdreams.dreamax

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.bigbigdreams.dreamax.ui.navigation.DreaMaxNavGraph
import com.bigbigdreams.dreamax.ui.theme.DreaMaxTheme
import com.bigbigdreams.dreamax.ui.theme.DreamaxBlack

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
        splashScreen.setKeepOnScreenCondition { false }
        enableEdgeToEdge()
        setContent {
            DreaMaxTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = DreamaxBlack
                ) {
                    DreaMaxAppRoot()
                }
            }
        }
    }
}

@Composable
fun DreaMaxAppRoot() {
    val navController = rememberNavController()
    DreaMaxNavGraph(navController = navController)
}

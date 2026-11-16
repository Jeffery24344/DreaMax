package com.bigbigdreams.dreamax.ui.store

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.bigbigdreams.dreamax.data.remote.StoreCatalogue
import com.bigbigdreams.dreamax.domain.model.Game
import com.bigbigdreams.dreamax.ui.navigation.Screen
import com.bigbigdreams.dreamax.ui.theme.DreamaxBlack
import com.bigbigdreams.dreamax.ui.theme.DreamaxDarkGrey
import com.bigbigdreams.dreamax.ui.theme.DreamaxLightGrey
import com.bigbigdreams.dreamax.ui.theme.DreamaxRed
import com.bigbigdreams.dreamax.ui.theme.DreamaxWhite

@Composable
fun StoreScreen(navController: NavController) {
    val games = remember { StoreCatalogue.games }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DreamaxBlack)
    ) {
        Text(
            text = "DreaMax Store",
            color = DreamaxWhite,
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 20.dp, vertical = 16.dp)
        )
        Text(
            text = "${games.size} sample games · real download URLs come later",
            color = DreamaxLightGrey,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.padding(horizontal = 20.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        LazyColumn(
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(games, key = { it.id }) { game ->
                StoreGameCard(game = game) {
                    navController.navigate(Screen.GameDetails.createRoute(game.id))
                }
            }
        }
    }
}

@Composable
private fun StoreGameCard(game: Game, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        colors = CardDefaults.cardColors(containerColor = DreamaxDarkGrey),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier.padding(14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(52.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(DreamaxBlack),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = game.title.take(1).uppercase(),
                    color = DreamaxRed,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(modifier = Modifier.width(12.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(game.title, color = DreamaxWhite, fontWeight = FontWeight.SemiBold)
                Text(
                    listOfNotNull(game.category, game.versionName?.let { "v$it" }).joinToString(" · "),
                    color = DreamaxLightGrey,
                    style = MaterialTheme.typography.bodySmall
                )
                game.description?.let {
                    Text(
                        it,
                        color = DreamaxLightGrey,
                        style = MaterialTheme.typography.bodySmall,
                        maxLines = 2
                    )
                }
            }
        }
    }
}

package com.krossovochkin.cardsoccer

import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.krossovochkin.cardsoccer.common.Card

@ExperimentalMaterialApi
@Composable
fun Deck(
    onClick: () -> Unit,
    deck: List<Card>,
) {
    Card(onClick = onClick) {
        CardBack {
            Text(
                text = deck.size.toString(),
                color = Color.White,
            )
        }
    }
}
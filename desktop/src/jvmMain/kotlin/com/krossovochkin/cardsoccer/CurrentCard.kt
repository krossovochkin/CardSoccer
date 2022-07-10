package com.krossovochkin.cardsoccer

import androidx.compose.runtime.Composable
import com.krossovochkin.cardsoccer.common.Card

@Composable
fun CurrentCard(currentCard: Card?) {
    if (currentCard != null) {
        CardFront(currentCard)
    }
}
package com.krossovochkin.cardsoccer

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.krossovochkin.cardsoccer.common.Card
import com.krossovochkin.cardsoccer.common.FieldCard

const val CARD_WIDTH_DP = 50
const val CARD_HEIGHT_DP = 70

@Composable
fun CardFront(card: FieldCard) {
    CardFront(card.card, card.selectionState)
}

@Composable
fun CardFront(card: Card, selectionState: FieldCard.SelectionState = FieldCard.SelectionState.NotSelected) {
    Card {
        Box(
            modifier = Modifier
                .background(
                    when (selectionState) {
                        FieldCard.SelectionState.NotSelected -> Color.White
                        FieldCard.SelectionState.SelectedMatch -> Color.Green
                        FieldCard.SelectionState.SelectedNotMatch -> Color.Magenta
                    }
                )
                .size(CARD_WIDTH_DP.dp, CARD_HEIGHT_DP.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = card.toString(),
                color = when (card.suite.color) {
                    Card.Color.Red -> Color.Red
                    Card.Color.Black -> Color.Black
                },
            )
        }
    }
}

@Composable
fun CardBack(
    content: @Composable () -> Unit = {}
) {
    Card {
        Box(
            modifier = Modifier.background(Color.Blue).size(CARD_WIDTH_DP.dp, CARD_HEIGHT_DP.dp),
            contentAlignment = Alignment.Center
        ) {
            content()
        }
    }
}

package com.krossovochkin.cardsoccer

import FlipCard
import PADDING_DP
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.krossovochkin.cardsoccer.common.FieldCard

@ExperimentalMaterialApi
@Composable
fun Field(
    field: List<List<FieldCard>>,
    onClick: (row: Int, column: Int) -> Unit
) {
    field.forEachIndexed { rowIndex, row ->
        Row(horizontalArrangement = Arrangement.spacedBy(PADDING_DP.dp)) {
            row.forEachIndexed { columnIndex, card ->
                FlipCard(
                    cardFace = if (card.isOpened) CardFace.Front else CardFace.Back,
                    onClick = { onClick(rowIndex, columnIndex) },
                    front = { CardFront(card) },
                    back = { CardBack() }
                )
            }
        }
    }
}
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.singleWindowApplication
import com.krossovochkin.cardsoccer.*
import com.krossovochkin.cardsoccer.common.*
import kotlinx.coroutines.delay


const val PADDING_DP = 10

fun main() = singleWindowApplication(state = WindowState(size = DpSize(500.dp, 550.dp)), title = "Card Soccer") {
    var gameState by remember { mutableStateOf(GameState.create()) }

    LaunchedEffect(gameState) {
        delay(1_000L)
        gameState = GameEngine.makeMove(gameState)
    }

    MaterialTheme {
        Game(
            gameState = gameState,
            onAction = { action ->
                if (gameState.isPlayerMove) {
                    gameState = gameState.reduce(action)
                }
            }
        )
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun Game(gameState: GameState, onAction: (GameAction) -> Unit) {
    Box(modifier = Modifier.background(Color.LightGray).fillMaxSize().padding(PADDING_DP.dp)) {
        Row(horizontalArrangement = Arrangement.spacedBy(PADDING_DP.dp)) {
            Column(verticalArrangement = Arrangement.spacedBy(PADDING_DP.dp)) {
                DeckWithGoalRow(
                    onDeckClick = { onAction(GameAction.OpenCard) },
                    onGoalClick = { onAction(GameAction.SelectGoal) },
                    goal = gameState.opponentGoal,
                    deck = gameState.opponentDeck,
                    currentCard = gameState.opponentCurrentCard,
                )

                Field(
                    field = gameState.field,
                    onClick = { row, column ->
                        onAction(GameAction.SelectRowCard(row = row, column = column))
                    }
                )

                DeckWithGoalRow(
                    onDeckClick = { onAction(GameAction.OpenCard) },
                    onGoalClick = { onAction(GameAction.SelectGoal) },
                    deck = gameState.playerDeck,
                    goal = gameState.playerGoal,
                    currentCard = gameState.playerCurrentCard,
                )
            }
            Column {
                InfoPanel(gameState = gameState, onAction = { onAction(it) })
            }
        }
    }
}

@ExperimentalMaterialApi
@Composable
private fun DeckWithGoalRow(
    onDeckClick: () -> Unit,
    onGoalClick: (CardFace) -> Unit,
    deck: List<Card>,
    goal: FieldCard,
    currentCard: Card?,
) {
    Row {
        if (currentCard != null) {
            CurrentCard(currentCard)
        } else {
            Deck(
                onClick = onDeckClick,
                deck = deck,
            )
        }
        Spacer(modifier = Modifier.size((PADDING_DP / 2 + PADDING_DP + CARD_WIDTH_DP / 2).dp))
        FlipCard(
            cardFace = if (goal.isOpened) CardFace.Front else CardFace.Back,
            onClick = onGoalClick,
            front = { CardFront(goal) },
            back = { CardBack() },
        )
    }
}

@Composable
private fun InfoPanel(gameState: GameState, onAction: (GameAction) -> Unit) {
    Text("Score: ${gameState.playerScore} - ${gameState.opponentScore}")
    Spacer(modifier = Modifier.size(10.dp))
    Text(gameState.instruction)
    Spacer(modifier = Modifier.size(10.dp))
    if (gameState.phase == GamePhase.GameOver) {
        Button(
            onClick = { onAction(GameAction.NewGame) }
        ) { Text("New game") }
    }
}
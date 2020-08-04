package model

data class InfoMessage(
    val type: String,
    val lastOpenedCard: String,
    val openedCardPos: Int,
    val lastTurn: String
)
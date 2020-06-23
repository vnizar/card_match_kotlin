package model

data class InfoMessage(
    val type: String,
    val lastOpenedCard: String,
    val openedCardPost: Int,
    val lastTurn: String
)
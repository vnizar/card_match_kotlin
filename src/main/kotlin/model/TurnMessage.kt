package model

data class TurnMessage(
    val type: String,
    val turn: Int,
    val lastTurn: String,
    val currentTurn: String
)
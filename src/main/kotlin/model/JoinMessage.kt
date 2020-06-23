package model

data class JoinMessage(
    val from: String = "player",
    val type: String = "join",
    val room: String,
    val playerName: String,
    val clientId: String
)
package model

import AppConfig.ROOM_ID

data class PickMessage(
    val type: String = "pick",
    val from: String = "player",
    val room: String = ROOM_ID,
    val clientId: String,
    val cardPos: Int
)
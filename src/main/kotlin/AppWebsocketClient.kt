import AppConfig.HOST
import AppConfig.PLAYER_NAME
import AppConfig.ROOM_ID
import com.google.gson.Gson
import model.*
import org.java_websocket.client.WebSocketClient
import org.java_websocket.handshake.ServerHandshake
import java.net.URI

class AppWebsocketClient(uri: URI = URI(HOST), private val callback: SocketCallback) : WebSocketClient(uri) {
    interface SocketCallback {
        fun onInfoEvent(info: InfoMessage)
        fun onTurnData(turnData: TurnMessage)
        fun sendPickPos(): Int
    }

    private val gson: Gson = Gson()
    private var clientId: String? = null

    override fun onOpen(handshakedata: ServerHandshake?) {
        print("Connected")
    }

    override fun onClose(code: Int, reason: String?, remote: Boolean) {
        print("Bye bye")
    }

    override fun onMessage(message: String?) {
        val data = gson.fromJson(message, TypeMessage::class.java)

        when (data.type) {
            "create" -> {
                val createdData = gson.fromJson(message, CreateMessage::class.java)
                clientId = createdData.clientId
                send(gson.toJson(JoinMessage(room = ROOM_ID, playerName = PLAYER_NAME, clientId = clientId.orEmpty())))
            }
            "info" -> {
                val infoData = gson.fromJson(message, InfoMessage::class.java)
                callback.onInfoEvent(infoData)
            }
            "turn" -> {
                val turnData = gson.fromJson(message, TurnMessage::class.java)
                callback.onTurnData(turnData)
                send(gson.toJson(PickMessage(clientId = clientId.orEmpty(), cardPos = callback.sendPickPos())))
            }
        }
    }

    override fun onError(ex: Exception?) {
        print(ex)
    }

}
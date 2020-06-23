import model.InfoMessage
import model.TurnMessage

fun main(args: Array<String>) {
    AppWebsocketClient(callback = object : AppWebsocketClient.SocketCallback {
        override fun sendPickPos(): Int {
            // To pick a card you can send the index of the card you want to pick by using
            // sendPickPos() function and return the index

            return 12
        }

        override fun onInfoEvent(info: InfoMessage) {
            // everytime a card flipped you will get data about flipped card, EX:
            // { type: 'info', lastOpenedCard: 'seven-a', openedCardPos: 3, lastTurn: 'f6d11cbf-1e88' }
        }

        override fun onTurnData(turnData: TurnMessage) {
            // if it's your turn to pick a card, you will get this event with data
            // { type: 'turn', turn: 1, lastTurn: 'f6d11cbf-1e88', currentTurn: 'f6d11cbf-1e88' }
            // turn : 0 = first pick
            // turn : 1 = second pick
        }
    }).connect()
}
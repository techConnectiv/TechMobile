package model

class ListEvent {

    var iconOng: Int? = 0
    var listEvent: String? = null
    var data: String? = null
    var hora: String? = null

    constructor(iconOng: Int?, listEvent: String?, data: String?, hora: String?) {
        this.iconOng = iconOng
        this.listEvent = listEvent
        this.data = data
        this.hora = hora

    }
}
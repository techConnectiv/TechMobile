package model

class ListEvent {

    var iconOng: Int? = 0
    var listEvent: String? = null
    var data: String? = null
    var hora: String? = null
    var description: String? = null

    constructor(iconOng: Int?, listEvent: String?, data: String?, hora: String?, description: String?) {
        this.iconOng = iconOng
        this.listEvent = listEvent
        this.data = data
        this.hora = hora
        this.description = description

    }
}
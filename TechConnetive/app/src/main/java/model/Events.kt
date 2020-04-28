package model

data class Events(
    val nomeOng: String,
    val hora: String,
    val data: String
)

class EventsBuilder {
    var nomeOng: String = ""
    var hora: String = ""
    var data: String = ""

    fun build(): Events = Events(nomeOng, hora, data)
}

fun events(block: EventsBuilder.() -> Unit): Events = EventsBuilder().apply(block).build()

fun fakeEvents(): MutableList<Events> = mutableListOf(
    events {
        nomeOng = "Cuidados com a sáude"
        hora = "09hrs - 21hrs"
        data = "Quarta-feira - 29/04/2020"
    },

    events {
        nomeOng = "Arrecadação"
        hora = "11hrs - 16hrs"
        data = "Domingo - 03/05/2020"
    },

    events {
        nomeOng = "Distribuição de sopa"
        hora = "11hrs - 14hrs"
        data = "Todos os dias"
    },

    events {
        nomeOng = "Voluntariado"
        hora = "08hrs - 18hrs"
        data = "Terça-feira - 05/05/2020"
    },

    events {
        nomeOng = "Arrecadação"
        hora = "08hrs - 18hrs"
        data = "Todos os dias"
    },

    events {
        nomeOng = "Visita Albert Einstein"
        hora = "09hrs - 18hrs"
        data = "Domingo - 03/05/2020"
    }
)
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

    fun build() : Events = Events(nomeOng, hora, data)
}

fun events(block: EventsBuilder.() -> Unit): Events = EventsBuilder().apply(block).build()

fun fakeEvents(): MutableList<Events> = mutableListOf(
    events {
        nomeOng = "Covid-19"
        hora = "11hrs - 16hrs"
        data = "Domingo - 15/04/2020"
    },

    events {
        nomeOng = "Covid-19"
        hora = "11hrs - 16hrs"
        data = "Domingo - 15/04/2020"
    },

    events {
        nomeOng = "Covid-19"
        hora = "11hrs - 16hrs"
        data = "Domingo - 15/04/2020"
    },

    events {
        nomeOng = "Covid-19"
        hora = "11hrs - 16hrs"
        data = "Domingo - 15/04/2020"
    },

    events {
        nomeOng = "Covid-19"
        hora = "11hrs - 16hrs"
        data = "Domingo - 15/04/2020"
    },
    events {
        nomeOng = "Covid-19"
        hora = "11hrs - 16hrs"
        data = "Domingo - 15/04/2020"
    },

    events {
        nomeOng = "Covid-19"
        hora = "11hrs - 16hrs"
        data = "Domingo - 15/04/2020"
    },

    events {
        nomeOng = "Covid-19"
        hora = "11hrs - 16hrs"
        data = "Domingo - 15/04/2020"
    },
    events {
        nomeOng = "Covid-19"
        hora = "11hrs - 16hrs"
        data = "Domingo - 15/04/2020"
    },
    events {
        nomeOng = "Covid-19"
        hora = "11hrs - 16hrs"
        data = "Domingo - 15/04/2020"
    },

    events {
        nomeOng = "Covid-19"
        hora = "11hrs - 16hrs"
        data = "Domingo - 15/04/2020"
    }

)
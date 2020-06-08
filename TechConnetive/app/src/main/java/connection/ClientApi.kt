package connection

import feign.Feign
import feign.gson.GsonDecoder
import feign.gson.GsonEncoder

object ClientApi {

    fun login():ApiRequests {
        return Feign.builder()
            .decoder(GsonDecoder())
            .encoder(GsonEncoder())
            .target(ApiRequests::class.java, "https://techconn.herokuapp.com/api")
    }

}
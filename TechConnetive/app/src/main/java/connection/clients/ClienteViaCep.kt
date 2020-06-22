package connection.clients

import connection.ApiRequests
import feign.Feign
import feign.gson.GsonDecoder
import feign.gson.GsonEncoder

object ClienteViaCep {

    fun criar(): ApiRequests {
        return Feign.builder()
            .decoder(GsonDecoder())
            .encoder(GsonEncoder())
            .target(ApiRequests::class.java, "https://viacep.com.br")
    }

}
package connection

import feign.Headers
import feign.RequestLine

interface ApiRequests {

    @RequestLine("POST /login/usuario")
    @Headers("Content-Type: application/json")
    fun login(user: Credenciais): Usuario

}
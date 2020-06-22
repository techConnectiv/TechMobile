package connection

import connection.objetos.*
import feign.Headers
import feign.Param
import feign.RequestLine

interface ApiRequests {

    /** POST's**/
    @RequestLine("POST /login/usuario")
    @Headers("Content-Type: application/json")
    fun login(user: Credenciais): Usuario

    @RequestLine("POST /usuario/criar")
    @Headers("Content-Type: application/json")
    fun cadastro(user: Usuario): String

    @RequestLine("POST /doacao/criar")
    @Headers("Content-Type: application/json")
    fun doacao(doacao: Doacao): String


    /** GET's**/
    @RequestLine("GET /ws/{cep}/json")
    @Headers("Content-Type: application/json")
    fun getCep(@Param("cep") cep: String): Cep

    @RequestLine("GET /doacao/list")
    @Headers("Content-Type: application/json")
    fun getDoacao(): ArrayList<Doacao>

    @RequestLine("GET /evento")
    @Headers("Content-Type: application/json")
    fun getEvento(): ArrayList<Eventos>

    @RequestLine("GET /procurar/ong/{nomeDaOng}")
    @Headers("Content-Type: application/json")
    fun getOng(@Param("nomeDaOng") ong: String): ArrayList<Instituicao>

    @RequestLine("GET /ong/list")
    @Headers("Content-Type: application/json")
    fun getAllOng(): ArrayList<Instituicao>

}
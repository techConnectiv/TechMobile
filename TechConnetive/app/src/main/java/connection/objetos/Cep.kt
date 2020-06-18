package connection.objetos

import java.io.Serializable

data class Cep(
    val logradouro: String, val numero: String,
    val bairro: String, val cep: String,
    val uf: Uf, val localidade: String
) : Serializable
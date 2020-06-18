package connection.objetos

import java.io.Serializable

data class Endereco(
    val logradouro: String, val numero: String,
    val bairro: String, val cep: String,
    val uf: String, val cidade: String
) : Serializable
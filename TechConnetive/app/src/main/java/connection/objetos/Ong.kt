package connection.objetos

import java.io.Serializable

data class Ong(
    val nomeInst: String, val credenciais: Credenciais, val cnpj: String, val url: String,
    val endereco: Endereco, val contato: Contato
) : Serializable
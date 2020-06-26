package connection.objetos

import java.util.*

data class Doacao(
    val tipo: String,
    val nomeOng: String,
    val descricao: String,
    val qnt: String,
    val comentario: String,
    val validade: String,
    val endereco: Endereco,
    val nomeUser: String
)
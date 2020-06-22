package connection.objetos

import java.io.Serializable

data class Eventos(
    val endereco: Endereco,
    val descricao: String,
    val data: String,
    val nomeOng: String
)
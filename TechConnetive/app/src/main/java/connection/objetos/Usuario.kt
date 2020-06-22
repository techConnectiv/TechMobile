package connection.objetos

import java.io.Serializable

data class Usuario(
    val nome: String, val dt_Nasc: String, val cpf: String, val sex: String, val credenciais: Credenciais,
    val endereco: Endereco, val contato: Contato
): Serializable
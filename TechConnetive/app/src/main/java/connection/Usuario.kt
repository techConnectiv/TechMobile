package connection

import java.io.Serializable

data class Usuario(val nome: String, val cpf: String, val credenciais: Credenciais): Serializable
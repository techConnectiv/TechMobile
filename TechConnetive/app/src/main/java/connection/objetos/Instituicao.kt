package connection.objetos

import java.io.Serializable

data class Instituicao(
   // val icon: Int,
    val nomeInst: String,
    val credenciais: Credenciais,
    val cnpj: String,
    val endereco: Endereco,
    val contato: Contato,
    val geolocalizacao: Geolocalizacao
): Serializable
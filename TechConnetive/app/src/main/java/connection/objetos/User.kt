package connection.objetos

data class User(
    val nome: String,
    val cpf: String,
    val dataNascimento: String,
    val sex: String,
    val credenciais: Credenciais,
    val cep: Cep,
    val contato: Contato
)
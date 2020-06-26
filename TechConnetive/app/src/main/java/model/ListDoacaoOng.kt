package model

class ListDoacaoOng {

    var nomeUsuario: String? = null
    var data: String? = null
    var quantidade: Int? = null
    var itemDoado: String? = null
    var endereco: String? = null
    var comentario: String? = null

    constructor(
        nomeUsuario: String?,
        data: String?,
        quantidade: Int?,
        itemDoado: String?,
        endereco: String?,
        comentario: String?
    ) {

        this.nomeUsuario = nomeUsuario
        this.data = data
        this.quantidade = quantidade
        this.itemDoado = itemDoado
        this.endereco = endereco
        this.comentario = comentario
    }
}
package model

import connection.objetos.Endereco

class ListOng {

    var iconOng: Int? = 0
    var listOng: String? = null
    var descricao: String? = null
    var km: String? = null
    var endereco: String? = null

    constructor(
        iconOng: Int?,
        listOng: String?,
        descricao: String?,
        km: String?,
        endereco: String?
    ) {
        this.iconOng = iconOng
        this.listOng = listOng
        this.descricao = descricao
        this.km = km
        this.endereco = endereco
    }
}
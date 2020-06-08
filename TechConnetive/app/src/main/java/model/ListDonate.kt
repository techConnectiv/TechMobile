package model

class ListDonate {

    var nomeOng: String? = null
    var data: String? = null
    var hora: String? = null
    var quantidade: Int? = null
    var itemDoado: String? = null

    constructor(nomeOng: String?, data: String?, hora: String?, quantidade: Int?, itemDoado: String?) {

        this.nomeOng = nomeOng
        this.data = data
        this.hora = hora
        this.quantidade = quantidade
        this.itemDoado = itemDoado

    }
}
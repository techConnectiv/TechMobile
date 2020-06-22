package connection.task

import android.os.AsyncTask
import connection.clients.ClientApi
import connection.objetos.Usuario
import feign.FeignException

class PostCadastroTask : AsyncTask<Usuario, Void, String>() {

    override fun doInBackground(vararg params: Usuario?): String? {
        try {
            return ClientApi.connect().cadastro(params[0]!!)
        } catch (e: FeignException) {
            return e.toString()
        }
    }

}
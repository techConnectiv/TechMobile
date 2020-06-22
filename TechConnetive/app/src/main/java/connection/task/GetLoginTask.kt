package connection.task

import android.os.AsyncTask
import connection.clients.ClientApi
import connection.objetos.Credenciais
import connection.objetos.Usuario
import feign.FeignException

class GetLoginTask : AsyncTask<Credenciais, Void, Usuario>() {

    override fun doInBackground(vararg params: Credenciais?): Usuario? {
        try {
            return ClientApi.connect().login(params[0]!!)
        } catch (e: FeignException) {
            return null
        }
    }

}
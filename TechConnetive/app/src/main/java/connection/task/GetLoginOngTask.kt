package connection.task

import android.os.AsyncTask
import connection.clients.ClientApi
import connection.objetos.Credenciais
import connection.objetos.Ong
import feign.FeignException

class GetLoginOngTask : AsyncTask<Credenciais, Void, Ong>() {

    override fun doInBackground(vararg params: Credenciais?): Ong? {
        try {
            return ClientApi.connect().loginOng(params[0]!!)
        } catch (e: FeignException) {
            return null
        }
    }

}
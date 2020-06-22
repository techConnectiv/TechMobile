package connection.task

import android.os.AsyncTask
import connection.clients.ClientApi
import connection.objetos.Instituicao
import feign.FeignException

class GetOngTask : AsyncTask<String, Void, ArrayList<Instituicao>>() {

    override fun doInBackground(vararg params: String?): ArrayList<Instituicao>? {
        try {
            return ClientApi.connect().getOng(params[0]!!)
        } catch (e: FeignException) {
            return null
        }
    }

}
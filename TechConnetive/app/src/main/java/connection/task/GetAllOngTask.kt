package connection.task

import android.os.AsyncTask
import connection.clients.ClientApi
import connection.objetos.Instituicao
import feign.FeignException

class GetAllOngTask : AsyncTask<Void, Void, ArrayList<Instituicao>>() {

    override fun doInBackground(vararg params: Void): ArrayList<Instituicao>? {
        try {
            return ClientApi.connect().getAllOng()
        } catch (e: FeignException) {
            return null
        }
    }

}
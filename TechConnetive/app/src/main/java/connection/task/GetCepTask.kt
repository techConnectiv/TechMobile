package connection.task

import android.os.AsyncTask
import connection.clients.ClienteViaCep
import connection.objetos.Cep
import feign.FeignException

class GetCepTask : AsyncTask<String, Void, Cep>() {

    override fun doInBackground(vararg params: String?): Cep? {
        try {
            return ClienteViaCep.criar().getCep(params[0]!!)
        } catch (e: FeignException) {
            return null
        }
    }

}
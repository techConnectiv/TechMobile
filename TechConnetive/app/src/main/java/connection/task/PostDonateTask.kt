package connection.task

import android.os.AsyncTask
import connection.clients.ClientApi
import connection.objetos.Doacao
import feign.FeignException

class PostDonateTask : AsyncTask<Doacao, Void, String>() {

    override fun doInBackground(vararg params: Doacao?): String? {
        try {
            return ClientApi.connect().doacao(params[0]!!)
        } catch (e: FeignException) {
            return "Erro ao doar. Tente novamente!"
        }
    }

}
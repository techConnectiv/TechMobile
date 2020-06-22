package connection.task

import android.annotation.SuppressLint
import android.os.AsyncTask
import connection.clients.ClientApi
import connection.objetos.Eventos
import feign.FeignException

class GetEventTask : AsyncTask<Void, Void, ArrayList<Eventos>>() {

    @SuppressLint("WrongConstant")
    override fun doInBackground(vararg params: Void): ArrayList<Eventos>? {
        try {
            return ClientApi.connect().getEvento()
        } catch (e: FeignException) {
            return null
        }
    }

}
package connection.task

import android.annotation.SuppressLint
import android.os.AsyncTask
import android.util.Log
import android.widget.Toast
import connection.clients.ClientApi
import connection.objetos.Doacao
import feign.FeignException

class GetDonateTask : AsyncTask<Void, Void, ArrayList<Doacao>>() {

    @SuppressLint("WrongConstant")
    override fun doInBackground(vararg params: Void): ArrayList<Doacao>? {
        try {
            return ClientApi.connect().getDoacao()
        } catch (e: FeignException) {
            return null
        }
    }

}
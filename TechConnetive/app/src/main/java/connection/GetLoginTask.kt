package connection

import android.os.AsyncTask
import android.widget.Toast
import feign.FeignException

class GetLoginTask : AsyncTask<Credenciais, Void, Usuario>() {

    override fun doInBackground(vararg params: Credenciais?): Usuario? {
        try {
            return ClientApi.login().login(params[0]!!)
        } catch (e: FeignException) {
            return null
        }
    }

}
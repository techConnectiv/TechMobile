package techconnective.herokuapp.com.menu

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import connection.objetos.Eventos
import connection.task.GetEventTask
import model.EventAdapter
import model.ListEvent
import techconnective.herokuapp.com.R
import techconnective.herokuapp.com.eventos.EventDetailActivity
import java.sql.Time
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class HomeFragment : Fragment(), EventAdapter.OnClickEvent {


    private var recyclerView: RecyclerView? = null
    private var arrayList: ArrayList<ListEvent>? = null
    private var linearLayoutManager: LinearLayoutManager? = null
    private var name_user: TextView? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        //Toast.makeText(context, localidade, Toast.LENGTH_LONG).show()

        name_user = view.findViewById(R.id.name_user)
        recyclerView = view.findViewById(R.id.recycler_view)

        linearLayoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recyclerView?.layoutManager = linearLayoutManager
        recyclerView?.setHasFixedSize(true)
        arrayList = ArrayList()
        setDataInList()
        recyclerView?.adapter = EventAdapter(arrayList!!, this)

        return view
    }

    override fun onItemClick(item: ListEvent, position: Int) {

        val intent = Intent(context, EventDetailActivity::class.java)

        intent.putExtra("icon", item.iconOng)
        intent.putExtra("nameEvent", item.listEvent)
        intent.putExtra("data", item.data)
        //intent.putExtra("hora", item.hora)
        intent.putExtra("description", item.description)
        intent.putExtra("endereco", item.endereco)

        startActivity(intent)

    }

    private fun setDataInList() {

        val result: ArrayList<Eventos> = GetEventTask().execute().get()

        for (x in result) {

            val address =
                "Endereço do evento:\n" +
                        "${x.endereco.logradouro}, Nº ${x.endereco.numero}, ${x.endereco.bairro}, " +
                        "${x.endereco.cidade}-${x.endereco.uf}, ${x.endereco.cep}"

            arrayList!!.add(
                ListEvent(
                    R.drawable.latter_a,
                    x.nomeOng,
                    getDateTime(x.data),
                    x.descricao,
                    address
                )
            )
        }
    }

    @SuppressLint("SimpleDateFormat")
    private fun getDateTime(s: String): String? {
        try {
            val sdf = SimpleDateFormat("dd/MM/yyyy")
            val netDate = Date(s.toLong())

            val time = SimpleDateFormat("hh:mm")
            val netTime = Time(s.toLong())

            return "${sdf.format(netDate)} ${time.format(netTime)}"
        } catch (e: Exception) {
            return e.toString()
        }
    }

}

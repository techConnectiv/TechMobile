package techconnective.herokuapp.com

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
import connection.Usuario
import model.EventAdapter
import model.ListEvent

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

        recyclerView = view.findViewById(R.id.recycler_view)
        name_user = view.findViewById(R.id.name_user)

        val message = getActivity()?.getIntent()?.getSerializableExtra("user") as Usuario

        name_user?.text =  "Bem vindo, ${message.nome}."

        Toast.makeText(context, "${name_user!!.text}", Toast.LENGTH_LONG).show()

        linearLayoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recyclerView?.layoutManager = linearLayoutManager
        recyclerView?.setHasFixedSize(true)
        arrayList = ArrayList()
        setDataInList()
        recyclerView?.adapter = EventAdapter(arrayList!!, this)

        return view
    }

    override fun onItemClick(item: ListEvent, position: Int) {

        //Toast.makeText(context, item.listEvent, Toast.LENGTH_SHORT).show()

        val intent = Intent(context, EventDetailActivity::class.java)

        intent.putExtra("icon", item.iconOng)
        intent.putExtra("nameEvent", item.listEvent)
        intent.putExtra("data", item.data)
        intent.putExtra("hora", item.hora)
        intent.putExtra("description", item.description)

        startActivity(intent)

    }

    private fun setDataInList() {

        arrayList!!.add(
            ListEvent(
                R.drawable.latter_a,
                "Arrecadação de roupas",
                "Sex. 15/05/2020"
                ,
                "08hrs - 16hrs",
                "Endereço: R. Guaporé, 187 - Santa Maria, São Caetano do Sul - SP" +
                        "\n" +
                        "\n" +
                        "Detalhes: Com muita alegria a ONG CIVE convida a todos para participar do nosso primeiro evento do ano, que será dia 15 de Março. Venham e traga a família para compartilhar conosco uma tarde divertida acompanhada de uma boa comida! Convites antecipados a R\$ 20,00 por pessoa.\n" +
                        "\n" +
                        "O que teremos?\n" +
                        "    -Macarrão ao Sugo \n" +
                        "    -Macarrão a bolonhesa\n" +
                        "    -Macarrão alho e óleo com brócolis\n" +
                        "    -Frango ao molho\n" +
                        "    -Polenta\n" +
                        "e Muito Mais!\n" +
                        "\n" +
                        "Contamos com a sua participação.\n" +
                        "\n" +
                        "ABRACE ESTA CAUSA!"
            )
        )
        arrayList!!.add(
            ListEvent(
                R.drawable.latter_b, "Macarronada", "Dom. 17/05/2020"
                , "12hrs - 16hrs",
                "Endereço: R. Guaporé, 187 - Santa Maria, São Caetano do Sul - SP" +
                        "\n" +
                        "\n" +
                        "Detalhes: Com muita alegria a ONG CIVE convida a todos para participar do nosso primeiro evento do ano, que será dia 15 de Março. Venham e traga a família para compartilhar conosco uma tarde divertida acompanhada de uma boa comida! Convites antecipados a R\$ 20,00 por pessoa.\n" +
                        "\n" +
                        "O que teremos?\n" +
                        "    -Macarrão ao Sugo \n" +
                        "    -Macarrão a bolonhesa\n" +
                        "    -Macarrão alho e óleo com brócolis\n" +
                        "    -Frango ao molho\n" +
                        "    -Polenta\n" +
                        "e Muito Mais!\n" +
                        "\n" +
                        "Contamos com a sua participação.\n" +
                        "\n" +
                        "ABRACE ESTA CAUSA!"
            )
        )
        arrayList!!.add(
            ListEvent(
                R.drawable.latter_c, "Arrecadação de roupas", "Sáb. 02/05/2020"
                , "08hrs - 16hrs",
                "Endereço: R. Guaporé, 187 - Santa Maria, São Caetano do Sul - SP" +
                        "\n" +
                        "\n" +
                        "Detalhes: Com muita alegria a ONG CIVE convida a todos para participar do nosso primeiro evento do ano, que será dia 15 de Março. Venham e traga a família para compartilhar conosco uma tarde divertida acompanhada de uma boa comida! Convites antecipados a R\$ 20,00 por pessoa.\n" +
                        "\n" +
                        "O que teremos?\n" +
                        "    -Macarrão ao Sugo \n" +
                        "    -Macarrão a bolonhesa\n" +
                        "    -Macarrão alho e óleo com brócolis\n" +
                        "    -Frango ao molho\n" +
                        "    -Polenta\n" +
                        "e Muito Mais!\n" +
                        "\n" +
                        "Contamos com a sua participação.\n" +
                        "\n" +
                        "ABRACE ESTA CAUSA!"
            )
        )
        arrayList!!.add(
            ListEvent(
                R.drawable.latter_d, "Arrecadação de roupas", "Sáb. 02/05/2020"
                , "08hrs - 16hrs",
                "Endereço: R. Guaporé, 187 - Santa Maria, São Caetano do Sul - SP" +
                        "\n" +
                        "\n" +
                        "Detalhes: Com muita alegria a ONG CIVE convida a todos para participar do nosso primeiro evento do ano, que será dia 15 de Março. Venham e traga a família para compartilhar conosco uma tarde divertida acompanhada de uma boa comida! Convites antecipados a R\$ 20,00 por pessoa.\n" +
                        "\n" +
                        "O que teremos?\n" +
                        "    -Macarrão ao Sugo \n" +
                        "    -Macarrão a bolonhesa\n" +
                        "    -Macarrão alho e óleo com brócolis\n" +
                        "    -Frango ao molho\n" +
                        "    -Polenta\n" +
                        "e Muito Mais!\n" +
                        "\n" +
                        "Contamos com a sua participação.\n" +
                        "\n" +
                        "ABRACE ESTA CAUSA!"
            )
        )
        arrayList!!.add(
            ListEvent(
                R.drawable.latter_e, "Arrecadação de roupas", "Sáb. 02/05/2020"
                , "08hrs - 16hrs",
                "Endereço: R. Guaporé, 187 - Santa Maria, São Caetano do Sul - SP" +
                        "\n" +
                        "\n" +
                        "Detalhes: Com muita alegria a ONG CIVE convida a todos para participar do nosso primeiro evento do ano, que será dia 15 de Março. Venham e traga a família para compartilhar conosco uma tarde divertida acompanhada de uma boa comida! Convites antecipados a R\$ 20,00 por pessoa.\n" +
                        "\n" +

                        "O que teremos?\n" +
                        "    -Macarrão ao Sugo \n" +
                        "    -Macarrão a bolonhesa\n" +
                        "    -Macarrão alho e óleo com brócolis\n" +
                        "    -Frango ao molho\n" +
                        "    -Polenta\n" +
                        "e Muito Mais!\n" +
                        "\n" +
                        "Contamos com a sua participação.\n" +
                        "\n" +
                        "ABRACE ESTA CAUSA!"
            )
        )
        arrayList!!.add(
            ListEvent(
                R.drawable.latter_f, "Arrecadação de roupas", "Sáb. 02/05/2020"
                , "08hrs - 16hrs",
                "Endereço: R. Guaporé, 187 - Santa Maria, São Caetano do Sul - SP" +
                        "\n" +
                        "\n" +
                        "Detalhes: Com muita alegria a ONG CIVE convida a todos para participar do nosso primeiro evento do ano, que será dia 15 de Março. Venham e traga a família para compartilhar conosco uma tarde divertida acompanhada de uma boa comida! Convites antecipados a R\$ 20,00 por pessoa.\n" +
                        "\n" +
                        "O que teremos?\n" +
                        "    -Macarrão ao Sugo \n" +
                        "    -Macarrão a bolonhesa\n" +
                        "    -Macarrão alho e óleo com brócolis\n" +
                        "    -Frango ao molho\n" +
                        "    -Polenta\n" +
                        "e Muito Mais!\n" +
                        "\n" +
                        "Contamos com a sua participação.\n" +
                        "\n" +
                        "ABRACE ESTA CAUSA!"
            )
        )
        arrayList!!.add(
            ListEvent(
                R.drawable.latter_g, "Arrecadação de roupas", "Sáb. 02/05/2020"
                , "08hrs - 16hrs",
                "Endereço: R. Guaporé, 187 - Santa Maria, São Caetano do Sul - SP" +
                        "\n" +
                        "\n" +
                        "Detalhes: Com muita alegria a ONG CIVE convida a todos para participar do nosso primeiro evento do ano, que será dia 15 de Março. Venham e traga a família para compartilhar conosco uma tarde divertida acompanhada de uma boa comida! Convites antecipados a R\$ 20,00 por pessoa.\n" +
                        "\n" +
                        "O que teremos?\n" +
                        "    -Macarrão ao Sugo \n" +
                        "    -Macarrão a bolonhesa\n" +
                        "    -Macarrão alho e óleo com brócolis\n" +
                        "    -Frango ao molho\n" +
                        "    -Polenta\n" +
                        "e Muito Mais!\n" +
                        "\n" +
                        "Contamos com a sua participação.\n" +
                        "\n" +
                        "ABRACE ESTA CAUSA!"
            )
        )
    }

}

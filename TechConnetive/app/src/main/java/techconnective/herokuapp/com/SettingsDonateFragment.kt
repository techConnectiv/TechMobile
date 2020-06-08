package techconnective.herokuapp.com

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import model.DonateAdapters
import model.ListDonate

class SettingsDonateFragment : Fragment(), DonateAdapters.OnClickDonate{

    private var recyclerView: RecyclerView? = null
    private var arrayList: ArrayList<ListDonate>? = null
    private var linearLayoutManager: LinearLayoutManager? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_settings_donate, container, false)

        recyclerView = view.findViewById(R.id.recyclerViewDonate)

        linearLayoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recyclerView?.layoutManager = linearLayoutManager
        recyclerView?.setHasFixedSize(true)
        arrayList = ArrayList()
        setDataInList()
        recyclerView?.adapter = DonateAdapters(arrayList!!, this)

        return view
    }

    override fun onItemClick(item: ListDonate, position: Int) {

        val intent = Intent(context, DonateDetailActivity::class.java)

        intent.putExtra("nameOng", item.nomeOng)
        intent.putExtra("data", item.data)
        intent.putExtra("hora", item.hora)
        intent.putExtra("quantidade", item.quantidade)
        intent.putExtra("itemDoado", item.itemDoado)

        startActivity(intent)

    }

    private fun setDataInList() {

        arrayList!!.add(
            ListDonate(
                "AMAI", "29/04/2020"
                , "13:47",
                12, "Blusa de frio"
            )
        )
        arrayList!!.add(
            ListDonate(
                "Todos Juntos", "23/04/2020"
                , "16:23",
                20, "Boneco do Bem10"
            )
        )
        arrayList!!.add(
            ListDonate(
                "SÓ BEBÊ", "10/04/2020"
                , "18:11",
                30, "Pacote de fralda"
            )
        )
        arrayList!!.add(
            ListDonate(
                "YASMIN", "28/03/2020"
                , "12:32",
                5, "Pacote de arroz 5kg"
            )
        )
        arrayList!!.add(
            ListDonate(
                "despertar", "23/03/2020"
                , "12:20",
                40, "Brinquedos vareados"
            )
        )
        arrayList!!.add(
            ListDonate(
                "Ágatha", "17/03/2020"
                , "15:35",
                20, "Cesta básica"
            )
        )
        arrayList!!.add(
            ListDonate(
                "Erguendo Vidas", "03/03/2020"
                , "14:47",
                34, "Roupas variadas"
            )
        )
        arrayList!!.add(
            ListDonate(
                "Todos Juntos", "26/02/2020"
                , "19:23",
                10, "Camisas com estampa"
            )
        )
    }
}

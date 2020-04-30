package techconnective.herokuapp.com

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import model.DonateAdapters
import model.EventAdapter
import model.ListDonate
import model.ListEvent

class SettingsDonateFragment : Fragment() {

    private var recyclerView: RecyclerView? = null
    private var arrayList: ArrayList<ListDonate>? = null
    private var linearLayoutManager:LinearLayoutManager? = null
    private var donateAdapter: DonateAdapters? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_settings_donate, container, false)

        recyclerView = view.findViewById(R.id.recyclerViewDonate)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        linearLayoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false )
        recyclerView?.layoutManager = linearLayoutManager
        recyclerView?.setHasFixedSize(true)
        arrayList = ArrayList()
        arrayList = setDataInList()
        donateAdapter = context?.let { DonateAdapters(it, arrayList!!) }
        recyclerView?.adapter = donateAdapter
    }

    private fun setDataInList(): ArrayList<ListDonate> {
        var items: ArrayList<ListDonate> = ArrayList()

        items.add(
            ListDonate(
                "AMAI", "29/04/2020"
                , "13:47"
            )
        )
        items.add(
            ListDonate(
                "Todos Juntos", "23/04/2020"
                , "16:23"
            )
        )
        items.add(
            ListDonate(
                "SÓ BEBÊ", "10/04/2020"
                , "18:11"
            )
        )
        items.add(
            ListDonate(
                "YASMIN", "28/03/2020"
                , "12:32"
            )
        )
        items.add(
            ListDonate(
                "despertar", "23/03/2020"
                , "12:20"
            )
        )
        items.add(
            ListDonate(
                "Ágatha", "17/03/2020"
                , "15:35"
            )
        )
        items.add(
            ListDonate(
                "Erguendo Vidas", "03/03/2020"
                , "14:47"
            )
        )
        items.add(
            ListDonate(
                "Todos Juntos", "26/02/2020"
                , "19:23"
            )
        )

        return items
    }
}

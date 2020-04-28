package techconnective.herokuapp.com

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import model.ListOng
import model.OngAdapters

class DonateFragment : Fragment() {

    private var recyclerView:RecyclerView ? = null
    private var gridLayoutManager:GridLayoutManager ? = null
    private var arrayList:ArrayList<ListOng> ? = null
    private var ongAdapters:OngAdapters ? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_donate, container, false)

        recyclerView = view.findViewById(R.id.recyclerView)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        gridLayoutManager = GridLayoutManager(context,3, LinearLayoutManager.VERTICAL, false )
        recyclerView?.layoutManager = gridLayoutManager
        recyclerView?.setHasFixedSize(true)
        arrayList = ArrayList()
        arrayList = setDataInList()
        ongAdapters = context?.let { OngAdapters(it, arrayList!!) }
        recyclerView?.adapter = ongAdapters
    }

    private fun setDataInList() : ArrayList<ListOng> {
        var items:ArrayList<ListOng> = ArrayList()

        items.add(ListOng(R.drawable.latter_a, "Todos Juntos"))
        items.add(ListOng(R.drawable.latter_b, "AMAI"))
        items.add(ListOng(R.drawable.latter_c, "SÓ BEBÊ"))
        items.add(ListOng(R.drawable.latter_d, "YASMIN"))
        items.add(ListOng(R.drawable.latter_e, "despertar"))
        items.add(ListOng(R.drawable.latter_f, "Ágatha"))
        items.add(ListOng(R.drawable.latter_g, "Erguendo VIDAS"))

        return items
    }
}

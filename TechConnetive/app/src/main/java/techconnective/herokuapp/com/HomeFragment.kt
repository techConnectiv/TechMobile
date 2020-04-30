package techconnective.herokuapp.com

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import model.EventAdapter
import model.ListEvent

class HomeFragment : Fragment() {

    private var recyclerView: RecyclerView? = null
    private var arrayList: ArrayList<ListEvent>? = null
    private var linearLayoutManager:LinearLayoutManager? = null
    private var eventAdapter: EventAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        recyclerView = view.findViewById(R.id.recycler_view)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        linearLayoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false )
        recyclerView?.layoutManager = linearLayoutManager
        recyclerView?.setHasFixedSize(true)
        arrayList = ArrayList()
        arrayList = setDataInList()
        eventAdapter = context?.let { EventAdapter(it, arrayList!!) }
        recyclerView?.adapter = eventAdapter
    }

    private fun setDataInList(): ArrayList<ListEvent> {
        var items: ArrayList<ListEvent> = ArrayList()

        items.add(
            ListEvent(
                R.drawable.latter_a, "Arrecadação de roupas", "Sáb. 02/05/2020"
                , "08hrs - 16hrs"
            )
        )
        items.add(
            ListEvent(
                R.drawable.latter_b, "Arrecadação de roupas", "Sáb. 02/05/2020"
                , "08hrs - 16hrs"
            )
        )
        items.add(
            ListEvent(
                R.drawable.latter_c, "Arrecadação de roupas", "Sáb. 02/05/2020"
                , "08hrs - 16hrs"
            )
        )
        items.add(
            ListEvent(
                R.drawable.latter_d, "Arrecadação de roupas", "Sáb. 02/05/2020"
                , "08hrs - 16hrs"
            )
        )
        items.add(
            ListEvent(
                R.drawable.latter_e, "Arrecadação de roupas", "Sáb. 02/05/2020"
                , "08hrs - 16hrs"
            )
        )
        items.add(
            ListEvent(
                R.drawable.latter_f, "Arrecadação de roupas", "Sáb. 02/05/2020"
                , "08hrs - 16hrs"
            )
        )
        items.add(
            ListEvent(
                R.drawable.latter_g, "Arrecadação de roupas", "Sáb. 02/05/2020"
                , "08hrs - 16hrs"
            )
        )

        return items
    }
}

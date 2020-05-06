package techconnective.herokuapp.com

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import model.ListOng
import model.OngAdapters

class DonateFragment : Fragment(), OngAdapters.OnClickOng {

    private var recyclerView: RecyclerView? = null
    private var arrayList: ArrayList<ListOng>? = null
    private var gridLayoutManager: GridLayoutManager? = null
    private var brinquedo: View? = null
    private var roupa: View? = null
    private var food: View? = null

    private var valueType = "Roupa";


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_donate, container, false)

        recyclerView = view.findViewById(R.id.recyclerView)

        brinquedo = view.findViewById(R.id.brinquedo)
        roupa = view.findViewById(R.id.roupa)
        food = view.findViewById(R.id.food)


        brinquedo!!.setOnClickListener {
            brinquedo!!.setBackgroundResource(R.drawable.ic_checked)
            roupa!!.setBackgroundResource(R.drawable.ic_unchecked)
            food!!.setBackgroundResource(R.drawable.ic_unchecked)

            valueType = "Brinquedo"
        }
        roupa!!.setOnClickListener {
            brinquedo!!.setBackgroundResource(R.drawable.ic_unchecked)
            roupa!!.setBackgroundResource(R.drawable.ic_checked)
            food!!.setBackgroundResource(R.drawable.ic_unchecked)

            valueType = "Roupa"
        }
        food!!.setOnClickListener {
            brinquedo!!.setBackgroundResource(R.drawable.ic_unchecked)
            roupa!!.setBackgroundResource(R.drawable.ic_unchecked)
            food!!.setBackgroundResource(R.drawable.ic_checked)

            valueType = "Alimento"
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        gridLayoutManager = GridLayoutManager(context, 3, LinearLayoutManager.VERTICAL, false)
        recyclerView?.layoutManager = gridLayoutManager
        recyclerView?.setHasFixedSize(true)
        arrayList = ArrayList()
        setDataInList()
        recyclerView?.adapter = OngAdapters(arrayList!!, this)
    }

    override fun onItemClick(item: ListOng, position: Int) {

        //Toast.makeText(context, item.listEvent, Toast.LENGTH_SHORT).show()

        val intent = Intent(context, FormDonateActivity::class.java)

        intent.putExtra("icon", item.iconOng)
        intent.putExtra("nameOng", item.listOng)
        intent.putExtra("type", valueType)
        startActivity(intent)

    }

    private fun setDataInList() {

        arrayList!!.add(ListOng(R.drawable.latter_a, "Todos Juntos"))
        arrayList!!.add(ListOng(R.drawable.latter_b, "AMAI"))
        arrayList!!.add(ListOng(R.drawable.latter_c, "SÓ BEBÊ"))
        arrayList!!.add(ListOng(R.drawable.latter_d, "YASMIN"))
        arrayList!!.add(ListOng(R.drawable.latter_e, "despertar"))
        arrayList!!.add(ListOng(R.drawable.latter_f, "Ágatha"))
        arrayList!!.add(ListOng(R.drawable.latter_g, "Erguendo VIDAS"))

    }
}

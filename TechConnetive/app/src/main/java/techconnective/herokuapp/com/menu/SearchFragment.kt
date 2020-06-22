package techconnective.herokuapp.com.menu

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import connection.task.GetOngTask
import kotlinx.android.synthetic.main.fragment_serach.*
import model.ListOng
import model.OngListRecycler
import techconnective.herokuapp.com.Ong.OngActivity
import techconnective.herokuapp.com.R
import kotlin.collections.ArrayList


class SearchFragment : Fragment(), OngListRecycler.OnClickOngRecycler {

    private var recyclerView: RecyclerView? = null
    private var arrayList: ArrayList<ListOng>? = null
    private var linearLayoutManager: LinearLayoutManager? = null
    private var etOng: EditText? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_serach, container, false)

        recyclerView = view.findViewById(R.id.recycler_ong)

        linearLayoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recyclerView?.layoutManager = linearLayoutManager
        recyclerView?.setHasFixedSize(true)
        arrayList = ArrayList()
        recyclerView?.adapter = OngListRecycler(arrayList!!, this)

        etOng = view.findViewById(R.id.et_ong) as EditText
        val btnPesquisa = view.findViewById(R.id.btn_pesquisa) as ImageView


        btnPesquisa.setOnClickListener {
            val nomeOng = etOng?.text.toString()
            val result = GetOngTask().execute(nomeOng).get()


            if (result != null) {

                for (x in result) {
                    arrayList!!.add(
                        ListOng(
                            R.drawable.latter_a,
                            x.nomeInst,
                            x.credenciais.login,
                            "1.8km"
                        )
                    )
                }
            } else {
                Toast.makeText(
                    context,
                    "Desculpe, não encontramos nenhuma ONG com este nome!",
                    Toast.LENGTH_LONG
                ).show()
            }
        }

        return view
    }

    override fun onItemClick(item: ListOng, position: Int) {

        val intent = Intent(context, OngActivity::class.java)

        intent.putExtra("icon", item.iconOng)
        intent.putExtra("nomOng", item.listOng)
        intent.putExtra("descricao", item.descricao)
        intent.putExtra("km", item.km)

        startActivity(intent)

    }
}

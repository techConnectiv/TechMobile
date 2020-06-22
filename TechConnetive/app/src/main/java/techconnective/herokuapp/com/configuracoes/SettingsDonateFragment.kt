package techconnective.herokuapp.com.configuracoes

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import connection.objetos.Doacao
import connection.task.GetCepTask
import connection.task.GetDonateTask
import model.DonateAdapters
import model.ListDonate
import techconnective.herokuapp.com.doacao.DonateDetailActivity
import techconnective.herokuapp.com.R
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class SettingsDonateFragment : Fragment(), DonateAdapters.OnClickDonate {

    private var recyclerView: RecyclerView? = null
    private var arrayList: ArrayList<ListDonate>? = null
    private var linearLayoutManager: LinearLayoutManager? = null
    private var btnBack: ImageView? = null

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

        btnBack = view.findViewById(R.id.return_menu)

        btnBack?.setOnClickListener {

            fadein(btnBack!!)

            val fragmentManager = activity!!.supportFragmentManager
            fragmentManager.popBackStack()

        }

        return view
    }

    override fun onItemClick(item: ListDonate, position: Int) {

        val intent = Intent(context, DonateDetailActivity::class.java)

        intent.putExtra("nomeOng", item.nomeOng)
        intent.putExtra("data", item.data)
        intent.putExtra("hora", item.hora)
        intent.putExtra("quantidade", item.quantidade)
        intent.putExtra("itemDoado", item.itemDoado)

        startActivity(intent)

    }

    private fun setDataInList() {

        val result: ArrayList<Doacao> = GetDonateTask().execute().get()

        for (x in result) {
            arrayList!!.add(
                ListDonate(
                    x.nomeOng, getDateTime(x.validade)
                    , "13:47",
                    x.qnt.toInt(), x.descricao
                )
            )
        }
    }

    private fun getDateTime(s: String): String? {
        try {
            val sdf = SimpleDateFormat("MM/dd/yyyy")
            val netDate = Date(s.toLong())
            return sdf.format(netDate)
        } catch (e: Exception) {
            return e.toString()
        }
    }

    private fun fadein(view: View) {
        val animation = AlphaAnimation(0f, 1f)
        animation.duration = 300L
        animation.repeatMode = Animation.REVERSE
        animation.repeatCount = 0
        view.startAnimation(animation)
    }

}

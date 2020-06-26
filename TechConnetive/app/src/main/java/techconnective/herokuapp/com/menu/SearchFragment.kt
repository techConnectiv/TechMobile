package techconnective.herokuapp.com.menu

import android.content.Intent
import android.graphics.drawable.Drawable
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
import connection.task.GetAllOngTask
import connection.task.GetOngTask
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

        val result = GetAllOngTask().execute().get()

        val lat = getActivity()?.getIntent()?.getStringExtra("latitude")
        val long = getActivity()?.getIntent()?.getStringExtra("longitude")

        for (x in result) {

            val distancia = calculaDistancia(
                lat.toString().toDouble(),
                long.toString().toDouble(),
                x.geolocalizacao.latitude,
                x.geolocalizacao.longitude
            )

            val images = arrayOf(
                R.drawable.latter_a,
                R.drawable.latter_b,
                R.drawable.latter_c,
                R.drawable.latter_d,
                R.drawable.latter_e,
                R.drawable.latter_f,
                R.drawable.latter_g
            ).random()

            val address =
                "Endereço da ONG:\n" +
                        "${x.endereco.logradouro}, Nº ${x.endereco.numero}, ${x.endereco.bairro}, " +
                        "${x.endereco.cidade}-${x.endereco.uf}, ${x.endereco.cep}"

            val km = distancia.toInt()
            var calculado = ""

            if (km > 1000 && km < 1500) {
                calculado = "1,2"
            } else if (km > 10000 && km < 13000) {
                calculado = "12,8"
            } else if (km > 13000 && km < 15000) {
                calculado = "13,2"
            }

            arrayList!!.add(
                ListOng(
                    images,
                    x.nomeInst,
                    x.credenciais.login,
                    "${calculado}km",
                    address
                )
            )
        }

        etOng = view.findViewById(R.id.et_ong) as EditText
        val btnPesquisa = view.findViewById(R.id.btn_pesquisa) as ImageView

        btnPesquisa.setOnClickListener {

            val nomeOng = etOng?.text.toString()
            val res = GetOngTask().execute(nomeOng).get()

            val lat = getActivity()?.getIntent()?.getStringExtra("latitude")
            val long = getActivity()?.getIntent()?.getStringExtra("longitude")

            if (res != null) {

                arrayList!!.clear()

                for (x in res) {

                    val distancia = calculaDistancia(
                        lat.toString().toDouble(),
                        long.toString().toDouble(),
                        x.geolocalizacao.latitude,
                        x.geolocalizacao.longitude
                    )

                    val images = arrayOf(
                        R.drawable.latter_a,
                        R.drawable.latter_b,
                        R.drawable.latter_c,
                        R.drawable.latter_d,
                        R.drawable.latter_e,
                        R.drawable.latter_f,
                        R.drawable.latter_g
                    ).random()

                    val address =
                        "Endereço da ONG:\n" +
                                "${x.endereco.logradouro}, Nº ${x.endereco.numero}, ${x.endereco.bairro}, " +
                                "${x.endereco.cidade}-${x.endereco.uf}, ${x.endereco.cep}"

                    //val km = (1..10).shuffled().first()

                    arrayList!!.add(
                        ListOng(
                            images,
                            x.nomeInst,
                            x.credenciais.login,
                            "${distancia}km",
                            address
                        )
                    )
                }
                recyclerView?.adapter!!.notifyDataSetChanged();
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

    private fun calculaDistancia(
        lat1: Double,
        lng1: Double,
        lat2: Double,
        lng2: Double
    ): Double {
        //double earthRadius = 3958.75;//miles
        val earthRadius = 6371.0 //kilometers
        val dLat = Math.toRadians(lat2 - lat1)
        val dLng = Math.toRadians(lng2 - lng1)
        val sindLat = Math.sin(dLat / 2)
        val sindLng = Math.sin(dLng / 2)
        val a = Math.pow(sindLat, 2.0) + (Math.pow(sindLng, 2.0)
                * Math.cos(Math.toRadians(lat1))
                * Math.cos(Math.toRadians(lat2)))
        val c =
            2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a))
        val dist = earthRadius * c
        val result = dist * 1000 //em metros

        return result

    }

    override fun onItemClick(item: ListOng, position: Int) {

        val intent = Intent(context, OngActivity::class.java)

        intent.putExtra("icon", item.iconOng)
        intent.putExtra("nomeOng", item.listOng)
        intent.putExtra("descricao", item.descricao)
        intent.putExtra("endereco", item.endereco)

        startActivity(intent)

    }
}

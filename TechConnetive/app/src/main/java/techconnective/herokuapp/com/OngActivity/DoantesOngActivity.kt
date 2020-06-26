package techconnective.herokuapp.com.OngActivity

import android.annotation.SuppressLint
import android.app.PendingIntent.getActivity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import connection.objetos.Doacao
import connection.objetos.Ong
import connection.objetos.Usuario
import connection.task.GetDonateTask
import model.ListDoacaoOng
import model.MyDonations
import techconnective.herokuapp.com.R
import techconnective.herokuapp.com.doacao.DonateDetailActivity
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class DoantesOngActivity : AppCompatActivity(), MyDonations.OnClickDonate {

    private var recyclerView: RecyclerView? = null
    private var arrayList: ArrayList<ListDoacaoOng>? = null
    private var linearLayoutManager: LinearLayoutManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_doantes_ong)

        recyclerView = findViewById(R.id.recyclerDonate)

        linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView?.layoutManager = linearLayoutManager
        recyclerView?.setHasFixedSize(true)
        arrayList = ArrayList()
        setDataInList()
        recyclerView?.adapter = MyDonations(arrayList!!, this)
    }

    override fun onItemClick(item: ListDoacaoOng, position: Int) {

        val intent = Intent(this, DoacaoInfoActivity::class.java)

        intent.putExtra("nomeUser", item.nomeUsuario)
        intent.putExtra("data", item.data)
        intent.putExtra("quantidade", item.quantidade)
        intent.putExtra("itemDoado", item.itemDoado)
        intent.putExtra("comentario", item.comentario)
        intent.putExtra("endereco", item.endereco)

        startActivity(intent)

    }

    private fun setDataInList() {

        val result: ArrayList<Doacao> = GetDonateTask().execute().get()
        val objectUser = getIntent().getSerializableExtra("user") as Ong

        for (x in result) {

            if (x.nomeOng == objectUser.nomeInst) {

                val address =
                    "${x.endereco.logradouro}, Nº ${x.endereco.numero}, ${x.endereco.bairro}, " +
                            "${x.endereco.cidade}-${x.endereco.uf}, ${x.endereco.cep}"

                arrayList!!.add(
                    ListDoacaoOng(
                        x.nomeUser, getDateTime(x.validade),
                        x.qnt.toInt(), x.descricao,
                        address,
                        x.comentario
                    )
                )
            }
        }
    }

    @SuppressLint("SimpleDateFormat")
    private fun getDateTime(s: String): String? {
        try {

            val data = SimpleDateFormat("dd/MM/yyyy").format(s.toLong())
            val hora = SimpleDateFormat("HH:mm:ss").format(s.toLong())

            return data + " " + "|" + " " + hora
        } catch (e: Exception) {
            return e.toString()
        }
    }
}
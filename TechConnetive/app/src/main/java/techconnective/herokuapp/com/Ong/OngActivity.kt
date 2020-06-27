package techconnective.herokuapp.com.Ong

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.core.graphics.drawable.toDrawable
import connection.objetos.Usuario
import kotlinx.android.synthetic.main.activity_event_detail.*
import kotlinx.android.synthetic.main.activity_ong.*
import techconnective.herokuapp.com.R
import techconnective.herokuapp.com.doacao.FormDonateActivity

class OngActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_ong)

        val iconOng = intent.extras!!.getInt("icon")
        logo.setImageResource(iconOng)

        val nameOng = intent.extras!!.getString("nomeOng")
        name_ong.text = nameOng

        val descricao = intent.extras!!.getString("descricao")
        desc_ong.text = descricao

        val enderecoOng = intent.extras!!.getString("endereco")
        adress_ong.text = enderecoOng

        favorite?.setOnClickListener {
            favorite.setImageResource(R.drawable.ic_favorited)

            Toast.makeText(this, getString(R.string.favoritar), Toast.LENGTH_LONG).show()
        }

        btn_doacao?.setOnClickListener{
            val intent = Intent(this, FormDonateActivity::class.java)

            intent.putExtra("nameOng", nameOng)
            intent.putExtra("icon", iconOng)

            startActivity(intent)
        }
    }
}
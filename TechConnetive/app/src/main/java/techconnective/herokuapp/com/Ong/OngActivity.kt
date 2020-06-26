package techconnective.herokuapp.com.Ong

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.core.graphics.drawable.toDrawable
import kotlinx.android.synthetic.main.activity_event_detail.*
import kotlinx.android.synthetic.main.activity_ong.*
import techconnective.herokuapp.com.R

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

            Toast.makeText(this, getString(R.string.favorited_messenge), Toast.LENGTH_LONG).show()
        }
    }
}
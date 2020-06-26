package techconnective.herokuapp.com.OngActivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.widget.ImageView
import kotlinx.android.synthetic.main.activity_doacao_info.*
import kotlinx.android.synthetic.main.activity_donate_detail.*
import kotlinx.android.synthetic.main.activity_event_detail.*
import techconnective.herokuapp.com.R
import techconnective.herokuapp.com.configuracoes.SettingsDonateFragment

class DoacaoInfoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar?.hide()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_doacao_info)
        val btnBack = findViewById<ImageView>(R.id.return_doacao_list)

        btnBack?.setOnClickListener {

            fadein(btnBack)

            val intent = Intent(this, DoantesOngActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
            finishAndRemoveTask()

        }

        val nomeUser = intent.extras!!.getString("nomeUser")
        val data = intent.extras!!.getString("data")
        val hora = intent.extras!!.getString("hora")
        val quantidade = intent.extras!!.getInt("quantidade")
        val itemDoado = intent.extras!!.getString("itemDoado")
        val comentario = intent.extras!!.getString("comentario")
        val endereco = intent.extras!!.getString("endereco")



        val description = "Doação feita por ${nomeUser}, no dia ${data}" +
                " as ${hora}. \n" +
                "\nItem doado: ${itemDoado}\n" +
                "\nQuantidade: ${quantidade}\n" +
                "\nComentario do doador: ${comentario}\n" +
                "\nEndereço para retirada: ${endereco}"
        description_donate_list.setText(description)
    }

    private fun fadein(view: View) {
        val animation = AlphaAnimation(0f, 1f)
        animation.duration = 300L
        animation.repeatMode = Animation.REVERSE
        animation.repeatCount = 0
        view.startAnimation(animation)
    }
}
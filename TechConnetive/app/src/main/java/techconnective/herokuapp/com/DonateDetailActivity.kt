package techconnective.herokuapp.com

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.widget.ImageView
import kotlinx.android.synthetic.main.activity_donate_detail.*

class DonateDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide();
        setContentView(R.layout.activity_donate_detail)

        var btnBack = findViewById<ImageView>(R.id.return_donate_list)

        btnBack?.setOnClickListener {

            fadein(btnBack!!)

            val intent = Intent(this, SettingsDonateFragment::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
            finishAndRemoveTask()

            val nomeOng = intent.extras!!.getString("nameEvent")
            val data = intent.extras!!.getString("data")
            val hora = intent.extras!!.getString("hora")
            val quantidade = intent.extras!!.getInt("quantidade")
            val itemDoado = intent.extras!!.getString("itemDoado")

            var description = "Doação efetuada para ${nomeOng}, no dia ${data}" +
                    "as ${hora}. \n" +
                    "Item doado: ${itemDoado}\n" +
                    "Quantidade: ${quantidade}\n" +
                    "A ong ${nomeOng} é totalmente grata por sua contribuição e conta com você" +
                    "para continuar ajudando quem precisa!"

            description_donate.setText(description)
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

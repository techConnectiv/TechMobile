package techconnective.herokuapp.com.eventos

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_event_detail.*
import techconnective.herokuapp.com.menu.MenuActivity
import techconnective.herokuapp.com.R

class EventDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_event_detail)

        val btnBack = findViewById<ImageView>(R.id.return_events)

        btnBack?.setOnClickListener {

            fadein(btnBack)

            val intent = Intent(this, MenuActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
            finishAndRemoveTask()

        }

        val iconOng = intent.extras!!.getInt("icon")
        icon_ong.setImageResource(iconOng)

        val nomeEvent = intent.extras!!.getString("nameEvent")
        name_event.text = nomeEvent

        val descriptionEvent = intent.extras!!.getString("description")
        description.text = descriptionEvent

        val enderecoEvent = intent.extras!!.getString("endereco")
        endereco.text = enderecoEvent
    }

    private fun fadein(view: View) {
        val animation = AlphaAnimation(0f, 1f)
        animation.duration = 300L
        animation.repeatMode = Animation.REVERSE
        animation.repeatCount = 0
        view.startAnimation(animation)
    }

}

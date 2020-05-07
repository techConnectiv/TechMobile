package techconnective.herokuapp.com

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_event_detail.*

class EventDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide();
        setContentView(R.layout.activity_event_detail)

        var btnBack = findViewById<ImageView>(R.id.return_events)

        btnBack?.setOnClickListener {

            fadein(btnBack!!)

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
    }

    private fun fadein(view: View) {
        val animation = AlphaAnimation(0f, 1f)
        animation.duration = 300L
        animation.repeatMode = Animation.REVERSE
        animation.repeatCount = 0
        view.startAnimation(animation)
    }

}

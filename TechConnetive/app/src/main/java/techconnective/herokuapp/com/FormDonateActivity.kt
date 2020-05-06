package techconnective.herokuapp.com

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.customview.customView
import kotlinx.android.synthetic.main.activity_form_donate.*
import java.util.*

class FormDonateActivity : AppCompatActivity() {

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_form_donate)

        var btnBack = findViewById<ImageView>(R.id.return_donate)

        val btnFinaliza = findViewById<Button>(R.id.concluir_doacao)

        btnFinaliza.setOnClickListener {

            val dialog = MaterialDialog(this)
                .noAutoDismiss()
                .customView(R.layout.dialog_sucessfull)

            dialog.show()
            Handler().postDelayed({
                Intent(this, MenuActivity::class.java)
                finishAndRemoveTask()
            }, 1500)

        }

        btnBack?.setOnClickListener {

            fadein(btnBack!!)

            val intent = Intent(this, DonateFragment::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
            finishAndRemoveTask()

        }

        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        et_validate.setOnClickListener {
            val dpd = DatePickerDialog(
                this,
                DatePickerDialog.OnDateSetListener { view, mYear, mMonth, mDayOfMonth ->
                    et_validate.setText("" + mDayOfMonth + "/" + mMonth + "/" + mYear)
                },
                year,
                month,
                day
            )

            dpd.show()

        }

        iv_ong.setImageResource(intent.extras!!.getInt("icon"))
        txt_nome_ong.setText(intent.extras!!.getString("nameOng"))
        et_type.setText(intent.extras!!.getString("type"))
    }

    private fun fadein(view: View) {
        val animation = AlphaAnimation(0f, 1f)
        animation.duration = 300L
        animation.repeatMode = Animation.REVERSE
        animation.repeatCount = 0
        view.startAnimation(animation)
    }
}

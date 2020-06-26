package techconnective.herokuapp.com.doacao

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.customview.customView
import connection.objetos.Doacao
import connection.objetos.Usuario
import connection.task.PostDonateTask
import kotlinx.android.synthetic.main.activity_form_donate.*
import techconnective.herokuapp.com.R
import techconnective.herokuapp.com.menu.DonateFragment
import techconnective.herokuapp.com.menu.MenuActivity
import java.text.SimpleDateFormat
import java.util.*


class FormDonateActivity : AppCompatActivity() {

    private var nomeOng: TextView? = null
    private var typeDonate: EditText? = null
    private var whatDonate: EditText? = null
    private var qntDonate: EditText? = null

    //private var vldDonate: TextView? = null
    private var comentDonate: EditText? = null

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("SetTextI18n", "SimpleDateFormat")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_form_donate)

        nomeOng = findViewById(R.id.txt_nome_ong)
        typeDonate = findViewById(R.id.et_type)
        whatDonate = findViewById(R.id.et_what)
        qntDonate = findViewById(R.id.et_quant)
//        vldDonate = findViewById(R.id.et_validate)
        comentDonate = findViewById(R.id.et_coments)


        iv_ong.setImageResource(intent.extras!!.getInt("icon"))
        nomeOng?.setText(intent.extras!!.getString("nameOng"))
        typeDonate?.setText(intent.extras!!.getString("type"))

        var btnBack = findViewById<ImageView>(R.id.return_donate)

        val btnFinaliza = findViewById<Button>(R.id.concluir_doacao)

        btnFinaliza.setOnClickListener {

            if (whatDonate?.getText().toString().trim().equals("") ||
                qntDonate?.getText().toString().trim().equals("")
            ) {

                Toast.makeText(this, "Existem campos vazios!", Toast.LENGTH_SHORT).show();

                whatDonate?.setBackgroundResource(R.drawable.et_invalid)
                qntDonate?.setBackgroundResource(R.drawable.et_invalid)

                Handler().postDelayed({
                    whatDonate?.setBackgroundResource(R.drawable.et_valid)
                    qntDonate?.setBackgroundResource(R.drawable.et_valid)
                }, 2000)

            } else {

                val tipo = typeDonate?.text.toString()
                val nomeOng = nomeOng?.text.toString()
                val descricao = whatDonate?.text.toString()
                val quantidade = qntDonate?.text.toString()
                val comentario = comentDonate?.text.toString()
                val user = intent.extras!!.getSerializable("user") as Usuario

                val data = Date()
                val dataFormatada = SimpleDateFormat("yyyy-MM-dd").format(data)

                val res = PostDonateTask().execute(
                    Doacao(
                        tipo,
                        nomeOng,
                        descricao,
                        quantidade,
                        comentario,
                        dataFormatada,
                        user.endereco,
                        user.nome
                    )
                ).get()

                if (res == "Sucesso") {
                    val dialog = MaterialDialog(this)
                        .noAutoDismiss()
                        .customView(R.layout.dialog_sucessfull)

                    dialog.show()
                    Handler().postDelayed({
                        Intent(this, MenuActivity::class.java)
                        finishAndRemoveTask()
                    }, 2000)
                } else {
                    Toast.makeText(this, res, Toast.LENGTH_LONG).show()
                }
            }
        }

        btnBack?.setOnClickListener {

            fadein(btnBack)

            val intent = Intent(this, DonateFragment::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
            finishAndRemoveTask()

        }

//        val c = Calendar.getInstance()
//        val year = c.get(Calendar.YEAR)
//        val month = c.get(Calendar.MONTH)
//        val day = c.get(Calendar.DAY_OF_MONTH)

//        et_validate.setOnClickListener {
//            val dpd = DatePickerDialog(
//                this,
//                DatePickerDialog.OnDateSetListener { view, mYear, mMonth, mDayOfMonth ->
//
//                    et_validate.setText("${mYear}-0${mMonth}-${mDayOfMonth}")
//                },
//                year,
//                month,
//                day
//            )
//
//            dpd.show()
//
//        }
    }

    private fun fadein(view: View) {
        val animation = AlphaAnimation(0f, 1f)
        animation.duration = 300L
        animation.repeatMode = Animation.REVERSE
        animation.repeatCount = 0
        view.startAnimation(animation)
    }
}

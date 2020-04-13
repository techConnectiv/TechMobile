package techconnective.herokuapp.com

import android.app.ProgressDialog
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_create_account.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val TAG = "LoginActivity"

    //variaveis globais

    private var email: String? = null
    private var password: String? = null

    //Elementos de interface UI

    private var tvFogotPassword: TextView? = null
    private var etEmail: TextView? = null
    private var etPassword: TextView? = null
    private var btnLogin: TextView? = null
    private var btnCreateAccount: TextView? = null
    private var nProgressBar: ProgressDialog? = null

    //Referencias ao bd
    private var mAuth: FirebaseAuth? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

            window.setStatusBarColorTo(R.color.colorPrimary)

        initialise()
    }

    fun Window.setStatusBarColorTo(color: Int) {
        this.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        this.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        this.statusBarColor = ContextCompat.getColor(baseContext, color)
    }

    private fun initialise() {
        tvFogotPassword = findViewById(R.id.tv_forgot_password) as TextView
        etEmail = findViewById(R.id.login_user_name) as EditText
        etPassword = findViewById(R.id.pass_user_name) as EditText
        btnLogin = findViewById(R.id.entrar) as Button
        btnCreateAccount = findViewById(R.id.btn_register_account) as TextView
        nProgressBar = ProgressDialog(this)

        mAuth = FirebaseAuth.getInstance()

        tvFogotPassword!!.setOnClickListener {
            startActivity(
                Intent(
                    this@MainActivity,
                    ForgotPasswordActivity::class.java
                )
            )
        }

        btnCreateAccount!!.setOnClickListener {
            startActivity(
                Intent(
                    this@MainActivity,
                    CreateAccountActivity::class.java
                )
            )
        }
        btnLogin!!.setOnClickListener { loginUser() }
    }

    private fun loginUser() {
        email = etEmail?.text.toString()
        password = etPassword?.text.toString()

        if (!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)) {
            nProgressBar!!.setMessage("Verificando usuario")
            nProgressBar!!.show()

            Log.d(TAG, "Login do Usuário")

            mAuth!!.signInWithEmailAndPassword(email!!, password!!)
                .addOnCompleteListener(this) { task ->

                    nProgressBar!!.hide()

                    if (task.isSuccessful) {
                        Log.d(TAG, "Logado com sucesso")
                        updateUi()
                    } else {
                        Log.e(TAG, "Usuário não encontrado", task.exception)
                        Toast.makeText(
                            this@MainActivity,
                            "Falha na autenticação.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }


                }
        } else {
            Toast.makeText(this, "Entre com mais detalhes", Toast.LENGTH_SHORT).show()
        }
    }

    private fun updateUi() {
        val intent = Intent(this@MainActivity, MenuActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
    }
}

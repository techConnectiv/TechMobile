package techconnective.herokuapp.com.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import techconnective.herokuapp.com.R

class ForgotPasswordActivity : AppCompatActivity() {

    private var TAG = "ForgotPasswordActivity"

    private var etEmail: EditText? = null
    private var btnSubmit: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)
        initialise()
    }

    private fun initialise() {
        etEmail = findViewById(R.id.et_email) as EditText
        btnSubmit = findViewById(R.id.btn_submit) as Button

        btnSubmit!!.setOnClickListener { sendPasswordEmail() }
    }

    private fun sendPasswordEmail() {
        val email = etEmail!!.text.toString()

        if (!TextUtils.isEmpty(email)) {
//            mAuth!!
//                .sendPasswordResetEmail(email)
//                .addOnCompleteListener { task ->
//                    if (task.isSuccessful) {
//                        val message = "Email enviado."
//                        Log.d(TAG, message)
//                        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
//
//                        updateUi()
//                    } else {
//                        Log.w(TAG, task.exception)
//                        Toast.makeText(
//                            this, "Nenhum usuario encontrado",
//                            Toast.LENGTH_LONG
//                        ).show()
//                    }
//                }
        } else {
            Toast.makeText(
                this, "Campo vazio.",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun updateUi() {
        val intent = Intent(this@ForgotPasswordActivity, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
    }
}

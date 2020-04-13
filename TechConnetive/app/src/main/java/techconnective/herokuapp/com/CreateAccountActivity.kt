package techconnective.herokuapp.com

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_create_account.*
import org.w3c.dom.Text

class CreateAccountActivity : AppCompatActivity() {

    private var etNameUser: EditText? = null
    private var etEmailUser: EditText? = null
    private var etPassUser: EditText? = null
    private var etRePassUser: EditText? = null
    private var btnCreateAccount: Button? = null
    private var tvHaveAccount: TextView? = null

    private var mProgressBar: ProgressDialog? = null

    //Referencia Firebase

    private var mDatabaseReference: DatabaseReference? = null
    private var mDatabase: FirebaseDatabase? = null
    private var mAuth: FirebaseAuth? = null

    private val TAG = "CreateAccountActivity"

    //Variaveis globais

    private var userName: String? = null
    private var userEmail: String? = null
    private var userPass: String? = null
    private var userRePass: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_account)

        initialise()
    }

    private fun initialise() {
        etNameUser = findViewById(R.id.et_name_user) as EditText
        etEmailUser = findViewById(R.id.et_email_user) as EditText
        etPassUser = findViewById(R.id.et_pass_user) as EditText
        etRePassUser = findViewById(R.id.et_re_pass_user) as EditText
        tvHaveAccount = findViewById(R.id.tv_btn_login_user) as TextView
        btnCreateAccount = findViewById(R.id.btn_create_user) as Button
        mProgressBar = ProgressDialog(this)

        mDatabase = FirebaseDatabase.getInstance()
        mDatabaseReference = mDatabase!!.reference!!.child("Users")
        mAuth = FirebaseAuth.getInstance()

        btnCreateAccount!!.setOnClickListener { creatNewAccount() }

        tvHaveAccount!!.setOnClickListener {
            startActivity(
                Intent(
                    this@CreateAccountActivity,
                    MainActivity::class.java
                )
            )
        }
    }

    private fun creatNewAccount() {
        userName = etNameUser?.text.toString()
        userEmail = etEmailUser?.text.toString()
        userPass = etPassUser?.text.toString()
        userRePass = etRePassUser?.text.toString()

        if (!TextUtils.isEmpty(userName) && !TextUtils.isEmpty(userEmail)
            && !TextUtils.isEmpty(userPass) && !TextUtils.isEmpty(userRePass)
        ) {
            Toast.makeText(this, "Informações enviadas", Toast.LENGTH_SHORT).show()

            mProgressBar!!.setMessage("Registrando usuário...")
            mProgressBar!!.show()

            mAuth!!.createUserWithEmailAndPassword(userEmail!!, userPass!!)
                .addOnCompleteListener(this)
                { task ->
                    mProgressBar!!.hide()

                    if (task.isSuccessful) {
                        Log.d(TAG, "CreateUserWithEmail:Succes")

                        val userId = mAuth!!.currentUser!!.uid

                        //verificar se o usuario autenticou email
                        verifyEmail();

                        val currentUserDb = mDatabaseReference!!.child(userId)
                        currentUserDb.child("userName").setValue(userName)
                        currentUserDb.child("userEmail").setValue(userEmail)

                        //att infos no bd
                        updateUserInfoandUi()
                    } else {
                        Log.w(TAG, "CreateUserWithEmail:Failure", task.exception)
                        Toast.makeText(
                            this@CreateAccountActivity, "A autenticação falhou.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                }

        } else {
            Toast.makeText(this, "Todos os campos preciam ser preenchidos", Toast.LENGTH_SHORT)
                .show()
        }


    }

    private fun updateUserInfoandUi() {
        //iniciar a nova atividade
        val intent = Intent(this@CreateAccountActivity, MainActivity::class.java)

        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
    }

    private fun verifyEmail() {
        val mUser = mAuth!!.currentUser;
        mUser!!.sendEmailVerification().addOnCompleteListener(this)
        { task ->
            if (task.isSuccessful) {
                Toast.makeText(
                    this@CreateAccountActivity, "Verification email send to"
                            + mUser.getEmail(), Toast.LENGTH_SHORT
                ).show()
            } else {
                Log.e(TAG, "SendEmailVerification", task.exception)
                Toast.makeText(
                    this@CreateAccountActivity, "Failed to send verification " +
                            "email.", Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}

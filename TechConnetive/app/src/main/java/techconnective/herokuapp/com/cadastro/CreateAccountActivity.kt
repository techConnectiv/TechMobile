package techconnective.herokuapp.com.cadastro

import android.app.Dialog
import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.View.OnFocusChangeListener
import android.view.Window
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.afollestad.materialdialogs.MaterialDialog
import connection.objetos.*
import connection.task.GetCepTask
import connection.task.PostCadastroTask
import kotlinx.android.synthetic.main.activity_create_account.*
import kotlinx.android.synthetic.main.activity_main.*
import model.MaskEditUtil
import techconnective.herokuapp.com.R
import techconnective.herokuapp.com.login.MainActivity


class CreateAccountActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    private val TAG = "CreateAccountActivity"

    private var etEmailUser: EditText? = null
    private var etNameUser: EditText? = null
    private var etNascUser: EditText? = null
    private var etPassUser: EditText? = null
    private var etCpfUser: EditText? = null
    private var etCelular: EditText? = null
    private var etGenUser: Spinner? = null
    private var etCep: EditText? = null
    private var etCidade: EditText? = null
    private var etNumero: EditText? = null
    private var etBairro: EditText? = null
    private var etEstado: EditText? = null
    private var etRua: EditText? = null

    private var btnCreateAccount: Button? = null
    private var tvHaveAccount: TextView? = null

    private var mProgressBar: ProgressDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_account)

        var btnBack = findViewById<ImageView>(R.id.return_login)

        btnBack?.setOnClickListener {

            fadein(btnBack!!)

            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
            finishAndRemoveTask()

        }

        val adapter = ArrayAdapter.createFromResource(
            this,
            R.array.genero,
            android.R.layout.simple_spinner_item
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        et_genero_user!!.adapter = adapter
        et_genero_user!!.setOnItemSelectedListener(this)

        initialise()
    }

    private fun initialise() {
        etEmailUser = findViewById(R.id.et_email_user) as EditText
        etNameUser = findViewById(R.id.et_name_user) as EditText
        etNascUser = findViewById(R.id.et_nasc_user) as EditText
        etPassUser = findViewById(R.id.et_pass_user) as EditText
        etCpfUser = findViewById(R.id.et_cpf_user) as EditText
        etCelular = findViewById(R.id.et_celular_user) as EditText
        etGenUser = findViewById(R.id.et_genero_user) as Spinner
        etCep = findViewById(R.id.et_cep_user) as EditText
        etCidade = findViewById(R.id.et_cidade_user) as EditText
        etNumero = findViewById(R.id.et_numero_user) as EditText
        etBairro = findViewById(R.id.et_bairro_user) as EditText
        etEstado = findViewById(R.id.et_uf_user) as EditText
        etRua = findViewById(R.id.et_rua_user) as EditText

        etCpfUser?.addTextChangedListener(MaskEditUtil.mask(etCpfUser!!, MaskEditUtil.FORMAT_CPF))
        etCelular?.addTextChangedListener(MaskEditUtil.mask(etCelular!!, MaskEditUtil.FORMAT_FONE))
        etCep?.addTextChangedListener(MaskEditUtil.mask(etCep!!, MaskEditUtil.FORMAT_CEP))
        etNascUser?.addTextChangedListener(
            MaskEditUtil.mask(
                etNascUser!!,
                MaskEditUtil.FORMAT_DATE
            )
        )

        tvHaveAccount = findViewById(R.id.tv_btn_login_user) as TextView
        btnCreateAccount = findViewById(R.id.btn_create_user) as Button

        mProgressBar = ProgressDialog(this)

        btnCreateAccount!!.setOnClickListener { creatNewAccount() }

        tvHaveAccount!!.setOnClickListener {
            startActivity(
                Intent(
                    this@CreateAccountActivity,
                    MainActivity::class.java
                )
            )
        }

        etCep?.setOnFocusChangeListener(OnFocusChangeListener { v, hasFocus ->
            if (!hasFocus) {
                val cepPesquisa = etCep?.text.toString()
                val result = GetCepTask().execute(cepPesquisa).get()

                etCidade!!.setText("${result?.localidade}")
                etBairro!!.setText("${result?.bairro}")
                etEstado!!.setText("${result?.uf}")
                etRua!!.setText("${result?.logradouro}")
            }
        })
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        parent?.getItemAtPosition(position).toString()
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        ""
    }

    private fun creatNewAccount() {
        val userEmail = etEmailUser?.text.toString()
        val userPass = etPassUser?.text.toString()
        val userName = etNameUser?.text.toString()
        val userNasc = etNascUser?.text.toString()
        val userCpf = etCpfUser?.text.toString()
        val userCidade = etCidade?.text.toString()
        val userBairro = etBairro?.text.toString()
        val userEstado = etEstado?.text.toString()
        val userCep = etCep?.text.toString()
        val userNumero = etNumero?.text.toString()
        val userGenero = etGenUser?.getSelectedItem()
        val userRua = etRua?.text.toString()
        val userCelular = etCelular?.text.toString()

        val res = PostCadastroTask().execute(
            Usuario(
                userName,
                userNasc,
                userCpf,
                userGenero.toString(),
                Credenciais(userEmail, userPass),
                Endereco(
                    userRua, userNumero,
                    userBairro, userCep,
                    userEstado,
                    userCidade
                ),
                Contato(userCelular)
            )
        ).get()

        if (res == "Sucesso") {

            val dialog = Dialog(this)
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog.setCancelable(false)
            dialog.setContentView(R.layout.cutsom_layout)

            dialog.show()

            Handler().postDelayed({
                updateUserInfoandUi()
            }, 2000)
        }else {
            Toast.makeText(this, getString(R.string.falha), Toast.LENGTH_LONG).show()
        }

    }

    private fun updateUserInfoandUi() {
        //iniciar a nova atividade
        val intent = Intent(this@CreateAccountActivity, MainActivity::class.java)

        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
    }

    private fun fadein(view: View) {
        val animation = AlphaAnimation(0f, 1f)
        animation.duration = 300L
        animation.repeatMode = Animation.REVERSE
        animation.repeatCount = 0
        view.startAnimation(animation)
    }

}

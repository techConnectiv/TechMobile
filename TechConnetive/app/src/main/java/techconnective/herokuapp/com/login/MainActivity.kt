package techconnective.herokuapp.com.login

import android.Manifest
import android.app.ProgressDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.content.res.Configuration
import android.location.Location
import android.os.Bundle
import android.os.Handler
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GoogleApiAvailability
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.tasks.OnSuccessListener
import connection.objetos.Credenciais
import connection.task.GetLoginTask
import kotlinx.android.synthetic.main.activity_main.*
import techconnective.herokuapp.com.R
import techconnective.herokuapp.com.cadastro.CreateAccountActivity
import techconnective.herokuapp.com.menu.MenuActivity
import java.io.Serializable
import java.util.*

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    private val TAG = "LoginActivity"

    private var tvFogotPassword: TextView? = null
    private var etUserName: TextView? = null
    private var etPassword: TextView? = null
    private var btnLogin: TextView? = null
    private var btnCreateAccount: TextView? = null
    private var btnLoginOng: TextView? = null
    private var nProgressBar: ProgressDialog? = null
    private var checkBoxGrava: CheckBox? = null
    private var latitude: Double? = null
    private var longitude: Double? = null

    var client: FusedLocationProviderClient? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = ArrayAdapter.createFromResource(
            this,
            R.array.idioma,
            android.R.layout.simple_spinner_item
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner!!.adapter = adapter
        spinner!!.setOnItemSelectedListener(this)

        window.setStatusBarColorTo(R.color.colorPrimary)

        initialise()

        checkBoxGrava = findViewById(R.id.gravarLogin)

        val preferences = getPreferences(Context.MODE_PRIVATE)
        etUserName?.text = preferences.getString("loginUser", "")
        etPassword?.text = preferences.getString("passUser", "")

        val sharedPreferences = getSharedPreferences("checkd", Context.MODE_PRIVATE)
        checkBoxGrava?.isChecked = sharedPreferences.getBoolean("checked", false)

//        if (preferences!!.getString("loginUser", "") != "") {
//            var intent = Intent(this, MenuActivity::class.java)
//            startActivity(intent)
//
//        }

        checkBoxGrava?.setOnCheckedChangeListener { buttonView, isChecked ->

            if (buttonView.isChecked()) {

                val preferences = getPreferences(Context.MODE_PRIVATE)

                val editor = preferences.edit()

                editor.putString("loginUser", etUserName?.text.toString())
                editor.putString("passUser", etPassword?.text.toString())

                val sharedPreferences = getSharedPreferences("checkd", Context.MODE_PRIVATE)
                val checkBox = sharedPreferences.edit()

                editor.commit()

                checkBox.putBoolean("checked", true)

                checkBox.commit()
            }
        }

        client = LocationServices.getFusedLocationProviderClient(this);
    }

    override fun onResume() {
        super.onResume()
        val errorCode = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(this)
        when (errorCode) {
            ConnectionResult.SERVICE_MISSING, ConnectionResult.SERVICE_VERSION_UPDATE_REQUIRED, ConnectionResult.SERVICE_DISABLED -> {
                Log.d("Teste", "show dialog")
                GoogleApiAvailability.getInstance().getErrorDialog(
                    this,
                    errorCode,
                    0,
                    DialogInterface.OnCancelListener { ActivityCompat.finishAffinity(this) })
                    .show()
            }
            ConnectionResult.SUCCESS -> Log.d(
                "Teste",
                "Google Play Services up-to-date"
            )
        }

        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        client!!.lastLocation
            .addOnSuccessListener(OnSuccessListener { location ->

                if (location != null) {
//                    Toast.makeText(
//                        this,
//                        location.latitude.toString() + " " + location.longitude,
//                        Toast.LENGTH_LONG
//                    ).show()

                    latitude = location.latitude
                    longitude = location.longitude

                } else {
                    Toast.makeText(
                        this,
                        "Não foi possivel obter a localização! Por favor, ative o GPS.",
                        Toast.LENGTH_LONG
                    ).show()
                }
            })
            .addOnFailureListener { }
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val item = parent?.getItemAtPosition(position).toString()

        if (item == "Inglês") {
            trocaIdioma("en")
        } else if (item == "Português") {
            trocaIdioma("pt")
        } else if (item == "Espanhol") {
            trocaIdioma("es")
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {

    }

    fun Window.setStatusBarColorTo(color: Int) {
        this.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        this.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        this.statusBarColor = ContextCompat.getColor(baseContext, color)
    }

    private fun initialise() {
        tvFogotPassword = findViewById(R.id.tv_forgot_password)
        etUserName = findViewById(R.id.login_user_name)
        etPassword = findViewById(R.id.pass_user_name)
        btnLogin = findViewById(R.id.entrar)
        btnCreateAccount = findViewById(R.id.btn_register_account)
        btnLoginOng = findViewById(R.id.btn_login_ong)
        nProgressBar = ProgressDialog(this)

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
        btnLoginOng!!.setOnClickListener {
            startActivity(
                Intent(
                    this@MainActivity,
                    LoginOngActivity::class.java
                )
            )
            finish()
        }
        btnLogin?.setOnClickListener { loginUser() }
    }

    private fun loginUser() {

        val email = etUserName?.text.toString()
        val password = etPassword?.text.toString()

        if (!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)) {
            nProgressBar!!.setMessage(getString(R.string.verifica_user))
            nProgressBar!!.show()

            val res = GetLoginTask().execute(
                Credenciais(
                    email,
                    password
                )
            ).get()

            if (res != null) {
                val user = res

                Handler().postDelayed({

                    nProgressBar!!.hide()
                    updateUi(user, latitude!!, longitude!!)
                    Toast.makeText(
                        this,
                        getString(R.string.recepcao) + ", ${user.nome}",
                        Toast.LENGTH_SHORT
                    ).show()
                }, 1000)
            } else {
                nProgressBar!!.hide()
                Toast.makeText(this@MainActivity, getString(R.string.not_user), Toast.LENGTH_SHORT)
                    .show()
            }
        } else {
            Toast.makeText(this, getString(R.string.mais_detalhes), Toast.LENGTH_SHORT).show()
            nProgressBar?.hide()
        }
    }

    private fun updateUi(user: Serializable, latitude: Double, longitude: Double) {
        val intent = Intent(this@MainActivity, MenuActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        intent.putExtra("user", user)
        intent.putExtra("latitude", latitude.toString())
        intent.putExtra("longitude", longitude.toString())
        startActivity(intent)
        finish()
    }

    fun trocaIdioma(s: String) {

        val locale: Locale
        locale = Locale(s)

        val config = Configuration(applicationContext.resources.configuration)
        Locale.setDefault(locale)
        config.setLocale(locale)

        this.baseContext.resources.updateConfiguration(
            config,
            this.baseContext.resources.displayMetrics
        )

        val intent = getIntent()
        finish()
        startActivity(intent)
    }
}

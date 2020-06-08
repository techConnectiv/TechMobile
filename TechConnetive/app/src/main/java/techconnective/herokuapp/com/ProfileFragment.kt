package techconnective.herokuapp.com

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import connection.Usuario
import kotlinx.android.synthetic.main.fragment_profile.*

class ProfileFragment : Fragment() {

    private var btnConfig: LinearLayout? = null
    private var btnEdit: LinearLayout? = null
    private var btnLogout: LinearLayout? = null
    private var nProgressBar: ProgressDialog? = null
    private var name_user: TextView? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        name_user = view.findViewById(R.id.name_user)

        val objectUser = getActivity()?.getIntent()?.getSerializableExtra("user") as Usuario

        name_user?.text = objectUser.nome
        email_user?.text = objectUser.credenciais.login

        Toast.makeText(context, "${name_user!!.text}", Toast.LENGTH_LONG).show()

        nProgressBar = ProgressDialog(context)

        btnLogout = view.findViewById(R.id.ll_logout)

        btnLogout!!.setOnClickListener {

            nProgressBar!!.setMessage("Saindo...")
            nProgressBar!!.show()

            var intent = Intent(context, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK

            startActivity(intent)

            nProgressBar!!.hide()
        }

        btnConfig = view.findViewById(R.id.configuracao)
        btnEdit = view.findViewById(R.id.editar)

        btnConfig!!.setOnClickListener {

            fadein(btnConfig!!)

            loadFragment(SettingsFragment())

        }

        btnEdit!!.setOnClickListener {

            fadein(btnEdit!!)

            loadFragment(Edite_Profile_Fragment())
        }

        return view
    }

    private fun fadein(view: View) {
        val animation = AlphaAnimation(0f, 1f)
        animation.duration = 300L
        animation.repeatMode = Animation.REVERSE
        animation.repeatCount = 0
        view.startAnimation(animation)
    }

    @SuppressLint("WrongConstant")
    private fun loadFragment(fragment: Fragment) {
        val fragmentManager = activity!!.supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.container, fragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }
}

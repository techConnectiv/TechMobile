package techconnective.herokuapp.com.perfil

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import connection.objetos.Usuario
import kotlinx.android.synthetic.main.fragment_edite_profile.*
import techconnective.herokuapp.com.R

class Edite_Profile_Fragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_edite_profile, container, false)

        val btnBack = view.findViewById<ImageView>(R.id.return_peril)

        btnBack?.setOnClickListener {

            fadein(btnBack)

            val fragmentManager = activity!!.supportFragmentManager
            fragmentManager.popBackStack()

        }

        val objectUser = getActivity()?.getIntent()?.getSerializableExtra("user") as Usuario

        val txt_nome = view.findViewById<TextView>(R.id.txt_nome)
        val desc_cpf = view.findViewById<TextView>(R.id.desc_cpf)
        val desc_telefone = view.findViewById<TextView>(R.id.desc_telefone)
        val desc_email = view.findViewById<TextView>(R.id.desc_email)

        txt_nome?.text = objectUser.nome
        desc_cpf?.text = objectUser.cpf
        desc_telefone?.text = objectUser.contato.cel
        desc_email?.text = objectUser.credenciais.login

        return view
    }
    private fun fadein(view: View){
        val animation = AlphaAnimation(0f,1f)
        animation.duration = 300L
        animation.repeatMode = Animation.REVERSE
        animation.repeatCount = 0
        view.startAnimation(animation)
    }

}

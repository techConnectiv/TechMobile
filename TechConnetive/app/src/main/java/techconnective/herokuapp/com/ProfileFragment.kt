package techconnective.herokuapp.com

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.Navigation

class ProfileFragment : Fragment() {

    private var btnConfig: LinearLayout? = null
    private var btnEdit: LinearLayout? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        btnConfig = view.findViewById(R.id.configuracao)
        btnEdit = view.findViewById(R.id.editar)

        btnConfig!!.setOnClickListener {

            Toast.makeText(context, "Configurar app", Toast.LENGTH_SHORT).show()
        }

        btnEdit!!.setOnClickListener {

            Toast.makeText(context, "Editar perfil", Toast.LENGTH_SHORT).show()
        }

        return view
    }

//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        btnConfig!!.setOnClickListener {
//
//            Navigation.findNavController(it).navigate(R.id.setting_fragment)
//        }
//    }

//    private fun loadFragment(fragment: Fragment) {
//        activity!!.supportFragmentManager.beginTransaction().also { fragmentTransaction ->
//            fragmentTransaction.replace(R.id.container, fragment)
//            fragmentTransaction.commit()
//        }
//    }
}

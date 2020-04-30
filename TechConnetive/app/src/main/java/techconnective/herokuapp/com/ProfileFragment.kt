package techconnective.herokuapp.com

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.widget.LinearLayout
import androidx.fragment.app.Fragment

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

            fadein(btnConfig!!)

            loadFragment(SettingsFragment())

        }

        btnEdit!!.setOnClickListener {

            fadein(btnEdit!!)

            loadFragment(Edite_Profile_Fragment())
        }

        return view
    }

    private fun fadein(view: View){
        val animation = AlphaAnimation(0f,1f)
        animation.duration = 300L
        animation.repeatMode = Animation.REVERSE
        animation.repeatCount = 0
        view.startAnimation(animation)
    }

    private fun loadFragment(fragment: Fragment) {
        val fragmentManager = activity!!.supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.container, fragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }
}

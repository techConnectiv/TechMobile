package techconnective.herokuapp.com.configuracoes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.widget.ImageView
import androidx.fragment.app.Fragment
import techconnective.herokuapp.com.R

class SettingsNotifyFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_settings_notify, container, false)

        var btnBack = view.findViewById<ImageView>(R.id.return_menu)

        btnBack?.setOnClickListener {

            fadein(btnBack!!)

            val fragmentManager = activity!!.supportFragmentManager
            fragmentManager.popBackStack()

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

}

package techconnective.herokuapp.com

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView


class SettingsFavoriteFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =  inflater.inflate(R.layout.settings_favorite_fragment, container, false)


//        var btnBack = view.findViewById<ImageView>(R.id.return_fragment)
//
//        btnBack?.setOnClickListener {
//
//            val fragmentManager = activity!!.supportFragmentManager
//            fragmentManager.popBackStack()
//
//        }

        return view
    }

}

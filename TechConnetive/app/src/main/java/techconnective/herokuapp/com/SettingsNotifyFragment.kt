package techconnective.herokuapp.com

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment

class SettingsNotifyFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_settings_notify, container, false)

        var btnBack = view.findViewById<ImageView>(R.id.return_fragment)

        btnBack.setOnClickListener {

        }

        return view
    }

}

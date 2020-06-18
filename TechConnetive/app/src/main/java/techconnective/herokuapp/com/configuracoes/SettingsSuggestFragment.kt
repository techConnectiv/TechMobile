package techconnective.herokuapp.com.configuracoes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import techconnective.herokuapp.com.R

/**
 * A simple [Fragment] subclass.
 */
class SettingsSuggestFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_settings_suggest, container, false)
    }

}

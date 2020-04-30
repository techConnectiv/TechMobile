package techconnective.herokuapp.com

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.fragment.app.Fragment

class SettingsFragment : Fragment() {

    private var btnNotify: RelativeLayout? = null
    private var btnDonate: RelativeLayout? = null
    private var btnFavorite: RelativeLayout? = null
    private var btnHelp: RelativeLayout? = null
    private var btnSecurity: RelativeLayout? = null
    private var btnSuggest: RelativeLayout? = null
    private var btnPartner: RelativeLayout? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.settings_fragment, container, false)

        btnNotify = view.findViewById(R.id.notify)
        btnDonate = view.findViewById(R.id.donate)
        btnFavorite = view.findViewById(R.id.favorite)
        btnHelp = view.findViewById(R.id.help)
        btnSecurity = view.findViewById(R.id.security)
        btnSuggest = view.findViewById(R.id.suggest)
        btnPartner = view.findViewById(R.id.partner)

        btnNotify!!.setOnClickListener {

            loadFragment(SettingsNotifyFragment())

        }

        btnDonate!!.setOnClickListener {

            loadFragment(SettingsDonateFragment())

        }

        btnFavorite!!.setOnClickListener {

            loadFragment(SettingsFavoriteFragment())

        }

        btnHelp!!.setOnClickListener {

            loadFragment(SettingsHelpFragment())

        }

        btnSecurity!!.setOnClickListener {

            loadFragment(SettingsSecurityFragment())

        }

        btnSuggest!!.setOnClickListener {

            loadFragment(SettingsSuggestFragment())

        }

        btnPartner!!.setOnClickListener {

            loadFragment(SettingsPartnerFragment())

        }

        return view
    }

    private fun loadFragment(fragment: Fragment) {
        val fragmentManager = activity!!.supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.container, fragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }
}

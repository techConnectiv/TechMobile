package techconnective.herokuapp.com

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_menu.*
import kotlinx.android.synthetic.main.settings_fragment.*

class MenuActivity : AppCompatActivity() {

    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        loadFragment(HomeFragment())

        bottomNavigationView.setOnNavigationItemSelectedListener { menuItem ->
             when{
                 menuItem.itemId == R.id.navigationHome -> {
                     loadFragment(HomeFragment())
                     return@setOnNavigationItemSelectedListener true
                 }
                 menuItem.itemId == R.id.navigationSearch -> {
                     loadFragment(SearchFragment())
                     return@setOnNavigationItemSelectedListener true
                 }
                 menuItem.itemId == R.id.navigationDonate -> {
                     loadFragment(DonateFragment())
                     return@setOnNavigationItemSelectedListener true
                 }
                 menuItem.itemId == R.id.navigationPerfil -> {
                     loadFragment(ProfileFragment())
                     return@setOnNavigationItemSelectedListener true
                 }
                 else -> {
                     return@setOnNavigationItemSelectedListener false
                 }
             }
        }
    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().also { fragmentTransaction ->
            fragmentTransaction.replace(R.id.container, fragment)
            fragmentTransaction.commit()
        }
    }

}

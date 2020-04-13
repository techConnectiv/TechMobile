package techconnective.herokuapp.com

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_menu.*

class MenuActivity : AppCompatActivity() {

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

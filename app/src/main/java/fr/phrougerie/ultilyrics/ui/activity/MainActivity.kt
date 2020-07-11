package fr.phrougerie.ultilyrics.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI.onNavDestinationSelected
import androidx.navigation.ui.setupWithNavController
import fr.phrougerie.ultilyrics.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bottom_nav.setupWithNavController(Navigation.findNavController(this, R.id.my_nav_host_fragment))
        bottom_nav.setOnNavigationItemSelectedListener {
                item -> onNavDestinationSelected(item, Navigation.findNavController(this, R.id.my_nav_host_fragment))
        }

    }

     fun openFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

}

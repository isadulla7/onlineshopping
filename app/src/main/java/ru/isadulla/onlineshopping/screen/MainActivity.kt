package ru.isadulla.onlineshopping.screen

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_main.*
import ru.isadulla.onlineshopping.R
import ru.isadulla.onlineshopping.screen.cart.CartFragment
import ru.isadulla.onlineshopping.screen.favorite.FavoriteFragment
import ru.isadulla.onlineshopping.screen.home.HomeFragment
import ru.isadulla.onlineshopping.screen.profile.ProfileFragment

class MainActivity : AppCompatActivity() {

    val profileFragment = ProfileFragment.newInstance()
    val cartFragment = CartFragment.newInstance()
    val favoriteFragment = FavoriteFragment.newInstance()
    val homeFragment = HomeFragment.newInstance()
    var activeFragment: Fragment = homeFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .add(R.id.fragment_container, homeFragment, homeFragment.tag).hide(homeFragment)
            .commit()
        supportFragmentManager.beginTransaction()
            .add(R.id.fragment_container, favoriteFragment, favoriteFragment.tag)
            .hide(favoriteFragment).commit()
        supportFragmentManager.beginTransaction()
            .add(R.id.fragment_container, cartFragment, cartFragment.tag).hide(cartFragment)
            .commit()
        supportFragmentManager.beginTransaction()
            .add(R.id.fragment_container, profileFragment, profileFragment.tag)
            .hide(profileFragment).commit()

        supportFragmentManager.beginTransaction().show(activeFragment).commit()

        bottom_nav_view.setOnNavigationItemSelectedListener {
            if (it.itemId == R.id.actionHome) {
                supportFragmentManager.beginTransaction().hide(activeFragment).show(homeFragment)
                    .commit()
                activeFragment = homeFragment
            } else if (it.itemId == R.id.favorite) {
                supportFragmentManager.beginTransaction().hide(activeFragment)
                    .show(favoriteFragment).commit()
                activeFragment = favoriteFragment
            } else if (it.itemId == R.id.cart) {
                supportFragmentManager.beginTransaction().hide(activeFragment).show(cartFragment)
                    .commit()
                activeFragment = cartFragment
            } else if (it.itemId == R.id.profile) {
                supportFragmentManager.beginTransaction().hide(activeFragment).show(profileFragment)
                    .commit()
                activeFragment = profileFragment
            }
            return@setOnNavigationItemSelectedListener true
        }
    }
}
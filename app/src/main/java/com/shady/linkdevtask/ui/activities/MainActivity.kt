package com.shady.linkdevtask.ui.activities

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationView
import com.shady.linkdevtask.ui.fragments.HomeFragment
import com.shady.myapplication.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        setTitle(R.string.title_activity_main)

        setupDrawerLayout(toolbar)

        openHomeFragment()
    }

    private fun openHomeFragment() {
        openFragment(HomeFragment.newInstance())
    }

    fun openFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .add(R.id.fragment_container, fragment)
            .addToBackStack(fragment.javaClass.simpleName)
            .commit()
    }

    private fun setupDrawerLayout(toolbar: Toolbar) {
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)

        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar, 0, 0
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        navView.setNavigationItemSelectedListener(this)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_explore ->
                Toast.makeText(
                    this,
                    R.string.menu_explore, Toast.LENGTH_SHORT
                ).show()
            R.id.nav_live_chat ->
                Toast.makeText(
                    this,
                    R.string.menu_live_chat, Toast.LENGTH_SHORT
                ).show()
            R.id.nav_gallery ->
                Toast.makeText(
                    this,
                    R.string.menu_gallery, Toast.LENGTH_SHORT
                ).show()
            R.id.nav_wish_list ->
                Toast.makeText(
                    this,
                    R.string.menu_wish_list, Toast.LENGTH_SHORT
                ).show()
            R.id.nav_e_magazine ->
                Toast.makeText(
                    this,
                    R.string.menu_e_magazine, Toast.LENGTH_SHORT
                ).show()
        }
        drawer_layout.closeDrawers()
        return true
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 1)
            super.onBackPressed()
        else
            finish()
    }
}

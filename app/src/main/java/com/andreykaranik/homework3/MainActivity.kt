package com.andreykaranik.homework3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.view.View
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.LifecycleOwner
import com.google.android.material.navigation.NavigationBarView

class MainActivity : AppCompatActivity(), Navigator {

    private val currentFragment: Fragment get() = supportFragmentManager.findFragmentById(R.id.fragment_container)!!

    private val fragmentListener = object : FragmentManager.FragmentLifecycleCallbacks() {
        override fun onFragmentViewCreated(
            fm: FragmentManager,
            f: Fragment,
            v: View,
            savedInstanceState: Bundle?
        ) {
            super.onFragmentViewCreated(fm, f, v, savedInstanceState)
            updateUi()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.fragment_container, TripFragment())
                .commit()
            toolbar.title = getString(R.string.trip_fragment_title)
        }

        val bottomNavigationView = findViewById<NavigationBarView>(R.id.nav_view)

        supportFragmentManager.registerFragmentLifecycleCallbacks(fragmentListener, false)

        bottomNavigationView.setOnItemSelectedListener {item ->
            when(item.itemId){
                R.id.trips -> {
                    launchBaseFragment(TripFragment())
                    true
                }
                R.id.events -> {
                    launchBaseFragment(EventFragment())
                    true
                }
                R.id.profile -> {
                    launchBaseFragment(ProfileFragment())
                    true
                }
                else -> false
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        supportFragmentManager.unregisterFragmentLifecycleCallbacks(fragmentListener)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun launchBaseFragment(fragment: Fragment) {
        goToMenu()
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }

    private fun launchFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .addToBackStack(null)
            .replace(R.id.fragment_container, fragment)
            .commit()
    }

    override fun showEditTripFragment() {
        launchFragment(EditTripFragment())
    }

    override fun goBack() {
        onBackPressed()
    }

    override fun goToMenu() {
        supportFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
    }

    override fun <T : Parcelable> publishResult(result: T) {
        TODO("Not yet implemented")
    }

    override fun <T : Parcelable> listenResult(
        clazz: Class<T>,
        owner: LifecycleOwner,
        listener: ResultListener<T>
    ) {
        TODO("Not yet implemented")
    }

    private fun updateUi() {
        val fragment = currentFragment
        val toolbar = findViewById<Toolbar>(R.id.toolbar)

        if (fragment is HasCustomTitle) {
            toolbar.title = getString(fragment.getTitleRes())
        } else {
            toolbar.title = getString(R.string.default_toolbar_title)
        }

        Log.println(Log.INFO, "tag: ", supportFragmentManager.backStackEntryCount.toString())

        if (supportFragmentManager.backStackEntryCount > 0) {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.setDisplayShowHomeEnabled(true)
        } else {
            supportActionBar?.setDisplayHomeAsUpEnabled(false)
            supportActionBar?.setDisplayShowHomeEnabled(false)
        }

        toolbar.menu.clear()
    }

}
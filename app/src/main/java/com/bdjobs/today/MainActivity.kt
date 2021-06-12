package com.bdjobs.today

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.bdjobs.today.home.HomeFragment
import com.bdjobs.today.link.LinkActivity
import com.bdjobs.today.utill.Constants
import com.facebook.ads.AdSize
import com.facebook.ads.AdView
import com.facebook.ads.AudienceNetworkAds
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var builder: AlertDialog.Builder
    private lateinit var adView:AdView
    private val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //window.requestFeature(Window.FEATURE_PROGRESS)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        builder = AlertDialog.Builder(this)
        val arguments = Bundle()
        arguments.putString("url", Constants.URL)
        val fragment = HomeFragment()
        fragment.arguments = arguments
        fragmentTransaction(fragment)
        //window.setFeatureInt(Window.FEATURE_PROGRESS, Window.PROGRESS_VISIBILITY_ON)

        val actionBarDrawerToggle = ActionBarDrawerToggle(
            this,
            drawer_layout,
            toolbar,
            R.string.openNavDrawer,
            R.string.closeNavDrawer
        )

        drawer_layout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()
        nav_view.setNavigationItemSelectedListener(this)

        initFan()

        if (intent != null && intent.hasExtra("link")) {
            Log.v(TAG, "Notification: ${intent.extras?.get("link")}")
            startActivity(
                Intent(this, LinkActivity::class.java)
                    .putExtra("link", intent.extras?.get("link").toString())
            )
        }
    }

    private fun initFan() {
        AudienceNetworkAds.initialize(this)
        adView = AdView(this, getString(R.string.fan_banner), AdSize.BANNER_HEIGHT_50)
        banner_container.addView(adView)
        adView.loadAd()
        banner_container.visibility = View.VISIBLE
    }

    private fun fragmentTransaction(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .commit()
    }

    override fun onBackPressed() {
        builder.setIcon(android.R.drawable.ic_dialog_alert)
            .setTitle("Closing App")
            .setMessage("Are you sure you want to close?")
            .setPositiveButton("Yes") { _, _ ->
                finishAffinity()
                finish()
            }
            .setNegativeButton("No", null)
            .show()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.menu_home -> {
                val arguments = Bundle()
                arguments.putString("url", Constants.URL)
                val fragment = HomeFragment()
                fragment.arguments = arguments
                fragmentTransaction(fragment)
            }
            R.id.menu_category_aj -> {
                val arguments = Bundle()
                arguments.putString("url", Constants.Today)
                val fragment = HomeFragment()
                fragment.arguments = arguments
                fragmentTransaction(fragment)
            }
            R.id.menu_category_bj -> {
                val arguments = Bundle()
                arguments.putString("url", Constants.BankJObs)
                val fragment = HomeFragment()
                fragment.arguments = arguments
                fragmentTransaction(fragment)
            }
            R.id.menu_category_book -> {
                val arguments = Bundle()
                arguments.putString("url", Constants.Books)
                val fragment = HomeFragment()
                fragment.arguments = arguments
                fragmentTransaction(fragment)
            }
            R.id.menu_category_cj -> {
                val arguments = Bundle()
                arguments.putString("url", Constants.CompanyJobs)
                val fragment = HomeFragment()
                fragment.arguments = arguments
                fragmentTransaction(fragment)
            }
            R.id.menu_category_defence -> {
                val arguments = Bundle()
                arguments.putString("url", Constants.Defence)
                val fragment = HomeFragment()
                fragment.arguments = arguments
                fragmentTransaction(fragment)
            }
            R.id.menu_category_fj -> {
                val arguments = Bundle()
                arguments.putString("url", Constants.FridayJobs)
                val fragment = HomeFragment()
                fragment.arguments = arguments
                fragmentTransaction(fragment)
            }
            R.id.menu_category_gj -> {
                val arguments = Bundle()
                arguments.putString("url", Constants.GovtJobs)
                val fragment = HomeFragment()
                fragment.arguments = arguments
                fragmentTransaction(fragment)
            }
            R.id.menu_category_ngo -> {
                val arguments = Bundle()
                arguments.putString("url", Constants.NGO)
                val fragment = HomeFragment()
                fragment.arguments = arguments
                fragmentTransaction(fragment)
            }
            R.id.menu_category_yt -> {
                val arguments = Bundle()
                arguments.putString("url", Constants.YT)
                val fragment = HomeFragment()
                fragment.arguments = arguments
                fragmentTransaction(fragment)
            }
            R.id.menu_category_qa -> {
                val arguments = Bundle()
                arguments.putString("url", Constants.QA)
                val fragment = HomeFragment()
                fragment.arguments = arguments
                fragmentTransaction(fragment)
            }
            R.id.menu_category_notice -> {
                val arguments = Bundle()
                arguments.putString("url", Constants.Notice)
                val fragment = HomeFragment()
                fragment.arguments = arguments
                fragmentTransaction(fragment)
            }
            R.id.menu_result_hsc -> {
                val arguments = Bundle()
                arguments.putString("url", Constants.HSC)
                val fragment = HomeFragment()
                fragment.arguments = arguments
                fragmentTransaction(fragment)
            }
            R.id.menu_result_psc -> {
                val arguments = Bundle()
                arguments.putString("url", Constants.PSC)
                val fragment = HomeFragment()
                fragment.arguments = arguments
                fragmentTransaction(fragment)
            }
            R.id.menu_result_ssc -> {
                val arguments = Bundle()
                arguments.putString("url", Constants.SSC)
                val fragment = HomeFragment()
                fragment.arguments = arguments
                fragmentTransaction(fragment)
            }
            R.id.menu_result_jsc -> {
                val arguments = Bundle()
                arguments.putString("url", Constants.JSC)
                val fragment = HomeFragment()
                fragment.arguments = arguments
                fragmentTransaction(fragment)
            }
            R.id.menu_result_nu -> {
                val arguments = Bundle()
                arguments.putString("url", Constants.NU)
                val fragment = HomeFragment()
                fragment.arguments = arguments
                fragmentTransaction(fragment)
            }
            R.id.menu_pp -> {
                val arguments = Bundle()
                arguments.putString("url", Constants.Privacy)
                val fragment = HomeFragment()
                fragment.arguments = arguments
                fragmentTransaction(fragment)
            }
            R.id.menu_share -> {
                val shareIntent = Intent()
                shareIntent.action = Intent.ACTION_SEND
                shareIntent.type = "text/plain"
                shareIntent.putExtra(
                    Intent.EXTRA_TEXT,
                    "Get latest job updates: https://play.google.com/store/apps/details?id=$packageName"
                )
                startActivity(Intent.createChooser(shareIntent, "Share via"))
            }
            R.id.menu_rate -> {
                val intent = Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://play.google.com/store/apps/details?id=$packageName")
                )
                startActivity(intent)
            }
            R.id.menu_close -> {
                finishAffinity()
                finish()
            }
        }
        //item.isChecked = true
        drawer_layout.close()

        return true
    }

    override fun onDestroy() {
        if (adView != null) {
            adView.destroy()
        }
        super.onDestroy()
    }

}
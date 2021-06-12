package com.bdjobs.today.link

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.bdjobs.today.R

class LinkActivity : AppCompatActivity() {

    private val TAG = "LinkActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.link_activity)
        if (savedInstanceState == null) {
            val fragment = LinkFragment()
            if (intent != null && intent.hasExtra("link")) {
                val link:String = intent.extras?.get("link") as String
                Log.v(TAG, "Notification: $link")
                val arguments = Bundle()
                arguments.putString("link", link)
                fragment.arguments = arguments
                supportFragmentManager.beginTransaction()
                    .replace(R.id.container, fragment)
                    .commitNow()
            } else onBackPressed()
        }
    }
}
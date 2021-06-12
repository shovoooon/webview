package com.bdjobs.today

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {

    private lateinit var timer: CountDownTimer
    private val TAG = "SplashActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(R.layout.activity_spash)

        startTimer()
    }

    private fun startTimer() {
        timer = object : CountDownTimer(1500, 1000) {
            override fun onFinish() {
                if (intent != null && intent.hasExtra("link")) {
                    Log.v(TAG, "Notification: ${intent.extras?.get("link")}")
                    startActivity(
                        Intent(this@SplashActivity, MainActivity::class.java)
                            .putExtra("link", intent.extras?.get("link").toString())
                    )

                } else startActivity(Intent(this@SplashActivity, MainActivity::class.java))
            }

            override fun onTick(millisUntilFinished: Long) {}

        }.start()
    }
}
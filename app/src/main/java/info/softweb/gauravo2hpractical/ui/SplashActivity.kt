package info.softweb.gauravo2hpractical.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import info.softweb.gauravo2hpractical.R

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        initViews()
    }
    private fun initViews() {
        Handler(Looper.getMainLooper()).postDelayed(Runnable
        // Using handler with postDelayed called runnable run method
        {
            val i = Intent(this, MainActivity::class.java)
            startActivity(i)

            // close this activity
            finish()
        }, 5 * 1000
        ) // wait for 5 seconds

    }
}
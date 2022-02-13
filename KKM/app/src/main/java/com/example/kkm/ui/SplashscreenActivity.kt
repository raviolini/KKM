package com.example.kkm.ui

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.window.SplashScreen
import com.example.kkm.R
import com.example.kkm.databinding.ActivitySlashscreenBinding
import com.example.kkm.ui.activities.MainActivity

@SuppressLint("CustomSplashScreen")
class SplashscreenActivity : AppCompatActivity() {
    private lateinit var SplashScreenBinding : ActivitySlashscreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        SplashScreenBinding = ActivitySlashscreenBinding.inflate(layoutInflater)
        setContentView(SplashScreenBinding.root)

        supportActionBar?.hide() //hiding the action bar

        val logo = SplashScreenBinding.logo
        val slideAnim = AnimationUtils.loadAnimation(this, R.anim.splash_anim)
        logo.startAnimation(slideAnim)

        val background = object :Thread(){
            override fun run() {
                try {
                    /* simulating some workloads here */
                    sleep(2500)
                    /* simulating some workloads here */

                    //continue into next activity
                    val intent = Intent(this@SplashscreenActivity, MainActivity::class.java)
                    startActivity(intent)
                }catch (e : Exception){
                    e.printStackTrace()
                }
            }
        }
        background.start()
    }
}
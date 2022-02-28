package com.raviolini.kkm.ui.activities

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import com.example.kkm.R
import com.example.kkm.databinding.ActivitySplashscreenBinding



@SuppressLint("CustomSplashScreen")
class SplashscreenActivity : AppCompatActivity() {
    private lateinit var splashScreenBinding : ActivitySplashscreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        splashScreenBinding = ActivitySplashscreenBinding.inflate(layoutInflater)
        setContentView(splashScreenBinding.root)

        supportActionBar?.hide() //hiding the action bar

        val logo = splashScreenBinding.logo
        val wm = splashScreenBinding.wm
        val slideAnim = AnimationUtils.loadAnimation(this, R.anim.splash_anim)
        logo.startAnimation(slideAnim)
        wm.startAnimation(slideAnim)

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
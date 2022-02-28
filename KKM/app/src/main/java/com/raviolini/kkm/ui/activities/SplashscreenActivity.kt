package com.raviolini.kkm.ui.activities

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import com.raviolini.kkm.R
import com.raviolini.kkm.databinding.ActivitySplashscreenBinding

@SuppressLint("CustomSplashScreen")
class SplashscreenActivity : AppCompatActivity() {
    private lateinit var SplashScreenBinding : ActivitySplashscreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        SplashScreenBinding = ActivitySplashscreenBinding.inflate(layoutInflater)
        setContentView(SplashScreenBinding.root)

        supportActionBar?.hide() //hiding the action bar

        val logo = SplashScreenBinding.logo
        val wm = SplashScreenBinding.wm
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
package com.raviolini.kkm.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.raviolini.kkm.R
import com.raviolini.kkm.databinding.ActivitySplashScreenBinding


@SuppressLint("CustomSplashScreen")
class SplashScreenActivity : AppCompatActivity() {

    private lateinit var splashBinding : ActivitySplashScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        splashBinding = ActivitySplashScreenBinding.inflate(layoutInflater)

        setContentView(splashBinding.root)

        supportActionBar?.hide() //hiding the action bar

        val logo = splashBinding.logo
        val watermark = splashBinding.wm
        val slideAnim = AnimationUtils.loadAnimation(this, R.anim.splash_anim)

        logo.startAnimation(slideAnim)
        watermark.startAnimation(slideAnim)

        val background = object :Thread(){
            override fun run() {
                try {
                    /* simulating some workloads here */
                    sleep(1500)

                    /* simulating some workloads here */

                    //continue into next activity
                    val intent = Intent(this@SplashScreenActivity, MainActivity::class.java)
                    startActivity(intent)
                }catch (e : Exception){
                    e.printStackTrace()
                }
            }
        }
        background.start()
    }
}
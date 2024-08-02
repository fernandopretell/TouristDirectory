package com.fulbiopretell.touristdirectory.presentation.splash

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.fulbiopretell.touristdirectory.BuildConfig
import com.fulbiopretell.touristdirectory.presentation.MainActivity
import com.fulbiopretell.touristdirectory.util.KeystoreUtil
import dagger.hilt.android.AndroidEntryPoint

@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Handler().postDelayed({
            //Almacenar la clave de la API en el almacén de claves
            try {
                val apiKey = BuildConfig.MAPS_API_KEY
                KeystoreUtil.storeApiKey(this, apiKey)

                val intent = Intent(this@SplashActivity, MainActivity::class.java)
                intent.flags =
                    Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
                finish()
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }, 3000)
    }
}

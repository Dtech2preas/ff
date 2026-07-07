package com.orbit.offline

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.btnOnline).setOnClickListener {
            val intent = Intent(this, GameActivity::class.java)
            intent.putExtra("MODE", "ONLINE")
            startActivity(intent)
        }

        findViewById<Button>(R.id.btnOffline).setOnClickListener {
            val intent = Intent(this, GameActivity::class.java)
            intent.putExtra("MODE", "OFFLINE")
            startActivity(intent)
        }
    }
}
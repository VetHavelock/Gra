package com.example.gra

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main3a.*

class Main3aActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3a)

        var intent2 = intent
        var opponent = intent2.getStringExtra("opponent")
        var nick = intent2.getStringExtra("nick")
        var intent = Intent(this@Main3aActivity,Main3bActivity::class.java)
        intent.putExtra("nick",nick)
        intent.putExtra("opponent",opponent)
        button2.setOnClickListener(){
            startActivity(intent)
        }
    }
}

package com.example.gra

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_main2.*
import kotlinx.android.synthetic.main.activity_main4.*

class Main4Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main4)
        var intent = intent
        var nick:String = intent.getStringExtra("nick")
        var opponent:String = intent.getStringExtra("opponent")
        button6.setOnClickListener{

            var q:String = editText4.text.toString()
            var a:String = editText5.text.toString()
            addToRoom(nick,q,a)
            startGame(nick, opponent)
            }
        }
    fun addToRoom(nick:String,question:String,answer:String){

        addValueToDatabase("question",nick,question)
        addValueToDatabase("answer",nick,answer)
      //  addValueToDatabase("points",nick,"0")
    }
    fun startGame(nick: String,opponent: String){
        var intent2:Intent
        intent2 = Intent(this@Main4Activity, Main3aActivity::class.java)
        intent2.putExtra("nick",nick)
        intent2.putExtra("opponent",opponent)
        startActivity(intent2)
    }
    fun addValueToDatabase(name:String, nick:String, value:String){
        val myRef = FirebaseDatabase.getInstance().getReference("rooms/" + nick + "/"+name)
        val player = myRef.push().key
        if (player != null) {
            myRef.child(player).setValue(value)
        }
    }
    }


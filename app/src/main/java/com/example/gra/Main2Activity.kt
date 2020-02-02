package com.example.gra

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main2.*

class Main2Activity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        var intent = intent

        button7.setOnClickListener {

            var nick: String = intent.getStringExtra("nick")
            var opponent: String = editText8.text.toString()
            if (opponent.isEmpty()) {
                editText8.error = "Podaj nazwę"
            } else {

                startGame(nick,opponent,false)
            }
        }

        button3.setOnClickListener{

            var nick:String = intent.getStringExtra("nick")
            var q:String = editText6.text.toString()
            var a:String = editText7.text.toString()
            var opponent:String = editText3.text.toString()
            if (opponent.isEmpty())
            {
                editText3.error="Podaj nazwę"
            }
            else{

               startRoom(nick,opponent,q,a)
                startGame(nick,opponent, true)
            }
        }
    }
    fun startRoom(nick:String,opponent:String,question:String,answer:String){
        addValueToDatabase("player1",nick,nick)
        addValueToDatabase("player2",nick,opponent)
        addValueToDatabase("question",nick,question)
        addValueToDatabase("answer",nick,answer)
       // addValueToDatabase("points",nick,"0")
    }
    fun startGame(nick: String,opponent: String,i:Boolean){
        var intent2:Intent
        if (i)
        { intent2 = Intent(this@Main2Activity, Main3aActivity::class.java)}
        else{ intent2 = Intent(this@Main2Activity, Main3bActivity::class.java)}
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

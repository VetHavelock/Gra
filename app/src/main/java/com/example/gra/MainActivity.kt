package com.example.gra

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    lateinit var nick:String



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener{
            nick = editText.text.toString()
            loginUser(nick)
        }

    }
    private fun loginUser(nick:String){
        if (nick.isEmpty())
        {
            editText.error="Podaj nazwę"
        }
        else {
            addUserToDatabase(nick)
            var intent = Intent(this@MainActivity, Main2Activity::class.java)
            intent.putExtra("nick", nick)
            startActivity(intent)
        }
    }

    fun addUserToDatabase(nick: String){
        val myRef = FirebaseDatabase.getInstance().getReference("users/" + nick)
        val player = myRef.push().key
        if (player != null) {
            myRef.child(player).setValue(nick).addOnCompleteListener{
                Toast.makeText(this@MainActivity,"Zalogowano",Toast.LENGTH_LONG).show()
            }
        }
    }

}

package com.example.gra

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_main3b.*

class Main3bActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3b)
        var intent2 = intent
        var pytania:ArrayList<String> = ArrayList()
        var odpowiedzi:ArrayList<String> = ArrayList()
        var opponent = intent2.getStringExtra("opponent")
        var nick = intent2.getStringExtra("nick")
        var intent = Intent(this,Main4Activity::class.java)
        intent.putExtra("nick",nick)
        intent.putExtra("opponent",opponent)
        var newRef = FirebaseDatabase.getInstance().getReference("rooms/" + opponent + "/question")
        var question:String = ""
        var answer:String = ""
        var points = getPoints(nick)

        val postListener = object : ValueEventListener {
            override fun onDataChange(p0: DataSnapshot) {
                if(p0!!.exists()){

                    for (i in p0.children)
                    {
                        val post = i.getValue(String::class.java)

                        pytania.add(post!!)
                    }
                  question = pytania.last()
                    textView3.text = question


                }





                //textView3 = p0.value
            }

            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }


    }
         newRef.addValueEventListener(postListener)

        var new2Ref = FirebaseDatabase.getInstance().getReference("rooms/" + opponent + "/answer")
        val post2Listener = object : ValueEventListener {
            override fun onDataChange(p0: DataSnapshot) {
                if(p0!!.exists()){

                    for (i in p0.children)
                    {
                        val post = i.getValue(String::class.java)

                        odpowiedzi.add(post!!)
                    }
                    answer = odpowiedzi.last()
                    textView3.text = question
                    var check = editText2.text.toString()
                    if (check.equals(answer)){
                        textView4.setText("Poprawna odpowiedź")
                        addPoints(nick,points)
                    }
                    else{
                        textView4.setText("Niepoprawna odpowiedź")
                    }

                }

            }

            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }


        }

        button4.setOnClickListener{


            new2Ref.addValueEventListener(post2Listener)
            button4.isEnabled = false
        }

        button5.setOnClickListener{

           askQusetion(intent)

        }


}
        fun askQusetion(intent: Intent){
            button4.isEnabled=true
            startActivity(intent)
        }
        fun addPoints(nick: String,point:Int){
            addValueToDatabase("points",nick,(point+1).toString())
        }
    fun addValueToDatabase(name:String, nick:String, value:String){
        val myRef = FirebaseDatabase.getInstance().getReference("rooms/" + nick + "/"+name)
        val player = myRef.push().key
        if (player != null) {
            myRef.child(player).setValue(value)
        }
    }
        fun getPoints(nick:String):Int
        {
            var points:ArrayList<String> = ArrayList()
            var latestPoint:Int = 0
            var new2Ref = FirebaseDatabase.getInstance().getReference("rooms/" + nick + "/points")
            val post2Listener = object : ValueEventListener {
                override fun onDataChange(p0: DataSnapshot) {
                    if(p0!!.exists()){

                        for (i in p0.children)
                        {
                            val post = i.getValue(String::class.java)

                            points.add(post!!)
                        }
                        latestPoint = points.last().toString().toInt()


                    }

                }

                override fun onCancelled(p0: DatabaseError) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }


            }
            new2Ref.addValueEventListener(post2Listener)
            return latestPoint
        }
}

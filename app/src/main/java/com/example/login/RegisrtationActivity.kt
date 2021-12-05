package com.example.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class RegisrtationActivity : AppCompatActivity() {

    private lateinit var e_mail: EditText
    private lateinit var password: EditText
    private lateinit var secondPassword: EditText
    private lateinit var phoneNumber: EditText
    private lateinit var userProfession: EditText
    private lateinit var userID: EditText

    private lateinit var backToLogin: Button
    private lateinit var registration: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.regisrtation_activity)

        e_mail = findViewById(R.id.E_mailEditText)
        password = findViewById(R.id.passwordEditText)
        secondPassword = findViewById(R.id.secondPasswordEditText)
        phoneNumber = findViewById(R.id.phoneNumberEditText)
        userProfession = findViewById(R.id.professionEditText)
        userID = findViewById(R.id.ID)


        backToLogin = findViewById(R.id.backToLogInBtn)
        registration = findViewById(R.id.registracionBtn)


        registration.setOnClickListener {

            var email = e_mail.text.toString()
            var password = password.text.toString()
            var secondPassword = secondPassword.text.toString()
            var pNumber = phoneNumber.text.toString()
            var userProf = userProfession.text.toString()
            var ID = userID.text.toString()

//            var pass = false

//            for (i in password[1]..password[-1]){
//                pass = !(i >= 'a' && i<='z') || !(i>='A' && i<='Z')
//            }

            var database = FirebaseDatabase.getInstance().reference
            database.child(ID).setValue(User(pNumber,userProf,ID,email,password))


            if(email.isEmpty() || !email.contains("@")){
                Toast.makeText(this, "მეილი", Toast.LENGTH_SHORT).show()
//                return@setOnClickListener
            }else if(password.length < 8 || !password[0].isUpperCase()){
                Toast.makeText(this, "პაროლი", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }else if(secondPassword != password){
                Toast.makeText(this, "მეორე პაროლი", Toast.LENGTH_SHORT).show()
//                return@setOnClickListener
            }else if(pNumber.length != 9){
                Toast.makeText(this, "ნომერი", Toast.LENGTH_SHORT).show()
//                return@setOnClickListener
            }else if(userProf.isEmpty()) {
                Toast.makeText(this, "პროფესია", Toast.LENGTH_SHORT).show()
//                return@setOnClickListener
            }else if (ID.length != 11){
                Toast.makeText(this, "პირადი ნომერი", Toast.LENGTH_SHORT).show()
//                return@setOnClickListener
            }


            FirebaseAuth.getInstance()
                .createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this){ task ->
                    if (task.isSuccessful) {

                        val intent = Intent(this, LoginActivity::class.java)
                        startActivity(intent)
                        finish()

                    } else {

                        Toast.makeText(this, "დაფიქსირდა შეცდომა, სცადეთ ხელახლა ", Toast.LENGTH_SHORT).show()

                    }
                }


        }


        backToLogin.setOnClickListener {
            val intent = Intent(this,LoginActivity::class.java)
            startActivity(intent)
            finish()
        }






    }
}
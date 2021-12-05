package com.example.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    private lateinit var e_mail: EditText
    private lateinit var password: EditText

    private lateinit var logIn: Button
    private lateinit var registration: Button
    private lateinit var forgotPassword: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_activity)

        e_mail = findViewById(R.id.E_mailEditText)
        password = findViewById(R.id.passwordEditText)

        logIn = findViewById(R.id.logInBtn)
        registration = findViewById(R.id.registracionBtn)
        forgotPassword = findViewById(R.id.forgotPasswordBtn)



        registration.setOnClickListener{

            val intent = Intent(this,RegisrtationActivity::class.java)
            startActivity(intent)

        }

        forgotPassword.setOnClickListener{

            val intent = Intent(this, ResetPasswordActivity::class.java)
            startActivity(intent)

        }

        logIn.setOnClickListener {

            var email = e_mail.text.toString()
            var password = password.text.toString()

            if (email.isEmpty() || !email.contains("@")){
                Toast.makeText(this, "გთოვთ შეავსოთ e_mail სწორად", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }else if(password.isEmpty() || password.length < 7){
                Toast.makeText(this, "გთხოვთ შეავსოთ პაროლი, არანაკლებ 8 სიმბოლოთი", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }else{

                FirebaseAuth.getInstance()
                    .signInWithEmailAndPassword(email,password)
                    .addOnCompleteListener { task->

                        if (task.isSuccessful){
                            val intent = Intent(this,ProfileActivity::class.java)
                            startActivity(intent)
                            finish()
                        }else{
                            Toast.makeText(this, "დაფიქსირდა შეცდომა, სცადეთ ხელახლა", Toast.LENGTH_SHORT).show()
                        }

                    }


            }


        }






    }
}
package com.example.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class ResetPasswordActivity : AppCompatActivity() {

    private lateinit var e_mail: EditText

    private lateinit var sendNewPassword: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.password_reset_activity)

        e_mail = findViewById(R.id.e_mailEditText)

        sendNewPassword = findViewById(R.id.sendNewPasswordBtn)


        sendNewPassword.setOnClickListener {

            var email = e_mail.text.toString()

            if (email.isEmpty() || !email.contains("@")){

                Toast.makeText(this, "გთხოვთ შეიყვანოთ მეილი სრულად", Toast.LENGTH_SHORT).show()
                return@setOnClickListener

            }else{

                FirebaseAuth.getInstance()
                    .sendPasswordResetEmail(email)
                    .addOnCompleteListener { task->
                        if (task.isSuccessful){
                            Toast.makeText(this, "შეამოწმეთ მეილი", Toast.LENGTH_SHORT).show()
                            val intent = Intent(this, LoginActivity::class.java)
                            startActivity(intent)
                        }else{
//                            Toast.makeText(this, "${task.exception}", Toast.LENGTH_SHORT).show()
                        }
                    }

            }

        }

    }
}
package com.example.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class ChangePasswordActivity : AppCompatActivity() {


    private lateinit var newPassword: EditText
    private lateinit var confirmNewPassword: EditText

    private lateinit var sendNewPassword: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.change_password_activity)

        newPassword = findViewById(R.id.newPasswordEditText)
        confirmNewPassword = findViewById(R.id.confirmNewPasswordEditText)

        sendNewPassword = findViewById(R.id.sendNewPAssword)

        sendNewPassword.setOnClickListener {

            var newPass = newPassword.text.toString()
            var confNewPass = confirmNewPassword.text.toString()

            if (newPass.isEmpty() && confNewPass.isEmpty() && newPass.length < 7 && confNewPass.length < 7 && !confNewPass.contains(newPass)) {
                Toast.makeText(this, "შეავსეთ პაროლის ველები სწორად", Toast.LENGTH_SHORT).show()
            }else{

                FirebaseAuth.getInstance()
                    .currentUser?.updatePassword(newPass)
                    ?.addOnCompleteListener { task ->

                        if (task.isSuccessful){
                            Toast.makeText(this, "პაროლი წარმატყებით შეიცვალა", Toast.LENGTH_SHORT).show()

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
package com.example.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthActionCodeException
//import com.google.firebase.database.DataSnapshot
//import com.google.firebase.database.DatabaseError
//import com.google.firebase.database.FirebaseDatabase
//import com.google.firebase.database.ValueEventListener
import java.lang.StringBuilder

class ProfileActivity : AppCompatActivity() {

    private lateinit var userInfoId: TextView
    private lateinit var userInfoEmail: TextView
    private lateinit var userPhone: TextView
    private lateinit var userID: TextView
    private lateinit var userProfession: TextView


    private lateinit var changePassword: Button
    private lateinit var logOut: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.profile_activity)

        userInfoId = findViewById(R.id.userInfoId)
        userInfoEmail = findViewById(R.id.userInfoEmail)
        userPhone = findViewById(R.id.phoneNumberEditText)
        userID = findViewById(R.id.userID)
        userProfession = findViewById(R.id.userProfession)


        changePassword = findViewById(R.id.changePasswordBtn)
        logOut = findViewById(R.id.logOutBtn)

        userInfoId.text =
            "თვქენი ID FireBase-ზე: ${FirebaseAuth.getInstance().currentUser?.uid.toString()}"
        userInfoEmail.text =
            "თქვენი e_mail: ${FirebaseAuth.getInstance().currentUser?.email.toString()}"


//        var userPhoneNumber = userPhone.text
//        var userIdentity = userID.text.toString()
//        var userProfession =  userProfession.text.toString()
//
//        var getData = object : ValueEventListener{
//            override fun onDataChange(snapshot: DataSnapshot) {
//                userPhoneNumber = (snapshot.child("phoneNumber").getValue() as TextView).toString()
//                userIdentity = snapshot.child("id").getValue() as String
//                userProfession = snapshot.child("profession").getValue() as String
//            }
//
//            override fun onCancelled(error: DatabaseError) {
//            }
//
//        }
//
//        var database = FirebaseDatabase.getInstance().reference
//        database.addValueEventListener(getData)


        logOut.setOnClickListener {

            FirebaseAuth.getInstance().signOut()

            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()

        }

        changePassword.setOnClickListener {

            val intent = Intent(this, ChangePasswordActivity::class.java)
            startActivity(intent)
            finish()

        }

    }
}
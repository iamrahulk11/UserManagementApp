package com.example.usermanagement

import android.content.Intent
import android.os.Binder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import com.example.usermanagement.databinding.ActivitySecondBinding
import com.example.usermanagement.util.keys
import com.google.gson.Gson

class SecondActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var binding: ActivitySecondBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivitySecondBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val intent = intent
        val intentData =  intent.getStringExtra(keys.JSON_USER)
        val user = Gson().fromJson<User>(intentData, User::class.java)

        val result=user.hobbies.toString().replace("[", "").replace("]", "").replace(",","\n").replace(" ","")

        binding.DisplayData.text = "Username : ${user.fName} ${user.lName}\nPhone No : ${user.mobNo}\nAltn Phone No : ${user.atlMob}\nEmail : ${user.email}\nGender : ${user.gender}\nUser Hobbies :\n${result}"
        binding.btnclear.setOnClickListener(this)
    }
    override fun onBackPressed() {
        // super.onBackPressed()
        val alertDialogBuilder = AlertDialog.Builder(this)
        alertDialogBuilder.setTitle("Confirmation")
        alertDialogBuilder.setMessage("Do you want to return to home page?")
        alertDialogBuilder.setPositiveButton("Yes") { Dialog, which ->
            finish()
        }
        alertDialogBuilder.setNegativeButton("No") { Dialog, which ->
            Dialog.cancel()
        }
        val alertDialog: AlertDialog = alertDialogBuilder.create()
        // Set other dialog properties
        alertDialog.setCancelable(false)
        alertDialog.show()
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btnclear ->{
                val backHomePage = Intent(this, MainActivity::class.java)
                startActivity(backHomePage)
                finish()
            }
        }
    }
}
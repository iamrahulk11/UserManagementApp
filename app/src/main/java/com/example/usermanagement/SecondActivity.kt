package com.example.usermanagement

import android.content.Intent
import android.os.Binder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toolbar
import androidx.appcompat.app.AlertDialog
import com.example.usermanagement.databinding.ActivitySecondBinding
import com.example.usermanagement.util.keys
import com.google.gson.Gson

class SecondActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var binding: ActivitySecondBinding
    lateinit var toolbar: Toolbar
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivitySecondBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
//        getSupportActionBar()?.setTitle(Html.fromHtml("<font color=\"#FFFFFFFF\">"+getString(R.string.app_name)+"</font>"))
       // supportActionBar?.setDisplayShowHomeEnabled(true)


        val intent = intent
        val intentData =  intent.getStringExtra(keys.JSON_USER)
        val user = Gson().fromJson<User>(intentData, User::class.java)

        val result=user.hobbies.toString().replace("[", "").replace("]", "").replace(",","\n").replace(" ","")

        binding.DisplayData.text = "USERNAME : ${user.fName} ${user.lName}\nPHONE No : ${user.mobNo}\nALTN PHONE No : ${user.atlMob}\nEMAIL : ${user.email}\nGENDER : ${user.gender}\n\nUSER HOBBIES :\n${result}"
        binding.btnclear.setOnClickListener(this)
    }
    override fun onBackPressed() {
        // super.onBackPressed()
        val alertDialogBuilder = AlertDialog.Builder(this)
        alertDialogBuilder.setTitle("Confirmation")
        alertDialogBuilder.setMessage("Do you want to return to home page?")
        alertDialogBuilder.setPositiveButton("Yes") { Dialog, which ->
            val backHomePage = Intent(this, MainActivity::class.java)
            startActivity(backHomePage)
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
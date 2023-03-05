package com.example.usermanagement

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.CompoundButton
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.usermanagement.databinding.ActivityMainBinding
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity(), View.OnClickListener, RadioGroup.OnCheckedChangeListener,
    CompoundButton.OnCheckedChangeListener {
    private lateinit var binding: ActivityMainBinding
    private var fname: String? = null
    private var lname: String? = null
    private var mobileNo: String? = null
    private var email: String? = null
    var altMobNo: String? = null
    lateinit var context: Context
    private var txtGender: String? = null
    var UserHobbies: ArrayList<String> = ArrayList()

    //private val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\.+[a-z]+"
    // var check :Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        context = this@MainActivity

        binding.AboveDataShowing.visibility = View.INVISIBLE
        binding.clear.visibility = View.INVISIBLE
        binding.submit.setOnClickListener(this)
        binding.clear.setOnClickListener(this)

        // Radio Group/Radio Button listener
        binding.Radiogroup.setOnCheckedChangeListener(this)
        //hobbies listener
        binding.HProgramming.setOnCheckedChangeListener(this)
        binding.HTravelling.setOnCheckedChangeListener(this)
        binding.HGaming.setOnCheckedChangeListener(this)
        binding.HStudyNovel.setOnCheckedChangeListener(this)
        binding.hMovies.setOnCheckedChangeListener(this)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // menuInflater.inflate(R.menu.menu_design,menu)
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu_title, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item1 -> {
                Toast.makeText(
                    context, "Clicked About App",
                    Toast.LENGTH_LONG
                ).show()
            }
            R.id.item2 -> {
                Toast.makeText(
                    context, "Clicked Settings",
                    Toast.LENGTH_LONG
                ).show()
            }
            R.id.item3 -> {
                val alertDialogBuilder = AlertDialog.Builder(this)
                alertDialogBuilder.setTitle("Confirmation")
                alertDialogBuilder.setMessage("Do you want to Exit?")
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
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        // super.onBackPressed()
        val alertDialogBuilder = AlertDialog.Builder(this)
        alertDialogBuilder.setTitle("Confirmation")
        alertDialogBuilder.setMessage("Do you want to Exit?")
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

    override fun onStart() {
        super.onStart()
    }

    override fun onResume() {
        super.onResume()
    }

    //Radio Group taking input and storing in txtGender

    override fun onStop() {
        super.onStop()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onRestart() {
        super.onRestart()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    /*fun validateEmail(view: View?) {
        if (email.matches(emailPattern.toRegex())) {
            Toast.makeText(applicationContext, "Valid email address",
                Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(applicationContext, "Invalid email address",
                Toast.LENGTH_SHORT).show()
        }
    }*/

    override fun onClick(v: View?) {

        when (v?.id) {
            R.id.submit -> {
                if (binding.enterFirstName.editText!!.text.toString().isEmpty() ||
                    binding.enterLastName.editText!!.text.toString().isEmpty() ||
                    binding.enterPh.editText!!.text.toString().isEmpty() ||
                    binding.enterEmail.editText!!.text.toString().isEmpty() ||
                    binding.enterAltPhNo.editText!!.text.toString().isEmpty()
                ) {
                    if (binding.enterFirstName.editText!!.text.toString().isEmpty() &&
                        binding.enterLastName.editText!!.text.toString().isEmpty() &&
                        binding.enterPh.editText!!.text.toString().isEmpty() &&
                        binding.enterEmail.editText!!.text.toString().isEmpty() &&
                        binding.enterAltPhNo.editText!!.text.toString().isEmpty()
                    ) {
                        Toast.makeText(context, "All Fields are necessary", Toast.LENGTH_SHORT)
                            .show()
                        return
                    } else if (binding.enterFirstName.editText!!.text.toString().isEmpty()) {
                        Toast.makeText(
                            context,
                            "First Name should not be empty",
                            Toast.LENGTH_SHORT
                        ).show()
                        binding.enterFirstName.editText!!.requestFocus()
                        return
                    } else if (binding.enterLastName.editText!!.text.toString().isEmpty()) {
                        Toast.makeText(
                            context,
                            "Last Name should not be empty",
                            Toast.LENGTH_SHORT
                        ).show()
                        binding.enterLastName.editText!!.requestFocus()
                        return
                    } else if (binding.enterPh.editText!!.text.toString().isEmpty()) {
                        Toast.makeText(
                            context,
                            "Phone Number should not be empty",
                            Toast.LENGTH_SHORT
                        ).show()
                        binding.enterPh.editText!!.requestFocus()
                        return
                    } else if (binding.enterEmail.editText!!.text.toString().isEmpty()) {
                        Toast.makeText(
                            context,
                            "Email Address should not be empty",
                            Toast.LENGTH_SHORT
                        ).show()
                        binding.enterEmail.editText!!.requestFocus()
                        return
                    } else if (binding.enterAltPhNo.editText!!.text.toString().isEmpty()) {
                        Toast.makeText(
                            context,
                            "Alternative Number should not be empty",
                            Toast.LENGTH_SHORT
                        ).show()
                        binding.enterAltPhNo.editText!!.requestFocus()
                        return
                    }
                } else {
                    fname = binding.enterFirstName.editText!!.text.toString()
                        .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString() }
                    lname = binding.enterLastName.editText!!.text.toString()
                        .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString() }
                    mobileNo = binding.enterPh.editText!!.text.toString()
                    email = binding.enterEmail.editText!!.text.toString().lowercase()
                    altMobNo = binding.enterAltPhNo.editText!!.text.toString()
                }

                if (mobileNo!!.length != 10) {
                    Toast.makeText(
                        context,
                        "Phone number is INVALID",
                        Toast.LENGTH_SHORT
                    ).show()
                    binding.enterPh.editText!!.text.clear()
                    binding.enterPh.editText!!.requestFocus()
                    return
                }
                if (altMobNo!!.length != 10) {
                    Toast.makeText(
                        context,
                        "Alternative Phone number is INVALID",
                        Toast.LENGTH_SHORT
                    ).show()
                    binding.enterAltPhNo.editText!!.text.clear()
                    binding.enterAltPhNo.editText!!.requestFocus()
                    return
                }
                if (mobileNo == altMobNo) {
                    Toast.makeText(
                        context,
                        "Alternative Number And Phone number should not be Same",
                        Toast.LENGTH_SHORT
                    ).show()
                    binding.enterAltPhNo.editText!!.text.clear()
                    binding.enterAltPhNo.editText!!.requestFocus()
                    return
                }

                binding.enterFirstName.isEnabled = false
                binding.enterLastName.isEnabled = false
                binding.enterPh.isEnabled = false
                binding.enterAltPhNo.isEnabled = false
                binding.enterEmail.isEnabled = false

                binding.AboveDataShowing.visibility = View.VISIBLE
                binding.clear.visibility = View.VISIBLE
                binding.submit.isEnabled = false
                val result=UserHobbies.toString().replace("[", "").replace("]", "").replace(",","\n").replace(" ","")
                binding.AboveDataShowing.text =
                    "UserName : $fname " + "$lname\n" + "Gender : ${txtGender}\n" + "Mobile Number : $mobileNo\n" + "Alternative Number : $altMobNo\n" + "Email : $email\n" + "UserHobbies :\n${result}"
            }
            R.id.clear -> {

                binding.enterFirstName.isEnabled = true
                binding.enterLastName.isEnabled = true
                binding.enterPh.isEnabled = true
                binding.enterAltPhNo.isEnabled = true
                binding.enterEmail.isEnabled = true
                binding.submit.isEnabled = true
                binding.AboveDataShowing.visibility = View.INVISIBLE
                binding.clear.visibility = View.INVISIBLE
                binding.enterFirstName.editText!!.text.clear()
                binding.enterLastName.editText!!.text.clear()
                binding.enterPh.editText!!.text.clear()
                binding.enterEmail.editText!!.text.clear()
                binding.enterAltPhNo.editText!!.text.clear()
                binding.enterFirstName.editText!!.requestFocus()

            }

        }
    }

    override fun onCheckedChanged(group: RadioGroup?, checkedId: Int) {
        when (group?.checkedRadioButtonId) {
            R.id.male -> {
                val rbmale = findViewById<RadioButton>(checkedId)
                txtGender = rbmale.text.toString()

            }

            R.id.female -> {
                val rbfemale = findViewById<RadioButton>(checkedId)
                txtGender = rbfemale.text.toString()
            }

            R.id.other -> {
                val rbother = findViewById<RadioButton>(checkedId)
                txtGender = rbother.text.toString()
            }
        }
    }

    override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
        when (buttonView?.id) {
            R.id.H_programming -> {
                if (binding.HProgramming.isChecked) {
                    UserHobbies.add(binding.HProgramming.text.toString())
                }else{
                    UserHobbies.remove(binding.HProgramming.text.toString())
                }
            }
            R.id.H_Gaming -> {
                if (binding.HGaming.isChecked) {
                    UserHobbies.add(binding.HGaming.text.toString())
                }else{
                    UserHobbies.remove(binding.HGaming.text.toString())
                }
            }
            R.id.H_Study_Novel -> {
                if (binding.HStudyNovel.isChecked) {
                    UserHobbies.add(binding.HStudyNovel.text.toString())
                }else{
                    UserHobbies.remove(binding.HStudyNovel.text.toString())
                }
            }
            R.id.H_Travelling -> {
                if (binding.HTravelling.isChecked) {
                    UserHobbies.add(binding.HTravelling.text.toString())
                }else{
                    UserHobbies.remove(binding.HTravelling.text.toString())
                }
            }
            R.id.h_Movies -> {
                if (binding.hMovies.isChecked) {
                    UserHobbies.add(binding.hMovies.text.toString())
                }else{
                    UserHobbies.remove(binding.hMovies.text.toString())
                }
            }
        }
    }

}
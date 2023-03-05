package com.example.usermanagement

data class User(
    var fName: String = "",
    var lName: String = "",
    var mobNo: String = "",
    var email: String = "",
    var atlMob: String = "",
    var gender: String = "",
    var hobbies: ArrayList<String> = ArrayList()
)

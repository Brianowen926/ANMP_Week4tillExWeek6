package com.example.studentproject.model

import com.google.gson.annotations.SerializedName

data class Student(
    var id:String?,
    @SerializedName("student_name") //merujuk pada data JSON
    var name:String?,
    @SerializedName("birth_of_date") //merujuk pada data JSON
    var bod:String?,
    var phone:String?,
    @SerializedName("photo_url") //merujuk pada data JSON
    var photoUrl:String?
)

data class Car(
    val id:String?,
    val make:String?,
    val model:String?,
    val year:String?,
    val color:String?,
    val price:String?,
    val feature:List<String>?,
    val specs:CarSpesifications?
)

data class CarSpesifications(
    val engine:String?,
    val transmission:String?,
    val fuelType:String?,
    val motor:String?,
    val battery:String?
)
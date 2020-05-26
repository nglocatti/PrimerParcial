package com.example.a1parcial.Entities.Login

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "UserLogin")
class UserLogin ( UserName : String , Password : String ) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "idUserLogin")
    var iduserlogin : Int = 0

    @ColumnInfo(name = "UserName")
    var username : String = UserName

    @ColumnInfo(name = "Password")
    var password : String = Password

    constructor() : this("admin", "admin")
}
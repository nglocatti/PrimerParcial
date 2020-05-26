package com.example.a1parcial.Entities.Login

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "UserInfo")
class UserInfo ( idUser : Int, UserName : String , Email : String) {
    @PrimaryKey
    @ColumnInfo(name = "idUser")
    var iduser : Int = idUser

    @ColumnInfo(name = "UserName")
    var username : String = UserName

    @ColumnInfo(name = "Email")
    var email : String = Email

    constructor() : this(0,"admin", "a@a.com")
}
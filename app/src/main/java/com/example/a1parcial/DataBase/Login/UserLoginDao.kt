package com.example.a1parcial.DataBase.Login

import androidx.room.*
import com.example.a1parcial.Entities.Login.UserLogin

@Dao
interface UserLoginDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUserLogin(User: UserLogin?)

    @Update
    fun updateUser(User: UserLogin?)

    @Delete
    fun delete(User: UserLogin?)

    @Query("SELECT * FROM UserLogin WHERE iduserlogin = :idUser")
    fun loadUserById(idUser: Int): UserLogin?

    @Query("SELECT idUserLogin FROM UserLogin WHERE UserName = :User")
    fun getUserId(User: String): Int

    @Query("SELECT Password FROM UserLogin WHERE UserName = :UserName")
    fun loginUser(UserName: String): String
}
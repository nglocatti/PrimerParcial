package com.example.a1parcial.DataBase.Login

import androidx.room.*
import com.example.a1parcial.Entities.Login.UserInfo

@Dao
interface UserInfoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUserInfo(User: UserInfo?)

    @Update
    fun updateUserInfo(User: UserInfo?)

    @Delete
    fun deleteUserInfo(User: UserInfo?)

    @Query("SELECT * FROM UserInfo WHERE idUser = :idUser")
    fun loadUserInfoById(idUser: Int): UserInfo?
}
package com.example.a1parcial.DataBase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.a1parcial.DataBase.Articulos.ArtActualDao
import com.example.a1parcial.DataBase.Articulos.ArticulosDao
import com.example.a1parcial.DataBase.Login.UserInfoDao
import com.example.a1parcial.DataBase.Login.UserLoginDao
import com.example.a1parcial.Entities.Articulos.ArtActual
import com.example.a1parcial.Entities.Articulos.Articulos
import com.example.a1parcial.Entities.Login.UserInfo
import com.example.a1parcial.Entities.Login.UserLogin

@Database(
    entities = [UserLogin::class, UserInfo::class, Articulos::class, ArtActual::class],
    version = 1,
    exportSchema = false)
abstract class DataBase : RoomDatabase() {
    abstract fun userLoginDao(): UserLoginDao
    abstract fun userInfoDao(): UserInfoDao
    abstract fun articulosDao(): ArticulosDao
    abstract fun ArtActualDao(): ArtActualDao

    companion object {
        var INSTANCE: DataBase? = null

        fun getDataBase(context: Context): DataBase? {
            if (INSTANCE == null) {
                synchronized(DataBase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        DataBase::class.java,
                        "DataBase"
                    ).allowMainThreadQueries().build() // No es lo mas recomendable que se ejecute en el mainthread
                }
            }
            return INSTANCE
        }

        fun destroyDataBase(){
            INSTANCE = null
        }
    }
}
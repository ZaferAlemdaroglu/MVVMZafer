package com.zafer.mvvmzaferapp.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.zafer.mvvmzaferapp.data.db.entities.CURRENT_USER_ID
import com.zafer.mvvmzaferapp.data.db.entities.User


@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(user: User) : Long

    @Query("SELECT * FROM user WHERE uid =$CURRENT_USER_ID")
    fun getuser() : LiveData<User>

    @Query ( "SELECT id FROM user")
    fun getuserId(): Int


}
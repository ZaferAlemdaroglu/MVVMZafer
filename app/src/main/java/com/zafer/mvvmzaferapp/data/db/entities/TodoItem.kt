package com.zafer.mvvmzaferapp.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class TodoItem(
    val userId : Int,
    val name : String,
    val description: String,
    val deadline : String,
    val createDate: String,
    var isCompleted : Boolean

){
    @PrimaryKey(autoGenerate = true)
    var taskId : Long? = null
}
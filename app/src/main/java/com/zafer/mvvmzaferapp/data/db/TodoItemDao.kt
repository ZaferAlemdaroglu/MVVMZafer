package com.zafer.mvvmzaferapp.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.zafer.mvvmzaferapp.data.db.entities.TodoItem
import com.zafer.mvvmzaferapp.data.db.entities.User


@Dao
interface TodoItemDao{

/*    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveAllTodoItems(todoItems : List<TodoItem>)*/

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveTodoItem(todoItem : TodoItem)


    @Delete
    fun deleteTodoItem(todoItem: TodoItem)


    @Query("SELECT * FROM TodoItem WHERE  userId = :id")
    fun getTodoItems(id: Int) : LiveData<List<TodoItem>>

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateTodoItem(todoItem: TodoItem)


}
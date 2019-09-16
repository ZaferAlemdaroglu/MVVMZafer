package com.zafer.mvvmzaferapp.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.zafer.mvvmzaferapp.data.db.AppDatabase
import com.zafer.mvvmzaferapp.data.db.entities.Quote
import com.zafer.mvvmzaferapp.data.db.entities.TodoItem
import com.zafer.mvvmzaferapp.data.network.MyApi
import com.zafer.mvvmzaferapp.data.network.SafeApiRequest
import com.zafer.mvvmzaferapp.util.Coroutines
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TodoItemsRepository(
    private val db: AppDatabase
) : SafeApiRequest (){



    suspend fun getTodoItems():LiveData<List<TodoItem>>{
        return withContext(Dispatchers.IO){
             db.getTodoItemDao().getTodoItems(getUserId())
        }
    }


    suspend fun saveTodoItem(todoItem:TodoItem){
            db.getTodoItemDao().saveTodoItem(todoItem)
    }


     fun updateTodoItem(todoItem: TodoItem){
        Coroutines.io {
            db.getTodoItemDao().updateTodoItem(todoItem)
        }
    }

     fun deleteTodoItem(todoItem:TodoItem){
        Coroutines.io{
            db.getTodoItemDao().deleteTodoItem(todoItem)
        }
    }

     suspend fun getUserId(): Int{
         return withContext(Dispatchers.IO){
             db.getUserDao().getuserId()
         }
    }





}
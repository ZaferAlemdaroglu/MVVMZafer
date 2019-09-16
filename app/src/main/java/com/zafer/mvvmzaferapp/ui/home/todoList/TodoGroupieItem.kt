package com.zafer.mvvmzaferapp.ui.home.todoList

import android.widget.CompoundButton
import com.xwray.groupie.databinding.BindableItem
import com.xwray.groupie.databinding.ViewHolder
import com.zafer.mvvmzaferapp.R
import com.zafer.mvvmzaferapp.data.db.AppDatabase
import com.zafer.mvvmzaferapp.data.db.entities.TodoItem
import com.zafer.mvvmzaferapp.databinding.ItemTodoGroupieBinding

class TodoGroupieItem (
    private val todoItem: TodoItem
) : BindableItem<ItemTodoGroupieBinding>() {
    override fun getLayout()= R.layout.item_todo_groupie

    override fun bind(viewBinding: ItemTodoGroupieBinding, position: Int) {
       viewBinding.setTodoItem(todoItem)

     /*   viewBinding.switchComplete.setOnCheckedChangeListener{ buttonView, isChecked ->
            todoItem.isCompleted=isChecked
        }*/
    }
    fun getEntityDataClass(): TodoItem {

        return todoItem

    }




}
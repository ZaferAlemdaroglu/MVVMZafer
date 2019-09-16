package com.zafer.mvvmzaferapp.ui.home.todoList

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModel;
import com.zafer.mvvmzaferapp.R
import com.zafer.mvvmzaferapp.data.db.entities.TodoItem
import com.zafer.mvvmzaferapp.data.repositories.TodoItemsRepository
import com.zafer.mvvmzaferapp.util.Coroutines
import com.zafer.mvvmzaferapp.util.Coroutines.io
import com.zafer.mvvmzaferapp.util.convertDateToLong
import com.zafer.mvvmzaferapp.util.convertLongToTime
import com.zafer.mvvmzaferapp.util.lazyDeferred
import kotlinx.android.synthetic.main.item_dialog.view.*
import java.time.LocalDateTime

class TodoListViewModel(
    private val repository: TodoItemsRepository
) : ViewModel() {


    val todoItemList by lazyDeferred {
        repository.getTodoItems()
    }


    fun addItemButtonClicked(view: View) {

        addOrEditDialog(view.context, null)


    }

    fun removeItemFromList(
        todoItem: TodoItem
    ) {

        io {

            repository.deleteTodoItem(todoItem!!)
        }

    }

    fun editTodoItem(view: View, todoItem: TodoItem) {

        addOrEditDialog(view.context, todoItem)
    }

    fun addOrEditDialog(context: Context, todoItem: TodoItem?) {

        val mDialogView = LayoutInflater.from(context).inflate(R.layout.item_dialog, null)

        val mBuilder = AlertDialog.Builder(context)
            .setView(mDialogView)
            .setTitle("TodoList Add Item Form")

        val mAlertDialog = mBuilder.show()
        var calendarDate = convertLongToTime(mDialogView.dialogCalendarView.date)

        if (todoItem != null) {
            mDialogView.dialogName.setText(todoItem.name, TextView.BufferType.EDITABLE)
            mDialogView.dialogDescription.setText(todoItem.description, TextView.BufferType.EDITABLE)
            mDialogView.dialogCalendarView.setDate(convertDateToLong(todoItem.deadline))
            calendarDate = todoItem.deadline

        }


        mDialogView.dialogCalendarView.setOnDateChangeListener { view, year, month, dayOfMonth ->
            calendarDate =
                year.toString() + "." + String.format("%02d", month + 1) + "." + String.format("%02d", dayOfMonth)


        }

        mDialogView.dialogLoginBtn.setOnClickListener {


            mAlertDialog.dismiss()

            val name = mDialogView.dialogName.text.toString()
            val description = mDialogView.dialogDescription.text.toString()


            Coroutines.main {

                val newTodoItem = TodoItem(
                    repository.getUserId(),
                    name,
                    description,
                    calendarDate,
                    LocalDateTime.now().toString(),
                    false
                )

                if (todoItem != null) {
                    newTodoItem.taskId = todoItem.taskId
                    repository.updateTodoItem(newTodoItem)


                } else {
                    repository.saveTodoItem(newTodoItem)
                }

            }

        }
        mDialogView.dialogCancelBtn.setOnClickListener {
            mAlertDialog.dismiss()
        }
    }

    fun updateStatusItem(todoItem: TodoItem) {
        Coroutines.main {
            todoItem.isCompleted=!todoItem.isCompleted
            repository.updateTodoItem(todoItem)
        }

    }


}

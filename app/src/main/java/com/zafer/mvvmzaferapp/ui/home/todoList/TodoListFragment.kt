package com.zafer.mvvmzaferapp.ui.home.todoList

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.appcompat.widget.PopupMenu
import androidx.appcompat.widget.ViewUtils
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.kotlinandroidextensions.ViewHolder

import com.zafer.mvvmzaferapp.R
import com.zafer.mvvmzaferapp.data.db.entities.TodoItem
import com.zafer.mvvmzaferapp.databinding.TodoListFragmentBinding
import com.zafer.mvvmzaferapp.util.Coroutines
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance
import com.zafer.mvvmzaferapp.util.toast
import kotlinx.android.synthetic.main.todo_list_fragment.*

class TodoListFragment : Fragment(),KodeinAware {

    override val kodein by kodein()

    private val factory: TodoListViewModelFactory by instance()
    private lateinit var viewModel: TodoListViewModel

   override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: TodoListFragmentBinding =
            DataBindingUtil.inflate(inflater, R.layout.todo_list_fragment, container, false)
        viewModel = ViewModelProviders.of(this, factory).get(TodoListViewModel::class.java)
        binding.viewmodel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        bindUI()
    }



    private fun bindUI() = Coroutines.main{

        viewModel.todoItemList.await().observe(this, Observer {

            initRecycleView(it.toDoItemGroupieList())
        })
    }

    private fun initRecycleView(todoItemList: List<TodoGroupieItem>) {

        val mAdapter = GroupAdapter<ViewHolder>().apply {
            addAll(todoItemList)
        }

        mAdapter.setOnItemClickListener { item, view ->

            showPopup(view,(item as TodoGroupieItem).getEntityDataClass())

        }

        receyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = mAdapter
        }
    }



    private fun List<TodoItem>.toDoItemGroupieList():List<TodoGroupieItem>{
        return  this.map{
            TodoGroupieItem(it)
        }
    }




    private fun showPopup(view: View,todoItem: TodoItem) {
        var popup: PopupMenu? = null;
        popup = PopupMenu(context!!, view,Gravity.RIGHT)
        popup.inflate(R.menu.item_menu)

        popup.setOnMenuItemClickListener(PopupMenu.OnMenuItemClickListener { item: MenuItem? ->

            when (item!!.itemId) {
                R.id.complete_task -> {
                    viewModel.updateStatusItem(todoItem)
                }
                R.id.edit_task -> {
                    viewModel.addOrEditDialog(view.context,todoItem)
                }
                R.id.delete_task -> {
                    viewModel.removeItemFromList(todoItem)
                }
            }

            true
        })

        popup.show()
    }

}

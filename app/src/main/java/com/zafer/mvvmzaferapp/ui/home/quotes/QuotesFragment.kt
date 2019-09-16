package com.zafer.mvvmzaferapp.ui.home.quotes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import com.zafer.mvvmzaferapp.R
import com.zafer.mvvmzaferapp.data.db.entities.Quote
import com.zafer.mvvmzaferapp.util.Coroutines
import com.zafer.mvvmzaferapp.util.hide
import com.zafer.mvvmzaferapp.util.show
import kotlinx.android.synthetic.main.quotes_fragment.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance


class QuotesFragment : Fragment(),KodeinAware {


    override val kodein by kodein()

    private val factory : QuotesViewModelFactory by instance()

    private lateinit var viewModel: QuotesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.quotes_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this,factory).get(QuotesViewModel::class.java)

        bindUI()

    }

    private fun bindUI() = Coroutines.main{

        progress_bar.show()
        viewModel.quotes.await().observe(this, Observer {
            progress_bar.hide()
            initRecycleView(it.toQuoteItem())
        })
    }

    private fun initRecycleView(quoteItemList: List<QuoteItem>) {

        val mAdapter = GroupAdapter<ViewHolder>().apply {
            addAll(quoteItemList)
        }

        receyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = mAdapter
        }
    }


    private fun List<Quote>.toQuoteItem():List<QuoteItem>{
        return  this.map{
            QuoteItem(it)
        }
    }

}

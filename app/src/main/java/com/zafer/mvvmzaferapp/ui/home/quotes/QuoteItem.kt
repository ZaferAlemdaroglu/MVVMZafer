package com.zafer.mvvmzaferapp.ui.home.quotes

import com.xwray.groupie.databinding.BindableItem
import com.zafer.mvvmzaferapp.R
import com.zafer.mvvmzaferapp.data.db.entities.Quote
import com.zafer.mvvmzaferapp.databinding.ItemQuoteBinding

class QuoteItem (
    private val quote : Quote
) : BindableItem<ItemQuoteBinding>(){


    override fun getLayout() = R.layout.item_quote

    override fun bind(viewBinding: ItemQuoteBinding, position: Int) {

        viewBinding.setQuote(quote)

    }

}
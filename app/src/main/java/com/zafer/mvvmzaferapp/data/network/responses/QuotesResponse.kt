package com.zafer.mvvmzaferapp.data.network.responses

import com.zafer.mvvmzaferapp.data.db.entities.Quote

data class QuotesResponse(

    val isSuccessful: Boolean,
    val quotes: List<Quote>
)
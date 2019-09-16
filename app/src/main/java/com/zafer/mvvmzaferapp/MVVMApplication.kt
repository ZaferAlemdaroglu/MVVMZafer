package com.zafer.mvvmzaferapp

import android.app.Application
import com.zafer.mvvmzaferapp.data.db.AppDatabase
import com.zafer.mvvmzaferapp.data.network.MyApi
import com.zafer.mvvmzaferapp.data.network.NetworkConnectionInterceptor
import com.zafer.mvvmzaferapp.data.preferences.PreferenceProvider
import com.zafer.mvvmzaferapp.data.repositories.QuotesRepository
import com.zafer.mvvmzaferapp.data.repositories.TodoItemsRepository
import com.zafer.mvvmzaferapp.data.repositories.UserRepository
import com.zafer.mvvmzaferapp.ui.auth.AuthViewModelFactory
import com.zafer.mvvmzaferapp.ui.home.profile.ProfileViewModelFactory
import com.zafer.mvvmzaferapp.ui.home.quotes.QuotesViewModelFactory
import com.zafer.mvvmzaferapp.ui.home.todoList.TodoListViewModelFactory
import org.kodein.di.Kodein.Companion.lazy
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class MVVMApplication : Application(),KodeinAware {

    override val kodein = lazy {

        import(androidXModule(this@MVVMApplication))

        bind() from singleton { NetworkConnectionInterceptor(instance()) }
        bind() from singleton { MyApi(instance()) }
        bind() from singleton { AppDatabase(instance()) }
        bind() from singleton { PreferenceProvider(instance()) }

        bind() from singleton { UserRepository(instance(),instance()) }
        bind() from singleton { TodoItemsRepository(instance()) }
        bind() from singleton { QuotesRepository(instance(),instance(),instance()) }

        bind() from provider { AuthViewModelFactory(instance()) }
        bind() from provider { ProfileViewModelFactory(instance()) }
        bind() from provider { QuotesViewModelFactory(instance()) }
        bind() from provider { TodoListViewModelFactory(instance()) }




    }

}


package com.zafer.mvvmzaferapp.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.zafer.mvvmzaferapp.data.db.entities.Quote
import com.zafer.mvvmzaferapp.data.db.entities.TodoItem
import com.zafer.mvvmzaferapp.data.db.entities.User
import java.security.AccessControlContext
import kotlin.jvm.Volatile


@Database(
    entities = [User::class,Quote::class,TodoItem::class],
    version = 1
)

abstract class AppDatabase : RoomDatabase() {

    abstract fun getUserDao(): UserDao
    abstract fun getQuoteDao(): QuoteDao
    abstract fun getTodoItemDao() : TodoItemDao

    companion object {

        @Volatile
        private var instance: AppDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also {
                instance = it }
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "MyDatabase.db"
            ).build()

    }

}
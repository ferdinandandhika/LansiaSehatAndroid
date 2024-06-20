package com.example.lansiasehat.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.lansiasehat.model.Todo

@Database(entities = [Todo::class], version = 1)
abstract class TodoDatabase : RoomDatabase() {
    abstract fun todoDao(): TodoDao
}
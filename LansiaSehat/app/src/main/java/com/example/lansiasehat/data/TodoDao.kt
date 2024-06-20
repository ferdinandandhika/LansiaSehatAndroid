package com.example.lansiasehat.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.lansiasehat.model.Todo

@Dao
interface TodoDao {
    @Insert
    fun insert(todo: Todo)

    @Update
    fun update(todo: Todo)


    @Delete
    fun delete(todo: Todo)

    @Query("SELECT * FROM todo")
    fun getAllTodos(): List<Todo>

    @Query("SELECT * FROM todo")
    fun getAllTodosLive(): LiveData<List<Todo>>
}
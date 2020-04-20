package com.jyyoon.room_exam_kotlin

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.jyyoon.room_exam.Todo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(application: Application): AndroidViewModel(application) {
    private val db = Room.databaseBuilder(
        application,
        AppDatabase::class.java, "todo-db"
    ).build()

    var todos: LiveData<List<Todo>>
    var newTodo: String? = null

    init {
        todos = getAll()
    }

    fun getAll(): LiveData<List<Todo>> {
        return db.todoDao().getAll()
    }

    fun insert(todo: Todo) {
        viewModelScope.launch(Dispatchers.IO) {
            db.todoDao().insert(todo)
        }
    }
}
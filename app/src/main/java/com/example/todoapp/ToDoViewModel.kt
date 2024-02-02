package com.example.todoapp

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ToDoViewModel {

    private var currentEditPosition by mutableStateOf(-1)

    val currentEditItem:Data?
        get()=todoItems.getOrNull(currentEditPosition)

    fun onEditItemSelected(item: Data){
        currentEditPosition=todoItems.indexOf(item)
    }

    fun onEditDone(){
        currentEditPosition=-1
    }

    fun onEditItemChange(item: Data){
        val currentItem= requireNotNull(currentEditItem)
        require(currentItem.id==item.id){

        }
        todoItems[currentEditPosition]=item
    }

    var todoItems = mutableStateListOf<Data>()

    fun addItems(item:Data){
       todoItems.add(item)
    }
    fun removeItem(item:Data){
      todoItems.remove(item)
    }
}
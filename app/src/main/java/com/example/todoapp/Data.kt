package com.example.todoapp

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Done
import androidx.compose.ui.graphics.vector.ImageVector
import java.util.UUID



enum class ToDoIcons(val imageVector: ImageVector, @StringRes contentDescription:Int){
    Square(Icons.Default.Create, R.string.square),
    Done(Icons.Default.Done, R.string.done),
    Event(Icons.Default.Done, R.string.event),
   Privacy(Icons.Default.Done, R.string.privacy),
    Trash(Icons.Default.Done, R.string.trash);

    companion object{
        val Default=Square
    }
}

class Data(
    val task: String,
    val icons: ToDoIcons=ToDoIcons.Default,
    val id:UUID=UUID.randomUUID()
    ) {
    fun copy(icons: ToDoIcons, task: String): Data {
        TODO("Not yet implemented")
    }

    fun copy(task: String): Data {
        TODO("Not yet implemented")
    }

    fun copy(icons: ToDoIcons, task: ToDoIcons): Data {
        TODO("Not yet implemented")
    }
}
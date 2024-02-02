package com.example.todoapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import com.example.todoapp.ui.theme.ToDoAppTheme

class MainActivity : ComponentActivity() {

    private val toDoViewModel by viewModels<ViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ToDoAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    color = MaterialTheme.colorScheme.background) {
                    ToDoScreenActivity(toDoViewModel=toDoViewModel)
                     }
                }
            }
        }

    private fun ToDoScreenActivity(toDoViewModel: ViewModel) {

    }
}

@Composable
private fun ToDoScreenActivity(
    toDoViewModel: ToDoViewModel
) {
    Screen(
        items = toDoViewModel.todoItems, onAddItem = toDoViewModel::addItems,
    onRemoveItem = toDoViewModel::removeItem,
    currentEditing =toDoViewModel.currentEditItem,
        onStartEdit = toDoViewModel::onEditItemSelected,
        onEditItemChange = toDoViewModel::onEditItemChange,
        onEditDone = toDoViewModel::onEditDone)
}

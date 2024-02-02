package com.example.todoapp

fun RandomData():Data{
    val items= listOf(
        "Learn Compose",
        "Learn Ui",
        "State in Compose",
        "Learn ViewModel"
    ).random()

    val icons = ToDoIcons.values().random()

    return Data(items, icons)

}
package com.example.todoapp

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import kotlin.random.Random
import kotlin.reflect.KFunction0
import kotlin.reflect.KFunction1

@Composable
fun Screen(
    items: List<Data>,
    onAddItem: (Data) -> Unit,
    onRemoveItem: (Data) -> Unit,
    currentEditing: Data?,
    onStartEdit: KFunction1<Data, Unit>,
    onEditItemChange: KFunction1<Data, Unit>,
    onEditDone: KFunction0<Unit>
) {
    Column {
        TodoItemInputBackGround(elevate = true, modifier = Modifier.fillMaxWidth()) {
            TodoInput(onItemComplete = onAddItem)
        }
        LazyColumn(modifier = Modifier.weight(1f),
        contentPadding = PaddingValues(top = 8.dp)
        ){
            items(items=items){
                ToDoRow(todo = it,
                    onItemClick = {onRemoveItem(it)} ,
                    modifier = Modifier.fillParentMaxWidth() 
                )
            }
        }
Button(onClick = { onAddItem(RandomData()) },
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
        ) {
            Text(text = "Add an Item")
            }
        }
    }

@Composable
fun ToDoRow(
    todo:Data,
    onItemClick:(Data)->Unit,
    modifier: Modifier
){
    Row(modifier = modifier
        .clickable { onItemClick(todo) }
        .padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween)
    {
        Text(todo.task )
        val iconAlpha:Float= remember(todo.id){
            RandomTint()
        }
        Icon(imageVector = todo.icons.imageVector,
            tint= LocalContentColor.current.copy(alpha = iconAlpha),
            contentDescription = stringResource(id = todo.icons.ordinal))
    }
}

@Composable
fun TodoInput(onItemComplete:(Data)->Unit){

    val (text,setText)=remember{
        mutableStateOf("")
    }

    val(icon,setIcon)= remember {
        mutableStateOf(ToDoIcons.Default)
    }

    val iconVisible=text.isNotBlank()

    val submit={onItemComplete(Data(text,icon))
        setIcon(ToDoIcons.Default)}

    TodoItemInput(text=text,
        onTextChange=setText,
        submit=submit,
        iconVisible=iconVisible,
        icon=icon,
        onIconChange=setIcon)
}

fun TodoItemInput(
    text: String,
    onTextChange: (String) -> Unit,
    submit: () -> Unit,
    iconVisible: Boolean,
    icon: ToDoIcons,
    onIconChange: (ToDoIcons) -> Unit
) {
    TODO("Not yet implemented")
}

@Composable
fun TodoItemInput (
    text: String,
    setText: (String) -> Unit,
    submit: () -> Unit,
    iconVisible: Boolean,
    icon: ToDoIcons,
    setIcon: (ToDoIcons) -> Unit,
    onTextChange: (String) -> Unit,
    onIconChange: (ToDoIcons) -> Unit
) {
    Column {
        Row(
            Modifier
                .padding(horizontal = 16.dp)
                .padding(top = 8.dp)
        ) {
            TodoInputText(
                text = text,
                onTextChange = setText,
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp),
                onImeAction = submit
            )

            TodoEditButton(
                onClick = submit, text = "Add",
                enabled = text.isNotBlank(),
                modifier = Modifier.align(Alignment.CenterVertically)
            )
        }
        if (iconVisible) {
            IconRow(icon, setIcon, Modifier.padding(top = 16.dp))
        } else {
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

fun RandomTint(): Float{

    return Random.nextFloat().coerceIn(.3f,.7f)

}
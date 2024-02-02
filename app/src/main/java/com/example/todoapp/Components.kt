package com.example.todoapp

import androidx.annotation.StringRes
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialogDefaults.shape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp



@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
@Composable
fun TodoInputText(
    text:String,
    onTextChange: (String)->Unit,
    modifier: Modifier,
    onImeAction: () -> Unit={}
){
    val keyBoardController=LocalSoftwareKeyboardController.current

    TextField(value = text, onValueChange = onTextChange,
    maxLines = 1, keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
        keyboardActions = KeyboardActions(onDone = {
            onImeAction()
            keyBoardController?.hide()
        }),
        modifier = modifier
    )
}

@Composable
fun TodoEditButton(
    onClick:() -> Unit,
    text:String,
    modifier: Modifier=Modifier,
    enabled:Boolean=true
){
    TextButton(onClick = onClick,
    shape = CircleShape, enabled=enabled, modifier = modifier) {
        Text(text)
    }
}

@Composable
fun TodoItemInputBackGround(
    elevate:Boolean,
    modifier: Modifier=Modifier,
    content:@Composable RowScope.() -> Unit
){
    val animatedElevation by animateDpAsState(if (elevate) 1.dp else 0.dp,TweenSpec(500))
    Surface(color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.05f),
    shape = RectangleShape){
        Row(modifier = modifier.animateContentSize(TweenSpec(300)),
        content=content)
    }
}

@Composable
fun SelectableButton(
    icon:ImageVector,
    @StringRes iconContentDescription:Int,
    onIconsSelected:()->Unit,
    isSelected:Boolean,
    modifier:Modifier=Modifier
)
{
    val tint=if (isSelected){
        MaterialTheme.colorScheme.primary
    }else{
        MaterialTheme.colorScheme.onSurface.copy(.6f)
    }
    TextButton(onClick = { onIconsSelected() },
    shape= RectangleShape,
    modifier = Modifier) {
        Column {

            Icon(
                imageVector = icon,
                contentDescription = stringResource(id = iconContentDescription)
            )
            if (isSelected) {
                Box(
                    Modifier
                        .padding(top = 3.dp)
                        .width(icon.defaultWidth)
                        .height(1.dp)
                        .background(tint)
                )
            } else {
                Spacer(modifier = Modifier.height(4.dp))
            }
        }
    }
}
@Composable
fun IconRow(
    icon:ToDoIcons,
    onIconChange:(ToDoIcons)->Unit,
    modifier:Modifier=Modifier
){
    Row(modifier){
        for(todoIcons in ToDoIcons.values()){
            SelectableButton(
                icon = todoIcons.imageVector,
                iconContentDescription = todoIcons.ordinal,
                onIconsSelected = { onIconChange(todoIcons) },
                isSelected = todoIcons==icon
            )
        }
    }
}
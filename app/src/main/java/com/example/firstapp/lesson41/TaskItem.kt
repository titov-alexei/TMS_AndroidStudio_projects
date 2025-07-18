package com.example.firstapp.lesson41

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun TaskItem(modifier: Modifier = Modifier,
             items : Task) {
    Row(modifier = modifier.padding(vertical = 40.dp)) {
        Text(items.title)
        Checkbox(items.isCheck, null)
    }

}

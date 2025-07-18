package com.example.firstapp.lesson41

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController


@Composable
fun TaskList(navController: NavController) {
    val tasks: List<Task> = listOf(
        Task(1, "Task 1", "Detail 1", false),
        Task(2, "Task 2", "Detail 2"),
        Task(3, "Task 3", "Detail 3", true),
        Task(4, "Task 4", "Detail 4"),
        Task(5, "Task 5", "Detail 5"),
        Task(6, "Task 6", "Detail 6", true),
        Task(7, "Task 7", "Detail 7", true),
        Task(8, "Task 8", "Detail 8"),
        Task(9, "Task 9", "Detail 9"),
        Task(10, "Task 10", "Detail 10", true),
        Task(11, "Task 11", "Detail 11"),
        Task(12, "Task 12", "Detail 12", true),
    )

    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        LazyColumn(Modifier.padding(innerPadding)) {
            items(tasks, key = {
                it.id
            }) { item ->
                TaskItem(
                    Modifier
                        .padding(innerPadding)
                        .clickable { navController.navigate("TaskList/${item.details}") },
                    item
                )
            }

        }
    }
}
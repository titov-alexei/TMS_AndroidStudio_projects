package com.example.firstapp.lesson41

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController


@Composable
fun AppNavigation() {
    val navigationController = rememberNavController()

    NavHost(
        navController = navigationController,
        startDestination = "TaskList"
    ) {
        composable("TaskList") {
            TaskList(navigationController)
        }
        composable("TaskList/{details}") { backStackEntry ->
            val detail = backStackEntry.arguments?.getString("details").toString()
            Details(detail = detail, navController = navigationController)
        }
    }

}
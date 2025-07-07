package com.example.firstapp.lesson40


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.firstapp.R
import com.example.firstapp.lesson40.ui.theme.FirstAppTheme

@Composable
fun Profile(modifier : Modifier) {
    var status by remember { mutableStateOf(true) }
    var textStatus by remember { mutableStateOf("online") }
    var clickLike by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .background(Color.LightGray, shape = RoundedCornerShape(20.dp))
            .padding(10.dp)
    ) {
        Row {
            Image(
                contentDescription = "Avatar",
                painter = painterResource(R.drawable.ic_launcher_background),
                modifier = Modifier
                    .clip(RoundedCornerShape(20.dp))
                    .shadow(10.dp)
                //shape = RoundedCornerShape(20.dp)

            )
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "Alexei Titov", fontSize = 30.sp)
                Text(text = textStatus,
                    color = if(status) Color.Green else Color.Red
                )
                Text(text = "Status", color = Color.DarkGray)
            }
        }

        Row(horizontalArrangement = Arrangement.End,
            modifier = Modifier.fillMaxWidth()) {
            Icon(
                modifier = Modifier
                    .size(40.dp)
                    .clickable{
                         clickLike = !clickLike
                    },
                contentDescription = "Likes",
                imageVector = if (clickLike) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder
            )
            Icon(
                modifier = Modifier
                    .size(40.dp),
                contentDescription = "Send message",
                imageVector = Icons.Filled.Email
            )
            Icon(
                modifier = Modifier
                    .size(40.dp),
                contentDescription = "Add to friends",
                imageVector = Icons.Filled.Face
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfilePreview() {
    FirstAppTheme {
        Profile(modifier = Modifier)
    }
}
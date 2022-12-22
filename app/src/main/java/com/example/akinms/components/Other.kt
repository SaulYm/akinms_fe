package com.example.akinms.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun Other(navController: NavHostController){
    Box(
        modifier = Modifier
            .padding(start = 25.dp, end = 25.dp, top = 13.dp, bottom = 8.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .height(180.dp)
            .background(Color(0xFFEF476F))
    ){

    }
    Box(
        modifier = Modifier
            .padding(start = 25.dp, end = 25.dp, top = 13.dp, bottom = 8.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .height(180.dp)
            .background(Color(0xFFEF476F))
    ){

    }
}
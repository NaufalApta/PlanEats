package com.myapplication.planeats.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.myapplication.planeats.ui.navigation.Screen
import com.myapplication.planeats.ui.theme.ButtonGreen
import com.myapplication.planeats.ui.theme.PaleGreenBg
import com.myapplication.planeats.ui.theme.TextDark
import com.myapplication.planeats.ui.theme.TextGray
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {
    var selectedCategory by remember { mutableStateOf("All") }
    val categories = listOf("All", "Breakfast", "Lunch", "Dinner", "Snack")
    var currentTab by remember { mutableStateOf("Home") }
    val currentDate = remember {
        val formatter = SimpleDateFormat("EEEE, d MMMM yyyy", Locale.getDefault())
        formatter.format(Date())
    }

    Scaffold(
        // FAB
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate(Screen.AddMeal.route) },
                containerColor = ButtonGreen,
                contentColor = Color.White,
                shape = CircleShape,
                modifier = Modifier
                    .size(50.dp)
                    .offset(y = 60.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add Meal",
                    modifier = Modifier.size(32.dp)
                )
            }
        },
        floatingActionButtonPosition = FabPosition.Center,

        // Bottom Bar
        bottomBar = {
            BottomAppBar(
                containerColor = Color.White,
                tonalElevation = 10.dp,
                modifier = Modifier.height(80.dp),
                contentPadding = PaddingValues(0.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        modifier = Modifier.weight(1f),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        BottomNavItem(
                            icon = Icons.Default.Home,
                            label = "Home",
                            isSelected = currentTab == "Home",
                            onClick = { currentTab = "Home" }
                        )
                        BottomNavItem(
                            icon = Icons.Default.CalendarToday,
                            label = "Planner",
                            isSelected = currentTab == "Planner",
                            onClick = {
                                currentTab = "Planner"
                                navController.navigate(Screen.Planner.route)
                            }
                        )
                    }

                    Spacer(modifier = Modifier.width(72.dp))

                    Row(
                        modifier = Modifier.weight(1f),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        BottomNavItem(
                            icon = Icons.Default.EmojiEvents,
                            label = "Achievement",
                            isSelected = currentTab == "Achievement",
                            onClick = {
                                currentTab = "Achievement"
                                navController.navigate(Screen.Achievement.route)
                            }
                        )
                        BottomNavItem(
                            icon = Icons.Default.Person,
                            label = "Profile",
                            isSelected = currentTab == "Profile",
                            onClick = {
                                currentTab = "Profile"
                                navController.navigate(Screen.Profile.route)
                            }
                        )
                    }
                }
            }
        }
    ) { paddingValues ->
        // ISI KONTEN
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF9F9F9))
                .padding(paddingValues)
                .padding(horizontal = 24.dp)
        ) {
            Spacer(modifier = Modifier.height(24.dp))

            Text(text = "Hello, Joseph!", fontSize = 16.sp, color = TextGray)

            // MENGGUNAKAN TANGGAL DINAMIS
            Text(
                text = currentDate,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = TextDark
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Chips Kategori
            LazyRow(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                items(categories) { category ->
                    val isSelected = selectedCategory == category
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(100))
                            .background(if (isSelected) ButtonGreen else Color.White)
                            .clickable { selectedCategory = category }
                            .padding(horizontal = 20.dp, vertical = 12.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = category,
                            color = if (isSelected) Color.White else TextDark,
                            fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Normal,
                            fontSize = 14.sp
                        )
                    }
                }
            }

            // Empty State
            Box(modifier = Modifier.weight(1f).fillMaxWidth(), contentAlignment = Alignment.Center) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Box(
                        modifier = Modifier.size(140.dp).background(PaleGreenBg, CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(Icons.Default.Restaurant, null, tint = ButtonGreen, modifier = Modifier.size(56.dp))
                    }
                    Spacer(modifier = Modifier.height(24.dp))
                    Text("There is no healthy menu yet.", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = TextDark)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("Add meal now!", fontSize = 16.sp, color = TextGray)
                }
            }
        }
    }
}

@Composable
fun BottomNavItem(
    icon: ImageVector,
    label: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val contentColor = if (isSelected) ButtonGreen else TextGray

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) { onClick() }
            .padding(8.dp)
            .width(60.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = label,
            tint = contentColor,
            modifier = Modifier.size(26.dp)
        )
        Spacer(modifier = Modifier.height(6.dp))
        Text(
            text = label,
            fontSize = 10.sp,
            color = contentColor,
            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
            maxLines = 1
        )
    }
}
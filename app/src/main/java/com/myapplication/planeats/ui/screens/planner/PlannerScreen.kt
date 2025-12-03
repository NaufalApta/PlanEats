package com.myapplication.planeats.ui.screens.planner

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.myapplication.planeats.ui.navigation.Screen
import com.myapplication.planeats.ui.theme.ButtonGreen
import com.myapplication.planeats.ui.theme.TextDark
import com.myapplication.planeats.ui.theme.TextGray

data class PlannedMeal(
    val title: String,
    val time: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlannerScreen(navController: NavController) {
    var currentTab by remember { mutableStateOf("Planner") }

    val weeklyPlan = mapOf(
        "Monday" to listOf(
            PlannedMeal("Avocado Toast", "08:00 AM"),
            PlannedMeal("Grilled Chicken Salad", "01:00 PM"),
            PlannedMeal("Berry Yoghurt Bowl", "04:00 PM"),
            PlannedMeal("Salmon with Quinoa", "07:00 PM")
        ),
        "Tuesday" to emptyList(),
        "Wednesday" to emptyList(),
        "Thursday" to emptyList(),
        "Friday" to emptyList(),
        "Saturday" to emptyList(),
        "Sunday" to emptyList()
    )

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        "Meal Planner",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        color = TextDark
                    )
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color(0xFFF9F9F9)
                )
            )
        },
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
                Icon(Icons.Default.Add, contentDescription = "Add Meal", modifier = Modifier.size(32.dp))
            }
        },
        floatingActionButtonPosition = FabPosition.Center,
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
                    Row(modifier = Modifier.weight(1f), horizontalArrangement = Arrangement.SpaceEvenly) {
                        BottomNavItem(
                            icon = Icons.Default.Home, label = "Home",
                            isSelected = currentTab == "Home",
                            onClick = {
                                currentTab = "Home"
                                navController.navigate(Screen.Home.route) {
                                    popUpTo(Screen.Home.route) { inclusive = true }
                                }
                            }
                        )
                        BottomNavItem(
                            icon = Icons.Default.CalendarToday, label = "Planner",
                            isSelected = currentTab == "Planner",
                            onClick = { /* Sudah di Planner */ }
                        )
                    }
                    Spacer(modifier = Modifier.width(72.dp))
                    Row(modifier = Modifier.weight(1f), horizontalArrangement = Arrangement.SpaceEvenly) {
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
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF9F9F9))
                .padding(paddingValues)
                .padding(horizontal = 24.dp),
            contentPadding = PaddingValues(bottom = 100.dp)
        ) {
            items(weeklyPlan.toList()) { (day, meals) ->

                DayPlanSection(day, meals, navController)
                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}

@Composable
fun DayPlanSection(day: String, meals: List<PlannedMeal>, navController: NavController) {
    Column {
        Text(
            text = day,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            color = TextDark,
            modifier = Modifier.padding(bottom = 12.dp)
        )

        Card(
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                if (meals.isEmpty()) {
                    Text(
                        text = "No meals planned",
                        color = TextGray,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 12.dp),
                        textAlign = TextAlign.Center
                    )
                } else {
                    meals.forEachIndexed { index, meal ->
                        MealRowItem(meal, onClick = {
                            navController.navigate(Screen.MealDetail.route)
                        })

                        if (index < meals.size - 1) {
                            Spacer(modifier = Modifier.height(12.dp))
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun MealRowItem(meal: PlannedMeal, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(Color(0xFFF5F7F9))
            .clickable { onClick() }
            .padding(horizontal = 16.dp, vertical = 14.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = meal.title,
            fontWeight = FontWeight.Medium,
            color = TextDark,
            fontSize = 14.sp
        )
        Text(
            text = meal.time,
            color = TextGray,
            fontSize = 12.sp
        )
    }
}

@Composable
fun BottomNavItem(
    icon: ImageVector, label: String, isSelected: Boolean, onClick: () -> Unit
) {
    val contentColor = if (isSelected) ButtonGreen else TextGray
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .clickable(indication = null, interactionSource = remember { MutableInteractionSource() }) { onClick() }
            .padding(8.dp)
            .width(60.dp)
    ) {
        Icon(imageVector = icon, contentDescription = label, tint = contentColor, modifier = Modifier.size(26.dp))
        Spacer(modifier = Modifier.height(6.dp))
        Text(text = label, fontSize = 10.sp, color = contentColor, fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium, maxLines = 1)
    }
}
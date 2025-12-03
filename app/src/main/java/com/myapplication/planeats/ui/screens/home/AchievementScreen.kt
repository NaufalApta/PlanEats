package com.myapplication.planeats.ui.screens.home

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocalFireDepartment
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.myapplication.planeats.R
import com.myapplication.planeats.ui.navigation.Screen
import com.myapplication.planeats.ui.theme.ButtonGreen
import com.myapplication.planeats.ui.theme.TextDark
import com.myapplication.planeats.ui.theme.TextGray
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun AchievementScreen(navController: NavController) {
    val context = LocalContext.current
    var currentTab by remember { mutableStateOf("Achievement") }

    var streakCount by remember { mutableStateOf(0) }
    var currentXP by remember { mutableStateOf(1250f) }
    val maxXP = 1500f

    val progress = (currentXP / maxXP).coerceIn(0f, 1f)

    LaunchedEffect(Unit) {
        val prefs = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
        val today = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())

        // 1. LOGIKA STREAK
        val lastLoginDate = prefs.getString("last_login_date", "")
        var savedStreak = prefs.getInt("streak_count", 0)

        if (lastLoginDate != today) {
            savedStreak += 1
            with(prefs.edit()) {
                putString("last_login_date", today)
                putInt("streak_count", savedStreak)
                apply()
            }
        }
        streakCount = savedStreak

        // 2. LOGIKA HITUNG TOTAL XP
        var calculatedXP = 1250f

        val lastMealDate = prefs.getString("last_meal_added_date", "")
        val dailyCount = if(lastMealDate == today) prefs.getInt("daily_meal_count", 0) else 0
        val weeklyCount = prefs.getInt("weekly_meal_days", 0)

        // a. Challenge Streak (+5 XP)
        calculatedXP += 5f

        // b. Challenge Add Meal (+15 XP)
        if (dailyCount >= 4) {
            calculatedXP += 15f
        }

        // c. Challenge Weekly (+50 XP)
        if (weeklyCount >= 7) {
            calculatedXP += 50f
        }

        currentXP = calculatedXP
    }

    Scaffold(
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
                Row(modifier = Modifier.fillMaxSize(), verticalAlignment = Alignment.CenterVertically) {
                    // Kiri
                    Row(modifier = Modifier.weight(1f), horizontalArrangement = Arrangement.SpaceEvenly) {
                        AchievementBottomItem(Icons.Default.Home, "Home", currentTab == "Home") {
                            navController.navigate(Screen.Home.route)
                        }
                        AchievementBottomItem(Icons.Default.CalendarToday, "Planner", currentTab == "Planner") {
                            navController.navigate(Screen.Planner.route)
                        }
                    }
                    Spacer(modifier = Modifier.width(72.dp))
                    // Kanan
                    Row(modifier = Modifier.weight(1f), horizontalArrangement = Arrangement.SpaceEvenly) {
                        AchievementBottomItem(Icons.Default.EmojiEvents, "Achievement", currentTab == "Achievement") {
                        }
                        AchievementBottomItem(Icons.Default.Person, "Profile", currentTab == "Profile") {
                            navController.navigate(Screen.Profile.route)
                        }
                    }
                }
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF9F9F9))
                .padding(paddingValues)
                .padding(horizontal = 24.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Achievement",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = TextDark,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(24.dp))

            // 1. LEVEL CARD
            Card(
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(0.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(20.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Image(
                            painter = painterResource(id = R.drawable.joseph),
                            contentDescription = "Profile",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .size(56.dp)
                                .clip(CircleShape)
                                .background(Color.LightGray)
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                        Column {
                            Text("Hello, Joseph!", fontSize = 14.sp, color = TextGray)
                            Text("Level 1", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = TextDark)
                        }
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Default.Star, null, tint = ButtonGreen, modifier = Modifier.size(16.dp))
                            Spacer(modifier = Modifier.width(4.dp))
                            Text("XP", color = ButtonGreen, fontWeight = FontWeight.Bold, fontSize = 14.sp)
                        }
                        Text(
                            text = "${currentXP.toInt()} / ${maxXP.toInt()}",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = TextDark
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    LevelProgressBar(
                        progress = progress,
                        modifier = Modifier.fillMaxWidth().height(10.dp),
                        color = ButtonGreen,
                        trackColor = Color(0xFFE0E0E0)
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // 2. STREAK CARD
            Card(
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(20.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.LocalFireDepartment,
                                contentDescription = "Streak",
                                tint = ButtonGreen,
                                modifier = Modifier.size(32.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text("Streak", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = TextDark)
                        }
                        Text(
                            text = streakCount.toString(),
                            fontSize = 32.sp,
                            fontWeight = FontWeight.Bold,
                            color = ButtonGreen
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Keep it up! Complete your meals daily to grow your streak.",
                        color = TextGray,
                        fontSize = 14.sp,
                        lineHeight = 20.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // 3. CHALLENGE CARD
            Card(
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { navController.navigate(Screen.Challenge.route) }
            ) {
                Column(modifier = Modifier.padding(20.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.EmojiEvents,
                            contentDescription = "Challenge",
                            tint = ButtonGreen,
                            modifier = Modifier.size(28.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Challenge", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = TextDark)
                    }

                    Spacer(modifier = Modifier.height(8.dp))
                    Text("Complete challenges to level up!", fontSize = 14.sp, fontWeight = FontWeight.SemiBold, color = TextDark)

                    Spacer(modifier = Modifier.height(16.dp))

                    ChallengeItem("Turn on Daily Streak", "+5 XP")
                    ChallengeItem("Add a Meal", "+15 XP")
                    ChallengeItem("Weekly Meal Builder", "+50 XP")
                }
            }

            Spacer(modifier = Modifier.height(100.dp))
        }
    }
}

@Composable
fun LevelProgressBar(
    progress: Float,
    modifier: Modifier = Modifier,
    color: Color,
    trackColor: Color
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(100))
            .background(trackColor)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth(progress)
                .fillMaxHeight()
                .background(color)
        )
    }
}

@Composable
fun ChallengeItem(title: String, xp: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(title, color = TextGray, fontSize = 14.sp)
        Text(xp, color = ButtonGreen, fontWeight = FontWeight.Bold, fontSize = 14.sp)
    }
}

@Composable
private fun AchievementBottomItem(icon: ImageVector, label: String, isSelected: Boolean, onClick: () -> Unit) {
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
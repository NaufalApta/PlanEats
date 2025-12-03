package com.myapplication.planeats.ui.screens.home

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.LocalFireDepartment
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.myapplication.planeats.ui.theme.ButtonGreen
import com.myapplication.planeats.ui.theme.TextDark
import com.myapplication.planeats.ui.theme.TextGray
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChallengeScreen(navController: NavController) {
    val context = LocalContext.current

    var isDailyStreakDone by remember { mutableStateOf(false) }
    var dailyMealCount by remember { mutableStateOf(0) }
    var weeklyDayCount by remember { mutableStateOf(0) }

    LaunchedEffect(Unit) {
        val prefs = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
        val today = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())
        val lastLogin = prefs.getString("last_login_date", "")
        val lastMealDate = prefs.getString("last_meal_added_date", "")

        // 1. Cek Streak
        isDailyStreakDone = (lastLogin == today)

        // 2. Cek Meal Harian
        if (lastMealDate == today) {
            dailyMealCount = prefs.getInt("daily_meal_count", 0)
        } else {
            dailyMealCount = 0
        }

        // 3. Cek Weekly
        weeklyDayCount = prefs.getInt("weekly_meal_days", 0)
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Challenge", fontWeight = FontWeight.SemiBold) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = Color(0xFFF9F9F9))
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF9F9F9))
                .padding(padding)
                .padding(horizontal = 24.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            Text("Daily Challenge", fontWeight = FontWeight.Bold, fontSize = 18.sp, color = TextDark)
            Spacer(modifier = Modifier.height(16.dp))


            ChallengeCard(
                icon = Icons.Default.LocalFireDepartment,
                title = "Turn on Daily Streak",
                description = "Login or open the app at least once.",
                xp = "+5 XP",
                current = if (isDailyStreakDone) 1 else 0,
                target = 1,
                color = Color(0xFFE8F5E9)
            )

            Spacer(modifier = Modifier.height(16.dp))

            ChallengeCard(
                icon = Icons.Default.Restaurant,
                title = "Add a Meal",
                description = "Add 4 meal menus today.",
                xp = "+15 XP",
                current = dailyMealCount,
                target = 4,
                color = Color(0xFFE8F5E9)
            )

            Spacer(modifier = Modifier.height(32.dp))

            Text("Weekly Challenge", fontWeight = FontWeight.Bold, fontSize = 18.sp, color = TextDark)
            Spacer(modifier = Modifier.height(16.dp))

            ChallengeCard(
                icon = Icons.Default.CalendarToday,
                title = "Weekly Meal Builder",
                description = "Add meals every day for 1 week.",
                xp = "+50 XP",
                current = weeklyDayCount,
                target = 7,
                color = Color(0xFFE8F5E9)
            )

            Spacer(modifier = Modifier.height(50.dp))
        }
    }
}

@Composable
fun ChallengeCard(
    icon: ImageVector,
    title: String,
    description: String,
    xp: String,
    current: Int,
    target: Int,
    color: Color
) {
    val safeCurrent = if (current > target) target else current
    val progress = safeCurrent.toFloat() / target.toFloat()

    Card(
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(0.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Row(verticalAlignment = Alignment.Top) {
                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .background(color, CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(icon, contentDescription = null, tint = ButtonGreen, modifier = Modifier.size(24.dp))
                }

                Spacer(modifier = Modifier.width(16.dp))

                Column {
                    Text(title, fontWeight = FontWeight.Bold, fontSize = 16.sp, color = TextDark)
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(description, color = TextGray, fontSize = 13.sp, lineHeight = 18.sp)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Badge XP dan Counter
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Surface(
                    color = Color(0xFFFFF3E0),
                    shape = RoundedCornerShape(100)
                ) {
                    Text(
                        text = xp,
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                        color = Color(0xFFE65100),
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp
                    )
                }

                Text(
                    text = "$safeCurrent / $target",
                    color = TextGray,
                    fontWeight = FontWeight.Medium,
                    fontSize = 14.sp
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            ChallengeProgressBar(progress = progress)
        }
    }
}

@Composable
fun ChallengeProgressBar(progress: Float) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(8.dp)
            .clip(RoundedCornerShape(100))
            .background(Color(0xFFE8F5E9))
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth(progress)
                .fillMaxHeight()
                .background(ButtonGreen)
        )
    }
}
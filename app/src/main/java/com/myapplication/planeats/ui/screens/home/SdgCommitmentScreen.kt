package com.myapplication.planeats.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.myapplication.planeats.ui.theme.TextDark
import com.myapplication.planeats.ui.theme.TextGray

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SdgCommitmentScreen(navController: NavController) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        "Our Commitment to SDG 3",
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 18.sp
                    )
                },
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

            Text(
                text = "Sustainable Development Goal 3 focuses on ensuring healthy lives and promoting well-being for everyone. At PlanEats, we support this mission by empowering users to build healthier eating habits through simple and accessible meal planning.",
                fontSize = 14.sp,
                color = TextGray,
                lineHeight = 22.sp
            )

            Spacer(modifier = Modifier.height(24.dp))

            //SECTION 1
            SdgSectionHeader(emoji = "🥗", title = "Promoting Healthier Eating Habits")
            Text(
                text = "PlanEats helps users plan healthy meals every day.",
                fontSize = 14.sp,
                color = TextGray,
                lineHeight = 22.sp
            )

            Spacer(modifier = Modifier.height(24.dp))

            //SECTION 2
            SdgSectionHeader(emoji = "📅", title = "Forming Good Habits through Daily & Weekly Planners")
            Text(
                text = "Structured meal planning:",
                fontSize = 14.sp,
                color = TextGray,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            BulletPoints(
                items = listOf(
                    "Reducing impulsive decisions",
                    "Supporting long-term health management"
                )
            )

            Spacer(modifier = Modifier.height(24.dp))

            //SECTION 3
            SdgSectionHeader(emoji = "🎮", title = "Gamification to Increase Motivation")
            Text(
                text = "To keep your healthy lifestyle enjoyable, PlanEats presents:",
                fontSize = 14.sp,
                color = TextGray,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            BulletPoints(
                items = listOf(
                    "Leveling system",
                    "Daily challenges",
                    "Weekly challenge"
                )
            )

            Spacer(modifier = Modifier.height(24.dp))

            //SECTION 4
            SdgSectionHeader(emoji = "🌿", title = "Building Sustainable Habits")
            Text(
                text = "PlanEats is not just an app, but a companion tool that helps build healthy, consistent, and sustainable habits.",
                fontSize = 14.sp,
                color = TextGray,
                lineHeight = 22.sp
            )

            Spacer(modifier = Modifier.height(24.dp))

            //SECTION 5
            SdgSectionHeader(emoji = "❤️", title = "Together, let’s build a healthier lifestyle.")
            Text(
                text = "With PlanEats, every small decision—such as choosing a healthy meal—is a tangible step toward the global goal of health and well-being.",
                fontSize = 14.sp,
                color = TextGray,
                lineHeight = 22.sp
            )

            Spacer(modifier = Modifier.height(50.dp))
        }
    }
}

@Composable
fun SdgSectionHeader(emoji: String, title: String) {
    Row(
        verticalAlignment = Alignment.Top,
        modifier = Modifier.padding(bottom = 8.dp)
    ) {
        Text(text = emoji, fontSize = 18.sp)
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = title,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            color = TextDark
        )
    }
}

@Composable
fun BulletPoints(items: List<String>) {
    Column {
        items.forEach { item ->
            Row(
                modifier = Modifier.padding(bottom = 4.dp),
                verticalAlignment = Alignment.Top
            ) {
                Text(
                    text = "•",
                    fontSize = 14.sp,
                    color = TextGray,
                    modifier = Modifier.padding(horizontal = 8.dp)
                )
                Text(
                    text = item,
                    fontSize = 14.sp,
                    color = TextGray,
                    lineHeight = 20.sp
                )
            }
        }
    }
}
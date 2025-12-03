package com.myapplication.planeats.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.Description
import androidx.compose.material.icons.outlined.Eco
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Feedback
import androidx.compose.material.icons.outlined.Gavel
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Security
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
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
import com.myapplication.planeats.ui.theme.TextDark
import com.myapplication.planeats.ui.theme.TextGray

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutSupportScreen(navController: NavController) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("About & Support", fontWeight = FontWeight.SemiBold) },
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

            SectionTitle("About PlanEats")
            AboutSectionCard {
                // Item 1: SDG 3
                AboutItemRow(
                    icon = Icons.Outlined.Eco,
                    title = "Our Commitment to SDG 3",
                    iconColor = Color(0xFF4CAF50),
                    iconBgColor = Color(0xFFE8F5E9),
                    onClick = {

                        navController.navigate(Screen.SdgCommitment.route)
                    }
                )
                HorizontalDivider(color = Color(0xFFF0F0F0))

                // Item 2: Version
                AboutItemRow(
                    icon = Icons.Outlined.Info,
                    title = "Version",
                    iconColor = Color(0xFF4CAF50),
                    iconBgColor = Color(0xFFE8F5E9),
                    endText = "1.0.0",
                    onClick = { }
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            SectionTitle("Help & Support")
            AboutSectionCard {
                // Item 1: Contact Us
                AboutItemRow(
                    icon = Icons.Outlined.Email,
                    title = "Contact Us",
                    iconColor = Color(0xFF4CAF50),
                    iconBgColor = Color(0xFFE8F5E9),
                    onClick = {
                        navController.navigate(Screen.ContactUs.route)
                    }
                )
                HorizontalDivider(color = Color(0xFFF0F0F0))

                // Item 2: Send Feedback
                AboutItemRow(
                    icon = Icons.Outlined.Feedback,
                    title = "Send Feedback",
                    iconColor = Color(0xFFF57C00),
                    iconBgColor = Color(0xFFFFF3E0),
                    onClick = {
                        navController.navigate(Screen.SendFeedback.route)
                    }
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            SectionTitle("Legal")
            AboutSectionCard {
                // Item 1: Privacy Policy
                AboutItemRow(
                    icon = Icons.Outlined.Security,
                    title = "Privacy Policy",
                    iconColor = Color(0xFF4CAF50),
                    iconBgColor = Color(0xFFE8F5E9),
                    onClick = {
                        navController.navigate(Screen.PrivacyPolicy.route)
                    }
                )
                HorizontalDivider(color = Color(0xFFF0F0F0))

                // Item 2: Terms of Service
                AboutItemRow(
                    icon = Icons.Outlined.Gavel,
                    title = "Terms of Service",
                    iconColor = Color(0xFF4CAF50),
                    iconBgColor = Color(0xFFE8F5E9),
                    onClick = {
                        navController.navigate(Screen.TermsOfService.route)
                    }
                )
            }

            Spacer(modifier = Modifier.height(50.dp))
        }
    }
}

@Composable
fun SectionTitle(text: String) {
    Text(
        text = text,
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold,
        color = TextDark,
        modifier = Modifier.padding(bottom = 12.dp)
    )
}

@Composable
fun AboutSectionCard(content: @Composable ColumnScope.() -> Unit) {
    Card(
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(0.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(horizontal = 4.dp)) {
            content()
        }
    }
}

@Composable
fun AboutItemRow(
    icon: ImageVector,
    title: String,
    iconColor: Color,
    iconBgColor: Color,
    endText: String? = null,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(horizontal = 16.dp, vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Icon Box
        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(iconBgColor),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = iconColor,
                modifier = Modifier.size(20.dp)
            )
        }

        Spacer(modifier = Modifier.width(16.dp))

        // Title
        Text(
            text = title,
            fontSize = 16.sp,
            color = TextDark,
            fontWeight = FontWeight.Normal,
            modifier = Modifier.weight(1f)
        )

        // End Element
        if (endText != null) {
            Text(
                text = endText,
                fontSize = 16.sp,
                color = TextGray,
                fontWeight = FontWeight.Medium
            )
        } else {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                contentDescription = null,
                tint = TextGray
            )
        }
    }
}
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
import com.myapplication.planeats.ui.theme.ButtonGreen
import com.myapplication.planeats.ui.theme.TextDark
import com.myapplication.planeats.ui.theme.TextGray

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PrivacyPolicyScreen(navController: NavController) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        "Privacy Policy",
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
                text = "Last updated: November 30, 2025",
                fontSize = 12.sp,
                color = TextGray
            )

            Spacer(modifier = Modifier.height(16.dp))

            PolicyBodyText(
                "PlanEats is committed to protecting your privacy. This Privacy Policy explains how we collect, use, disclose, and safeguard your information when you use our mobile application, PlanEats – Healthy Meal Planner. Please read this privacy policy carefully. If you do not agree with the terms of this privacy policy, please do not access the application."
            )

            //SECTION 1
            PolicyHeading("1. Information We Collect")
            PolicyBodyText("We may collect information about you in a variety of ways. The information we may collect via the Application depends on the content and materials you use, and includes:")

            PolicySubHeading("Personal Data")
            PolicyBodyText("Demographic and other personally identifiable information (such as your name and email address) that you voluntarily give to us when choosing to participate in various activities related to the Application, such as chat, posting messages in comment sections or our forums, liking posts, sending feedback, and responding to surveys.")

            PolicySubHeading("Derivative Data")
            PolicyBodyText("Information our servers automatically collect when you access the Application, such as your native actions that are integral to the Application, including liking, re-blogging, or replying to a post, as well as other interactions with the Application and other users via server log files.")

            //SECTION 2
            PolicyHeading("2. Use of Your Information")
            PolicyBodyText("Having accurate information about you permits us to provide you with a smooth, efficient, and customized experience. Specifically, we may use information collected about you via the Application to:")

            // Bullet Points
            PolicyBulletPoint("Create and manage your account.")
            PolicyBulletPoint("Email you regarding your account or order.")
            PolicyBulletPoint("Enable user-to-user communications.")
            PolicyBulletPoint("Generate a personal profile about you to make future visits to the Application more personalized.")
            PolicyBulletPoint("Increase the efficiency and operation of the Application.")

            //SECTION 3
            PolicyHeading("3. Disclosure of Your Information")
            PolicyBodyText("We may share information we have collected about you in certain situations. Your information may be disclosed as follows: by law or to protect rights, if we believe the release of information about you is necessary to respond to legal process, to investigate or remedy potential violations of our policies, or to protect the rights, property, and safety of others.")

            //SECTION 4
            PolicyHeading("4. Security of Your Information")
            PolicyBodyText("We use administrative, technical, and physical security measures to help protect your personal information. While we have taken reasonable steps to secure the personal information you provide to us, please be aware that despite our efforts, no security measures are perfect or impenetrable, and no method of data transmission can be guaranteed against any interception or other type of misuse.")

            //CONTACT US
            PolicyHeading("Contact Us")
            PolicyBodyText("If you have questions or comments about this Privacy Policy, please contact us at:")
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "support@planeats.app",
                color = ButtonGreen, // Warna Hijau
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium
            )

            Spacer(modifier = Modifier.height(50.dp))
        }
    }
}

@Composable
fun PolicyHeading(text: String) {
    Spacer(modifier = Modifier.height(24.dp))
    Text(
        text = text,
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold,
        color = TextDark
    )
    Spacer(modifier = Modifier.height(8.dp))
}

@Composable
fun PolicySubHeading(text: String) {
    Spacer(modifier = Modifier.height(12.dp))
    Text(
        text = text,
        fontSize = 16.sp,
        fontWeight = FontWeight.SemiBold,
        color = TextDark
    )
    Spacer(modifier = Modifier.height(4.dp))
}

@Composable
fun PolicyBodyText(text: String) {
    Text(
        text = text,
        fontSize = 14.sp,
        color = TextGray,
        lineHeight = 22.sp
    )
}

@Composable
fun PolicyBulletPoint(text: String) {
    Row(
        modifier = Modifier.padding(bottom = 4.dp, start = 8.dp),
        verticalAlignment = Alignment.Top
    ) {
        Text(
            text = "•",
            fontSize = 14.sp,
            color = TextGray,
            modifier = Modifier.padding(end = 8.dp)
        )
        Text(
            text = text,
            fontSize = 14.sp,
            color = TextGray,
            lineHeight = 20.sp
        )
    }
}
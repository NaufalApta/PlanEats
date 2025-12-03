package com.myapplication.planeats.ui.screens.auth

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.myapplication.planeats.ui.navigation.Screen
import com.myapplication.planeats.ui.theme.ButtonGreen
import com.myapplication.planeats.ui.theme.TextDark
import com.myapplication.planeats.ui.theme.TextGray

@Composable
fun SignInScreen(navController: NavController) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF9F9F9))
            .padding(24.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Judul
        Text(
            text = "Welcome to PlanEats",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = TextDark
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Sign in or create an account to start planning.",
            fontSize = 16.sp,
            color = TextGray,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(48.dp))

        // Form Input
        PlanEatsTextField(
            label = "Email",
            value = email,
            onValueChange = { email = it },
            placeholder = "Enter your email address"
        )

        Spacer(modifier = Modifier.height(24.dp))

        PlanEatsPasswordField(
            label = "Password",
            value = password,
            onValueChange = { password = it },
            placeholder = "Enter your password"
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Forgot Password
        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterEnd) {
            Text(
                text = "Forgot Password?",
                color = TextGray,
                fontSize = 14.sp,
                modifier = Modifier.clickable { /* TODO: Navigasi Forgot Password */ }
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Tombol Continue (Sign In)
        Button(
            onClick = { navController.navigate(Screen.Home.route) },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            colors = ButtonDefaults.buttonColors(containerColor = ButtonGreen),
            shape = RoundedCornerShape(100)
        ) {
            Text("Continue", fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Tombol Create Account (Outlined)
        OutlinedButton(
            onClick = { navController.navigate(Screen.CreateAccount.route) },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            border = BorderStroke(1.5.dp, ButtonGreen),
            shape = RoundedCornerShape(100) // Bentuk Pil
        ) {
            Text("Create Account", fontSize = 16.sp, fontWeight = FontWeight.SemiBold, color = ButtonGreen)
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Footer Terms
        Text(
            text = "By continuing, you agree to our Terms of Service and Privacy Policy.",
            fontSize = 12.sp,
            color = TextGray,
            textAlign = TextAlign.Center,
            lineHeight = 18.sp
        )
    }
}
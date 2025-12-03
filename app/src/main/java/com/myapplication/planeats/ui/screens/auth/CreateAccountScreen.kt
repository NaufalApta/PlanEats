package com.myapplication.planeats.ui.screens.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
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
fun CreateAccountScreen(navController: NavController) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF9F9F9))
            .padding(24.dp)
            .verticalScroll(rememberScrollState())
    ) {
        // Tombol Back
        IconButton(
            onClick = { navController.popBackStack() }, // Kembali ke layar sebelumnya
            modifier = Modifier.size(24.dp)
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Back",
                tint = TextDark
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Header
        Text(
            text = "Create Account",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = TextDark
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Let’s get you started with your healthy meal plan.",
            fontSize = 16.sp,
            color = TextGray
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Form Inputs
        PlanEatsTextField(
            label = "Name",
            value = name,
            onValueChange = { name = it },
            placeholder = "Enter your full name"
        )

        Spacer(modifier = Modifier.height(16.dp))

        PlanEatsTextField(
            label = "Email",
            value = email,
            onValueChange = { email = it },
            placeholder = "Enter your email address"
        )

        Spacer(modifier = Modifier.height(16.dp))

        PlanEatsPasswordField(
            label = "Password",
            value = password,
            onValueChange = { password = it },
            placeholder = "Enter your password"
        )

        Spacer(modifier = Modifier.height(16.dp))

        PlanEatsPasswordField(
            label = "Confirm Password",
            value = confirmPassword,
            onValueChange = { confirmPassword = it },
            placeholder = "Confirm your password"
        )

        Spacer(modifier = Modifier.height(40.dp))

        // Tombol Create
        Button(
            onClick = {
                navController.navigate(Screen.Home.route) {
                    popUpTo(Screen.Welcome.route) { inclusive = true }
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            colors = ButtonDefaults.buttonColors(containerColor = ButtonGreen),
            shape = RoundedCornerShape(100)
        ) {
            Text("Create Account", fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Footer Terms
        Text(
            text = "By creating an account, you agree to our Terms of Service and Privacy Policy.",
            fontSize = 12.sp,
            color = TextGray,
            textAlign = TextAlign.Center,
            lineHeight = 18.sp,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
    }
}
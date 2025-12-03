package com.myapplication.planeats.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.myapplication.planeats.ui.navigation.Screen
import com.myapplication.planeats.ui.theme.ButtonGreen
import com.myapplication.planeats.ui.theme.TextDark
import com.myapplication.planeats.ui.theme.TextGray
import java.util.Calendar
import android.content.Context
import androidx.compose.ui.platform.LocalContext
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddMealScreen(navController: NavController) {
    val context = LocalContext.current
    var title by remember { mutableStateOf("") }
    var calories by remember { mutableStateOf("") }
    var selectedCategory by remember { mutableStateOf("Breakfast") }

    // State untuk DATE PICKER
    val dateState = rememberDatePickerState()
    var showDatePicker by remember { mutableStateOf(false) }
    var selectedDateText by remember {
        mutableStateOf(SimpleDateFormat("EEEE, d MMMM yyyy", Locale.getDefault()).format(Date()))
    }

    // State untuk TIME PICKER
    val currentTime = Calendar.getInstance()
    val timeState = rememberTimePickerState(
        initialHour = currentTime.get(Calendar.HOUR_OF_DAY),
        initialMinute = currentTime.get(Calendar.MINUTE),
        is24Hour = false
    )
    var showTimePicker by remember { mutableStateOf(false) }
    var selectedTimeText by remember {
        mutableStateOf(SimpleDateFormat("hh:mm a", Locale.getDefault()).format(currentTime.time))
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Add Meal", fontWeight = FontWeight.SemiBold) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = Color.White)
            )
        },
        bottomBar = {
            // Tombol Save Meal
            Button(
                onClick = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Home.route) { inclusive = true }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp)
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(containerColor = ButtonGreen),
                shape = RoundedCornerShape(100)
            ) {
                Text("Save Meal", fontSize = 16.sp, fontWeight = FontWeight.Bold)
            }
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
            Spacer(modifier = Modifier.height(24.dp))

            // 1. Meal Title
            Text("Meal Title", fontWeight = FontWeight.Medium)
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = title,
                onValueChange = { title = it },
                placeholder = { Text("e.g., Avocado Toast", color = Color.LightGray) },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = inputColors()
            )

            Spacer(modifier = Modifier.height(16.dp))

            // 2. Calories
            Text("Calories (kcal)", fontWeight = FontWeight.Medium)
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = calories,
                onValueChange = { if (it.all { char -> char.isDigit() }) calories = it },
                placeholder = { Text("e.g. 350", color = Color.LightGray) },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = inputColors(),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(16.dp))

            // 3. Category (Chips)
            Text("Category", fontWeight = FontWeight.Medium)
            Spacer(modifier = Modifier.height(8.dp))
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                CategoryOption("Breakfast", selectedCategory) { selectedCategory = "Breakfast" }
                CategoryOption("Lunch", selectedCategory) { selectedCategory = "Lunch" }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                CategoryOption("Dinner", selectedCategory) { selectedCategory = "Dinner" }
                CategoryOption("Snack", selectedCategory) { selectedCategory = "Snack" }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // 4. Time Picker
            Text("Time", fontWeight = FontWeight.Medium)
            Spacer(modifier = Modifier.height(8.dp))

            Box {
                OutlinedTextField(
                    value = selectedTimeText,
                    onValueChange = {},
                    readOnly = true, // Agar keyboard tidak muncul
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    trailingIcon = { Icon(Icons.Default.Schedule, contentDescription = null, tint = TextGray) },
                    colors = inputColors(),
                    // Trik agar saat diklik muncul DatePicker
                    interactionSource = remember { MutableInteractionSource() }
                        .also { interactionSource ->
                            LaunchedEffect(interactionSource) {
                                interactionSource.interactions.collect {
                                    if (it is PressInteraction.Release) {
                                        showTimePicker = true
                                    }
                                }
                            }
                        }
                )

                Box(
                    modifier = Modifier
                        .matchParentSize()
                        .clickable { showTimePicker = true }
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // 5. Date Picker
            Text("Date", fontWeight = FontWeight.Medium)
            Spacer(modifier = Modifier.height(8.dp))

            Box {
                OutlinedTextField(
                    value = selectedDateText,
                    onValueChange = {},
                    readOnly = true,
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    trailingIcon = { Icon(Icons.Default.CalendarToday, contentDescription = null, tint = TextGray) },
                    colors = inputColors(),
                    interactionSource = remember { MutableInteractionSource() }
                        .also { interactionSource ->
                            LaunchedEffect(interactionSource) {
                                interactionSource.interactions.collect {
                                    if (it is PressInteraction.Release) {
                                        showDatePicker = true
                                    }
                                }
                            }
                        }
                )
                Box(
                    modifier = Modifier
                        .matchParentSize()
                        .clickable { showDatePicker = true }
                )
            }

            Spacer(modifier = Modifier.height(100.dp))
        }


        // 1. DATE PICKER DIALOG
        if (showDatePicker) {
            DatePickerDialog(
                onDismissRequest = { showDatePicker = false },
                confirmButton = {
                    TextButton(onClick = {
                        dateState.selectedDateMillis?.let { millis ->
                            val formatter = SimpleDateFormat("EEEE, d MMMM yyyy", Locale.getDefault())
                            selectedDateText = formatter.format(Date(millis))
                        }
                        showDatePicker = false
                    }) { Text("OK", color = ButtonGreen) }
                },
                dismissButton = {
                    TextButton(onClick = { showDatePicker = false }) { Text("Cancel", color = TextGray) }
                }
            ) {
                DatePicker(state = dateState)
            }
        }

        // 2. TIME PICKER DIALOG
        if (showTimePicker) {
            AlertDialog(
                onDismissRequest = { showTimePicker = false },
                confirmButton = {
                    TextButton(onClick = {
                        val cal = Calendar.getInstance()
                        cal.set(Calendar.HOUR_OF_DAY, timeState.hour)
                        cal.set(Calendar.MINUTE, timeState.minute)
                        val formatter = SimpleDateFormat("hh:mm a", Locale.getDefault())
                        selectedTimeText = formatter.format(cal.time)
                        showTimePicker = false
                    }) { Text("OK", color = ButtonGreen) }
                },
                dismissButton = {
                    TextButton(onClick = { showTimePicker = false }) { Text("Cancel", color = TextGray) }
                },
                text = {
                    // Tampilan Jam
                    TimePicker(state = timeState)
                }
            )
        }
    }
}

@Composable
fun CategoryOption(text: String, selectedCategory: String, onClick: () -> Unit) {
    val isSelected = text == selectedCategory
    Button(
        onClick = onClick,
        modifier = Modifier.width(160.dp),
        shape = RoundedCornerShape(100),
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isSelected) ButtonGreen else Color.White,
            contentColor = if (isSelected) Color.White else TextDark
        ),
        border = if (!isSelected) androidx.compose.foundation.BorderStroke(1.dp, Color.LightGray) else null,
        elevation = ButtonDefaults.buttonElevation(0.dp)
    ) {
        Text(text)
    }
}

@Composable
fun inputColors() = OutlinedTextFieldDefaults.colors(
    unfocusedContainerColor = Color.White,
    focusedContainerColor = Color.White,
    unfocusedBorderColor = Color.LightGray,
    focusedBorderColor = ButtonGreen
)
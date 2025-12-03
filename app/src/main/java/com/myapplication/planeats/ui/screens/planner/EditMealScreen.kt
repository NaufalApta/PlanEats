package com.myapplication.planeats.ui.screens.planner

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
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditMealScreen(navController: NavController) {
    var title by remember { mutableStateOf("Avocado Toast") }
    var calories by remember { mutableStateOf("350") }
    var selectedCategory by remember { mutableStateOf("Breakfast") }
    var selectedTimeText by remember { mutableStateOf("08:00 AM") }
    var selectedDateText by remember { mutableStateOf("Today, 24 July") }

    var showDatePicker by remember { mutableStateOf(false) }
    var showTimePicker by remember { mutableStateOf(false) }
    val dateState = rememberDatePickerState()
    val timeState = rememberTimePickerState()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Edit Meal", fontWeight = FontWeight.SemiBold) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = Color.White)
            )
        },
        bottomBar = {
            Column(modifier = Modifier.padding(24.dp)) {
                // Tombol Save Changes
                Button(
                    onClick = {
                        navController.navigate(Screen.Planner.route) {
                            popUpTo(Screen.Planner.route) { inclusive = true }
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = ButtonGreen),
                    shape = RoundedCornerShape(100)
                ) {
                    Text("Save Changes", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Tombol Delete Meal
                TextButton(
                    onClick = {
                        navController.navigate(Screen.Planner.route) {
                            popUpTo(Screen.Planner.route) { inclusive = true }
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Delete Meal", color = Color(0xFFE53935), fontSize = 16.sp, fontWeight = FontWeight.Medium)
                }
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
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = editInputColors()
            )

            Spacer(modifier = Modifier.height(16.dp))

            // 2. Calories
            Text("Calories (kcal)", fontWeight = FontWeight.Medium)
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = calories,
                onValueChange = { if (it.all { char -> char.isDigit() }) calories = it },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = editInputColors()
            )

            Spacer(modifier = Modifier.height(16.dp))

            // 3. Category
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

            // 4. Time
            Text("Time", fontWeight = FontWeight.Medium)
            Spacer(modifier = Modifier.height(8.dp))
            Box {
                OutlinedTextField(
                    value = selectedTimeText,
                    onValueChange = {},
                    readOnly = true,
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    trailingIcon = { Icon(Icons.Default.Schedule, contentDescription = null, tint = TextGray) },
                    colors = editInputColors(),
                    interactionSource = remember { MutableInteractionSource() }
                        .also { interactionSource ->
                            LaunchedEffect(interactionSource) {
                                interactionSource.interactions.collect {
                                    if (it is PressInteraction.Release) showTimePicker = true
                                }
                            }
                        }
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // 5. Date
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
                    colors = editInputColors(),
                    interactionSource = remember { MutableInteractionSource() }
                        .also { interactionSource ->
                            LaunchedEffect(interactionSource) {
                                interactionSource.interactions.collect {
                                    if (it is PressInteraction.Release) showDatePicker = true
                                }
                            }
                        }
                )
            }
            Spacer(modifier = Modifier.height(20.dp))
        }

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
                dismissButton = { TextButton(onClick = { showDatePicker = false }) { Text("Cancel", color = TextGray) } }
            ) { DatePicker(state = dateState) }
        }

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
                dismissButton = { TextButton(onClick = { showTimePicker = false }) { Text("Cancel", color = TextGray) } },
                text = { TimePicker(state = timeState) }
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
fun editInputColors() = OutlinedTextFieldDefaults.colors(
    unfocusedContainerColor = Color.White,
    focusedContainerColor = Color.White,
    unfocusedBorderColor = Color.LightGray,
    focusedBorderColor = ButtonGreen
)
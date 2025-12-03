package com.myapplication.planeats

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.myapplication.planeats.ui.navigation.Screen
import com.myapplication.planeats.ui.screens.intro.WelcomeScreen
import com.myapplication.planeats.ui.screens.auth.SignInScreen
import com.myapplication.planeats.ui.screens.auth.CreateAccountScreen
import com.myapplication.planeats.ui.screens.home.HomeScreen
import com.myapplication.planeats.ui.screens.home.AddMealScreen
import com.myapplication.planeats.ui.screens.planner.PlannerScreen
import com.myapplication.planeats.ui.screens.planner.MealDetailScreen
import com.myapplication.planeats.ui.screens.planner.EditMealScreen
import com.myapplication.planeats.ui.screens.home.AchievementScreen
import com.myapplication.planeats.ui.screens.home.ChallengeScreen
import com.myapplication.planeats.ui.theme.PlanEatsTheme
import com.myapplication.planeats.ui.screens.home.ProfileScreen
import com.myapplication.planeats.ui.screens.home.EditProfileScreen
import com.myapplication.planeats.ui.screens.home.ChangePasswordScreen
import com.myapplication.planeats.ui.screens.home.AboutSupportScreen
import com.myapplication.planeats.ui.screens.home.SdgCommitmentScreen
import com.myapplication.planeats.ui.screens.home.ContactUsScreen
import com.myapplication.planeats.ui.screens.home.PrivacyPolicyScreen
import com.myapplication.planeats.ui.screens.home.SendFeedbackScreen
import com.myapplication.planeats.ui.screens.home.TermsOfServiceScreen


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PlanEatsTheme {

                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = Screen.Welcome.route
                ) {

                    composable(Screen.Welcome.route) {
                        WelcomeScreen(navController = navController)
                    }

                    composable(Screen.SignIn.route) {
                        SignInScreen(navController = navController)
                    }

                    composable(Screen.CreateAccount.route) {
                        CreateAccountScreen(navController = navController)
                    }

                    composable(Screen.Home.route) {
                        HomeScreen(navController = navController)
                    }

                    composable(Screen.AddMeal.route) {
                        AddMealScreen(navController = navController)
                    }

                    composable(Screen.Planner.route) {
                        PlannerScreen(navController = navController)
                    }

                    composable(Screen.MealDetail.route) {
                        MealDetailScreen(navController = navController)
                    }

                    composable(Screen.EditMeal.route) {
                        EditMealScreen(navController = navController)
                    }

                    composable(Screen.Achievement.route) {
                        AchievementScreen(navController = navController)
                    }

                    composable(Screen.Challenge.route) {
                        ChallengeScreen(navController = navController)
                    }

                    composable(Screen.Profile.route) {
                        ProfileScreen(navController = navController)
                    }

                    composable(Screen.EditProfile.route) {
                        EditProfileScreen(navController = navController)
                    }

                    composable(Screen.ChangePassword.route) {
                        ChangePasswordScreen(navController = navController)
                    }

                    composable(Screen.AboutSupport.route) {
                        AboutSupportScreen(navController = navController)
                    }

                    composable(Screen.SdgCommitment.route) {
                        SdgCommitmentScreen(navController = navController)
                    }

                    composable(Screen.ContactUs.route) {
                        ContactUsScreen(navController = navController)
                    }

                    composable(Screen.SendFeedback.route) {
                        SendFeedbackScreen(navController = navController)
                    }

                    composable(Screen.PrivacyPolicy.route) {
                        PrivacyPolicyScreen(navController = navController)
                    }

                    composable(Screen.TermsOfService.route) {
                        TermsOfServiceScreen(navController = navController)
                    }

                }
            }
        }
    }
}
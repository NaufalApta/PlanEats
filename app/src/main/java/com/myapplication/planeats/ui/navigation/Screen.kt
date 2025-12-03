package com.myapplication.planeats.ui.navigation

sealed class Screen(val route: String) {
    object Welcome : Screen("welcome_screen")
    object SignIn : Screen("sign_in_screen")
    object CreateAccount : Screen("create_account_screen")
    object Home : Screen("home_screen")
    object AddMeal : Screen("add_meal_screen")
    object Planner : Screen("planner_screen")
    object MealDetail : Screen("meal_detail_screen")
    object EditMeal : Screen("edit_meal_screen")
    object Achievement : Screen("achievement_screen")
    object Challenge : Screen("challenge_screen")
    object Profile : Screen("profile_screen")
    object EditProfile : Screen("edit_profile_screen")
    object ChangePassword : Screen("change_password_screen")
    object AboutSupport : Screen("about_support_screen")
    object SdgCommitment : Screen("sdg_commitment_screen")
    object ContactUs : Screen("contact_us_screen")
    object SendFeedback : Screen("send_feedback_screen")
    object PrivacyPolicy : Screen("privacy_policy_screen")
    object TermsOfService : Screen("terms_of_service_screen")

}
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
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.myapplication.planeats.ui.theme.ButtonGreen
import com.myapplication.planeats.ui.theme.TextDark
import com.myapplication.planeats.ui.theme.TextGray

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TermsOfServiceScreen(navController: NavController) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        "Terms of Service",
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
                text = "Last Updated: November 30, 2025",
                fontSize = 12.sp,
                color = TextGray
            )

            Spacer(modifier = Modifier.height(16.dp))

            TermsBodyText(
                "Welcome to PlanEats! These Terms of Service govern your use of the PlanEats – Healthy Meal Planner mobile application, and any related services provided by us. By using our App, you agree to these Terms."
            )

            //SECTION 1
            TermsHeading("1. Acceptance of Terms")
            TermsBodyText("By accessing or using our App, you confirm that you can form a binding contract with PlanEats, that you accept these Terms and that you agree to comply with them. Your access to and use of our App is also subject to our Privacy Policy.")

            //SECTION 2
            TermsHeading("2. Use of the App")

            TermsAnnotatedText("Eligibility:", " You must be at least 13 years old to use the App.")
            Spacer(modifier = Modifier.height(8.dp))

            TermsAnnotatedText("License:", " We grant you a limited, non-exclusive, non-transferable, and revocable license to use our App for your personal, non-commercial use.")
            Spacer(modifier = Modifier.height(8.dp))

            TermsAnnotatedText("Prohibited Conduct:", " You agree not to misuse the App or help anyone else to do so. This includes, but is not limited to, interfering with our services, infringing on intellectual property rights, or uploading malicious content.")

            //SECTION 3
            TermsHeading("3. Accounts")
            TermsBodyText("When you create an account with us, you must provide information that is accurate, complete, and current at all times. Failure to do so constitutes a breach of the Terms, which may result in immediate termination of your account on our service. You are responsible for safeguarding the password that you use to access the App.")

            //SECTION 4
            TermsHeading("4. Content and Health Disclaimer")
            TermsBodyText("The content provided in the App, including meal plans and nutritional information, is for informational purposes only. It is not intended to be a substitute for professional medical advice, diagnosis, or treatment. Always seek the advice of your physician or other qualified health provider with any questions you may have regarding a medical condition.")

            //SECTION 5
            TermsHeading("5. Changes to Terms")
            TermsBodyText("We reserve the right, at our sole discretion, to modify or replace these Terms at any time. We will provide at least 30 days’ notice before any new terms take effect. By continuing to access or use our App after those revisions become effective, you agree to be bound by the revised terms.")

            //CONTACT US
            TermsHeading("Contact Us")
            TermsBodyText("If you have any questions or comments about this Terms, please contact us at:")
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "support@planeats.app",
                color = ButtonGreen,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium
            )

            Spacer(modifier = Modifier.height(50.dp))
        }
    }
}


@Composable
fun TermsHeading(text: String) {
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
fun TermsBodyText(text: String) {
    Text(
        text = text,
        fontSize = 14.sp,
        color = TextGray,
        lineHeight = 22.sp
    )
}

@Composable
fun TermsAnnotatedText(boldPrefix: String, normalText: String) {
    Text(
        text = buildAnnotatedString {
            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold, color = TextGray)) {
                append(boldPrefix)
            }
            withStyle(style = SpanStyle(fontWeight = FontWeight.Normal, color = TextGray)) {
                append(normalText)
            }
        },
        fontSize = 14.sp,
        lineHeight = 22.sp
    )
}
package com.example.travelwave

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.travelwave.ui.LoginScreen
import com.example.travelwave.ui.SignUpScreen
import com.example.travelwave.ui.MainScreen
import com.example.travelwave.ui.NotificationScreen
import com.example.travelwave.ui.OnboardingScreen
import com.example.travelwave.ui.ProfileSettingsScreen
import com.example.travelwave.ui.SplashScreen
import com.example.travelwave.ui.TripDetailsScreen
import com.example.travelwave.ui.theme.TravelWaveTheme
import kotlinx.coroutines.delay
import coil.Coil
import coil.ImageLoader
import okhttp3.OkHttpClient

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // Configure Coil to use a standard User-Agent to prevent 403 Forbidden from strict image hosts (like Wikimedia/Unsplash).
        val imageLoader = ImageLoader.Builder(this)
            .okHttpClient {
                OkHttpClient.Builder()
                    .addInterceptor { chain ->
                        val request = chain.request().newBuilder()
                            .header("User-Agent", "Mozilla/5.0 (Linux; Android 10) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36")
                            .build()
                        chain.proceed(request)
                    }
                    .build()
            }
            .build()
        Coil.setImageLoader(imageLoader)
        
        enableEdgeToEdge()
        setContent {
            TravelWaveTheme {
                var currentScreen by remember { mutableStateOf(Screen.Splash) }

                LaunchedEffect(Unit) {
                    delay(3000)
                    currentScreen = Screen.Onboarding
                }

                when (currentScreen) {
                    Screen.Splash -> SplashScreen()
                    Screen.Onboarding -> OnboardingScreen(
                        onGetStarted = { currentScreen = Screen.Login }
                    )
                    Screen.Login -> LoginScreen(
                        onContinue = { currentScreen = Screen.Home },
                        onSignUpClick = { currentScreen = Screen.SignUp },
                        onSkip = { currentScreen = Screen.Home }
                    )
                    Screen.SignUp -> SignUpScreen(
                        onContinue = { currentScreen = Screen.Home },
                        onLoginClick = { currentScreen = Screen.Login },
                        onSkip = { currentScreen = Screen.Home }
                    )
                    Screen.Home -> MainScreen(
                        onNavigateToProfile = { currentScreen = Screen.ProfileSettings },
                        onNavigateToNotifications = { currentScreen = Screen.Notifications },
                        onNavigateToTripDetails = { currentScreen = Screen.TripDetails }
                    )
                    Screen.ProfileSettings -> ProfileSettingsScreen(
                        onBackClick = { currentScreen = Screen.Home },
                        onLogoutClick = { 
                            com.google.firebase.auth.FirebaseAuth.getInstance().signOut()
                            currentScreen = Screen.Login 
                        },
                        onSecurityClick = { currentScreen = Screen.Security },
                        onNotificationsClick = { currentScreen = Screen.Notifications },
                        onLinkedAccountsClick = { currentScreen = Screen.LinkedAccounts },
                        onHelpCenterClick = { currentScreen = Screen.HelpCenter },
                        onContactUsClick = { currentScreen = Screen.ContactUs },
                        onPrivacyPolicyClick = { currentScreen = Screen.PrivacyPolicy },
                        onTermsOfServiceClick = { currentScreen = Screen.TermsOfService }
                    )
                    Screen.Notifications -> NotificationScreen(
                        onBackClick = { currentScreen = Screen.Home }
                    )
                    Screen.TripDetails -> TripDetailsScreen(
                        onBackClick = { currentScreen = Screen.Home },
                        onBookNowClick = {} // Handled via MainScreen usually, but providing empty lambda for direct access
                    )
                    Screen.Security -> com.example.travelwave.ui.SecurityScreen(
                        onBackClick = { currentScreen = Screen.ProfileSettings },
                        onLinkedAccountsClick = { currentScreen = Screen.LinkedAccounts }
                    )
                    Screen.LinkedAccounts -> com.example.travelwave.ui.LinkedAccountsScreen(
                        onBackClick = { currentScreen = Screen.Security }
                    )
                    Screen.HelpCenter -> com.example.travelwave.ui.AIAssistantScreen(onBackClick = { currentScreen = Screen.ProfileSettings })
                    Screen.ContactUs -> com.example.travelwave.ui.ContactUsScreen(onBackClick = { currentScreen = Screen.ProfileSettings })
                    Screen.PrivacyPolicy -> com.example.travelwave.ui.PrivacyPolicyScreen(onBackClick = { currentScreen = Screen.ProfileSettings })
                    Screen.TermsOfService -> com.example.travelwave.ui.SettingsDetailScreen(title = "Terms of Service", onBackClick = { currentScreen = Screen.ProfileSettings })
                }
            }
        }
    }
}

enum class Screen {
    Splash, Onboarding, Login, SignUp, Home, ProfileSettings, Notifications, TripDetails,
    Security, LinkedAccounts, HelpCenter, ContactUs, PrivacyPolicy, TermsOfService
}
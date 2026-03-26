package com.example.travelwave.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.travelwave.ui.theme.GreenPrimary

@Composable
fun PrivacyPolicyScreen(onBackClick: () -> Unit) {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF0e110f))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF0e110f).copy(alpha = 0.8f))
                .padding(horizontal = 16.dp, vertical = 24.dp), 
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBackIosNew,
                contentDescription = "Back",
                tint = GreenPrimary,
                modifier = Modifier
                    .size(24.dp)
                    .clickable { onBackClick() }
            )
            Text(
                text = "Privacy Policy",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 24.dp), 
                textAlign = TextAlign.Center
            )
        }
        
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
                .verticalScroll(scrollState)
        ) {
            Text(
                text = "App Privacy & Trust",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = GreenPrimary,
                modifier = Modifier.padding(bottom = 12.dp)
            )
            
            Text(
                text = "Welcome to TravelWave. Your privacy and security are our top priorities. This app is trustable, and we handle your data with the highest level of care.\n\n" +
                        "1. Information We Collect\n" +
                        "We may collect information you provide directly to us, such as your name, email address, profile photo, and location data to improve your travel experience.\n\n" +
                        "2. How We Use Your Information\n" +
                        "Your information is used solely to provide and improve our services, including personalized trip recommendations and customer support. We do not sell your personal data to third parties.\n\n" +
                        "3. Data Security\n" +
                        "We implement robust security measures to ensure your data is safe and protected against unauthorized access, alteration, or destruction.\n\n" +
                        "4. Your Rights\n" +
                        "You have the right to access, update, or delete your personal information at any time from the app settings.\n\n" +
                        "If you have any questions or concerns about this privacy policy, please contact our support team from the Help Center.",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.LightGray,
                lineHeight = MaterialTheme.typography.bodyMedium.lineHeight * 1.5
            )
            
            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

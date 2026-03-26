package com.example.travelwave.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.HelpCenter
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Link
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.Mail
import androidx.compose.material.icons.filled.NotificationsActive
import androidx.compose.material.icons.filled.Policy
import androidx.compose.material.icons.filled.Security
import androidx.compose.material.icons.filled.Verified
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.travelwave.ui.theme.GreenPrimary
import com.example.travelwave.ui.theme.TravelWaveTheme
import com.google.firebase.auth.FirebaseAuth

@Composable
fun ProfileSettingsScreen(
    onBackClick: () -> Unit,
    onLogoutClick: () -> Unit,
    onSecurityClick: () -> Unit = {},
    onNotificationsClick: () -> Unit = {},
    onLinkedAccountsClick: () -> Unit = {},
    onHelpCenterClick: () -> Unit = {},
    onContactUsClick: () -> Unit = {},
    onPrivacyPolicyClick: () -> Unit = {},
    onTermsOfServiceClick: () -> Unit = {}
) {
    val scrollState = rememberScrollState()

    CustomScaffold(
        topBar = {
            SettingsTopBar(onBackClick)
        },
        bottomBar = {
            LogoutButton(onLogoutClick)
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(scrollState)
                .padding(bottom = 24.dp) // Extra padding for scrolling above bottom bar
        ) {
            ProfileHeaderCard()

            SettingsSectionTitle("Account")
            SettingsSectionCard {
                SettingsItem(Icons.Default.Security, "Security", onClick = onSecurityClick)
                Divider()
                SettingsItem(Icons.Default.NotificationsActive, "Notifications", onClick = onNotificationsClick)
                Divider()
                SettingsItem(Icons.Default.Link, "Linked Accounts", onClick = onLinkedAccountsClick)
            }

            SettingsSectionTitle("Support")
            SettingsSectionCard {
                SettingsItem(Icons.Default.HelpCenter, "Help Center", onClick = onHelpCenterClick)
                Divider()
                SettingsItem(Icons.Default.Mail, "Contact Us", onClick = onContactUsClick)
                Divider()
                SettingsItem(Icons.Default.Policy, "Privacy Policy", onClick = onPrivacyPolicyClick)
            }

            SettingsSectionTitle("About")
            SettingsSectionCard {
                AppVersionItem()
                Divider()
                SettingsItem(Icons.Default.Description, "Terms of Service", onClick = onTermsOfServiceClick)
            }
        }
    }
}

// Helper to mimic the custom scaffold behavior in the design (fixed bottom, sticky top)
@Composable
fun CustomScaffold(
    topBar: @Composable () -> Unit,
    bottomBar: @Composable () -> Unit,
    content: @Composable (androidx.compose.foundation.layout.PaddingValues) -> Unit
) {
    Box(modifier = Modifier.fillMaxSize().background(Color(0xFF0e110f))) {
        Column(modifier = Modifier.fillMaxSize()) {
            topBar()
            Box(modifier = Modifier.weight(1f)) {
                content(androidx.compose.foundation.layout.PaddingValues(0.dp))
            }
        }
        Box(
             modifier = Modifier.align(Alignment.BottomCenter)
        ) {
            bottomBar()
        }
    }
}


@Composable
fun SettingsTopBar(onBackClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF0e110f).copy(alpha = 0.8f))
            .padding(horizontal = 16.dp, vertical = 16.dp),
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
            text = "More Settings",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier
                .weight(1f)
                .padding(end = 24.dp), // Balance the back button
            textAlign = androidx.compose.ui.text.style.TextAlign.Center
        )
    }
}

@Composable
fun ProfileHeaderCard() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(Color(0xFF161b18), RoundedCornerShape(12.dp))
            .border(1.dp, Color.White.copy(alpha = 0.05f), RoundedCornerShape(12.dp))
            .padding(16.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .size(56.dp)
                    .clip(CircleShape)
                    .background(GreenPrimary.copy(alpha = 0.2f))
                    .border(2.dp, GreenPrimary, CircleShape)
            ) {
                AsyncImage(
                    model = "https://lh3.googleusercontent.com/aida-public/AB6AXuABihcFFcjIRgCiu_zYrVKZNf0MyKuDjPORsOukMFtDbfje39NNHEdjYZb26oM8ZTIp4uMyfj-fPI8PsgwIqCqLOdHBjapg0stXxFZjuWxiIEu2OFMpyqKDeI_W7khegh4-2Hm_zS70tHNeu9kqSo79URRdqpBVcmOsP3u0Vng_iUHsSCKHyPgDPfOzXrh3lqVclS39B1-1Of7iuWCGkM1bgSz1HozmB0heOXmrWOZ8N7id4a3PY2XVmMBOwxoHaqHnMgVRrGSBQ5M",
                    contentDescription = "Profile",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                val currentUser = FirebaseAuth.getInstance().currentUser
                val displayName = currentUser?.displayName?.takeIf { it.isNotBlank() } ?: "User"
                Text(
                    text = displayName,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Text(
                    text = "Pro Explorer • 12 Badges",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color(0xFF94a3b8) // slate-400
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            Icon(
                imageVector = Icons.Default.Verified,
                contentDescription = "Verified",
                tint = GreenPrimary,
                modifier = Modifier.size(24.dp)
            )
        }
    }
}

@Composable
fun SettingsSectionTitle(title: String) {
    Text(
        text = title.uppercase(),
        style = MaterialTheme.typography.labelSmall,
        fontWeight = FontWeight.Bold,
        color = Color(0xFF94a3b8), // slate-400
        letterSpacing = 2.sp,
        modifier = Modifier.padding(start = 24.dp, top = 24.dp, bottom = 12.dp)
    )
}

@Composable
fun SettingsSectionCard(content: @Composable () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(Color(0xFF161b18))
            .border(1.dp, Color.White.copy(alpha = 0.05f), RoundedCornerShape(12.dp))
    ) {
        content()
    }
}

@Composable
fun SettingsItem(icon: ImageVector, title: String, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .background(GreenPrimary.copy(alpha = 0.1f), RoundedCornerShape(8.dp)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = GreenPrimary,
                modifier = Modifier.size(24.dp)
            )
        }
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = title,
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Medium,
            color = Color.White,
            modifier = Modifier.weight(1f)
        )
        Icon(
            imageVector = Icons.Default.ChevronRight,
            contentDescription = null,
            tint = Color(0xFF64748b) // slate-500
        )
    }
}

@Composable
fun AppVersionItem() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .background(GreenPrimary.copy(alpha = 0.1f), RoundedCornerShape(8.dp)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.Info,
                contentDescription = null,
                tint = GreenPrimary,
                modifier = Modifier.size(24.dp)
            )
        }
        Spacer(modifier = Modifier.width(16.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = "App Version",
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Medium,
                color = Color.White
            )
            Text(
                text = "TripWave v2.4.12 (Build 405)",
                style = MaterialTheme.typography.bodySmall,
                color = Color(0xFF64748b) // slate-500
            )
        }
    }
}

@Composable
fun Divider() {
    HorizontalDivider(
        modifier = Modifier.padding(horizontal = 16.dp),
        thickness = 1.dp,
        color = Color.White.copy(alpha = 0.05f)
    )
}

@Composable
fun LogoutButton(onLogoutClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                Brush.verticalGradient(
                    colors = listOf(Color.Transparent, Color(0xFF0e110f).copy(alpha = 0.9f), Color(0xFF0e110f))
                )
            )
            .padding(24.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .border(2.dp, Color(0xFFff4d4d), RoundedCornerShape(12.dp))
                .clickable { onLogoutClick() }
                .padding(16.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.Logout,
                contentDescription = "Logout",
                tint = Color(0xFFff4d4d),
                modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Logout",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = Color(0xFFff4d4d)
            )
        }
    }
}

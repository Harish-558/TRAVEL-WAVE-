package com.example.travelwave.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Facebook
import androidx.compose.material.icons.filled.PhoneAndroid
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.travelwave.ui.theme.GreenPrimary

@Composable
fun LinkedAccountsScreen(onBackClick: () -> Unit) {
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
                text = "Linked Accounts",
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
                .padding(16.dp)
        ) {
            Text(
                text = "Manage your connected accounts",
                style = MaterialTheme.typography.titleMedium,
                color = Color.LightGray,
                modifier = Modifier.padding(bottom = 24.dp)
            )

            LinkedAccountItem(
                icon = Icons.Default.Email,
                providerName = "Google",
                accountDesc = "alex.rivera@gmail.com",
                isConnected = true
            )

            Spacer(modifier = Modifier.height(16.dp))

            LinkedAccountItem(
                icon = Icons.Default.Facebook,
                providerName = "Facebook",
                accountDesc = "Not connected",
                isConnected = false
            )

            Spacer(modifier = Modifier.height(16.dp))

            LinkedAccountItem(
                icon = Icons.Default.PhoneAndroid,
                providerName = "Phone Number",
                accountDesc = "+1 234 567 8900",
                isConnected = true
            )
        }
    }
}

@Composable
fun LinkedAccountItem(
    icon: ImageVector,
    providerName: String,
    accountDesc: String,
    isConnected: Boolean
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF161b18), RoundedCornerShape(12.dp))
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(48.dp)
                .background(
                    if (isConnected) GreenPrimary.copy(alpha = 0.2f) else Color.DarkGray.copy(alpha = 0.5f),
                    RoundedCornerShape(12.dp)
                ),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = providerName,
                tint = if (isConnected) GreenPrimary else Color.Gray,
                modifier = Modifier.size(24.dp)
            )
        }
        
        Spacer(modifier = Modifier.width(16.dp))
        
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = providerName,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            Text(
                text = accountDesc,
                style = MaterialTheme.typography.bodyMedium,
                color = if (isConnected) Color.LightGray else Color.Gray
            )
        }
        
        Button(
            onClick = { /* Toggle connection */ },
            colors = ButtonDefaults.buttonColors(
                containerColor = if (isConnected) Color(0xFF2C3E35) else GreenPrimary,
                contentColor = if (isConnected) GreenPrimary else Color.White
            ),
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier.height(36.dp)
        ) {
            Text(
                text = if (isConnected) "Disconnect" else "Connect",
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

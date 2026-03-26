package com.example.travelwave.ui

import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.CardGiftcard
import androidx.compose.material.icons.filled.ChatBubble
import androidx.compose.material.icons.filled.ConfirmationNumber
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.travelwave.ui.theme.GreenPrimary
import com.example.travelwave.ui.theme.TravelWaveTheme

@Composable
fun NotificationScreen(onBackClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF0e110f)) // Dark background
    ) {
        NotificationHeader(onBackClick)
        NotificationList()
    }
}

@Composable
fun NotificationHeader(onBackClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Default.ArrowBackIosNew,
            contentDescription = "Back",
            tint = Color.White,
            modifier = Modifier
                .size(24.dp)
                .clickable { onBackClick() }
        )
        Text(
            text = "Notifications",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier
                .weight(1f)
                .padding(start = 16.dp)
        )
        Text(
            text = "Mark all as read",
            style = MaterialTheme.typography.bodyMedium,
            color = GreenPrimary,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.clickable { }
        )
    }
}

@Composable
fun NotificationList() {
    LazyColumn(
        contentPadding = androidx.compose.foundation.layout.PaddingValues(bottom = 24.dp)
    ) {
        item { SectionHeader("TODAY", "3 NEW") }
        item {
            NotificationItem(
                icon = Icons.Default.ConfirmationNumber,
                iconColor = GreenPrimary,
                title = "Booking Confirmed",
                description = "Your trip to Bali is all set. Your e-tickets are now available in your wallet.",
                time = "2M AGO",
                isUnread = true
            )
        }
        item {
            NotificationItem(
                icon = Icons.Default.CardGiftcard,
                iconColor = GreenPrimary,
                title = "Flash Sale: 20% Off",
                description = "Get 20% off on your next luxury villa booking in Tuscany. Ends tonight!",
                time = "1H AGO",
                isUnread = true
            )
        }
        item {
            NotificationItem(
                icon = Icons.Default.Notifications,
                iconColor = GreenPrimary, // Using primary for reminder too, or custom color
                title = "Check-in Reminder",
                description = "Don't forget to check-in for your flight TW-402 to Tokyo tomorrow.",
                time = "3H AGO",
                isUnread = true
            )
        }

        item { SectionHeader("YESTERDAY", null) }
        item {
            NotificationItem(
                icon = Icons.Default.ChatBubble,
                iconColor = Color(0xFF9CA3AF), // Gray
                title = "Message from host",
                description = "Marco: \"Hello! Looking forward to hosting you in Rome. Let me know...\"",
                time = "YESTERDAY",
                isUnread = false
            )
        }
        item {
            NotificationItem(
                icon = Icons.Default.Star,
                iconColor = Color(0xFF9CA3AF), // Gray
                title = "Share your experience",
                description = "How was your stay at the Sunset Ridge? Leave a review for your host.",
                time = "YESTERDAY",
                isUnread = false
            )
        }

        item { SectionHeader("EARLIER THIS WEEK", null) }
        item {
            NotificationItem(
                icon = Icons.Default.Map,
                iconColor = Color(0xFF9CA3AF), // Gray
                title = "New Destination Unlocked",
                description = "We've added 50+ new tours in the Swiss Alps. Discover the peaks!",
                time = "3 DAYS AGO",
                isUnread = false
            )
        }
    }
}

@Composable
fun SectionHeader(title: String, badge: String?) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.labelSmall,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF9CA3AF),
            letterSpacing = 1.sp
        )
        if (badge != null) {
            Box(
                modifier = Modifier
                    .background(Color(0xFF1c3024), RoundedCornerShape(4.dp)) // Dark green bg
                    .padding(horizontal = 8.dp, vertical = 2.dp)
            ) {
                Text(
                    text = badge,
                    style = MaterialTheme.typography.labelSmall,
                    fontWeight = FontWeight.Bold,
                    color = GreenPrimary
                )
            }
        }
    }
}

@Composable
fun NotificationItem(
    icon: ImageVector,
    iconColor: Color,
    title: String,
    description: String,
    time: String,
    isUnread: Boolean
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 6.dp)
            .background(Color(0xFF161b18), RoundedCornerShape(16.dp))
            .padding(16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.Top
        ) {
            // Icon
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .background(iconColor.copy(alpha = 0.1f), CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = iconColor,
                    modifier = Modifier.size(24.dp)
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            // Content
            Column(modifier = Modifier.weight(1f)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = title,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    Text(
                        text = time,
                        style = MaterialTheme.typography.labelSmall,
                        color = Color(0xFF6B7280), // Gray 500
                        fontSize = 10.sp
                    )
                }
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = description,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color(0xFF9CA3AF), // Gray 400
                    lineHeight = 20.sp
                )
            }
        }
        if (isUnread) {
            Box(
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .padding(top = 24.dp) // Reposition dot roughly matching design relative to text layout
                    .size(8.dp)
                    .background(GreenPrimary, CircleShape)
            )
        }
    }
}

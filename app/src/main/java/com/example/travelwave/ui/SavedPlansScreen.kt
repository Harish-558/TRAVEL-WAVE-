package com.example.travelwave.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.FlightTakeoff
import androidx.compose.material.icons.filled.Hiking
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.MoreHoriz
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.Spa
import androidx.compose.material.icons.outlined.EditNote
import androidx.compose.material.icons.rounded.TempleBuddhist
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.isSystemInDarkTheme
import coil.compose.AsyncImage
import com.example.travelwave.ui.theme.BackgroundDark
import com.example.travelwave.ui.theme.BackgroundLight
import com.example.travelwave.ui.theme.GreenPrimary

@Composable
fun SavedPlansContent(
    savedTrips: Set<TripData>,
    onToggleSave: (TripData) -> Unit,
    onTripClick: (TripData) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        SavedPlansHeader(savedCount = savedTrips.size)
        
        if (savedTrips.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 80.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "No saved trips yet!\nExplore to add some favorites.",
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.Gray,
                    textAlign = androidx.compose.ui.text.style.TextAlign.Center
                )
            }
        } else {
            androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid(
                columns = androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells.Fixed(2),
                contentPadding = PaddingValues(start = 20.dp, end = 20.dp, top = 20.dp, bottom = 100.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalItemSpacing = 12.dp,
                modifier = Modifier.fillMaxSize()
            ) {
                items(savedTrips.toList()) { trip ->
                    TripCard(
                        trip = trip,
                        isSaved = true,
                        onToggleSave = { onToggleSave(trip) },
                        onClick = { onTripClick(trip) }
                    )
                }
            }
        }
    }
}

@Composable
fun SavedPlansHeader(savedCount: Int) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background.copy(alpha = 0.95f))
            .padding(bottom = 16.dp)
    ) {
        // Top Bar
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = { /* Handle Back */ },
                modifier = Modifier
                    .size(48.dp)
                    .background(Color.Transparent, CircleShape)
            ) {
                Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = MaterialTheme.colorScheme.onBackground)
            }
            
            IconButton(
                onClick = { /* Handle Add */ },
                modifier = Modifier
                    .size(48.dp)
                    .background(GreenPrimary.copy(alpha = 0.2f), CircleShape)
            ) {
                Icon(Icons.Default.Add, contentDescription = "Add", tint = GreenPrimary)
            }
        }
        
        // Title & Sort
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Bottom
        ) {
            Column {
                Text(
                    text = "My Saved Plans",
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onBackground
                )
                Text(
                    text = "$savedCount upcoming adventures",
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Medium,
                    color = Color.Gray
                )
            }
            
            IconButton(
                onClick = { /* Sort */ },
                modifier = Modifier
                    .size(40.dp)
                    .background(Color(0xFF1c3024), CircleShape) // Surface Dark basically
            ) {
                Icon(Icons.Default.FilterList, contentDescription = "Sort", tint = Color(0xFF93c8a9)) // Text Secondary
            }
        }
        
        Spacer(modifier = Modifier.height(24.dp))
        
        // Filter Chips
        val filters = listOf("All Plans", "Upcoming", "Completed", "Drafts")
        LazyRow(
            contentPadding = PaddingValues(horizontal = 20.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(filters) { filter ->
                val isSelected = filter == "All Plans"
                Button(
                    onClick = { },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (isSelected) GreenPrimary else Color(0xFF1c3024),
                        contentColor = if (isSelected) BackgroundDark else Color(0xFF93c8a9)
                    ),
                    shape = RoundedCornerShape(50),
                    contentPadding = PaddingValues(horizontal = 20.dp, vertical = 0.dp),
                    modifier = Modifier.height(36.dp)
                ) {
                    Text(
                        text = filter,
                        style = MaterialTheme.typography.bodySmall,
                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium
                    )
                }
            }
        }
    }
}

@Composable
fun SavedPlanCard(
    imageUrl: String,
    title: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    location: String,
    duration: String,
    createdTime: String,
    badgeText: String?,
    badgeIcon: androidx.compose.ui.graphics.vector.ImageVector?,
    badgeColor: Color?,
    isDraft: Boolean
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(340.dp), // Approximate height from reference
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
             containerColor = if (isSystemInDarkTheme()) Color(0xFF1c3024) else Color.White
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            // Image Section
            Box(
                modifier = Modifier
                    .height(224.dp)
                    .fillMaxWidth()
                    .background(Color.Gray)
            ) {
                 AsyncImage(
                    model = imageUrl,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
                // Overlay
                Box(modifier = Modifier.fillMaxSize().background(Brush.verticalGradient(listOf(Color.Black.copy(alpha = 0.6f), Color.Transparent))))
                
                // Badge
                if (badgeText != null && badgeColor != null) {
                    Box(
                        modifier = Modifier
                            .padding(16.dp)
                            .align(Alignment.TopStart)
                            .background(badgeColor, RoundedCornerShape(50))
                            .border(1.dp, Color.White.copy(alpha = 0.1f), RoundedCornerShape(50))
                            .padding(horizontal = 12.dp, vertical = 6.dp)
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                            if (badgeIcon != null) {
                                Icon(badgeIcon, contentDescription = null, tint = if (isDraft) Color.Black else GreenPrimary, modifier = Modifier.size(16.dp))
                            }
                            Text(
                                text = badgeText,
                                color = if (isDraft) Color.Black else Color.White,
                                style = MaterialTheme.typography.labelSmall,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
                
                // More Menu
                IconButton(
                    onClick = { },
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(16.dp)
                        .size(32.dp)
                        .background(Color.Black.copy(alpha = 0.4f), CircleShape)
                ) {
                    Icon(Icons.Default.MoreHoriz, contentDescription = "Menu", tint = Color.White)
                }
            }
            
            // Content Section
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Top,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = title,
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier.weight(1f)
                    )
                    Box(
                        modifier = Modifier
                            .size(32.dp)
                            .background(GreenPrimary.copy(alpha = 0.1f), CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                         Icon(icon, contentDescription = null, tint = GreenPrimary, modifier = Modifier.size(20.dp))
                    }
                }
                
                // Info Row
                Row(
                   verticalAlignment = Alignment.CenterVertically,
                   horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                     Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                         Icon(Icons.Default.LocationOn, contentDescription = null, tint = GreenPrimary, modifier = Modifier.size(18.dp))
                         Text(text = location, color = Color.Gray, style = MaterialTheme.typography.bodyMedium)
                     }
                     Box(modifier = Modifier.size(4.dp).background(Color.Gray, CircleShape))
                      Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                         Icon(Icons.Default.Schedule, contentDescription = null, tint = GreenPrimary, modifier = Modifier.size(18.dp))
                         Text(text = duration, color = Color.Gray, style = MaterialTheme.typography.bodyMedium)
                     }
                }
                
                Divider(color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.05f))
                
                // Footer
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = createdTime,
                        style = MaterialTheme.typography.labelMedium,
                        color = Color.Gray,
                        fontWeight = FontWeight.Medium
                    )
                    
                    Button(
                        onClick = { },
                         colors = ButtonDefaults.buttonColors(
                            containerColor = if (isDraft) Color.White.copy(alpha = 0.1f) else GreenPrimary,
                            contentColor = if (isDraft) Color.White else BackgroundDark
                        ),
                        shape = RoundedCornerShape(50),
                        contentPadding = PaddingValues(horizontal = 24.dp, vertical = 0.dp),
                        modifier = Modifier.height(40.dp)
                    ) {
                        Text(
                            text = if (isDraft) "Continue Edit" else "View Plan",
                            style = MaterialTheme.typography.titleSmall,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}

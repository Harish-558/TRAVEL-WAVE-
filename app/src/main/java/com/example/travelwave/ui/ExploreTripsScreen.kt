package com.example.travelwave.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Tune
import androidx.compose.material.icons.outlined.Explore
import androidx.compose.material.icons.outlined.Hiking
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.PersonPin
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.travelwave.data.MockData
import com.example.travelwave.ui.theme.GreenPrimary
import com.example.travelwave.ui.theme.TravelWaveTheme

@Composable
fun ExploreTripsScreen(
    savedTrips: Set<TripData>,
    onToggleSave: (TripData) -> Unit,
    searchQuery: String,
    onSearchQueryChange: (String) -> Unit,
    onTripClick: (TripData) -> Unit
) {
    var selectedCategory by remember { mutableStateOf<String?>(null) }
    
    val filteredTrips = MockData.exploreTrips.filter { trip ->
        val matchesSearch = if (searchQuery.isBlank()) true else {
            trip.title.contains(searchQuery, ignoreCase = true) ||
            trip.tag?.contains(searchQuery, ignoreCase = true) == true
        }
        
        val matchesCategory = if (selectedCategory == null) true else {
            // Map category names to relevant tags or keywords in title
            when (selectedCategory) {
                "Adventures" -> trip.tag?.contains("Adventure", true) == true || trip.tag?.contains("Hike", true) == true || trip.title.contains("Trek", true) || trip.title.contains("Hike", true) || trip.title.contains("Safari", true) || trip.title.contains("Expedition", true)
                "Stays" -> trip.tag?.contains("Cruise", true) == true || trip.tag?.contains("Camping", true) == true || trip.title.contains("Houseboat", true) || trip.title.contains("Retreat", true)
                "Tours" -> trip.tag?.contains("Tour", true) == true || trip.tag?.contains("Heritage", true) == true || trip.tag?.contains("Architecture", true) == true || trip.tag?.contains("History", true) == true || trip.title.contains("Tour", true)
                "Guides" -> trip.tag?.contains("Experience", true) == true || trip.tag?.contains("Culture", true) == true || trip.tag?.contains("Wildlife", true) == true || trip.title.contains("Exploration", true) || trip.title.contains("Walk", true)
                else -> true
            }
        }
        
        matchesSearch && matchesCategory
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        ExploreHeader()
        ExploreSearchBar(
            searchQuery = searchQuery,
            onSearchQueryChange = onSearchQueryChange
        )
        ExploreCategories(
            selectedCategory = selectedCategory,
            onCategorySelected = { category ->
                selectedCategory = if (selectedCategory == category) null else category
            }
        )
        TripsMasonryGrid(
            savedTrips = savedTrips,
            trips = filteredTrips,
            onToggleSave = onToggleSave,
            onTripClick = onTripClick
        )
    }
}

@Composable
fun ExploreHeader() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        // Back Button
        Box(
            modifier = Modifier
                .size(40.dp)
                .background(MaterialTheme.colorScheme.surface, CircleShape)
                .clip(CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBackIosNew,
                contentDescription = "Back",
                tint = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.size(20.dp)
            )
        }

        Text(
            text = "Explore All Trips",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground
        )

        // Notification Button
        Box(
            modifier = Modifier
                .size(40.dp)
                .background(MaterialTheme.colorScheme.surface, CircleShape)
                .clip(CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.Notifications,
                contentDescription = "Notifications",
                tint = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.size(24.dp)
            )
        }
    }
}

@Composable
fun ExploreSearchBar(
    searchQuery: String,
    onSearchQueryChange: (String) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 2.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        // Search Input
        Row(
            modifier = Modifier
                .weight(1f)
                .height(52.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(MaterialTheme.colorScheme.surface)
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search",
                tint = GreenPrimary,
                modifier = Modifier.size(24.dp)
            )
            androidx.compose.material3.TextField(
                value = searchQuery,
                onValueChange = onSearchQueryChange,
                placeholder = { Text("Where to next?", color = Color.Gray, style = MaterialTheme.typography.bodyMedium) },
                colors = androidx.compose.material3.TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                singleLine = true,
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
fun ExploreCategories(
    selectedCategory: String?,
    onCategorySelected: (String) -> Unit
) {
    val categories = listOf(
        CategoryItem("Tours", Icons.Outlined.Explore, selectedCategory == "Tours"),
        CategoryItem("Stays", Icons.Outlined.Home, selectedCategory == "Stays"),
        CategoryItem("Adventures", Icons.Outlined.Hiking, selectedCategory == "Adventures"),
        CategoryItem("Guides", Icons.Outlined.PersonPin, selectedCategory == "Guides")
    )

    LazyRow(
        contentPadding = PaddingValues(horizontal = 20.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.padding(vertical = 16.dp)
    ) {
        items(categories) { category ->
            Row(
                modifier = Modifier
                    .height(36.dp)
                    .clip(RoundedCornerShape(50))
                    .background(if (category.isSelected) GreenPrimary else MaterialTheme.colorScheme.surface)
                    .clickable { onCategorySelected(category.text) }
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Icon(
                    imageVector = category.icon,
                    contentDescription = category.text,
                    tint = if (category.isSelected) Color.White else MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier.size(18.dp)
                )
                Text(
                    text = category.text,
                    style = MaterialTheme.typography.labelLarge,
                    fontWeight = if (category.isSelected) FontWeight.SemiBold else FontWeight.Medium,
                    color = if (category.isSelected) Color.White else MaterialTheme.colorScheme.onBackground
                )
            }
        }
    }
}

data class CategoryItem(val text: String, val icon: ImageVector, val isSelected: Boolean)

@Composable
fun TripsMasonryGrid(
    savedTrips: Set<TripData>,
    trips: List<TripData>,
    onToggleSave: (TripData) -> Unit,
    onTripClick: (TripData) -> Unit
) {
    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(2),
        contentPadding = PaddingValues(start = 20.dp, end = 20.dp, bottom = 100.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalItemSpacing = 12.dp,
        modifier = Modifier.fillMaxSize()
    ) {
        items(trips) { trip ->
            TripCard(
                trip = trip,
                isSaved = savedTrips.contains(trip),
                onToggleSave = { onToggleSave(trip) },
                onClick = { onTripClick(trip) }
            )
        }
    }
}

@Composable
fun TripCard(
    trip: TripData,
    isSaved: Boolean,
    onToggleSave: () -> Unit,
    onClick: (TripData) -> Unit
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick(trip) }
    ) {
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(trip.aspectRatio)
            ) {
                AsyncImage(
                    model = trip.imageUrl,
                    contentDescription = trip.title,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )

                // Favorite Icon
                Box(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(8.dp)
                        .size(28.dp)
                        .background(Color.White.copy(alpha = 0.9f), CircleShape)
                        .clip(CircleShape)
                        .clickable { onToggleSave() },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Favorite,
                        contentDescription = "Favorite",
                        tint = if (isSaved) Color(0xFFF5876F) else Color.Gray,
                        modifier = Modifier.size(16.dp)
                    )
                }

                // Tag
                if (trip.tag != null) {
                    Box(
                        modifier = Modifier
                            .align(Alignment.BottomStart)
                            .padding(8.dp)
                            .background(trip.tagColor.copy(alpha = 0.9f), RoundedCornerShape(8.dp))
                            .padding(horizontal = 8.dp, vertical = 2.dp)
                    ) {
                        Text(
                            text = trip.tag.uppercase(),
                            color = Color.White,
                            style = MaterialTheme.typography.labelSmall,
                            fontSize = 9.sp,
                            fontWeight = FontWeight.Bold,
                            letterSpacing = 0.5.sp
                        )
                    }
                }
            }

            Column(modifier = Modifier.padding(12.dp)) {
                Text(
                    text = trip.title,
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(6.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = null,
                        tint = Color(0xFFF5876F), // accent-coral
                        modifier = Modifier.size(14.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = trip.rating,
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Spacer(modifier = Modifier.width(2.dp))
                    Text(
                        text = trip.reviews,
                        style = MaterialTheme.typography.labelSmall,
                        color = Color.Gray
                    )
                }
                Spacer(modifier = Modifier.height(4.dp))
                Row(verticalAlignment = Alignment.Bottom) {
                    Text(
                        text = trip.price,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = GreenPrimary
                    )
                    Spacer(modifier = Modifier.width(2.dp))
                    Text(
                        text = trip.unit,
                        style = MaterialTheme.typography.labelSmall,
                        color = Color.Gray
                    )
                }
            }
        }
    }
}

data class TripData(
    val imageUrl: String,
    val title: String,
    val rating: String,
    val reviews: String,
    val price: String,
    val unit: String,
    val tag: String?,
    val tagColor: Color,
    val aspectRatio: Float,
    val days: Int = (title.length % 4) + 2
) {
    val nights: Int get() = days - 1
}

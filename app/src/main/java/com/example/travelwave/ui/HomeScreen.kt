package com.example.travelwave.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Tune
import androidx.compose.material.icons.rounded.AutoAwesome
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.travelwave.data.MockData
import com.example.travelwave.ui.theme.GreenPrimary
import com.example.travelwave.ui.theme.TravelWaveTheme

@Composable
fun HomeContent(
    savedTrips: Set<TripData>,
    onToggleSave: (TripData) -> Unit,
    userLocation: String,
    onUserLocationChange: (String) -> Unit,
    searchQuery: String,
    onSearchQueryChange: (String) -> Unit,
    onNavigateToProfile: () -> Unit,
    onNavigateToNotifications: () -> Unit,
    onSeeAllHotDeals: (List<TripData>) -> Unit,
    onSeeAllTopPicks: (List<TripData>) -> Unit,
    onSeeAllPopular: (List<TripData>) -> Unit,
    onTripClick: (TripData) -> Unit,
    onCompanyClick: (com.example.travelwave.data.CompanyData) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        HeaderSection(
            userLocation = userLocation,
            onUserLocationChange = onUserLocationChange,
            onProfileClick = onNavigateToProfile,
            onNotificationClick = onNavigateToNotifications
        )

        Spacer(modifier = Modifier.height(16.dp))

        SearchBar(
            searchQuery = searchQuery,
            onSearchQueryChange = onSearchQueryChange
        )

        Spacer(modifier = Modifier.height(24.dp))

        if (searchQuery.isBlank()) {
            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                item {
                    HeroCarousel(
                        savedTrips = savedTrips,
                        onToggleSave = onToggleSave,
                        onSeeAll = onSeeAllHotDeals,
                        onTripClick = onTripClick
                    )
                }
                item {
                    TopPicksSection(
                        savedTrips = savedTrips,
                        onToggleSave = onToggleSave,
                        onSeeAll = onSeeAllTopPicks,
                        onTripClick = onTripClick
                    )
                }
                item {
                    CompaniesSection(onCompanyClick = onCompanyClick)
                }
                item {
                    PopularDestinations(
                        savedTrips = savedTrips,
                        onToggleSave = onToggleSave,
                        onSeeAll = onSeeAllPopular,
                        onTripClick = onTripClick
                    )
                }
            }
        } else {
            val filteredTrips = MockData.exploreTrips.filter {
                it.title.contains(searchQuery, ignoreCase = true) ||
                (it.tag?.contains(searchQuery, ignoreCase = true) == true)
            }
            TripsMasonryGrid(
                savedTrips = savedTrips,
                trips = filteredTrips,
                onToggleSave = onToggleSave,
                onTripClick = onTripClick
            )
        }
    }
}

@Composable
fun HeaderSection(
    userLocation: String,
    onUserLocationChange: (String) -> Unit,
    onProfileClick: () -> Unit,
    onNotificationClick: () -> Unit
) {
    var showLocationDialog by remember { mutableStateOf(false) }

    if (showLocationDialog) {
        var tempLocation by remember { mutableStateOf(userLocation) }
        AlertDialog(
            onDismissRequest = { showLocationDialog = false },
            title = { Text("Update Location") },
            text = {
                OutlinedTextField(
                    value = tempLocation,
                    onValueChange = { tempLocation = it },
                    label = { Text("Location") },
                    singleLine = true
                )
            },
            confirmButton = {
                TextButton(onClick = {
                    onUserLocationChange(tempLocation)
                    showLocationDialog = false
                }) {
                    Text("Save")
                }
            },
            dismissButton = {
                TextButton(onClick = { showLocationDialog = false }) {
                    Text("Cancel")
                }
            }
        )
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        // Profile Image
        AsyncImage(
            model = "https://lh3.googleusercontent.com/aida-public/AB6AXuAPbgn2GQyeRnv4lXRwRSPjWp2kz1PZeVgUO-OVX6MZDVXZM3lHIrlBMA1Chc4YbRwfYB_N5n1dd0nDA9yMNNqh-T-wa1z6MjjxH8Jg9RWMazfLayqsdEFIM9wvx6PXdc_T9gzM628-68K4F7npCL5nhFKU0DYVJIKx8WvjKBrdF1HiPu8JII_pwL4_t4Hj6Lt_IhQEWzDBi8FjIpE-3iMMBhqrPvkiv285PA6krOZZX8GEA9zrkAfPzBC10XQjJCGfgbXHG9MwM4M",
            contentDescription = "User Profile",
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(Color.Gray)
                .clickable { onProfileClick() },
            contentScale = ContentScale.Crop
        )

        // Location
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "Current Location",
                style = MaterialTheme.typography.labelSmall,
                color = Color.Gray
            )
            Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.clickable { showLocationDialog = true }) {
                Icon(
                    imageVector = Icons.Default.LocationOn,
                    contentDescription = null,
                    tint = GreenPrimary,
                    modifier = Modifier.size(20.dp)
                )
                Text(
                    text = userLocation,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onBackground
                )
                Icon(
                    imageVector = Icons.Default.ExpandMore,
                    contentDescription = null,
                    tint = Color.Gray,
                    modifier = Modifier.size(18.dp)
                )
            }
        }

        // Notification
        Box {
            IconButton(
                onClick = { onNotificationClick() },
                modifier = Modifier
                    .size(40.dp)
                    .background(Color.White.copy(alpha = 0.1f), CircleShape)
            ) {
                Icon(
                    imageVector = Icons.Default.Notifications,
                    contentDescription = "Notifications",
                    tint = MaterialTheme.colorScheme.onBackground
                )
            }
            Box(
                modifier = Modifier
                    .size(10.dp)
                    .background(GreenPrimary, CircleShape)
                    .align(Alignment.TopEnd)
                    .padding(2.dp)
            )
        }
    }
}

@Composable
fun SearchBar(
    searchQuery: String,
    onSearchQueryChange: (String) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 6.dp)
            .height(56.dp)
            .background(MaterialTheme.colorScheme.surface, RoundedCornerShape(28.dp))
            .padding(horizontal = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        androidx.compose.material3.TextField(
            value = searchQuery,
            onValueChange = onSearchQueryChange,
            placeholder = { Text("Where to next?", color = Color.Gray, style = MaterialTheme.typography.bodyMedium) },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search",
                    tint = GreenPrimary,
                    modifier = Modifier.size(24.dp)
                )
            },
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

@Composable
fun HeroCarousel(
    savedTrips: Set<TripData>,
    onToggleSave: (TripData) -> Unit,
    onSeeAll: (List<TripData>) -> Unit,
    onTripClick: (TripData) -> Unit
) {
    Column(modifier = Modifier.padding(bottom = 0.dp)) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "HOT DEALS",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground
            )
            Text(
                text = "See all",
                color = GreenPrimary,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.clickable {
                    onSeeAll(MockData.extendedHotDeals)
                }
            )
        }

        val himalayaTrek = TripData(
            imageUrl = "https://picsum.photos/id/1015/600/400",
            title = "Himalayan Trekking",
            rating = "4.9",
            reviews = "(320)",
            price = "₹5,500",
            unit = "/person",
            tag = "Adventure",
            tagColor = GreenPrimary,
            aspectRatio = 1f
        )
        val goaBeaches = TripData(
            imageUrl = "https://picsum.photos/id/1016/600/400",
            title = "Goa Beach Party",
            rating = "4.7",
            reviews = "(850)",
            price = "₹3,500",
            unit = "/person",
            tag = "Beach",
            tagColor = Color(0xFF3B82F6),
            aspectRatio = 1f
        )
        val keralaBackwaters = TripData(
            imageUrl = "https://picsum.photos/id/1018/600/400",
            title = "Kerala Houseboat Cruise",
            rating = "4.8",
            reviews = "(410)",
            price = "₹6,000",
            unit = "/night",
            tag = "Relaxation",
            tagColor = Color(0xFF10B981),
            aspectRatio = 1f
        )
        val ladakhBikeTrip = TripData(
            imageUrl = "https://picsum.photos/id/1036/600/400",
            title = "Ladakh Bike Expedition",
            rating = "5.0",
            reviews = "(530)",
            price = "₹12,000",
            unit = "/trip",
            tag = "Road Trip",
            tagColor = Color(0xFFF59E0B),
            aspectRatio = 1f
        )
        val andamanScuba = TripData(
            imageUrl = "https://picsum.photos/id/1043/600/400",
            title = "Andaman Scuba Diving",
            rating = "4.9",
            reviews = "(620)",
            price = "₹4,500",
            unit = "/dive",
            tag = "Water Sports",
            tagColor = Color(0xFF0EA5E9),
            aspectRatio = 1f
        )
        val rajasthanSafari = TripData(
            imageUrl = "https://picsum.photos/id/1044/600/400",
            title = "Rajasthan Desert Safari",
            rating = "4.6",
            reviews = "(290)",
            price = "₹2,800",
            unit = "/person",
            tag = "Safari",
            tagColor = Color(0xFFD97706),
            aspectRatio = 1f
        )

        LazyRow(
            contentPadding = PaddingValues(horizontal = 20.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.padding(top = 12.dp, bottom = 24.dp)
        ) {
            item {
                HeroCard(
                    tripData = himalayaTrek,
                    subtitle = "Explore the peaks of Manali",
                    badgeText = "20% OFF",
                    badgeColor = GreenPrimary,
                    badgeTextColor = Color.Black,
                    isSaved = savedTrips.contains(himalayaTrek),
                    onToggleSave = { onToggleSave(himalayaTrek) },
                    onClick = { onTripClick(himalayaTrek) }
                )
            }
            item {
                HeroCard(
                    tripData = goaBeaches,
                    subtitle = "Experience the nightlife of Goa",
                    badgeText = "New",
                    badgeColor = Color.White,
                    badgeTextColor = Color.Black,
                    isSaved = savedTrips.contains(goaBeaches),
                    onToggleSave = { onToggleSave(goaBeaches) },
                    onClick = { onTripClick(goaBeaches) }
                )
            }
            item {
                HeroCard(
                    tripData = keralaBackwaters,
                    subtitle = "Serene backwaters of Alleppey",
                    badgeText = "15% OFF",
                    badgeColor = GreenPrimary,
                    badgeTextColor = Color.Black,
                    isSaved = savedTrips.contains(keralaBackwaters),
                    onToggleSave = { onToggleSave(keralaBackwaters) },
                    onClick = { onTripClick(keralaBackwaters) }
                )
            }
            item {
                HeroCard(
                    tripData = ladakhBikeTrip,
                    subtitle = "Thrilling rides through the Himalayas",
                    badgeText = "Top Rated",
                    badgeColor = Color(0xFFFFD700),
                    badgeTextColor = Color.Black,
                    isSaved = savedTrips.contains(ladakhBikeTrip),
                    onToggleSave = { onToggleSave(ladakhBikeTrip) },
                    onClick = { onTripClick(ladakhBikeTrip) }
                )
            }
            item {
                HeroCard(
                    tripData = andamanScuba,
                    subtitle = "Explore underwater life in Havelock",
                    badgeText = "Trending",
                    badgeColor = Color(0xFFFF5722),
                    badgeTextColor = Color.White,
                    isSaved = savedTrips.contains(andamanScuba),
                    onToggleSave = { onToggleSave(andamanScuba) },
                    onClick = { onTripClick(andamanScuba) }
                )
            }
            item {
                HeroCard(
                    tripData = rajasthanSafari,
                    subtitle = "Camp under the stars in Jaisalmer",
                    badgeText = "Special",
                    badgeColor = Color.White,
                    badgeTextColor = Color.Black,
                    isSaved = savedTrips.contains(rajasthanSafari),
                    onToggleSave = { onToggleSave(rajasthanSafari) },
                    onClick = { onTripClick(rajasthanSafari) }
                )
            }
        }
    }
}

@Composable
fun HeroCard(
    tripData: TripData,
    subtitle: String,
    badgeText: String,
    badgeColor: Color,
    badgeTextColor: Color,
    isSaved: Boolean,
    onToggleSave: () -> Unit,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .width(320.dp)
            .height(192.dp)
            .clip(RoundedCornerShape(16.dp))
            .clickable { onClick() }
    ) {
        AsyncImage(
            model = tripData.imageUrl,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(Color.Transparent, Color.Black.copy(alpha = 0.8f))
                    )
                )
        )

        // Favorite Icon
        Box(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(16.dp)
                .size(32.dp)
                .background(Color.White.copy(alpha = 0.9f), CircleShape)
                .clip(CircleShape)
                .clickable { onToggleSave() },
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.Favorite,
                contentDescription = "Favorite",
                tint = if (isSaved) Color(0xFFF5876F) else Color.Gray,
                modifier = Modifier.size(18.dp)
            )
        }

        Column(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(20.dp)
        ) {
            Box(
                modifier = Modifier
                    .background(badgeColor, RoundedCornerShape(50))
                    .padding(horizontal = 12.dp, vertical = 4.dp)
            ) {
                Text(
                    text = badgeText,
                    color = badgeTextColor,
                    style = MaterialTheme.typography.labelSmall,
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = tripData.title,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            Text(
                text = subtitle,
                style = MaterialTheme.typography.bodySmall,
                color = Color.LightGray
            )
        }
    }
}

@Composable
fun TopPicksSection(
    savedTrips: Set<TripData>,
    onToggleSave: (TripData) -> Unit,
    onSeeAll: (List<TripData>) -> Unit,
    onTripClick: (TripData) -> Unit
) {
    Column(modifier = Modifier.padding(bottom = 24.dp)) {
        val rishikesh = TripData(
            imageUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/7/74/Trayambakeshwar_Temple_VK.jpg/960px-Trayambakeshwar_Temple_VK.jpg",
            title = "Rishikesh River Rafting",
            rating = "4.9",
            reviews = "(450)",
            price = "₹1,200",
            unit = "/ person",
            tag = "Guided Tour",
            tagColor = GreenPrimary,
            aspectRatio = 1f
        )
        val munnar = TripData(
            imageUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/b/b9/Munnar_Overview.jpg/960px-Munnar_Overview.jpg",
            title = "Munnar Tea Gardens Hike",
            rating = "4.7",
            reviews = "(180)",
            price = "₹800",
            unit = "/ hour",
            tag = "Activity",
            tagColor = Color.Transparent,
            aspectRatio = 1f
        )
        val jaipur = TripData(
            imageUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/4/41/East_facade_Hawa_Mahal_Jaipur_from_ground_level_%28July_2022%29_-_img_01.jpg/960px-East_facade_Hawa_Mahal_Jaipur_from_ground_level_%28July_2022%29_-_img_01.jpg",
            title = "Jaipur Cooking Masterclass",
            rating = "5.0",
            reviews = "(240)",
            price = "₹2,500",
            unit = "/ person",
            tag = "Workshop",
            tagColor = Color.Transparent,
            aspectRatio = 1f
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Top Picks Near You",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground
            )
            Text(
                text = "See all",
                color = GreenPrimary,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.clickable { onSeeAll(MockData.extendedTopPicks) }
            )
        }

        LazyRow(
            contentPadding = PaddingValues(horizontal = 20.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                TopPickCard(
                    tripData = rishikesh,
                    distance = "2.5 km",
                    isSaved = savedTrips.contains(rishikesh),
                    onToggleSave = { onToggleSave(rishikesh) },
                    onClick = { onTripClick(rishikesh) }
                )
            }
            item {
                TopPickCard(
                    tripData = munnar,
                    distance = "5.2 km",
                    isSaved = savedTrips.contains(munnar),
                    onToggleSave = { onToggleSave(munnar) },
                    onClick = { onTripClick(munnar) }
                )
            }
            item {
                TopPickCard(
                    tripData = jaipur,
                    distance = "0.8 km",
                    isSaved = savedTrips.contains(jaipur),
                    onToggleSave = { onToggleSave(jaipur) },
                    onClick = { onTripClick(jaipur) }
                )
            }
        }
    }
}

@Composable
fun TopPickCard(
    tripData: TripData,
    distance: String,
    isSaved: Boolean,
    onToggleSave: () -> Unit,
    onClick: () -> Unit
) {
    Column(modifier = Modifier.width(250.dp).clickable { onClick() }) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(0.8f)
                .clip(RoundedCornerShape(16.dp))
                .background(MaterialTheme.colorScheme.surface)
        ) {
            AsyncImage(
                model = tripData.imageUrl,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )

            // Favorite Icon
            Box(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(12.dp)
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
            // Rating
            Box(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(12.dp)
                    .background(Color.Black.copy(alpha = 0.4f), RoundedCornerShape(8.dp))
                    .padding(horizontal = 8.dp, vertical = 4.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                    Icon(Icons.Default.Star, contentDescription = null, tint = Color(0xFFFFD700), modifier = Modifier.size(14.dp))
                    Text(text = tripData.rating, color = Color.White, style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold)
                }
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = tripData.title,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.padding(vertical = 4.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(2.dp)) {
                Icon(Icons.Default.LocationOn, contentDescription = null, tint = Color.Gray, modifier = Modifier.size(14.dp))
                Text(text = distance, color = Color.Gray, style = MaterialTheme.typography.bodySmall)
            }
            Box(modifier = Modifier.size(4.dp).background(Color.Gray, CircleShape))
            Text(text = tripData.tag ?: "Activity", color = Color.Gray, style = MaterialTheme.typography.bodySmall)
        }
        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(4.dp)) {
            Text(text = tripData.price, color = GreenPrimary, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
            Text(text = tripData.unit, color = Color.Gray, style = MaterialTheme.typography.bodySmall)
        }
    }
}

@Composable
fun PopularDestinations(
    savedTrips: Set<TripData>,
    onToggleSave: (TripData) -> Unit,
    onSeeAll: (List<TripData>) -> Unit,
    onTripClick: (TripData) -> Unit
) {
    Column(modifier = Modifier.padding(horizontal = 20.dp)) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "POPULAR DESTINATION",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground
            )
            Text(
                text = "See all",
                color = GreenPrimary,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.clickable { 
                    onSeeAll(MockData.extendedPopularDestinations)
                }
            )
        }
        val varanasi = TripData(
            imageUrl = "https://picsum.photos/id/1055/400/300",
            title = "Varanasi Ganges Tour",
            rating = "4.8",
            reviews = "(510)",
            price = "₹1,000",
            unit = "/person",
            tag = "Heritage",
            tagColor = Color.Transparent,
            aspectRatio = 1f
        )
        val jaipurCity = TripData(
            imageUrl = "https://picsum.photos/id/1059/400/300",
            title = "Jaipur Palaces Visit",
            rating = "4.9",
            reviews = "(320)",
            price = "₹3,000",
            unit = "/person",
            tag = "City Tour",
            tagColor = Color.Transparent,
            aspectRatio = 1f
        )
        val agraTaj = TripData(
            imageUrl = "https://picsum.photos/id/1040/400/300",
            title = "Agra Taj Mahal",
            rating = "5.0",
            reviews = "(950)",
            price = "₹2,000",
            unit = "/person",
            tag = "Monument",
            tagColor = Color.Transparent,
            aspectRatio = 1f
        )
        val mysorePalace = TripData(
            imageUrl = "https://picsum.photos/id/1033/400/300",
            title = "Mysore Royal Tour",
            rating = "4.7",
            reviews = "(410)",
            price = "₹1,500",
            unit = "/person",
            tag = "Heritage",
            tagColor = Color.Transparent,
            aspectRatio = 1f
        )
        val mumbaiCity = TripData(
            imageUrl = "https://picsum.photos/id/1067/400/300",
            title = "Mumbai City Tour",
            rating = "4.8",
            reviews = "(620)",
            price = "₹2,500",
            unit = "/person",
            tag = "City Tour",
            tagColor = Color.Transparent,
            aspectRatio = 1f
        )
        val delhiHeritage = TripData(
            imageUrl = "https://picsum.photos/id/1065/400/300",
            title = "Delhi Heritage Walk",
            rating = "4.6",
            reviews = "(380)",
            price = "₹1,200",
            unit = "/person",
            tag = "Heritage",
            tagColor = Color.Transparent,
            aspectRatio = 1f
        )
        val udaipurLakes = TripData(
            imageUrl = "https://picsum.photos/id/1050/400/300",
            title = "Udaipur Lake Tour",
            rating = "4.9",
            reviews = "(540)",
            price = "₹3,500",
            unit = "/person",
            tag = "Nature",
            tagColor = Color.Transparent,
            aspectRatio = 1f
        )
        val chennaiTemples = TripData(
            imageUrl = "https://picsum.photos/id/1053/400/300",
            title = "Chennai Temple Trail",
            rating = "4.7",
            reviews = "(400)",
            price = "₹1,800",
            unit = "/person",
            tag = "Temple Run",
            tagColor = Color.Transparent,
            aspectRatio = 1f
        )

        Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
            Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                DestinationCard(
                    tripData = varanasi,
                    modifier = Modifier.weight(1f),
                    isSaved = savedTrips.contains(varanasi),
                    onToggleSave = { onToggleSave(varanasi) },
                    onClick = { onTripClick(varanasi) }
                )
                DestinationCard(
                    tripData = jaipurCity,
                    modifier = Modifier.weight(1f),
                    isSaved = savedTrips.contains(jaipurCity),
                    onToggleSave = { onToggleSave(jaipurCity) },
                    onClick = { onTripClick(jaipurCity) }
                )
            }
            Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                DestinationCard(
                    tripData = agraTaj,
                    modifier = Modifier.weight(1f),
                    isSaved = savedTrips.contains(agraTaj),
                    onToggleSave = { onToggleSave(agraTaj) },
                    onClick = { onTripClick(agraTaj) }
                )
                DestinationCard(
                    tripData = mysorePalace,
                    modifier = Modifier.weight(1f),
                    isSaved = savedTrips.contains(mysorePalace),
                    onToggleSave = { onToggleSave(mysorePalace) },
                    onClick = { onTripClick(mysorePalace) }
                )
            }
            Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                DestinationCard(
                    tripData = mumbaiCity,
                    modifier = Modifier.weight(1f),
                    isSaved = savedTrips.contains(mumbaiCity),
                    onToggleSave = { onToggleSave(mumbaiCity) },
                    onClick = { onTripClick(mumbaiCity) }
                )
                DestinationCard(
                    tripData = delhiHeritage,
                    modifier = Modifier.weight(1f),
                    isSaved = savedTrips.contains(delhiHeritage),
                    onToggleSave = { onToggleSave(delhiHeritage) },
                    onClick = { onTripClick(delhiHeritage) }
                )
            }
            Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                DestinationCard(
                    tripData = udaipurLakes,
                    modifier = Modifier.weight(1f),
                    isSaved = savedTrips.contains(udaipurLakes),
                    onToggleSave = { onToggleSave(udaipurLakes) },
                    onClick = { onTripClick(udaipurLakes) }
                )
                DestinationCard(
                    tripData = chennaiTemples,
                    modifier = Modifier.weight(1f),
                    isSaved = savedTrips.contains(chennaiTemples),
                    onToggleSave = { onToggleSave(chennaiTemples) },
                    onClick = { onTripClick(chennaiTemples) }
                )
            }
        }
    }
}

@Composable
fun DestinationCard(
    tripData: TripData,
    modifier: Modifier = Modifier,
    isSaved: Boolean,
    onToggleSave: () -> Unit,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .height(128.dp)
            .clip(RoundedCornerShape(16.dp))
            .clickable { onClick() }
    ) {
        AsyncImage(
            model = tripData.imageUrl,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.3f))
        )
        
        // Favorite Icon
        Box(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(8.dp)
                .size(24.dp)
                .background(Color.White.copy(alpha = 0.9f), CircleShape)
                .clip(CircleShape)
                .clickable { onToggleSave() },
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.Favorite,
                contentDescription = "Favorite",
                tint = if (isSaved) Color(0xFFF5876F) else Color.Gray,
                modifier = Modifier.size(14.dp)
            )
        }
        
        Text(
            text = tripData.title.substringBefore(" "), // Taking first word as city conceptually
            color = Color.White,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@Composable
fun PopularCompaniesSection() {
    Column(modifier = Modifier.padding(horizontal = 20.dp, vertical = 24.dp)) {
        Text(
            text = "Popular Tourism Companies",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        
        val companies = listOf(
            CompanyItem("TripAdvisor", "https://picsum.photos/id/10/80/80"),
            CompanyItem("Expedia", "https://picsum.photos/id/11/80/80"),
            CompanyItem("Booking", "https://picsum.photos/id/12/80/80"),
            CompanyItem("Airbnb", "https://picsum.photos/id/13/80/80"),
            CompanyItem("MakeMyTrip", "https://picsum.photos/id/14/80/80"),
            CompanyItem("Agoda", "https://picsum.photos/id/15/80/80"),
            CompanyItem("Skyscanner", "https://picsum.photos/id/16/80/80"),
            CompanyItem("Cleartrip", "https://picsum.photos/id/17/80/80"),
            CompanyItem("Trivago", "https://picsum.photos/id/18/80/80")
        )
        
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(0.dp)
        ) {
            items(companies) { company ->
                CompanyCard(company)
            }
        }
    }
}

@Composable
fun CompanyCard(company: CompanyItem) {
    Column(
        modifier = Modifier
            .width(100.dp)
            .clickable { },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(80.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(MaterialTheme.colorScheme.surface),
            contentAlignment = Alignment.Center
        ) {
            AsyncImage(
                model = company.imageUrl,
                contentDescription = company.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = company.name,
            style = MaterialTheme.typography.bodySmall,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.onBackground,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
    }
}

data class CompanyItem(
    val name: String,
    val imageUrl: String
)

@Composable
fun BottomNavBar(
    currentTab: Int,
    onTabSelected: (Int) -> Unit
) {
    NavigationBar(
        containerColor = Color.Transparent, 
        modifier = Modifier.background(MaterialTheme.colorScheme.background.copy(alpha = 0.95f))
    ) {
        NavigationBarItem(
            selected = currentTab == 0,
            onClick = { onTabSelected(0) },
            icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
            label = { Text("Home") },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = GreenPrimary,
                selectedTextColor = GreenPrimary,
                indicatorColor = Color.Transparent 
            )
        )
        NavigationBarItem(
            selected = currentTab == 1,
            onClick = { onTabSelected(1) },
            icon = { Icon(Icons.Default.Map, contentDescription = "Trips") },
            label = { Text("Trips") },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = GreenPrimary,
                selectedTextColor = GreenPrimary,
                indicatorColor = Color.Transparent,
                unselectedIconColor = Color.Gray,
                unselectedTextColor = Color.Gray
            )
        )
        // FAB Center Item
        Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.Center) {
            Box(
                 modifier = Modifier
                     .size(56.dp)
                     .background(GreenPrimary, CircleShape)
                     .clickable { onTabSelected(2) },
                 contentAlignment = Alignment.Center
            ) {
                 Icon(Icons.Rounded.AutoAwesome, contentDescription = "AI", tint = Color.Black, modifier = Modifier.size(32.dp))
            }
        }
        NavigationBarItem(
            selected = currentTab == 3,
            onClick = { onTabSelected(3) },
            icon = { Icon(Icons.Default.Bookmark, contentDescription = "Saved") },
            label = { Text("Saved") },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = GreenPrimary,
                selectedTextColor = GreenPrimary,
                indicatorColor = Color.Transparent,
                unselectedIconColor = Color.Gray,
                unselectedTextColor = Color.Gray
            )
        )
        NavigationBarItem(
            selected = currentTab == 4,
            onClick = { onTabSelected(4) },
            icon = { Icon(Icons.Default.Person, contentDescription = "Profile") },
            label = { Text("Profile") },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = GreenPrimary,
                selectedTextColor = GreenPrimary,
                indicatorColor = Color.Transparent,
                unselectedIconColor = Color.Gray,
                unselectedTextColor = Color.Gray
            )
        )
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    TravelWaveTheme {
        HomeContent(
            savedTrips = emptySet(),
            onToggleSave = {},
            userLocation = "India, Ongole",
            onUserLocationChange = {},
            searchQuery = "",
            onSearchQueryChange = {},
            onNavigateToProfile = {},
            onNavigateToNotifications = {},
            onSeeAllHotDeals = {},
            onSeeAllTopPicks = {},
            onSeeAllPopular = {},
            onTripClick = {},
            onCompanyClick = {}
        )
    }
}

@Composable
fun CompaniesSection(onCompanyClick: (com.example.travelwave.data.CompanyData) -> Unit) {
    Column(modifier = Modifier.padding(bottom = 24.dp)) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Companies",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground
            )
        }

        LazyRow(
            contentPadding = PaddingValues(horizontal = 20.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(com.example.travelwave.data.MockData.companies) { company ->
                CompanyCard(company = company, onClick = { onCompanyClick(company) })
            }
        }
    }
}

@Composable
fun CompanyCard(company: com.example.travelwave.data.CompanyData, onClick: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .width(100.dp)
            .clickable { onClick() }
    ) {
        AsyncImage(
            model = company.logoUrl,
            contentDescription = company.name,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(72.dp)
                .clip(CircleShape)
                .background(Color.LightGray)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = company.name,
            style = MaterialTheme.typography.bodySmall,
            fontWeight = FontWeight.Medium,
            color = MaterialTheme.colorScheme.onBackground,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}


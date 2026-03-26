package com.example.travelwave.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Bed
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.ChatBubble
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.EventBusy
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.HealthAndSafety
import androidx.compose.material.icons.filled.Hotel
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.StarHalf
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.travelwave.ui.theme.GreenPrimary
import com.example.travelwave.ui.theme.TravelWaveTheme
import androidx.compose.ui.viewinterop.AndroidView
import android.webkit.WebView
import android.webkit.WebViewClient
import android.net.Uri

@Composable
fun TripDetailsScreen(onBackClick: () -> Unit, onBookNowClick: () -> Unit, tripData: TripData? = null) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF0e110f)) // Dark background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(bottom = 100.dp) // Space for sticky bottom bar
        ) {
            HeroSection(onBackClick, tripData)
            TripInfoSection(tripData)
            QuickStatsSection(tripData)
            OverviewSection(tripData)
            ItinerarySection(tripData)
            InclusionsSection()
            LocationSection(tripData)
            ReviewsSection()
            ComparisonsSection(tripData)
            ThingsToKnowSection()
        }

        // Sticky Bottom Bar
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
        ) {
            BookingBottomBar(onBookNowClick, tripData)
        }
    }
}

@Composable
fun HeroSection(onBackClick: () -> Unit, tripData: TripData? = null) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(350.dp)
    ) {
        AsyncImage(
            model = tripData?.imageUrl ?: "https://lh3.googleusercontent.com/aida-public/AB6AXuBHa05ibx3UUOfD9hvSNKP44yTn9IarRQd4I9lndgHM0oRoNmFSRis2bqZel7ZxL1SdR6cy2UhzGWJfYoN_XeBfsChv7DQBoLfzLqijEo2o1Pxwz7ekqaCOPs5HfMFj1dQCY8d8oFJpQzb0l-Y4uuktojV8u2k_nxEGLLNUBrNIRNkCsLsvcvEHUkOErf8xbg9uDHXTYnoPXn0bTB0T-5vnpLs8KJPu0mffUrOvA9fHMCd6CRD_5EU-zP4UILf5sXmxM4gyGEZAiwY",
            contentDescription = "Trip Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        // Gradient Overlay
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color.Black.copy(alpha = 0.4f),
                            Color.Transparent,
                            Color(0xFF0e110f).copy(alpha = 0.9f),
                            Color(0xFF0e110f)
                        )
                    )
                )
        )

        // Top Actions
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 48.dp, start = 16.dp, end = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            IconButton(
                onClick = { onBackClick() },
                modifier = Modifier
                    .size(44.dp)
                    .background(Color.Black.copy(alpha = 0.4f), CircleShape)
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBackIosNew,
                    contentDescription = "Back",
                    tint = Color.White,
                    modifier = Modifier.size(20.dp)
                )
            }
            IconButton(
                onClick = { },
                modifier = Modifier
                    .size(44.dp)
                    .background(Color.Black.copy(alpha = 0.4f), CircleShape)
            ) {
                Icon(
                    imageVector = Icons.Default.Share,
                    contentDescription = "Share",
                    tint = Color.White,
                    modifier = Modifier.size(20.dp)
                )
            }
        }
    }
}

@Composable
fun TripInfoSection(tripData: TripData? = null) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .padding(top = 16.dp) // Hero gradient handles visual overlap
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Top
        ) {
            Text(
                text = tripData?.title ?: "Tropical Island Escape",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.weight(1f)
            )
            Icon(
                imageVector = Icons.Default.Favorite,
                contentDescription = "Favorite",
                tint = GreenPrimary,
                modifier = Modifier.padding(start = 8.dp)
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = Icons.Default.LocationOn,
                contentDescription = null,
                tint = Color(0xFF94a3b8), // slate-400
                modifier = Modifier.size(16.dp)
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = tripData?.tag ?: "Curated Indian Experience",
                style = MaterialTheme.typography.bodyMedium,
                color = Color(0xFF94a3b8)
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .background(GreenPrimary.copy(alpha = 0.2f), RoundedCornerShape(8.dp))
                    .padding(horizontal = 8.dp, vertical = 4.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = null,
                        tint = GreenPrimary,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = tripData?.rating ?: "4.8",
                        style = MaterialTheme.typography.labelLarge,
                        fontWeight = FontWeight.Bold,
                        color = GreenPrimary
                    )
                }
            }
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = tripData?.reviews ?: "1.2k Reviews",
                style = MaterialTheme.typography.bodyMedium,
                color = Color(0xFF94a3b8)
            )
        }
    }
}

@Composable
fun QuickStatsSection(tripData: TripData? = null) {
    val days = tripData?.days ?: 4
    val nights = tripData?.nights ?: 3
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        QuickStatItem(Icons.Default.CalendarToday, "$days Days")
        QuickStatItem(Icons.Default.DarkMode, "$nights Nights")
        QuickStatItem(Icons.Default.Restaurant, "All Meals")
        QuickStatItem(Icons.Default.Hotel, "4-Star")
    }
}

@Composable
fun QuickStatItem(icon: ImageVector, text: String) {
    Column(
        modifier = Modifier
            .size(80.dp)
            .background(Color(0xFF161b18), RoundedCornerShape(16.dp))
            .border(1.dp, Color.White.copy(alpha = 0.05f), RoundedCornerShape(16.dp)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = GreenPrimary,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = text,
            style = MaterialTheme.typography.labelSmall,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF94a3b8),
            fontSize = 10.sp
        )
    }
}

@Composable
fun OverviewSection(tripData: TripData? = null) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "Plan Overview",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = tripData?.let { "${it.title} - ${it.price} ${it.unit}" } ?: "Experience the ultimate getaway in our curated Indian escape. From beautiful hills to ancient temples, every detail is designed for memorable experiences. Perfect for couples and small families looking to explore.",
            style = MaterialTheme.typography.bodyMedium,
            color = Color(0xFF94a3b8),
            lineHeight = 22.sp
        )
    }
}

@Composable
fun ItinerarySection(tripData: TripData? = null) {
    val days = tripData?.days ?: 4
    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "Detailed Itinerary",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
        Spacer(modifier = Modifier.height(12.dp))

        for (day in 1..days) {
            val title = when (day) {
                1 -> "Arrival & Check-in"
                days -> "Departure"
                else -> "Local Sightseeing & Activities"
            }
            val details = when (day) {
                1 -> "Welcome to the destination. Transfer to resort, relax and enjoy the evening."
                days -> "Pack your memories. Private transfer to the airport for your onward journey."
                else -> "Experience the best of the destination with guided tours, free time for shopping, and exquisite local cuisine."
            }
            
            ItineraryDayItem(day = day, title = title, details = details, isExpandedInitial = (day == 1))
            
            if (day < days) {
                Spacer(modifier = Modifier.height(12.dp))
            }
        }
    }
}

@Composable
fun ItineraryDayItem(day: Int, title: String, details: String, isExpandedInitial: Boolean) {
    var isExpanded by remember { mutableStateOf(isExpandedInitial) }

    if (isExpanded) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF161b18), RoundedCornerShape(12.dp))
                .border(2.dp, Color(0xFF161b18), RoundedCornerShape(12.dp))
                .clickable { isExpanded = false }
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF161b18), RoundedCornerShape(12.dp))
            ) {
                 Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Top
                ) {
                     Box(
                         modifier = Modifier
                             .width(4.dp)
                             .height(40.dp)
                             .background(GreenPrimary, RoundedCornerShape(2.dp))
                     )
                     Spacer(modifier = Modifier.width(12.dp))
                     Column(modifier = Modifier.weight(1f)) {
                         Text(
                             text = "DAY $day",
                             style = MaterialTheme.typography.labelSmall,
                             fontWeight = FontWeight.Bold,
                             color = GreenPrimary
                         )
                         Text(
                             text = title,
                             style = MaterialTheme.typography.titleMedium,
                             fontWeight = FontWeight.Bold,
                             color = Color.White
                         )
                         Spacer(modifier = Modifier.height(8.dp))
                         Text(
                             text = details,
                             style = MaterialTheme.typography.bodySmall,
                             color = Color(0xFF94a3b8)
                         )
                     }
                     Icon(
                         imageVector = Icons.Default.ExpandLess,
                         contentDescription = null,
                         tint = Color.Gray
                     )
                 }
            }
        }
    } else {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF161b18), RoundedCornerShape(12.dp))
                .clickable { isExpanded = true }
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
             Row(verticalAlignment = Alignment.CenterVertically) {
                 Box(
                     modifier = Modifier
                         .width(4.dp)
                         .height(24.dp)
                         .background(Color(0xFF294233), RoundedCornerShape(2.dp))
                 )
                 Spacer(modifier = Modifier.width(12.dp))
                 Column {
                     Text(
                         text = "DAY $day",
                         style = MaterialTheme.typography.labelSmall,
                         fontWeight = FontWeight.Bold,
                         color = Color(0xFF94a3b8)
                     )
                     Text(
                         text = title,
                         style = MaterialTheme.typography.titleMedium,
                         fontWeight = FontWeight.Bold,
                         color = Color.White
                     )
                 }
             }
             Icon(
                 imageVector = Icons.Default.ExpandMore,
                 contentDescription = null,
                 tint = Color.Gray
             )
        }
    }
}

@Composable
fun InclusionsSection() {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "What's Included",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
        Spacer(modifier = Modifier.height(12.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(12.dp), modifier = Modifier.weight(1f)) {
                InclusionItem("Airport Transfers", true)
                InclusionItem("Luxury Suite", true)
            }
            Column(verticalArrangement = Arrangement.spacedBy(12.dp), modifier = Modifier.weight(1f)) {
                InclusionItem("Daily Breakfast", true)
                InclusionItem("Snorkeling Kit", true)
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "What's Not Included",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
        Spacer(modifier = Modifier.height(12.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(12.dp), modifier = Modifier.weight(1f)) {
                InclusionItem("Flight Tickets", false)
                InclusionItem("Personal Laundry", false)
            }
            Column(verticalArrangement = Arrangement.spacedBy(12.dp), modifier = Modifier.weight(1f)) {
                InclusionItem("Travel Insurance", false)
                InclusionItem("Entry Visas", false)
            }
        }
    }
}

@Composable
fun InclusionItem(text: String, isIncluded: Boolean) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
            imageVector = if (isIncluded) Icons.Default.CheckCircle else Icons.Default.Cancel,
            contentDescription = null,
            tint = if (isIncluded) GreenPrimary else Color(0xFF94a3b8).copy(alpha = 0.5f),
            modifier = Modifier.size(20.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = text,
            style = MaterialTheme.typography.bodyMedium,
            color = if (isIncluded) Color(0xFF94a3b8) else Color(0xFF94a3b8).copy(alpha = 0.5f)
        )
    }
}

fun extractLocationName(title: String): String {
    val ignoreWords = listOf(
        "Trekking", "Trek", "Party", "Houseboat", "Cruise", "Expedition", "Diving", "Dive", 
        "Safari", "Road", "Trip", "Caving", "Explore", "Exploration", "Tiger", "Snowboarding", 
        "Skiing", "Camp", "Rafting", "Tea", "Estate", "Toy", "Train", "Rhino", "Monastery", 
        "Glass", "Bottom", "Boat", "Ruins", "Quarter", "Coffee", "Stay", "Treehouse", "Darshan", 
        "Festival", "Getaway", "Table", "Land", "River", "Gardens", "Hike", "Masterclass", 
        "Fort", "Sunrise", "Temple", "Walk", "Island", "Hopping", "Desert", "Sunset", "Alps", 
        "Ride", "Colosseum", "Louvre", "Fast", "Track", "Eye", "Experience", "Helicopter", 
        "Skywalk", "Jungle", "Pyramids", "Penguin", "Beach", "Opera", "House", "Snorkel", 
        "Sky", "Jump", "Hop", "Overwater", "Villa", "Picnic", "Catamaran", "Shark", "Pearl", 
        "Farm", "Volcano", "Retreat", "Fun", "City", "Run", "Crawl", "Nightlife", "Pilgrimage", 
        "Romantic", "Luxury", "Royal", "Monument", "Architecture", "Spiritual", "Culture", "Activity"
    )
    val words = title.split(" ")
    val locationWords = words.filter { word ->
        ignoreWords.none { it.equals(word, ignoreCase = true) }
    }
    return if (locationWords.isNotEmpty()) locationWords.joinToString(" ") else title
}

@Composable
fun LocationSection(tripData: TripData? = null) {
    var mapUrl by remember { mutableStateOf<String?>(null) }
    var isLoading by remember { mutableStateOf(true) }
    val locationName = extractLocationName(tripData?.title ?: "India")

    androidx.compose.runtime.LaunchedEffect(locationName) {
        kotlinx.coroutines.withContext(kotlinx.coroutines.Dispatchers.IO) {
            var finalLat = 20.5937
            var finalLon = 78.9629
            var success = false

            try {
                val client = okhttp3.OkHttpClient()
                // Append "India" to prioritize local results
                val encodedQuery = Uri.encode(locationName + " India")
                val request = okhttp3.Request.Builder()
                    .url("https://nominatim.openstreetmap.org/search?format=json&q=${'$'}encodedQuery&limit=1")
                    .header("User-Agent", "TravelWaveApp/1.0 (contact@travelwave.com)")
                    .build()
                
                val response = client.newCall(request).execute()
                val jsonStr = response.body?.string()
                
                if (jsonStr != null && jsonStr.trim().startsWith("[")) {
                    val jsonArray = org.json.JSONArray(jsonStr)
                    if (jsonArray.length() > 0) {
                        val obj = jsonArray.getJSONObject(0)
                        finalLat = obj.getString("lat").toDouble()
                        finalLon = obj.getString("lon").toDouble()
                        success = true
                    }
                }

                // If not found, fallback to simpler query without "India"
                if (!success && locationName != "India") {
                    val fallbackQuery = Uri.encode(locationName)
                    val fallbackReq = okhttp3.Request.Builder()
                        .url("https://nominatim.openstreetmap.org/search?format=json&q=${'$'}fallbackQuery&limit=1")
                        .header("User-Agent", "TravelWaveApp/1.0 (contact@travelwave.com)")
                        .build()
                    val fallbackRes = client.newCall(fallbackReq).execute()
                    val fallbackJsonStr = fallbackRes.body?.string()
                    if (fallbackJsonStr != null && fallbackJsonStr.trim().startsWith("[")) {
                        val jsonArr = org.json.JSONArray(fallbackJsonStr)
                        if (jsonArr.length() > 0) {
                            val obj = jsonArr.getJSONObject(0)
                            finalLat = obj.getString("lat").toDouble()
                            finalLon = obj.getString("lon").toDouble()
                        }
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
            
            val offset = 0.05
            val bbox = "${'$'}{finalLon - offset},${'$'}{finalLat - offset},${'$'}{finalLon + offset},${'$'}{finalLat + offset}"
            mapUrl = "https://www.openstreetmap.org/export/embed.html?bbox=${'$'}bbox&layer=mapnik&marker=${'$'}finalLat,${'$'}finalLon"
            
            isLoading = false
        }
    }

    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "Location",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
        Spacer(modifier = Modifier.height(12.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(Color(0xFF161b18)),
            contentAlignment = Alignment.Center
        ) {
            if (isLoading) {
                androidx.compose.material3.CircularProgressIndicator(color = GreenPrimary)
            } else if (mapUrl != null) {
                AndroidView(
                    factory = { context ->
                        WebView(context).apply {
                            settings.javaScriptEnabled = true
                            webViewClient = WebViewClient()
                        }
                    },
                    update = { webView ->
                        webView.loadUrl(mapUrl!!)
                    },
                    modifier = Modifier.fillMaxSize()
                )
            } else {
                Text(
                    text = "Location map not available",
                    color = Color.Gray,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}



@Composable
fun ReviewsSection() {
     Column(modifier = Modifier.padding(16.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Traveler Reviews",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            Text(
                text = "View All",
                style = MaterialTheme.typography.labelLarge,
                fontWeight = FontWeight.Bold,
                color = GreenPrimary
            )
        }
        Spacer(modifier = Modifier.height(16.dp))

        // Review Stats Row
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF161b18), RoundedCornerShape(12.dp))
                .border(1.dp, Color.White.copy(alpha = 0.05f), RoundedCornerShape(12.dp))
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "4.8",
                    style = MaterialTheme.typography.displayMedium,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Row {
                    repeat(4) {
                        Icon(Icons.Default.Star, null, tint = GreenPrimary, modifier = Modifier.size(12.dp))
                    }
                    Icon(Icons.Default.StarHalf, null, tint = GreenPrimary, modifier = Modifier.size(12.dp))
                }
            }
            Spacer(modifier = Modifier.width(24.dp))
            Column(modifier = Modifier.weight(1f)) {
                 ReviewBar(5, 0.85f)
                 ReviewBar(4, 0.10f)
                 ReviewBar(3, 0.03f)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Horizontal Reviews List
        Row(modifier = Modifier.horizontalScroll(rememberScrollState())) {
            ReviewCard("Ananya R.", "March 2024", "Absolutely breathtaking experience! The sunset cruise was the highlight of our trip. Professional staff and luxurious accommodation.")
            Spacer(modifier = Modifier.width(12.dp))
            ReviewCard("Vikram M.", "Feb 2024", "Great value for money. The snorkeling spots recommended by the guide were amazing. Highly recommend the 4-day package.")
        }
    }
}

@Composable
fun ReviewBar(star: Int, percentage: Float) {
    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(vertical = 2.dp)) {
        Text(text = "$star", color = Color(0xFF94a3b8), fontSize = 10.sp, modifier = Modifier.width(10.dp))
        Spacer(modifier = Modifier.width(8.dp))
        Box(
            modifier = Modifier
                .weight(1f)
                .height(6.dp)
                .background(Color(0xFF294233), RoundedCornerShape(3.dp))
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(percentage)
                    .fillMaxSize()
                    .background(GreenPrimary, RoundedCornerShape(3.dp))
            )
        }
    }
}

@Composable
fun ReviewCard(name: String, date: String, text: String) {
    Column(
        modifier = Modifier
            .width(280.dp)
            .background(Color(0xFF161b18), RoundedCornerShape(12.dp))
            .border(1.dp, Color.White.copy(alpha = 0.05f), RoundedCornerShape(12.dp))
            .padding(16.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                 modifier = Modifier
                     .size(32.dp)
                     .background(Color(0xFF294233), CircleShape),
                 contentAlignment = Alignment.Center
            ) {
                Icon(Icons.Default.Person, null, tint = Color(0xFF94a3b8), modifier = Modifier.size(20.dp))
            }
            Spacer(modifier = Modifier.width(8.dp))
            Column {
                Text(text = name, style = MaterialTheme.typography.labelLarge, fontWeight = FontWeight.Bold, color = Color.White)
                Text(text = date, style = MaterialTheme.typography.labelSmall, color = Color(0xFF94a3b8))
            }
            Spacer(modifier = Modifier.weight(1f))
            Row {
                repeat(5) { Icon(Icons.Default.Star, null, tint = GreenPrimary, modifier = Modifier.size(10.dp)) }
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "\"$text\"",
            style = MaterialTheme.typography.bodySmall,
             color = Color(0xFF94a3b8),
             fontStyle = androidx.compose.ui.text.font.FontStyle.Italic,
             maxLines = 3,
             overflow = TextOverflow.Ellipsis
        )
    }
}

@Composable
fun ThingsToKnowSection() {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "Things to Know",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
        Spacer(modifier = Modifier.height(16.dp))
        ThingToKnowItem(Icons.Default.EventBusy, "Cancellation Policy", "Full refund for cancellations made 7 days prior to arrival. 50% refund for 48-hour cancellations.")
        ThingToKnowItem(Icons.Default.HealthAndSafety, "Health & Safety", "First-aid kits available at all points. Life jackets mandatory during all water activities.")
        ThingToKnowItem(Icons.Default.Description, "Entry Requirements", "Passport valid for 6 months. On-arrival visa for most nationalities. Mandatory IMUGA health declaration.")
    }
}

@Composable
fun ThingToKnowItem(icon: ImageVector, title: String, desc: String) {
    Row(
        modifier = Modifier.padding(bottom = 16.dp),
        verticalAlignment = Alignment.Top
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = GreenPrimary,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(text = title, style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.Bold, color = Color.White)
            Text(text = desc, style = MaterialTheme.typography.bodySmall, color = Color(0xFF94a3b8), lineHeight = 18.sp)
        }
    }
}

@Composable
fun ComparisonsSection(tripData: TripData?) {
    if (tripData == null) return
    val comparisons = com.example.travelwave.data.MockData.getComparisonsForTrip(tripData)
    
    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "Comparisons",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
        Spacer(modifier = Modifier.height(16.dp))
        
        comparisons.forEach { comparison ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 12.dp)
                    .background(Color(0xFF161b18), RoundedCornerShape(12.dp))
                    .border(1.dp, Color.White.copy(alpha = 0.05f), RoundedCornerShape(12.dp))
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    AsyncImage(
                        model = comparison.company.logoUrl,
                        contentDescription = "Company Logo",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(40.dp)
                            .clip(CircleShape)
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Column {
                        Text(
                            text = comparison.company.name,
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                        Text(
                            text = "Provided for ${tripData.title}",
                            style = MaterialTheme.typography.bodySmall,
                            color = Color(0xFF94a3b8),
                            modifier = Modifier.fillMaxWidth(0.6f),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }
                Text(
                    text = comparison.price,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = GreenPrimary
                )
            }
        }
    }
}

@Composable
fun BookingBottomBar(onBookNowClick: () -> Unit, tripData: TripData? = null) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF0e110f).copy(alpha = 0.95f))
            .border(1.dp, Color.White.copy(alpha = 0.05f))
            .padding(16.dp)
             // Handle Safe Area
            .padding(WindowInsets.navigationBars.asPaddingValues())
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Row(verticalAlignment = Alignment.Bottom) {
                     Text(
                        text = tripData?.price ?: "₹15,000",
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.ExtraBold,
                        color = GreenPrimary
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = tripData?.unit?.uppercase() ?: "/ PERSON",
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF94a3b8),
                        modifier = Modifier.padding(bottom = 4.dp)
                    )
                }
                Text(
                    text = "All Inclusive Price",
                    style = MaterialTheme.typography.labelSmall,
                    color = Color(0xFF94a3b8)
                )
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                 Box(
                     modifier = Modifier
                         .size(48.dp)
                         .background(Color(0xFF161b18), RoundedCornerShape(12.dp))
                         .border(1.dp, Color.White.copy(alpha = 0.1f), RoundedCornerShape(12.dp))
                         .clickable {},
                     contentAlignment = Alignment.Center
                 ) {
                     Icon(Icons.Default.ChatBubble, "Chat", tint = GreenPrimary, modifier = Modifier.size(24.dp))
                 }
                 Spacer(modifier = Modifier.width(12.dp))
                 Button(
                     onClick = onBookNowClick,
                     colors = ButtonDefaults.buttonColors(containerColor = GreenPrimary),
                     shape = RoundedCornerShape(12.dp),
                     modifier = Modifier.height(48.dp)
                 ) {
                     Text(
                         text = "Book Now",
                         style = MaterialTheme.typography.titleMedium,
                         fontWeight = FontWeight.Bold,
                         color = Color(0xFF0e110f)
                     )
                 }
            }
        }
    }
}


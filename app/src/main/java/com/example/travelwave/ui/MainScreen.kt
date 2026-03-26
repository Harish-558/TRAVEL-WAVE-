package com.example.travelwave.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.padding
import com.example.travelwave.ui.AIAssistantScreen

@Composable
fun MainScreen(
    onNavigateToProfile: () -> Unit,
    onNavigateToNotifications: () -> Unit,
    onNavigateToTripDetails: () -> Unit
) {
    var currentTab by remember { mutableStateOf(0) }
    var selectedTrip by remember { mutableStateOf<TripData?>(null) }
    var selectedCompany by remember { mutableStateOf<com.example.travelwave.data.CompanyData?>(null) }
    var savedTrips by remember { mutableStateOf(setOf<TripData>()) }
    var userLocation by remember { mutableStateOf("India, Ongole") }
    var searchQuery by remember { mutableStateOf("") }
    
    val onToggleSave: (TripData) -> Unit = { trip ->
        savedTrips = if (savedTrips.contains(trip)) {
            savedTrips - trip
        } else {
            savedTrips + trip
        }
    }
    
    // State for See All screen
    var seeAllTitle by remember { mutableStateOf("") }
    var seeAllTrips by remember { mutableStateOf<List<TripData>>(emptyList()) }

    Scaffold(
        bottomBar = { 
            BottomNavBar(
                currentTab = currentTab,
                onTabSelected = { tab ->
                    if (tab == 4) {
                        onNavigateToProfile()
                    } else {
                        currentTab = tab
                    }
                }
            ) 
        },
        containerColor = MaterialTheme.colorScheme.background
    ) { innerPadding ->
        // We apply innerPadding to the content, or handle it inside the specific screens.
        // Since HomeContent and SavedPlansContent handle their own scrolling and might want content behind status bars (edge-to-edge),
        // we pass the padding or specific sub-composables use it. 
        // For now, let's wrap in Box with padding for simplicity, or just pass it down.
        // But edge-to-edge usually means we want bottom bar to overlap or pad content.
        // Based on HomeScreen implementation, it was taking innerPadding.
        
        Box(
             modifier = Modifier
                .fillMaxSize()
        ) {
             // We adjust padding strategy based on screen needs.
             // For now conform to previous HomeScreen behavior:
             Box(modifier = androidx.compose.ui.Modifier.padding(innerPadding)) {
                 when (currentTab) {
                    0 -> HomeContent(
                        savedTrips = savedTrips,
                        onToggleSave = onToggleSave,
                        userLocation = userLocation,
                        onUserLocationChange = { userLocation = it },
                        searchQuery = searchQuery,
                        onSearchQueryChange = { searchQuery = it },
                        onNavigateToProfile = onNavigateToProfile,
                        onNavigateToNotifications = onNavigateToNotifications,
                        onSeeAllHotDeals = { trips ->
                            seeAllTitle = "Hot Deals"
                            seeAllTrips = trips
                            currentTab = 6
                        },
                        onSeeAllTopPicks = { trips ->
                            seeAllTitle = "Top Picks Near You"
                            seeAllTrips = trips
                            currentTab = 6
                        },
                        onSeeAllPopular = { trips ->
                            seeAllTitle = "Popular Destinations"
                            seeAllTrips = trips
                            currentTab = 6
                        },
                        onTripClick = { trip ->
                            selectedTrip = trip
                            currentTab = 5
                        },
                        onCompanyClick = { company ->
                            selectedCompany = company
                            currentTab = 8
                        }
                    )
                    1 -> ExploreTripsScreen(
                        savedTrips = savedTrips,
                        onToggleSave = onToggleSave,
                        searchQuery = searchQuery,
                        onSearchQueryChange = { searchQuery = it },
                        onTripClick = { trip ->
                            selectedTrip = trip
                            currentTab = 5
                        }
                    )
                    2 -> AIAssistantScreen()
                    3 -> SavedPlansContent(
                        savedTrips = savedTrips,
                        onToggleSave = onToggleSave,
                        onTripClick = { trip ->
                            selectedTrip = trip
                            currentTab = 5
                        }
                    )
                    5 -> TripDetailsScreen(
                        onBackClick = { currentTab = 0 }, // Go back to Home or previous ideally, hardcoded 0 for simple flow based on where they came from
                        onBookNowClick = { currentTab = 7 },
                        tripData = selectedTrip
                    )
                    6 -> SeeAllScreen(
                        title = seeAllTitle,
                        trips = seeAllTrips,
                        savedTrips = savedTrips,
                        onToggleSave = onToggleSave,
                        onBackClick = { currentTab = 0 },
                        onTripClick = { trip ->
                            selectedTrip = trip
                            currentTab = 5
                        }
                    )
                    7 -> PaymentScreen(
                        tripData = selectedTrip,
                        onBackClick = { currentTab = 5 },
                        onPaymentSuccess = { currentTab = 0 }
                    )
                    8 -> {
                        selectedCompany?.let { company ->
                            CompanyTripsScreen(
                                company = company,
                                savedTrips = savedTrips,
                                onToggleSave = onToggleSave,
                                onTripClick = { trip ->
                                    selectedTrip = trip
                                    currentTab = 5
                                },
                                onBackClick = { currentTab = 0 }
                            )
                        } ?: run {
                            currentTab = 0
                        }
                    }
                }
             }
        }
    }
}

@Composable
fun PlaceholderScreen(name: String) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = "$name Screen", style = MaterialTheme.typography.headlineMedium)
    }
}

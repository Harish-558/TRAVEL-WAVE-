package com.example.travelwave.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import com.example.travelwave.data.CompanyData
import com.example.travelwave.data.MockData

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CompanyTripsScreen(
    company: CompanyData,
    savedTrips: Set<TripData>,
    onToggleSave: (TripData) -> Unit,
    onTripClick: (TripData) -> Unit,
    onBackClick: () -> Unit
) {
    val trips = MockData.getTripsForCompany(company.id)
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = { 
                    Text(
                        text = "${company.name} Trips",
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back",
                            tint = MaterialTheme.colorScheme.onBackground
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            )
        },
        containerColor = MaterialTheme.colorScheme.background
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            if (trips.isEmpty()) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text("No trips available", color = Color.Gray)
                }
            } else {
                TripsMasonryGrid(
                    savedTrips = savedTrips,
                    trips = trips,
                    onToggleSave = onToggleSave,
                    onTripClick = onTripClick
                )
            }
        }
    }
}

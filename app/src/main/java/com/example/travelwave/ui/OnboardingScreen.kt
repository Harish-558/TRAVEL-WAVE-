package com.example.travelwave.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.travelwave.ui.theme.BackgroundDark
import com.example.travelwave.ui.theme.BackgroundLight
import com.example.travelwave.ui.theme.GreenPrimary

data class OnboardingSlide(
    val imageUrl: String,
    val tag: String
)

val onboardingSlides = listOf(
    OnboardingSlide(
        imageUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuD0lQx1NfCh-eQL9dYmv9hy_aVB2MHEm92f5C-3OOjTCx0vffYZ_AIFU4n-is7IQB6I9jAlBjHscNwi9QV86DEv1WQmczT3NeCZiHv83cU8aZn41YDdVmhhWWpNGOt_5oVJhWffeUhExzVkttpMdsxA80ZOz7fbL4_9NR2qYS6JbxgY_sIL3OGM5u5lkdmQMQgjFVqXiFkd6-3Xj8BHeorFL6Hnw9pjOnPJXQ7Bev89-QrGdYH8BlSsvnhBCKnDn7UuNHQ6Dv68dQs",
        tag = "TOP RATED GUIDES"
    ),
    OnboardingSlide(
        imageUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuAbGsoHqgvEaBJ0F4kzLwRqmOBeyv8gHWwsDnjJwLsbWtw5kSJo9_O-MwH1zA3PnaWCR-6LVmx8jSn37KTja5FWhP71-Lx1iXkCOI1XwR-RYonIq2_9y9zOZ4G7L2hDrKYaTBTmZFO1ZxVFN7FoaG6w42ivBj3Ca4LX0IivbPMhmvQHowhTRC0Cu7smGi1YdxdcnZqb9S4qa-dp6A06UsFw209eRq26NVc_xzI57ZM9BisO0yGbnuQFZni1CJbeufEI_8-OOKXfrak",
        tag = "UNIQUE STAYS"
    ),
    OnboardingSlide(
        imageUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuDWYN-3DGjyZUcCmeEi1o3gBdz5A6Z5db7vTXK_RhN-G-XkdyB5v2BJduuFVMszbZaMl87kZQ-bLGJUFhuBWmK7GHWUAIyxSCTin1jRRRPKjcjj679tym-_003SQSOI9TjQgW2Mv8m26-wMUGFiVxCEA46Qe5y2Ynql9iITiBPw05OnP8mIUZugO-pxhPLMJATcilLo7Sl5RTRMxDElnqNNK6hHW6wPGW3IMqOAflHIWKckxfBSNxc1bXpVTMx8cJqnAKFlHrd-y-A",
        tag = "CULINARY TOURS"
    )
)

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnboardingScreen(onGetStarted: () -> Unit) {
    val pagerState = rememberPagerState(pageCount = { onboardingSlides.size })

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundDark)
    ) {
        // Immersive Carousel Section
        Box(
            modifier = Modifier
                .weight(0.65f)
                .fillMaxWidth()
                .clip(RoundedCornerShape(bottomStart = 40.dp, bottomEnd = 40.dp))
        ) {
            HorizontalPager(
                state = pagerState,
                modifier = Modifier.fillMaxSize()
            ) { page ->
                val slide = onboardingSlides[page]
                Box(modifier = Modifier.fillMaxSize()) {
                    AsyncImage(
                        model = slide.imageUrl,
                        contentDescription = null,
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
                                        Color.Black.copy(alpha = 0.1f),
                                        Color.Transparent,
                                        BackgroundDark.copy(alpha = 0.9f)
                                    )
                                )
                            )
                    )

                    // Tag
                    Box(
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .padding(top = 56.dp, end = 24.dp)
                            .background(
                                color = Color.White.copy(alpha = 0.2f),
                                shape = CircleShape
                            )
                            .padding(horizontal = 16.dp, vertical = 6.dp)
                    ) {
                        Text(
                            text = slide.tag,
                            color = Color.White,
                            style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.Bold,
                            letterSpacing = 1.sp
                        )
                    }
                }
            }
        }

        // Content Area
        Column(
            modifier = Modifier
                .weight(0.35f)
                .padding(horizontal = 24.dp, vertical = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            // Pagination Indicators
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(top = 16.dp)
            ) {
                repeat(onboardingSlides.size) { iteration ->
                    val color = if (pagerState.currentPage == iteration) GreenPrimary else Color.White.copy(alpha = 0.2f)
                    val width = if (pagerState.currentPage == iteration) 24.dp else 6.dp
                    
                    Box(
                        modifier = Modifier
                            .padding(2.dp)
                            .height(6.dp)
                            .width(width)
                            .background(color, CircleShape)
                    )
                }
            }

            // Text Block
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Discover Local",
                    style = MaterialTheme.typography.headlineLarge,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
                Text(
                    text = "Experiences",
                    style = MaterialTheme.typography.headlineLarge,
                    color = GreenPrimary,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = "Connect with local guides, book homestays, and plan your perfect trip with AI.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.White.copy(alpha = 0.7f),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
            }

            // Action Area
            Button(
                onClick = onGetStarted,
                colors = ButtonDefaults.buttonColors(
                    containerColor = GreenPrimary
                ),
                shape = CircleShape,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
            ) {
                Text(
                    text = "Get Started",
                    color = BackgroundDark,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.width(8.dp))
                Icon(
                    imageVector = Icons.Filled.ArrowForward,
                    contentDescription = null,
                    tint = BackgroundDark
                )
            }
        }
    }
}

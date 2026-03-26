package com.example.travelwave.ui

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
import androidx.compose.material.icons.filled.AccountBalance
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.CreditCard
import androidx.compose.material.icons.filled.PhoneAndroid
import androidx.compose.material.icons.filled.Wallet
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.travelwave.ui.theme.GreenPrimary

@Composable
fun PaymentScreen(
    tripData: TripData?,
    onBackClick: () -> Unit,
    onPaymentSuccess: () -> Unit
) {
    var selectedMethod by remember { mutableStateOf<String?>(null) }
    
    // Convert price string to something that looks like a total
    val basePriceStr = tripData?.price?.replace("₹", "")?.replace(",", "") ?: "15000"
    val basePrice = basePriceStr.toIntOrNull() ?: 15000
    val tax = (basePrice * 0.18).toInt() // 18% GST example
    val total = basePrice + tax

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF0e110f)) // Dark theme background matching TripDetailsScreen
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(bottom = 100.dp)
        ) {
            // Header
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 48.dp, start = 16.dp, end = 16.dp, bottom = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = onBackClick,
                    modifier = Modifier
                        .size(44.dp)
                        .background(Color(0xFF161b18), CircleShape)
                        .border(1.dp, Color.White.copy(alpha = 0.05f), CircleShape)
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBackIosNew,
                        contentDescription = "Back",
                        tint = Color.White,
                        modifier = Modifier.size(20.dp)
                    )
                }
                Text(
                    text = "Select Payment Method",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = Modifier.padding(start = 16.dp)
                )
            }

            // Order Summary
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .background(Color(0xFF161b18), RoundedCornerShape(16.dp))
                    .border(1.dp, Color.White.copy(alpha = 0.05f), RoundedCornerShape(16.dp))
                    .padding(20.dp)
            ) {
                Text(
                    text = "Order Summary",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(16.dp))
                
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Text(text = tripData?.title ?: "Trip Package", color = Color(0xFF94a3b8))
                    Text(text = "₹${"%,d".format(basePrice)}", color = Color.White, fontWeight = FontWeight.SemiBold)
                }
                Spacer(modifier = Modifier.height(8.dp))
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Text(text = "Taxes & Fees (18%)", color = Color(0xFF94a3b8))
                    Text(text = "₹${"%,d".format(tax)}", color = Color.White, fontWeight = FontWeight.SemiBold)
                }
                Spacer(modifier = Modifier.height(16.dp))
                HorizontalDivider(color = Color.White.copy(alpha = 0.1f))
                Spacer(modifier = Modifier.height(16.dp))
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Text(text = "Total Amount", style = MaterialTheme.typography.titleMedium, color = Color.White, fontWeight = FontWeight.Bold)
                    Text(text = "₹${"%,d".format(total)}", style = MaterialTheme.typography.titleLarge, color = GreenPrimary, fontWeight = FontWeight.ExtraBold)
                }
            }

            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = "UPI & Wallets",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            Spacer(modifier = Modifier.height(12.dp))

            // UPI Methods
            Column(modifier = Modifier.padding(horizontal = 16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                PaymentMethodCard(
                    id = "gpay",
                    name = "Google Pay",
                    icon = Icons.Default.PhoneAndroid,
                    iconTint = Color(0xFF4285F4),
                    isSelected = selectedMethod == "gpay",
                    onClick = { selectedMethod = "gpay" }
                )
                PaymentMethodCard(
                    id = "phonepe",
                    name = "PhonePe",
                    icon = Icons.Default.PhoneAndroid,
                    iconTint = Color(0xFF5E35B1),
                    isSelected = selectedMethod == "phonepe",
                    onClick = { selectedMethod = "phonepe" }
                )
                PaymentMethodCard(
                    id = "paytm",
                    name = "Paytm",
                    icon = Icons.Default.Wallet,
                    iconTint = Color(0xFF00B9F5),
                    isSelected = selectedMethod == "paytm",
                    onClick = { selectedMethod = "paytm" }
                )
                PaymentMethodCard(
                    id = "other_upi",
                    name = "Other UPI ID",
                    icon = Icons.Default.AccountBalance,
                    iconTint = GreenPrimary,
                    isSelected = selectedMethod == "other_upi",
                    onClick = { selectedMethod = "other_upi" }
                )
            }

            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = "Cards & Net Banking",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            Spacer(modifier = Modifier.height(12.dp))

            Column(modifier = Modifier.padding(horizontal = 16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                 PaymentMethodCard(
                    id = "credit_card",
                    name = "Credit / Debit Card",
                    icon = Icons.Default.CreditCard,
                    iconTint = Color(0xFFF59E0B),
                    isSelected = selectedMethod == "credit_card",
                    onClick = { selectedMethod = "credit_card" }
                )
                 PaymentMethodCard(
                    id = "netbanking",
                    name = "Net Banking",
                    icon = Icons.Default.AccountBalance,
                    iconTint = Color(0xFF3B82F6),
                    isSelected = selectedMethod == "netbanking",
                    onClick = { selectedMethod = "netbanking" }
                )
            }
        }

        // Bottom CTA
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .background(Color(0xFF0e110f).copy(alpha = 0.95f))
                .border(1.dp, Color.White.copy(alpha = 0.05f))
                .padding(16.dp)
        ) {
            Button(
                onClick = onPaymentSuccess,
                enabled = selectedMethod != null,
                colors = ButtonDefaults.buttonColors(
                    containerColor = GreenPrimary,
                    disabledContainerColor = Color(0xFF294233)
                ),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
            ) {
                Text(
                    text = if (selectedMethod != null) "Proceed to Pay ₹${"%,d".format(total)}" else "Select a Payment Method",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = if (selectedMethod != null) Color(0xFF0e110f) else Color.White.copy(alpha = 0.5f)
                )
            }
        }
    }
}

@Composable
fun PaymentMethodCard(
    id: String,
    name: String,
    icon: ImageVector,
    iconTint: Color,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(if (isSelected) GreenPrimary.copy(alpha = 0.1f) else Color(0xFF161b18), RoundedCornerShape(12.dp))
            .border(
                width = if (isSelected) 2.dp else 1.dp,
                color = if (isSelected) GreenPrimary else Color.White.copy(alpha = 0.05f),
                shape = RoundedCornerShape(12.dp)
            )
            .clickable { onClick() }
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .background(Color.White.copy(alpha = 0.05f), CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = name,
                tint = iconTint,
                modifier = Modifier.size(24.dp)
            )
        }
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = name,
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.SemiBold,
            color = Color.White,
            modifier = Modifier.weight(1f)
        )
        if (isSelected) {
            Icon(
                imageVector = Icons.Default.CheckCircle,
                contentDescription = "Selected",
                tint = GreenPrimary,
                modifier = Modifier.size(24.dp)
            )
        }
    }
}

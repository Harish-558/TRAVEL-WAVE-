package com.example.travelwave.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.rounded.Lightbulb
import androidx.compose.material3.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.platform.LocalContext
import android.content.Intent
import android.speech.RecognizerIntent
import android.app.Activity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import com.example.travelwave.ui.theme.GreenPrimary
import com.google.firebase.Firebase
import com.google.firebase.ai.ai
import com.google.firebase.ai.type.GenerativeBackend
import kotlinx.coroutines.launch

data class ChatMessage(
    val text: String,
    val isUserMessage: Boolean
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AIAssistantScreen(onBackClick: () -> Unit = {}) {
    var messages by remember { mutableStateOf(listOf<ChatMessage>()) }
    var input by remember { mutableStateOf(TextFieldValue("")) }
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    
    // We are using Nvidia API now
    val hasApiKey = true

    val speechRecognizerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val spokenText: String? =
                result.data?.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)?.let { results ->
                    if (results.isNotEmpty()) results[0] else null
                }
            if (!spokenText.isNullOrBlank()) {
                messages = messages + ChatMessage(spokenText, true)
                coroutineScope.launch {
                    try {
                        val prompt = "You are TravelWave AI, a professional and helpful travel assistant. Keep responses relatively short. User said: $spokenText"
                        val model = Firebase.ai(backend = GenerativeBackend.googleAI())
                            .generativeModel("gemini-3-flash-preview")
                        val responseText = model.generateContent(prompt).text ?: "Sorry, I couldn't generate a response."
                        messages = messages + ChatMessage(responseText, false)
                    } catch (e: Exception) {
                        e.printStackTrace()
                        messages = messages + ChatMessage("Error generating response with Nvidia API.", false)
                    }
                }
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.Default.ArrowBackIosNew,
                            contentDescription = "Back",
                            tint = GreenPrimary,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                },
                title = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(40.dp)
                                .background(
                                    brush = Brush.linearGradient(
                                        colors = listOf(GreenPrimary, Color(0xFF00D498))
                                    ),
                                    shape = CircleShape
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.AutoAwesome,
                                contentDescription = "AI",
                                tint = Color.Black,
                                modifier = Modifier.size(24.dp)
                            )
                        }
                        Column {
                            Text(
                                "TravelWave AI",
                                fontWeight = FontWeight.Bold,
                                fontSize = 18.sp
                            )
                            Text(
                                "Your trip companion",
                                fontSize = 12.sp,
                                color = Color.Gray
                            )
                        }
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface,
                    titleContentColor = MaterialTheme.colorScheme.onSurface
                )
            )
        },
        containerColor = MaterialTheme.colorScheme.background
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .consumeWindowInsets(innerPadding) // Important for avoiding double padding
                .imePadding() // Essential for raising above keyboard
        ) {
            // Messages area
            if (!hasApiKey) {
                Box(modifier = Modifier.weight(1f)) {
                    MissingApiKeyScreen()
                }
            } else if (messages.isEmpty()) {
                Box(modifier = Modifier.weight(1f)) {
                    WelcomeScreen()
                }
            } else {
                LazyColumn(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    contentPadding = PaddingValues(vertical = 12.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    reverseLayout = true
                ) {
                    items(messages.reversed()) { msg ->
                        ChatBubble(msg)
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Suggestion chips
            if (hasApiKey && messages.isEmpty()) {
                SuggestionChips { suggestion ->
                    messages = messages + ChatMessage(suggestion, true)
                    input = TextFieldValue("")
                    coroutineScope.launch {
                        try {
                            val prompt = "You are TravelWave AI, a professional and helpful travel assistant. Keep responses relatively short. User said: $suggestion"
                            val model = Firebase.ai(backend = GenerativeBackend.googleAI())
                                .generativeModel("gemini-3-flash-preview")
                            val responseText = model.generateContent(prompt).text ?: "Sorry, I couldn't generate a response."
                            messages = messages + ChatMessage(responseText, false)
                        } catch (e: Exception) {
                            e.printStackTrace()
                            messages = messages + ChatMessage("Error generating response with Nvidia API.", false)
                        }
                    }
                }
            }

            // Input area
            if (hasApiKey) {
                InputArea(
                    input = input,
                    onInputChange = { input = it },
                    onSend = {
                        if (input.text.isNotBlank()) {
                            val userText = input.text
                            messages = messages + ChatMessage(userText, true)
                            input = TextFieldValue("")
                            
                            coroutineScope.launch {
                                try {
                                    val prompt = "You are TravelWave AI, a professional and helpful travel assistant. Keep responses relatively short. User said: $userText"
                                    val model = Firebase.ai(backend = GenerativeBackend.googleAI())
                                        .generativeModel("gemini-3-flash-preview")
                                    val responseText = model.generateContent(prompt).text ?: "Sorry, I couldn't generate a response."
                                    messages = messages + ChatMessage(responseText, false)
                                } catch (e: Exception) {
                                     e.printStackTrace()
                                     messages = messages + ChatMessage("Error generating response with Nvidia API.", false)
                                }
                            }
                        }
                    },
                    onMicClick = {
                        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).apply {
                            putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
                            putExtra(RecognizerIntent.EXTRA_PROMPT, "Speak to TravelWave AI")
                        }
                        try {
                            speechRecognizerLauncher.launch(intent)
                        } catch (e: Exception) {
                            // Ignore or handle
                        }
                    }
                )
            }
        }
    }
}

@Composable
fun WelcomeScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .size(80.dp)
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(GreenPrimary, Color(0xFF00D498))
                    ),
                    shape = CircleShape
                ),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.AutoAwesome,
                contentDescription = "AI",
                tint = Color.Black,
                modifier = Modifier.size(48.dp)
            )
        }
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            "Welcome to TravelWave AI",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            "Your intelligent travel companion. Ask about destinations, get recommendations, plan your itinerary, and more!",
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Gray,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun ChatBubble(message: ChatMessage) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = if (message.isUserMessage) Arrangement.End else Arrangement.Start
    ) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(16.dp))
                .background(
                    if (message.isUserMessage) GreenPrimary
                    else MaterialTheme.colorScheme.surface
                )
                .padding(12.dp)
                .widthIn(max = 280.dp)
        ) {
            Text(
                text = message.text,
                color = if (message.isUserMessage) Color.Black else MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = if (message.isUserMessage) FontWeight.Medium else FontWeight.Normal
            )
        }
    }
}

@Composable
fun SuggestionChips(onSuggestionClick: (String) -> Unit) {
    Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        ) {
            Icon(
                imageVector = Icons.Rounded.Lightbulb,
                contentDescription = "Suggestions",
                tint = GreenPrimary,
                modifier = Modifier.size(20.dp)
            )
            Text(
                "Try asking:",
                style = MaterialTheme.typography.labelMedium,
                fontWeight = FontWeight.SemiBold,
                color = Color.Gray
            )
        }
        val suggestions = listOf(
            "Best restaurants near me",
            "Create 3-day itinerary",
            "Weather forecast",
            "Local attractions"
        )
        LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            items(suggestions) { suggestion ->
                SuggestionChip(
                    onClick = { onSuggestionClick(suggestion) },
                    label = { Text(suggestion) },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}

@Composable
fun InputArea(
    input: TextFieldValue,
    onInputChange: (TextFieldValue) -> Unit,
    onSend: () -> Unit,
    onMicClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp, vertical = 12.dp)
            .background(MaterialTheme.colorScheme.background),
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        OutlinedTextField(
            value = input,
            onValueChange = onInputChange,
            modifier = Modifier
                .weight(1f)
                .clip(RoundedCornerShape(24.dp))
                .background(Color.DarkGray), // Give it a visible background instead of surface
            placeholder = { Text("Ask me anything...", color = Color.LightGray) },
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = Color(0xFF1E1E1E),
                unfocusedContainerColor = Color(0xFF1E1E1E),
                focusedBorderColor = GreenPrimary,
                unfocusedBorderColor = Color.Transparent,
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White
            ),
            singleLine = false,
            maxLines = 3,
            shape = RoundedCornerShape(24.dp)
        )
        if (input.text.isBlank()) {
            IconButton(
                onClick = onMicClick,
                modifier = Modifier
                    .size(52.dp) // Adjusted height slightly to match outlined textfield
                    .clip(CircleShape)
                    .background(Color(0xFF1E1E1E))
            ) {
                Icon(
                    imageVector = Icons.Default.Mic,
                    contentDescription = "Microphone",
                    tint = GreenPrimary,
                    modifier = Modifier.size(24.dp)
                )
            }
        } else {
            Button(
                onClick = onSend,
                modifier = Modifier
                    .size(52.dp) // Adjusted height slightly to match outlined textfield
                    .clip(CircleShape),
                colors = ButtonDefaults.buttonColors(containerColor = GreenPrimary),
                contentPadding = PaddingValues(0.dp), // Fix internal padding potentially centering weirdly
                enabled = true
            ) {
                Icon(
                    imageVector = Icons.Default.Send,
                    contentDescription = "Send",
                    tint = Color.Black,
                    modifier = Modifier.size(20.dp)
                )
            }
        }
    }
}

@Composable
fun MissingApiKeyScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            imageVector = Icons.Default.AutoAwesome, // Or info icon
            contentDescription = "Missing API Key",
            tint = Color(0xFFFF5252), // Red warning color
            modifier = Modifier.size(64.dp)
        )
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            "API Key Required",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            "To use the TravelWave AI Assistant, you need to provide a Google Gemini API Key.",
            style = MaterialTheme.typography.bodyMedium,
            color = Color.LightGray,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(16.dp))
        Card(
            colors = CardDefaults.cardColors(containerColor = Color(0xFF1E1E1E)),
            shape = RoundedCornerShape(12.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    "Setup Instructions:",
                    fontWeight = FontWeight.Bold,
                    color = GreenPrimary,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Text("1. Get an API key from Google AI Studio", color = Color.LightGray)
                Text("2. Open your project's local.properties file", color = Color.LightGray)
                Text("3. Add the following line:", color = Color.LightGray)
                Text(
                    "GEMINI_API_KEY=your_key_here",
                    fontFamily = androidx.compose.ui.text.font.FontFamily.Monospace,
                    color = Color.White,
                    modifier = Modifier
                        .padding(vertical = 8.dp)
                        .background(Color.Black)
                        .padding(8.dp)
                        .fillMaxWidth()
                )
                Text("4. Click File -> Sync Project with Gradle Files", color = Color.LightGray)
                Text("5. Re-run the app", color = Color.LightGray)
            }
        }
    }
}

// Removed generateAIResponse fixed stub function

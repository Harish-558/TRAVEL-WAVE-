package com.example.travelwave.network

import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import java.util.concurrent.TimeUnit

object NvidiaApiService {
    private const val API_URL = "https://integrate.api.nvidia.com/v1/chat/completions"
    
    // Always use your securely stored API keys in production
    private const val API_KEY = "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"
    
    private val client = OkHttpClient.Builder()
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(60, TimeUnit.SECONDS)
        .writeTimeout(60, TimeUnit.SECONDS)
        .build()
        
    private val gson = Gson()
    
    data class ChatMessagePayload(val role: String, val content: String)
    data class NvidiaRequest(
        val model: String = "meta/llama-3.1-8b-instruct",
        val messages: List<ChatMessagePayload>,
        val max_tokens: Int = 1000,
        val temperature: Double = 0.60,
        val top_p: Double = 0.95,
        val top_k: Int = 20,
        val stream: Boolean = false
    )
    
    data class NvidiaResponse(val choices: List<Choice>?)
    data class Choice(val message: ChatMessagePayload?)
    
    suspend fun getChatCompletion(userMessage: String): String {
        return withContext(Dispatchers.IO) {
            try {
                // Formatting payload
                val payload = NvidiaRequest(
                    messages = listOf(ChatMessagePayload("user", userMessage))
                )
                
                val jsonPayload = gson.toJson(payload)
                val body = jsonPayload.toRequestBody("application/json; charset=utf-8".toMediaTypeOrNull())
                
                val request = Request.Builder()
                    .url(API_URL)
                    .addHeader("Authorization", "Bearer $API_KEY")
                    .addHeader("Accept", "application/json")
                    .post(body)
                    .build()
                    
                val response = client.newCall(request).execute()
                val responseBody = response.body?.string()
                
                if (response.isSuccessful && responseBody != null) {
                    val nvidiaResponse = gson.fromJson(responseBody, NvidiaResponse::class.java)
                    val content = nvidiaResponse?.choices?.firstOrNull()?.message?.content
                    
                    content?.trim() ?: "Sorry, I received an empty response."
                } else {
                    "Sorry, I encountered an error communicating with the AI service. Error: ${response.code}"
                }
            } catch (e: Exception) {
                e.printStackTrace()
                "Sorry, an unexpected error occurred: ${e.localizedMessage}"
            }
        }
    }
}

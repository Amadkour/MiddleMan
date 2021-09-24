package com.example.middleman

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import com.example.middleman.viewModel.UserViewModel
import kotlinx.coroutines.*


class MainActivity : AppCompatActivity() {
    companion object {
        var context: Context? = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        context=applicationContext
        setContent {
           
            Box(contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()) {
                Text(
                    "MiddleMan App", style = TextStyle(
                        fontSize = 40.sp,
                        color = Color.Magenta
                    )
                )
            }
        }
        val intent = IntentFilter("com.madkour.emitter")
        registerReceiver(MyReceiver(), intent)
    }



}

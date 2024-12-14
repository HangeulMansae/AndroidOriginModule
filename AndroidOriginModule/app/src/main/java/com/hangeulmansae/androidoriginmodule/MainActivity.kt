package com.hangeulmansae.androidoriginmodule

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import com.hangeulmansae.androidoriginmodule.application.ProjectApplication.Companion.retrofit
import com.hangeulmansae.androidoriginmodule.retrofit.TestApi
import com.hangeulmansae.androidoriginmodule.ui.theme.AndroidOriginModuleTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val testApi = retrofit.create(TestApi::class.java)
        val name = mutableStateOf("")

        lifecycleScope.launch(Dispatchers.IO) {
            delay(2000)
            testApi.testApi().body()?.let {
                name.value = it
            }
        }

        setContent {
            AndroidOriginModuleTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = name.value,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AndroidOriginModuleTheme {
        Greeting("Android")
    }
}
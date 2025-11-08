package com.example.calculatorscientific

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModelProvider
import com.example.calculatorscientific.ui.theme.CalculatorScientificTheme
import com.example.calculatorscientific.ui.theme.CalculatorViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val calculatorViewModel = ViewModelProvider(this)[CalculatorViewModel::class.java]

        setContent {
            CalculatorScientificTheme {
//                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
////                    CalculatorScreen(modifier = Modifier.padding(innerPadding), calculatorViewModel)
//                }
                AppContent(calculatorViewModel)
            }
        }
    }
}

@Composable
fun AppContent(viewModel: CalculatorViewModel) {
    MainNavigation(viewModel = viewModel)
}
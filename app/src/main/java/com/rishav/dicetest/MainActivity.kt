package com.rishav.dicetest

import android.graphics.Paint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rishav.dicetest.ui.theme.DiceTestTheme
import kotlinx.coroutines.delay
import kotlin.time.ExperimentalTime
import kotlin.time.seconds

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DiceTestTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MainScreen()
                }
            }
        }
    }
}

@OptIn(ExperimentalTime::class)
@Composable
fun MainScreen(){
    var result by remember { mutableStateOf(1) }
    var isLoading by remember { mutableStateOf(false) }
    val imageResource = when(result) {
        1 -> R.drawable.dice_1
        2 -> R.drawable.dice_2
        3 -> R.drawable.dice_3
        4 -> R.drawable.dice_4
        5 -> R.drawable.dice_5
        else -> R.drawable.dice_6
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,

    ) {
        Spacer(modifier = Modifier.height(50.dp))
        if(isLoading) {
            Spacer(modifier = Modifier.height(100.dp))
            CircularProgressIndicator()
            Spacer(modifier = Modifier.height(100.dp))
            LaunchedEffect(Unit) {
                delay(0.5.seconds)
                isLoading = false
            }
        }
        else
            Image(painter = painterResource(id = imageResource), contentDescription = result.toString())
        Spacer(modifier = Modifier.height(20.dp))
        Button(onClick = {  result = (1..6).random(); isLoading = true;}) {
            Text(text = "Roll Dice")
        }
    }
}

package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Surface (
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                LemonadeTheme {
                    MakeLemonade()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LemonadeApp() {
    MakeLemonade(modifier = Modifier
        .fillMaxSize()
        .wrapContentSize(Alignment.Center)
    )
}

@Composable
fun MakeLemonade(modifier: Modifier = Modifier) {
    var neededTaps by remember { mutableIntStateOf(1) }
    var numberOfTaps by remember { mutableIntStateOf( 0) }
    var step by remember { mutableIntStateOf( 1) }
    val imageResource = when(step) {
        1 -> R.drawable.lemon_tree
        2 -> R.drawable.lemon_squeeze
        3 -> R.drawable.lemon_drink
        else -> R.drawable.lemon_restart
    }

    val textLabelResource = when(step){
        1 -> R.string.lemon_tree
        2 -> R.string.lemon
        3 -> R.string.lemonade
        else -> R.string.empty_glass
    }

    val textActionResource = when(step){
        1 -> R.string.lemon_tree_action
        2 -> R.string.lemon_tap_action
        3 -> R.string.lemonade_tap_action
        else -> R.string.empty_glass_action
    }

    Column {
        Text(
            text = stringResource(textLabelResource),
            modifier = modifier
        )
        Column {
            Button(onClick = {
                if((neededTaps <= numberOfTaps) && (step <= 3)){
                    neededTaps = (2..4).random()
                    numberOfTaps = 0
                    step++
                }else{
                    neededTaps = (2..4).random()
                    numberOfTaps = 0
                    step = 1
                }
                numberOfTaps++
            }) {
                Image(
                    painter = painterResource(imageResource),
                    contentDescription = null
                )
            }
            Text(
                text = stringResource(textActionResource),
                modifier = modifier
            )
        }
    }
}


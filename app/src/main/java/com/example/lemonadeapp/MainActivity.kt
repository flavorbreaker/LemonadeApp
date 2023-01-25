package com.example.lemonadeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lemonadeapp.ui.theme.LemonadeAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    LemonadeStand()
                }
            }
        }
    }
}

@Composable
fun LemonadeStand () {

    var buttonImage by remember { mutableStateOf(R.drawable.lemon_tree) }
    var userClicks by remember { mutableStateOf(0) }
    var text by remember { mutableStateOf(R.string.lemon_tree) }
    var descText by remember { mutableStateOf(R.string.lemon_treeCD)}

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = stringResource(text))
        
        Spacer(modifier = Modifier.size(16.dp))

        val randomClicks = (2..4).random()

        Image(
            painter = painterResource(buttonImage),
            contentDescription = stringResource(descText),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .border(
                    1.dp,
                    colorResource(id = R.color.teal_200),
                    RoundedCornerShape(3.dp)
                )
                .clickable {

                    if (buttonImage == R.drawable.lemon_squeeze) {

                        userClicks += 1

                        if (userClicks == randomClicks) {
                            buttonImage = R.drawable.lemon_drink
                            text = R.string.lemon_drink
                            descText = R.string.lemon_drinkCD
                            userClicks = 0
                        }
                    } else {
                        when (buttonImage) {
                            R.drawable.lemon_tree -> {
                                buttonImage = R.drawable.lemon_squeeze
                                text = R.string.lemon_squeeze
                                descText = R.string.lemon_squeezeCD
                            }
                            R.drawable.lemon_drink -> {
                                buttonImage = R.drawable.lemon_restart
                                text = R.string.lemon_restart
                                descText = R.string.lemon_restartCD
                            }
                            R.drawable.lemon_restart -> {
                                buttonImage = R.drawable.lemon_tree
                                text = R.string.lemon_tree
                                descText = R.string.lemon_treeCD
                            }
                        }


                    }
                }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    LemonadeAppTheme {
        LemonadeStand()
    }
}
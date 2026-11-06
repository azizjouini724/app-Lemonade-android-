
package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeTheme {
                LemonApp()
            }
        }
    }
}

@Composable
fun LemonApp() {
    var currentStep by remember { mutableIntStateOf(1) }
    var squeezeCount by remember { mutableIntStateOf(0) }
    var requiredSqueezes by remember { mutableIntStateOf(0) }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            when (currentStep) {
                1 -> {
                    Text(
                        text = stringResource(R.string.tap_lemon_tree),
                        color = Color.Red
                    )

                    Spacer(modifier = Modifier.height(32.dp))

                    Image(
                        painter = painterResource(R.drawable.lemon_tree),
                        contentDescription = stringResource(
                            R.string.lemon_tree
                        ),
                        modifier = Modifier
                            .wrapContentSize()
                            .clickable {
                                currentStep = 2
                                squeezeCount = 0
                                requiredSqueezes = (2..4).random()
                            }
                    )
                }

                2 -> {
                    Text(
                        text = stringResource(R.string.keep_tapping_lemon),
                        color = Color.Red
                    )

                    Spacer(modifier = Modifier.height(32.dp))

                    Image(
                        painter = painterResource(R.drawable.lemon_squeeze),
                        contentDescription = stringResource(
                            R.string.tap_lemon_tree
                        ),
                        modifier = Modifier
                            .wrapContentSize()
                            .clickable {
                                squeezeCount++

                                if (squeezeCount >= requiredSqueezes) {
                                    currentStep = 3
                                }
                            }
                    )
                }

                3 -> {
                    Text(text = stringResource(R.string.tap_lemonade))

                    Spacer(modifier = Modifier.height(32.dp))

                    Image(
                        painter = painterResource(R.drawable.lemon_drink),
                        contentDescription = stringResource(
                            R.string.lemon_tree
                        ),
                        modifier = Modifier
                            .wrapContentSize()
                            .clickable {
                                currentStep = 4
                            }
                    )
                }

                4 -> {
                    Text(text = stringResource(R.string.empty_glass))

                    Spacer(modifier = Modifier.height(32.dp))

                    Image(
                        painter = painterResource(R.drawable.lemon_restart),
                        contentDescription = stringResource(
                            R.string.lemon_tree
                        ),
                        modifier = Modifier
                            .wrapContentSize()
                            .clickable {
                                currentStep = 1
                            }
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    LemonadeTheme {
        LemonApp()
    }
}

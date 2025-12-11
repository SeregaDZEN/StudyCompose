package ru.seregabelyi.composetutorial

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.seregabelyi.composetutorial.ui.components.FrostedSurface
import ru.seregabelyi.composetutorial.ui.theme.ArcticBlue
import ru.seregabelyi.composetutorial.ui.theme.ComposeTutorialTheme
import ru.seregabelyi.composetutorial.ui.theme.Green
import ru.seregabelyi.composetutorial.ui.theme.IceBlue
import ru.seregabelyi.composetutorial.ui.theme.PolarTurquoise

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeTutorialTheme {
                    MainActivityContent()
            }
        }
    }
}

@Composable
fun MainActivityContent() {
    var celsius by rememberSaveable { mutableIntStateOf(0) }
    var newCelsius by rememberSaveable { mutableStateOf("") }

    // Корневой фон с ледяным градиентом
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        IceBlue,
                        ArcticBlue.copy(alpha = 0.6f),
                        PolarTurquoise.copy(alpha = 0.4f)
                    )
                )
            )
            .padding(16.dp)
    ) {
        // Карточка с frosted glass, внутри которой весь UI
        FrostedSurface(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(12.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(16.dp)
            ) {
                Header(R.drawable.sunrise, "sun image")
                EnterTemperature(newCelsius) { newCelsius = it }
                ConvertButton {
                    newCelsius.toIntOrNull()?.let {
                        celsius = it
                    }
                }
                TemperatureText(celsius)
            }
        }
    }
}


@Composable
fun EnterTemperature(temp: String, changed: (String) -> Unit) {
    TextField(
        value = temp,
        label = { Text("Enter a temperature in Celsius") },
        onValueChange = changed,
        textStyle = TextStyle(textAlign = TextAlign.Center),
        modifier = Modifier.fillMaxWidth()
    )
}

@Preview
@Composable
fun PreviewMainScreen() {
    MaterialTheme {
        Surface { MainActivityContent() } // ←  для превью, нельзя с аргументами!
    }
}

@Composable
fun ConvertButton(clicked: () -> Unit) {
    Button(onClick = clicked) {
        Text("Convert")
    }
}

@Composable
fun Header(image: Int, description: String) {
    Image(
        painter = painterResource(image),
        contentDescription = description,
        modifier = Modifier
            .height(180.dp)
            .fillMaxWidth(), contentScale = ContentScale.Crop
    )
}

@Composable
fun TemperatureText(celsius: Int) {
    val fahrenheit = (celsius.toDouble()) * 9 / 5 + 32
    Text("$celsius is $fahrenheit Fahrenheit", color = Green, fontSize = 20.sp)
}


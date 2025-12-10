package ru.seregabelyi.composetutorial

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.seregabelyi.composetutorial.ui.theme.Blue
import ru.seregabelyi.composetutorial.ui.theme.Green

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainActivityContent()
        }
    }
}

@Composable
fun MainScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        MessageCard(listOf("a", "b", "c"))
    }
}

@Composable
fun MessageCard(names: List<String>) {
    names.forEach { name ->
        Text(
            "привет, $name!",
            color = Blue,    // сам добавил ))
            fontSize = 16.sp
        )
    }
}

@Preview
@Composable
fun PreviewMainScreen() {
    MainActivityContent() // ←  для превью, нельзя с аргументами!
}


@Composable
fun MainActivityContent() {
    var celsius by rememberSaveable { mutableIntStateOf(0) } // rememberSaveable - сохр данные при изм конфиг
    var newCelsius by rememberSaveable { mutableStateOf("") }

    Column( modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally)
     {
        Header(R.drawable.sunrise, "sun image")
        EnterTemperature(newCelsius) { newCelsius = it }
        ConvertButton {
            newCelsius.toIntOrNull()?.let {
                celsius = it
            }
        }


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


@Composable
fun ChangeHello() {
    var name by remember { mutableStateOf("friend") }

    Column {
        Text("Hello, $name!", color = Blue)
        Button(onClick = { name = "Compose" }) {
            Text("Change")
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


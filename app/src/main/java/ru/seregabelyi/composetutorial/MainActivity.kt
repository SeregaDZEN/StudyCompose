package ru.seregabelyi.composetutorial

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
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
    Column {
        Header(R.drawable.sunrise, "sun image")
        TemperatureText(0)
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
fun ConvertButton(clicked :  () -> Unit) {
    Button(onClick = clicked) {
        Text("Convert")
    }
}


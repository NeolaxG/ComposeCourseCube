package learn.basic.composecrash

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import learn.basic.composecrash.data.PersonData
import learn.basic.composecrash.ui.theme.ComposeCrashTheme
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val fontFamily = FontFamily(
            Font(R.font.geostar_regular, FontWeight.Thin)
        )

        val persons = listOf(
            PersonData(R.drawable.obiwane, "Jedi", "Obi Wan Kenobi"),
            PersonData(R.drawable.obiwane, "Jedi", "Anakin Skywalker"),
            PersonData(R.drawable.obiwane, "Jedi", "Mace Windu"),
            PersonData(R.drawable.obiwane, "Jedi", "Yoda")
        )


        setContent {
            ComposeCrashTheme {
//                val painter = painterResource(R.drawable.obiwane)
//                val contentDescription = "Obi Wan Kenobi"
//                val title = "Obi Wan Kenobi"

                Column(modifier = Modifier.fillMaxSize()) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color(0xFF101010)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = buildAnnotatedString {
                                withStyle(
                                    style = SpanStyle(
                                        color = Color.Red,
                                        fontSize = 48.sp
                                    )
                                ) {
                                    append("S")
                                }
                                append("tar")
                                withStyle(
                                    style = SpanStyle(
                                        color = Color.Red,
                                        fontSize = 48.sp
                                    )
                                ) {
                                    append("W")
                                }
                                append("ars")
                            },
                            color = Color.White,
                            fontSize = 32.sp,
                            fontFamily = fontFamily,
                            fontStyle = FontStyle.Italic,
                            textAlign = TextAlign.Center,
                            textDecoration = TextDecoration.Underline

                        )
                    }
                    persons.forEach { person ->
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(0.5f)
                            .padding(12.dp)
                    ) {
                            ImageCard(
                                personData = person
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ImageCard(
    personData: PersonData,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp)
    ) {
        Box(modifier = Modifier.height(200.dp)) {
            Image(
                painter = painterResource(personData.painterId),
                contentDescription = personData.contentDescription,
                contentScale = ContentScale.Crop,
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color.Black
                            ),
                            startY = 400f
                        )
                    )
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                contentAlignment = Alignment.BottomStart
            ) {
                Text(
                    personData.title, style = TextStyle(
                        color = Color.White,
                        fontSize = 12.sp
                    )
                )
            }

        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    val fontFamily = FontFamily(
        Font(R.font.geostar_regular, FontWeight.Thin)
    )

//        val painter = painterResource(R.drawable.obiwane)
//        val contentDescription = "Obi Wan Kenobi"
//        val title = "Obi Wan Kenobi"

    val colorState = remember {mutableStateOf(Color.Black)}
    val persons = listOf(
        PersonData(R.drawable.obiwane, "Jedi", "Obi Wan Kenobi"),
        PersonData(R.drawable.obiwane, "Jedi", "Anakin Skywalker"),
        PersonData(R.drawable.obiwane, "Jedi", "Mace Windu"),
        PersonData(R.drawable.obiwane, "Jedi", "Yoda")
    )

    ComposeCrashTheme {
        Column(modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState())) {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(colorState.value)
                    .clickable{
                        colorState.value = Color(
                            Random.nextFloat(),
                            Random.nextFloat(),
                            Random.nextFloat(),
                            1f,
                        )
                    },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                color = Color.Red,
                                fontSize = 48.sp
                            )
                        ) {
                            append("S")
                        }
                        append("tar")
                        withStyle(
                            style = SpanStyle(
                                color = Color.Red,
                                fontSize = 48.sp
                            )
                        ) {
                            append("W")
                        }
                        append("ars")
                    },
                    color = Color.White,
                    fontSize = 32.sp,
                    fontFamily = fontFamily,
                    fontStyle = FontStyle.Italic,
                    textAlign = TextAlign.Center,
                    textDecoration = TextDecoration.Underline

                )
            }
            persons.forEach { person ->
            Box(
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .padding(12.dp)
            ) {
                    ImageCard(
                        personData = person
                    )
                }
            }
        }
    }

}
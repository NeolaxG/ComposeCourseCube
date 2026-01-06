package learn.basic.composecrash

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toolbar
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import learn.basic.composecrash.data.PersonData
import learn.basic.composecrash.ui.theme.ComposeCrashTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeCrashTheme {
                LazyCard(jediPersons)
            }
        }
    }
}


@Composable
fun ItemCard(
    personData: PersonData,
    modifier: Modifier = Modifier
) {
    val onClick: () -> Unit = {
        Log.d("CLICK", "tap ${personData.title}")

    }
    OutlinedCard (
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 12.dp
        ),
        border = BorderStroke(1.dp, Color.Black),
        modifier = Modifier
            .padding(2.dp),
    ) {

        Row(
            modifier = Modifier
                .clickable(onClick = onClick)
                .fillMaxWidth()
                .padding(12.dp)
        )
        {
            Image(
                modifier = Modifier
                    .clip(RectangleShape)
                    .size(120.dp),
                painter = painterResource(personData.painterId),
                contentDescription = personData.personProf,
            )
            Column(
                modifier = Modifier
                    .padding(4.dp)
            ) {
                CardText(personData.title)
                CardText(personData.personProf)
                CardText(personData.personInfo)
            }
        }
    }
}

@Composable
fun LazyCard(persons: List<PersonData>) {
    LazyColumn(
        modifier = Modifier
            .background(
                Color(0x43C2BDBD)
            )
    ) {
        items(persons) { person ->
            ItemCard(
                person,
            )

//            Divider()
        }
    }
}

@Composable
fun CardText(yourText: String) {
    val gradientColors: List<Color> = listOf(
        Color.Black, Color.Red
    )

    var isExpanded by remember { mutableStateOf(false) }

    val textSizeState by remember { mutableStateOf(18.sp) }

    val brush = Brush.linearGradient(colors = gradientColors)

    Text(
        modifier = Modifier.clickable{
            isExpanded = !isExpanded
        },
        maxLines = if(isExpanded) Int.MAX_VALUE else 1,
        text = yourText,
        fontSize = textSizeState,
        fontStyle = FontStyle.Italic,
        fontWeight = FontWeight.Bold,
        style = TextStyle(
            brush = brush,
            shadow = Shadow(
                color = Color.Gray, offset = Offset(0.2f, 10.0f), blurRadius = 3f,
            )
        )
    )
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LazyCard(jediPersons)

}

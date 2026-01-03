package learn.basic.composecrash

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import learn.basic.composecrash.ui.theme.ComposeCrashTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeCrashTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Alex",

                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String,) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.End,
    ) {
        Text(
            text = "Hello $name!",
            fontSize = 16.sp,
            color = Color.Red,
        )
        Text(
            text = "Nice to meet you!",
            fontSize = 16.sp,
            color = Color.Red,
        )

    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .size(400.dp)
    ) {
        Text(
            text = "Hello $name!",
            fontSize = 16.sp,
            color = Color.Red,
//            modifier = Modifier
//                .background(Color.Blue)
//                .padding(16.dp)
//                .background(Color.Green),
        )
        Text(
            text = "Nice to meet you!",
            fontSize = 16.sp,
            color = Color.Red,
//            modifier = Modifier
//                .background(Color.Blue)
//                .padding(16.dp)
//                .background(Color.Green),
        )
        Image(painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = null,
            modifier = Modifier
                .size(100.dp)
        )
        for (i in 1..3) {
            Icon(
                imageVector = Icons.Default.Done,
                contentDescription = null
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ComposeCrashTheme {
        Greeting("Alex")
    }
}
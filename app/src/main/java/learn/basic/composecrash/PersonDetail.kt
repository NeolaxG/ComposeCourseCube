package learn.basic.composecrash

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import learn.basic.composecrash.data.PersonData
import learn.basic.composecrash.ui.theme.ComposeCrashTheme

class PersonDetail : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val person = PersonData(
            R.drawable.obiwane,
            "Jedi",
            "Obi Wan Kenobi"
        )

        setContent {
            ComposeCrashTheme {
                PersonList(person)
            }
        }
    }

    @Composable
    fun PersonList(personData: PersonData, modifier: Modifier = Modifier) {
        Column() {
            Image(
                painter = painterResource(personData.painterId),
                contentDescription = personData.contentDescription
            )
            Text(
                text = personData.contentDescription,
                modifier = modifier
            )
            Text(
                text = personData.title,
                modifier = modifier
            )
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun GreetingPreview2() {
        ComposeCrashTheme {
            val person = PersonData(
                R.drawable.obiwane,
                "Jedi",
                "Obi Wan Kenobi"
            )

            PersonList(person)

        }
    }
}
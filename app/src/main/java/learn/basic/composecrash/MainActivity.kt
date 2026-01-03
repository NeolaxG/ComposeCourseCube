package learn.basic.composecrash

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
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
                AddPerson(LocalContext.current)
            }
        }
    }
}

@Composable
fun AddPerson(context: Context) {
    var name by remember { mutableStateOf("") }
    var names by remember { mutableStateOf(listOf<String>()) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    )
    {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            OutlinedTextField(
                value = name,
                onValueChange = { text ->
                    name = text
                },
                label = { Text("Person name") },
                // вес элемента, если 1 занимает все место которое осталось от других (кнопки)
                modifier = Modifier.weight(1f)
            )

            // пустой элемент для создания отсупов
            Spacer(modifier = Modifier.width(16.dp))

            Button(onClick = {
                if (name.isNotBlank()) {
                    names = names + name
                    name = ""
                } else {
                    ToastTeser(context = context, "Please, write your name")
                }
            }) {
                Text(text = "Add")
            }
        }
        NameList(names = names)
    }
}

@Composable
fun NameList(names: List<String>, modifier: Modifier = Modifier) {
    LazyColumn {
        items(names) { currentName ->
            Text(
                text = currentName,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )
            // разделитель элементов
            Divider()
        }
    }
}

fun ToastTeser(context: Context, errorMes: String) {
    Toast.makeText(context, errorMes, Toast.LENGTH_SHORT).show()
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ComposeCrashTheme {
        AddPerson(LocalContext.current)
    }
}
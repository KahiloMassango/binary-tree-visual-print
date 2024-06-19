package components


import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun InputField(
    modifier: Modifier = Modifier,
    placeholder: String,
    buttonText: String,
    onButtonCLick: (Int) -> Unit

) {
    var value by remember { mutableStateOf("") }
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextField(
            modifier = Modifier,
            value = value,
            onValueChange = { value = it },
            label = {
                Text(placeholder, color = MyTheme.onSurfaceVariant)
            },
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = MyTheme.secondary,
                textColor = MyTheme.onSecondary,
            )

        )
        Spacer(modifier = Modifier.width(8.dp))
        Button(onClick = {
            value.toIntOrNull()?.let {
                onButtonCLick(it)
                value = ""
            }
        }) {
            Text(buttonText)
        }
    }
}


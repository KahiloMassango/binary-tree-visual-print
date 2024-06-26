package components

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import resources.MyTheme

@Composable
fun GraphTextField(
    placeholder1: String,
    placeholder2: String,
    buttonText: String,
    onClick: (src: String, dst: String) -> Unit
) {
    var srcNode by remember { mutableStateOf("") }
    var dstNode by remember { mutableStateOf("") }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            TextField(
                value = srcNode,
                onValueChange = { srcNode = it.uppercase() },
                label = { Text(placeholder1, color = MyTheme.onSurfaceVariant) },
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = MyTheme.secondary,
                    textColor = MyTheme.onSecondary,
                )
            )
            TextField(
                value = dstNode,
                onValueChange = { dstNode = it.uppercase() },
                label = { Text(placeholder2, color = MyTheme.onSurfaceVariant) },
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = MyTheme.secondary,
                    textColor = MyTheme.onSecondary,
                )
            )
        }
        Button(
            onClick = {
                if (srcNode.isNotEmpty()) {
                    onClick(srcNode.first().toString(), dstNode.first().toString())
                    srcNode = ""
                    dstNode = ""
                }

            },
            colors = ButtonDefaults.buttonColors(MyTheme.secondaryContainer, MyTheme.onSecondaryContainer)
        ) {
            Text(buttonText)
        }
    }
}


@Composable
fun GraphInputField(
    modifier: Modifier = Modifier,
    placeholder: String,
    buttonText: String,
    onButtonCLick: (String) -> Unit,
    buttonColors: ButtonColors = ButtonDefaults.buttonColors(MyTheme.secondaryContainer, MyTheme.onSecondaryContainer)

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
            onValueChange = { value = it.uppercase() },
            label = {
                Text(placeholder, color = MyTheme.onSurfaceVariant)
            },
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = MyTheme.secondary,
                textColor = MyTheme.onSecondary,
            )

        )
        Spacer(modifier = Modifier.width(8.dp))
        Button(
            colors = buttonColors,
            onClick = {
                if(value.isNotEmpty()) {
                    onButtonCLick(value.first().toString())
                    value = ""
                }
            }) {
            Text(buttonText)
        }
    }
}
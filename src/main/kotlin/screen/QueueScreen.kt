package screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import components.QueueInputField
import resources.MyTheme
import resources.queue.QueueItem

@Composable
fun QueueScreen() {
    val queue = remember { mutableStateListOf<String>() }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MyTheme.background,
        contentColor = MyTheme.onBackgroung
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(66.dp),

        ) {
            Column(
                modifier = Modifier,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                QueueInputField(
                    placeholder = "Value to enqueue",
                    buttonText = "Enqueue",
                    onButtonCLick = { value ->
                        queue.add(value)
                    },
                    buttonColors = ButtonDefaults.buttonColors(MyTheme.primary, MyTheme.onPrimary)
                )
                Button(
                    colors = ButtonDefaults.buttonColors(MyTheme.error, MyTheme.onError),
                    onClick = { queue.removeFirstOrNull() },
                    enabled = queue.isNotEmpty()
                ) {
                    Text(text = "Dequeue")
                }
            }
            if (queue.isEmpty()) {
                Text(
                    modifier = Modifier,
                    text = "The queue is empty",
                    fontSize = 24.sp,
                )
            }

            LazyVerticalGrid(
                columns = GridCells.Adaptive(90.dp),
                verticalArrangement = Arrangement.spacedBy(24.dp),
                horizontalArrangement = Arrangement.spacedBy(24.dp),
                modifier = Modifier.widthIn(200.dp)
            ) {
                items(queue.size) { index ->
                    QueueItem(
                        modifier = Modifier,
                        index = index,
                        value = queue[index]
                    )
                }
            }
        }
    }
}



package resources.queue

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun QueueItem(
    modifier: Modifier = Modifier,
    index: Int,
    value: Any
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "${index + 1}°",
            fontSize = 20.sp,
            color = MyTheme.onBackgroung
        )
        Card(
            backgroundColor = MyTheme.secondaryContainer,
            modifier = Modifier
                .fillMaxWidth(),
            elevation = 8.dp,
        ) {
            Text(
                text = value.toString(),
                fontWeight = FontWeight.Medium,
                fontSize = 22.sp,
                color = MyTheme.onSecondaryContainer,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(10.dp)
            )
        }
    }
}

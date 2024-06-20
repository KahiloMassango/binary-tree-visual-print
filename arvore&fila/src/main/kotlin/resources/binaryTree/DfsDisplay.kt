package resources.binaryTree

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun DfsDisplay(
    modifier: Modifier = Modifier,
    title: String,
    values: List<Int>
) {
    Column(
        modifier = modifier,
    ) {
        Text(
            modifier = Modifier.padding(bottom = 8.dp),
            text = title,
            fontSize = 18.sp,
            color = MyTheme.onBackgroung
        )

        LazyVerticalGrid(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 4.dp),
            columns = GridCells.Adaptive(48.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(values.size) { index ->
                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .background(MyTheme.secondaryContainer),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        modifier = Modifier.padding(8.dp),
                        text = values[index].toString(),
                        color = MyTheme.onSecondaryContainer
                    )

                }
            }
        }
        //Divider(color = MyTheme.outlineVariant)
    }
}


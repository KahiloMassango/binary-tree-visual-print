package resources.graphs

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun GraphMatrix(
    modifier: Modifier = Modifier,
    graph: Graph
) {

    Column(
        modifier = modifier
    ) {
        Row {
            Text("   ", modifier = Modifier.padding(7.dp))
            for (node in graph.nodes) {
                Text(
                    "$node  ",
                    modifier = Modifier.padding(4.dp),
                    fontSize = 20.sp,
                )
            }
        }
        for (i in graph.matrix.indices) {
            Row {
                Text(
                    "${graph.nodes[i]}  ",
                    modifier = Modifier.padding(4.dp),
                    fontSize = 20.sp,
                )
                for (j in graph.matrix[i].indices) {
                    Text("${graph.matrix[i][j]}  ",
                        modifier = Modifier.padding(4.dp),
                        fontSize = 20.sp,
                    )
                }
            }
        }
    }
}
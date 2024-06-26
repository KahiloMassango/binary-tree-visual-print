package screen

import resources.MyTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.unit.dp
import components.GraphInputField
import components.GraphTextField
import resources.graphs.DrawGraph
import resources.graphs.Graph

@Composable
fun GraphScreen(
    modifier: Modifier = Modifier,
) {
    val graph = remember { Graph() }
    val nodePositions = remember { mutableStateListOf<Offset>() }


    Surface(
        modifier = modifier.fillMaxSize(),
        color = MyTheme.background,
        contentColor = MyTheme.onBackgroung
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(66.dp)
        ) {
            Column(
                modifier = Modifier,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                GraphInputField(
                    placeholder = "Node",
                    buttonText = "Add",
                    onButtonCLick = { node ->
                        graph.addNode(node.uppercase())
                        nodePositions.clear()
                    },
                    buttonColors = ButtonDefaults.buttonColors(MyTheme.secondaryContainer, MyTheme.onSecondary),
                )
                GraphInputField(
                    placeholder = "Node",
                    buttonText = "Remove",
                    onButtonCLick = { node ->
                        graph.removeNode(node.uppercase())
                        nodePositions.clear()
                    },
                    buttonColors = ButtonDefaults.buttonColors(MyTheme.error, MyTheme.onError)
                )
                Spacer(modifier = Modifier.height(16.dp))
                GraphTextField(
                    placeholder1 = "Source node",
                    placeholder2 = "Destination node",
                    buttonText = "Add Edge",
                    onClick = { srcNode, dstNode ->
                        graph.addEdge(srcNode, dstNode)
                    }
                )
                Spacer(modifier = Modifier.height(16.dp))
                GraphTextField(
                    placeholder1 = "Source node",
                    placeholder2 = "Destination node",
                    buttonText = "Remove Edge",
                    onClick = { srcNode, dstNode ->
                        graph.removeEdge(srcNode, dstNode)
                    }
                )
            }
            DrawGraph(
                graph, nodePositions
            )
        }
    }
}


package screen

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import components.BinaryInputField
import drawBinaryTree
import resources.MyTheme
import resources.binaryTree.BinaryTree
import resources.binaryTree.DfsDisplay


@Preview
@Composable
fun BinaryTreeVisualizer() {

    val binaryTree = BinaryTree()

    Surface(
        modifier = Modifier,//.verticalScroll(rememberScrollState()),
        color = MyTheme.background
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            Column(
                modifier = Modifier
                    .width(600.dp)
            ) {
                BinaryInputField(
                    placeholder = "Valor a ser inserido na árvore",
                    buttonText = "Inserir",
                    onButtonCLick = { value ->
                        binaryTree.insert(value)
                    },
                    buttonColors = ButtonDefaults.buttonColors(MyTheme.primary, MyTheme.onPrimary)
                )
                Spacer(modifier = Modifier.height(8.dp))
                BinaryInputField(
                    placeholder = "Valor a ser removido",
                    buttonText = "Remover",
                    onButtonCLick = { value ->
                        binaryTree.remove(value)
                    },
                    buttonColors = ButtonDefaults.buttonColors(MyTheme.error, MyTheme.onError)
                )
                Spacer(modifier = Modifier.height(16.dp))
                DfsDisplay(
                    title = "Pré Ordem",
                    values = binaryTree.preOrderList
                )
                Spacer(modifier = Modifier.height(16.dp))
                DfsDisplay(
                    title = "Em Ordem",
                    values = binaryTree.inOrderList
                )
                Spacer(modifier = Modifier.height(16.dp))
                DfsDisplay(
                    title = "Pós ordem",
                    values = binaryTree.postOrderList
                )
            }

            Spacer(modifier = Modifier.height(44.dp))
            Canvas(
                modifier = Modifier
                    .weight(1f)

            ) {
                drawBinaryTree(binaryTree.head, size.width / 2, 50f, 100f, size.width / 4)
            }
        }
    }
}


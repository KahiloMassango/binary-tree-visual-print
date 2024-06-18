import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import components.DfsDisplay
import components.InputField
import kotlinx.coroutines.delay
import resources.BinaryTree


@Preview
@Composable
fun BinaryTreeVisualizer() {

    var binaryTree =  BinaryTree()
    val delayInMs = 200L

    LaunchedEffect(Unit) {
        binaryTree.insert(50)
        delay(delayInMs)
        binaryTree.insert(25)
        delay(delayInMs)
        binaryTree.insert(75)
        delay(delayInMs)
        binaryTree.insert(12)
        delay(delayInMs)
        binaryTree.insert(37)
        delay(delayInMs)
        binaryTree.insert(62)
        delay(delayInMs)
        binaryTree.insert(87)
        delay(delayInMs)
        binaryTree.insert(6)
        delay(delayInMs)
        binaryTree.insert(18)
        delay(delayInMs)
        binaryTree.insert(31)
        delay(delayInMs)
        binaryTree.insert(43)
        delay(delayInMs)
        binaryTree.insert(56)
        delay(delayInMs)
        binaryTree.insert(68)
        delay(delayInMs)
        binaryTree.insert(81)
        delay(delayInMs)
        binaryTree.insert(93)
        delay(delayInMs)
        binaryTree.remove(6)
        delay(delayInMs)
        binaryTree.insert(7)
        delay(delayInMs)
        binaryTree.remove(93)
        delay(delayInMs)
        binaryTree.insert(92)
        delay(delayInMs)
        binaryTree.insert(30)
        delay(delayInMs)
        binaryTree.insert(29)
        delay(delayInMs)
        binaryTree.insert(28)
        delay(delayInMs)
        binaryTree.remove(28)
        delay(delayInMs)
        binaryTree.insert(24)
        delay(delayInMs)
        binaryTree.remove(43)
        delay(delayInMs)
        binaryTree.insert(44)
        delay(delayInMs)
        binaryTree.insert(45)
        delay(delayInMs)
        binaryTree.remove(56)
        delay(delayInMs)
        binaryTree.insert(55)
        delay(delayInMs)
        binaryTree.insert(54)
        delay(delayInMs)
        binaryTree.insert(52)
        delay(delayInMs)
        binaryTree.insert(53)
        delay(delayInMs)
        binaryTree.insert(51)
        delay(delayInMs)
        binaryTree.insert(1)
        delay(delayInMs)
        binaryTree.insert(10)
        delay(delayInMs)
        binaryTree.insert(15)
        delay(delayInMs)
        binaryTree.remove(10)
        delay(delayInMs)
        binaryTree.insert(8)
        delay(delayInMs)
        binaryTree.remove(1)
        delay(delayInMs)
        binaryTree.insert(2)
        delay(delayInMs)


    }


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
                InputField(
                    placeholder = "Valor a ser inserido na árvore",
                    buttonText = "Inserir",
                    onButtonCLick = { value ->
                        binaryTree.insert(value)
                    }
                )
                Spacer(modifier = Modifier.height(8.dp))
                InputField(
                    placeholder = "Valor a ser removido",
                    buttonText = "Remover",
                    onButtonCLick = { value ->
                        binaryTree.remove(value)
                    }
                )
                Spacer(modifier = Modifier.height(8.dp))
                DfsDisplay(
                    title = "Cabeça",
                    values = binaryTree.nodesList
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


import androidx.compose.animation.*
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.NavigationRail
import androidx.compose.material.NavigationRailItem
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import resources.MyTheme
import screen.BinaryTreeVisualizer
import screen.GraphScreen
import screen.QueueScreen

@OptIn(ExperimentalAnimationApi::class)
@Preview
@Composable
fun App(
    modifier: Modifier = Modifier
) {
    var currentScreen by remember { mutableStateOf(Screen.BinaryTree) }

    Surface(
        modifier = modifier.fillMaxSize(),
        color = MyTheme.background
    ) {
        Row(
            Modifier.fillMaxSize()
        ) {
            NavigationRail(
                backgroundColor = MyTheme.secondary,
                contentColor = MyTheme.onSecondary,
            ) {
                NavigationRailItem(
                    selected = currentScreen == Screen.BinaryTree,
                    icon = { },
                    onClick = { currentScreen = Screen.BinaryTree },
                    label = { Text("Binary Tree", fontSize = 14.sp, fontWeight = FontWeight.Medium) },
                    selectedContentColor = MyTheme.primary,
                    unselectedContentColor = MyTheme.onSecondary
                )
                NavigationRailItem(
                    selected = currentScreen == Screen.Queue,
                    icon = { },
                    onClick = {
                        currentScreen = Screen.Queue
                    },
                    label = { Text(text = "Queue", fontSize = 14.sp, fontWeight = FontWeight.Medium) },
                    selectedContentColor = MyTheme.primary,
                    unselectedContentColor = MyTheme.onSecondary
                )
                NavigationRailItem(
                    selected = currentScreen == Screen.Graphs,
                    icon = { },
                    onClick = {
                        currentScreen = Screen.Graphs
                    },
                    label = { Text(text = "Graphs", fontSize = 14.sp, fontWeight = FontWeight.Medium) },
                    selectedContentColor = MyTheme.primary,
                    unselectedContentColor = MyTheme.onSecondary
                )
            }
            AnimatedContent(
                targetState = currentScreen,
                transitionSpec = {
                    if (targetState == Screen.Queue) {
                        slideInVertically { height -> height } + fadeIn() togetherWith  slideOutVertically { height -> -height } + fadeOut()
                    } else {
                        slideInVertically { height -> -height } + fadeIn() togetherWith  slideOutVertically { height -> height } + fadeOut()
                    }.using(
                        SizeTransform(clip = false)
                    )
                }
            ){ screen ->
                when(screen){
                    Screen.Queue -> QueueScreen()
                    Screen.BinaryTree -> BinaryTreeVisualizer()
                    Screen.Graphs -> GraphScreen()
                }
            }
        }
    }
}

enum class Screen {
    BinaryTree, Queue, Graphs
}

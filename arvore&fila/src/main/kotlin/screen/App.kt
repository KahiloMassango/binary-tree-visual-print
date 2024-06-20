package screen

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.with
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.NavigationRail
import androidx.compose.material.NavigationRailItem
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalAnimationApi::class)
@Preview
@Composable
fun App(
    modifier: Modifier = Modifier
) {
    var currentScreen by remember { mutableStateOf(Screen.BinaryTree) }

    Surface(
        modifier = Modifier.fillMaxSize(),
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
                    label = { Text("Árvore Binária", fontSize = 14.sp, fontWeight = FontWeight.Medium) },
                    selectedContentColor = MyTheme.primary,
                    unselectedContentColor = MyTheme.onSecondary
                )
                NavigationRailItem(
                    selected = currentScreen == Screen.Queue,
                    icon = { },
                    onClick = {
                        currentScreen = Screen.Queue
                        println("current: $currentScreen")
                    },
                    label = { Text(text = "Fila", fontSize = 14.sp, fontWeight = FontWeight.Medium) },
                    selectedContentColor = MyTheme.primary,
                    unselectedContentColor = MyTheme.onSecondary
                )
            }
            AnimatedContent(
                targetState = currentScreen,
                transitionSpec = {
                    if (targetState == Screen.Queue) {
                        slideInVertically { height -> height } + fadeIn() with slideOutVertically { height -> -height } + fadeOut()
                    } else {
                        slideInVertically { height -> -height } + fadeIn() with slideOutVertically { height -> height } + fadeOut()
                    }.using(
                        SizeTransform(clip = false)
                    )
                }
            ){ screen ->
                when(screen){
                    Screen.Queue -> QueueScreen()
                    Screen.BinaryTree -> BinaryTreeVisualizer()
                }
            }
        }
    }
}

enum class Screen {
    BinaryTree, Queue
}

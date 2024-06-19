import androidx.compose.material.MaterialTheme
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPlacement
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.application

fun main() = application {
    val windowState = WindowState(placement = WindowPlacement.Fullscreen)
    Window(onCloseRequest = ::exitApplication, state = windowState) {
        MaterialTheme {
            BinaryTreeVisualizer()
        }
    }
}

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPlacement
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.application
import screen.App

fun main() = application {
    val windowState = WindowState(placement = WindowPlacement.Fullscreen)
    Window(onCloseRequest = ::exitApplication, state = windowState) {
        MaterialTheme {
            Surface(
                modifier = Modifier,
                color = MyTheme.background,
            ) {
                App()
            }
        }
    }
}

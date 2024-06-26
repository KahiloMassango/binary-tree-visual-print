import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.*
import resources.MyTheme

fun main() = application {
    val windowState = WindowState(size = DpSize(1300.dp, 800.dp))
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

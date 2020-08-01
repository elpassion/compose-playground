package pl.elpassion.composeplayground

import androidx.compose.getValue
import androidx.compose.mutableStateOf
import androidx.compose.setValue
import java.time.ZonedDateTime

data class Training(
    val title: String,
    val time: ZonedDateTime
) {
    var counter by mutableStateOf(0)
}
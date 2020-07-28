package pl.elpassion.composeplayground

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Composable
import androidx.compose.state
import androidx.ui.core.ContentScale
import androidx.ui.core.Modifier
import androidx.ui.core.clip
import androidx.ui.core.setContent
import androidx.ui.foundation.Image
import androidx.ui.foundation.Text
import androidx.ui.foundation.shape.corner.RoundedCornerShape
import androidx.ui.layout.*
import androidx.ui.material.Button
import androidx.ui.material.MaterialTheme
import androidx.ui.res.imageResource
import androidx.ui.res.stringResource
import androidx.ui.tooling.preview.Preview
import androidx.ui.unit.dp
import pl.elpassion.composeplayground.ui.typography
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KotlinGym()
        }
    }
}

@Composable
fun KotlinGym() {
    val imageAsset = imageResource(id = R.drawable.woman_in_gym)
    val formattedDate = DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG)
        .format(ZonedDateTime.parse("2020-07-29T00:00:00+00:00"))
    val clicks = state(init = { 0 })
    MaterialTheme {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                stringResource(id = R.string.train_hard),
                style = typography.h2
            )
            val imageModifier = Modifier
                .preferredHeightIn(maxHeight = 160.dp)
                .fillMaxWidth()
                .clip(shape = RoundedCornerShape(16.dp))
            Image(
                imageAsset,
                modifier = imageModifier,
                contentScale = ContentScale.Crop
            )
            Spacer(Modifier.preferredHeight(16.dp))
            Text(
                "What's new in Jetpack Compose?",
                style = typography.body2
            )
            Text(
                formattedDate,
                style = typography.body2
            )
            Text(
                "Counter: ${clicks.value}",
                style = typography.body2
            )
            Button(onClick = {
                clicks.value++
            }, modifier = Modifier.padding(top = 8.dp)) {
                Text("Train")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun KotlinGymPreview() {
    KotlinGym()
}

@Preview(showBackground = true, locale = "pl")
@Composable
fun KotlinGymPreviewInPolish() {
    KotlinGym()
}

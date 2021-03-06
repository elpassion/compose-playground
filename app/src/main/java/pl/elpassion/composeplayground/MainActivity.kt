package pl.elpassion.composeplayground

import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.*
import androidx.ui.core.ContentScale
import androidx.ui.core.Modifier
import androidx.ui.core.clip
import androidx.ui.core.setContent
import androidx.ui.foundation.Image
import androidx.ui.foundation.Text
import androidx.ui.foundation.drawBackground
import androidx.ui.graphics.Color
import androidx.ui.graphics.ColorFilter
import androidx.ui.graphics.toArgb
import androidx.ui.layout.*
import androidx.ui.layout.ColumnScope.weight
import androidx.ui.material.Button
import androidx.ui.material.Divider
import androidx.ui.material.MaterialTheme
import androidx.ui.material.MaterialTheme.colors
import androidx.ui.res.colorResource
import androidx.ui.res.imageResource
import androidx.ui.res.stringResource
import androidx.ui.tooling.preview.Preview
import androidx.ui.unit.dp
import pl.elpassion.composeplayground.ui.KotlinGymTheme
import pl.elpassion.composeplayground.ui.shapes
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
    val trainings = remember {
        listOf(
            Training("What's new?", ZonedDateTime.parse("2020-07-29T00:00:00+00:00")),
            Training("Kotlin rocks", ZonedDateTime.parse("2020-07-30T00:00:00+00:00")),
            Training("Zdrowy kręgosłup", ZonedDateTime.parse("2020-07-31T00:00:00+00:00"))
        )
    }
    KotlinGymTheme {
        Column {
            for (training in trainings) {
                TrainingContent(training, onClick = {
                    training.counter++
                })
            }
        }
    }
}


@Composable
private fun TrainingContent(
    training: Training,
    onClick: () -> Unit
) {

    val imageAsset = imageResource(id = R.drawable.woman_in_gym)
    val formattedDate = DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG)
        .format(training.time)
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(16.dp, 16.dp, 16.dp, 0.dp)
    ) {
        Text(
            stringResource(id = R.string.train_hard),
            style = typography.h2,
            color = MaterialTheme.colors.onPrimary
        )
        val imageModifier = Modifier
            .preferredSizeIn()
            .heightIn(maxHeight = 120.dp)
            .fillMaxWidth()
            .clip(shape = shapes.small)
        Image(
            imageAsset,
            modifier = imageModifier,
            contentScale = ContentScale.Crop
        )
        Spacer(Modifier.preferredHeight(16.dp))
        Text(
            training.title,
            style = typography.h6
        )
        Divider(color = MaterialTheme.colors.primary)
        Text(
            formattedDate,
            style = typography.body2
        )
        Text(
            "Counter: ${training.counter}",
            style = typography.body2
        )
        Button(onClick = {
            onClick()
        }, modifier = Modifier.padding(top = 8.dp)) {
            Text("Train")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun KotlinGymPreview() {
    KotlinGym()
}

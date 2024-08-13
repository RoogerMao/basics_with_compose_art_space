package com.example.artspace

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ArtSpaceTheme {
                ArtSpaceInterface()
            }
        }
    }
}

@Composable
fun ArtSpaceInterface() {
    var imageNumber by remember { mutableIntStateOf(1) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {
        ArtDisplay(imageNumber)
        NavigationButtons(
            displayPreviousPainting = {
                if(imageNumber == 1) imageNumber = 5
                else imageNumber--
            },
            displayNextPainting = {
                if(imageNumber == 5) imageNumber = 1
                else imageNumber++
            }
        )
    }
}

@Composable
fun NavigationButtons(
    displayPreviousPainting: () -> Unit,
    displayNextPainting: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.Bottom,
        modifier = Modifier.fillMaxHeight()
    ) {
        Button(
            colors = ButtonColors(
                containerColor = Color(0xFF535D77),
                contentColor = Color.White,
                disabledContainerColor = Color(0xFF535D77),
                disabledContentColor = Color.White
            ),
            onClick = displayPreviousPainting,
            modifier = Modifier
                .weight(2f)
        ) {
            Text(
                text = "Previous"
            )
        }
        Spacer(
            modifier = Modifier.weight(1f)
        )
        Button(
            colors = ButtonColors(
                containerColor = Color(0xFF535D77),
                contentColor = Color.White,
                disabledContainerColor = Color(0xFF535D77),
                disabledContentColor = Color.White
            ),
            onClick = displayNextPainting,
            modifier = Modifier.weight(2f)
        ) {
            Text(
                text = "Next"
            )
        }
    }
}

@Composable
fun ArtDisplay(displayNumber: Int) {
    var displayID = R.drawable.school_of_athens
    var displayTitleID = R.string.athens_title
    var artistID = R.string.athens_artist
    var displayDateID = R.string.athens_date

    when (displayNumber) {
        2 -> {
            displayID = R.drawable.starry_night
            displayTitleID = R.string.starry_title
            artistID = R.string.starry_artist
            displayDateID = R.string.starry_date
        }
        3 -> {
            displayID = R.drawable.the_hay_wain
            displayTitleID = R.string.hay_title
            artistID = R.string.hay_artist
            displayDateID = R.string.hay_date
        }
        4 -> {
            displayID = R.drawable.wanderer_sea_of_fog
            displayTitleID = R.string.wanderer_title
            artistID = R.string.wanderer_artist
            displayDateID = R.string.wanderer_date
        }
        5 -> {
            displayID = R.drawable.woman_with_a_parasol
            displayTitleID = R.string.woman_title
            artistID = R.string.woman_artist
            displayDateID = R.string.woman_date
        }
        else -> {}
    }

    Column (
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxHeight(0.9f)
            .verticalScroll(rememberScrollState())
    ){
        Image(
            painter = painterResource(id = displayID),
            contentDescription = "${stringResource(id = displayTitleID)} by ${stringResource(id = artistID)}",
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 48.dp)
                .border(
                    width = 16.dp,
                    brush = SolidColor(value = Color.White),
                    shape = RectangleShape
                )
                .shadow(elevation = 10.dp)
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color(0xFFECEBF0))
                .padding(32.dp)
        ) {
            Text(
                text = stringResource(id = displayTitleID),
                fontSize = 42.sp,
                lineHeight = 48.sp,
                fontWeight = FontWeight.Light,
                textAlign = TextAlign.Center
            )
            Text(
                text = buildAnnotatedString {
                    withStyle(SpanStyle(fontWeight = FontWeight.Medium)) {
                        append(stringResource(id = artistID))
                    }
                    append(" (")
                    withStyle(SpanStyle(fontWeight = FontWeight.Light)) {
                        append(stringResource(id = displayDateID))
                    }
                    append(")")
                },
                fontSize = 22.sp,
                lineHeight = 28.sp,
                fontWeight = FontWeight.Light,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ArtSpaceTheme {
        ArtSpaceInterface()
    }
}
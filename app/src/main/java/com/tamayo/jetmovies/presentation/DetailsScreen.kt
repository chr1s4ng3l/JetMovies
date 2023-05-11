package com.tamayo.jetmovies.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tamayo.jetmovies.R
import com.tamayo.jetmovies.utils.YouTubePlayer

@Preview(showSystemUi = true)
@Composable
fun DetailsScreen() {
    Column(modifier = Modifier.fillMaxSize()) {
        YouTubePlayer("Eq1hj7MoJio", modifier = Modifier.fillMaxWidth())

        Text(
            modifier = Modifier.padding(4.dp),
            text = "The pope's exorcist",
            fontWeight = FontWeight.ExtraBold,
            fontSize = 22.sp,
            color = Color.White
        )

        Spacer(modifier = Modifier.size(4.dp))

        Row {
            Text(
                modifier = Modifier.padding(4.dp),
                text = "73% match",
                color = Color.Green,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(modifier = Modifier.size(4.dp))
            Text(
                modifier = Modifier.padding(4.dp),
                text = "2023-04-05",
                color = Color.White,
                fontWeight = FontWeight.SemiBold
            )

        }

        Text(
            modifier = Modifier.padding(4.dp),
            text = "Father Gabriele Amorth," +
                    " Chief Exorcist of the Vatican, " +
                    "investigates a young boy's terrifying " +
                    "possession and ends up uncovering a " +
                    "centuries-old conspiracy the Vatican has " +
                    "desperately tried to keep hidden.", color = Color.White
        )

        Image(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            contentScale = ContentScale.Crop,
            painter = painterResource(id = R.drawable.logo),
            contentDescription = ""
        )


    }

}
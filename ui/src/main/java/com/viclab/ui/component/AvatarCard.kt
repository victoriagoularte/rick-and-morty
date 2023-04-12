package com.viclab.ui.component

import android.content.res.Configuration
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.viclab.ui.R
import com.viclab.ui.theme.RickNMortyTheme

@Composable
fun AvatarCard(
    data: AvatarCardData,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier,
        shape = MaterialTheme.shapes.small,
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(data.avatarUrl)
                    .crossfade(true)
                    .build(),
                contentDescription = stringResource(R.string.character_avatar),
                modifier = Modifier
                    .size(88.dp)
                    .clip(RoundedCornerShape(4.dp))

            )
            Text(
                text = data.text,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier
                    .weight(2f)
                    .padding(horizontal = 16.dp)
            )
            Icon(
                imageVector = Icons.Default.ChevronRight,
                contentDescription = null,
                modifier = Modifier
            )
        }
    }

}

@Preview(name = "DarkMode", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(showBackground = true, backgroundColor = 0xFFF0EAE2)
@Composable
private fun PreviewAvatarCard() {
    RickNMortyTheme {
        AvatarCard(
            AvatarCardData(
                text = "Rick Sanchez",
                avatarUrl = "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
            )
        )
    }
}

data class AvatarCardData(
    val text: String,
    val avatarUrl: String
)

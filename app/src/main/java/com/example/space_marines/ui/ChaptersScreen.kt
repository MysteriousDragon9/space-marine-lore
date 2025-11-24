package com.example.space_marines.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.space_marines.data.Chapter

@Composable
fun ChaptersScreen(viewModel: ChaptersViewModel) {
    val state by viewModel.state.collectAsState()

    when (state) {
        is ChaptersUiState.Loading -> Text("Loading...")
        is ChaptersUiState.Error -> Text("Error: ${(state as ChaptersUiState.Error).message}")
        is ChaptersUiState.Success -> ChaptersList((state as ChaptersUiState.Success).chapters)
    }
}

@Composable
fun ChaptersList(chapters: List<Chapter>) {
    LazyColumn {
        items(chapters) { chapter -> ChapterRow(chapter) }
    }
}

@Composable
fun ChapterRow(chapter: Chapter) {
    Column(Modifier.fillMaxWidth().padding(16.dp)) {
        Row {
            AsyncImage(
                model = chapter.iconUrl,
                contentDescription = "${chapter.name} icon",
                modifier = Modifier.size(64.dp),
                contentScale = ContentScale.Crop
            )
            Spacer(Modifier.width(12.dp))
            Column {
                Text(chapter.name, style = MaterialTheme.typography.titleMedium)
                Text("Homeworld: ${chapter.homeworld}", style = MaterialTheme.typography.bodyMedium)
                Text("Primarch: ${chapter.primarch}", style = MaterialTheme.typography.bodyMedium)
                Text("Specialty: ${chapter.specialty}", style = MaterialTheme.typography.bodyMedium)
            }
        }
        Spacer(Modifier.height(8.dp))
        Text(chapter.lore, style = MaterialTheme.typography.bodySmall, maxLines = 4)
    }
}
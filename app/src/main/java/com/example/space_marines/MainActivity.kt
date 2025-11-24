
package com.example.space_marines

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import com.example.space_marines.network.ChaptersApi
import com.example.space_marines.repository.ChaptersRepository
import com.example.space_marines.ui.ChaptersScreen
import com.example.space_marines.ui.ChaptersViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val chaptersUrl = "https://raw.githubusercontent.com/MysteriousDragon9/space-marine-lore/main/chapters.json"
        val api = ChaptersApi.create()
        val repo = ChaptersRepository(api, chaptersUrl)
        val viewModel = ChaptersViewModel(repo)

        setContent {
            MaterialTheme {
                ChaptersScreen(viewModel)
            }
        }
    }
}
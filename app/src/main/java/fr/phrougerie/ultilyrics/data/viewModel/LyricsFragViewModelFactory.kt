package fr.phrougerie.ultilyrics.data.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import fr.phrougerie.ultilyrics.data.model.tracks.Track

class LyricsFragViewModelFactory(private val track: Track) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return LyricsFragViewModel(track) as T
    }

}
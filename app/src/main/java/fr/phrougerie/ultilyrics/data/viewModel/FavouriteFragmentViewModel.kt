package fr.phrougerie.ultilyrics.data.viewModel

import androidx.lifecycle.ViewModel
import fr.phrougerie.ultilyrics.data.persistance.LyricsDatabase
import fr.phrougerie.ultilyrics.data.persistance.TrackRepository

class FavouriteFragmentViewModel : ViewModel() {
    private val repository: TrackRepository = TrackRepository(LyricsDatabase.getInstance().trackDAO())

    var trackData = repository.getAll()

}
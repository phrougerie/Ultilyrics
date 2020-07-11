package fr.phrougerie.ultilyrics.data.viewModel

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fr.phrougerie.ultilyrics.data.Constants
import fr.phrougerie.ultilyrics.data.Constants.OK
import fr.phrougerie.ultilyrics.data.model.Lyrics.LyricsResult
import fr.phrougerie.ultilyrics.data.model.tracks.Track
import fr.phrougerie.ultilyrics.data.Services.getService
import fr.phrougerie.ultilyrics.data.persistance.LyricsDatabase
import fr.phrougerie.ultilyrics.data.persistance.TrackRepository
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LyricsFragViewModel(val track: Track) : ViewModel() {

    val repository: TrackRepository = TrackRepository(LyricsDatabase.getInstance().trackDAO())
    var client = getService()
    val trackData = getTrack(track)

    private fun getTrack(track: Track): LiveData<Track> {
        val liveData = MutableLiveData<Track>()
        if(track.lyrics==null){
            client.trackLyrics(Constants.API_KEY, track.trackId).enqueue(object:
                Callback<LyricsResult> {
                override fun onResponse(
                    call: Call<LyricsResult>,
                    response: Response<LyricsResult>
                ) {
                    if (response.code() == OK) {
                        val lyrics = response.body()
                        lyrics?.let {
                            track.lyrics = lyrics.lyricsMessage.lyricsBody.lyrics?.lyrics
                            liveData.value = track
                        }
                    } else{
                        Log.d("error", response.message())
                    }
                }
                override fun onFailure(call: Call<LyricsResult>, t: Throwable) {
                    track.lyrics=""
                    liveData.value = track
                }
            })
        }
        else{
            liveData.value = track
        }
        return liveData
    }

    fun saveTrack(){

        trackData.value?.let {
            viewModelScope.launch{
                repository.insert(it)
            }

        }
    }

    fun removeTrack(){
        trackData.value?.let {
            viewModelScope.launch {
                repository.delete(it)
            }
        }
    }

}
package fr.phrougerie.ultilyrics.data.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import fr.phrougerie.ultilyrics.data.Constants
import fr.phrougerie.ultilyrics.data.Constants.OK

import fr.phrougerie.ultilyrics.data.Services.getService
import fr.phrougerie.ultilyrics.data.model.tracks.Track
import fr.phrougerie.ultilyrics.data.model.tracks.TrackResult
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchFragmentViewModel : ViewModel() {

    var tracks = MutableLiveData<ArrayList<Track>>()


    fun search(artist : String, track : String) {
        tracks.value?.let{ it.clear() }
        var trackstmp = ArrayList<Track>()
        val call = getService()!!.trackSearch(Constants.API_KEY,artist,track)
        call.enqueue(object: Callback<TrackResult> {
            override fun onResponse(
                call: Call<TrackResult>,
                response: Response<TrackResult>
            ) {
                if (response.code() == OK) {
                    val allTracks = response.body()
                    allTracks?.let {
                        for (track in it.trackMessage.trackBody.tracks!!) {
                            trackstmp.add(track.track)
                            //tracksAdapter.notifyItemInserted(tracksVM.tracks.size - 1 )
                        }
                        tracks.value = trackstmp
                    }
                }
                else{
                    Log.d("error", response.message())
                }
            }
            override fun onFailure(call: Call<TrackResult>, t: Throwable) {
                Log.d("error",t.message)
            }
        })
    }
}
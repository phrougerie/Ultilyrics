package fr.phrougerie.ultilyrics.data.Services


import fr.phrougerie.ultilyrics.data.Constants.NAME_KEY
import fr.phrougerie.ultilyrics.data.Methods.TRACK_LYRICS
import fr.phrougerie.ultilyrics.data.Methods.TRACK_SEARCH
import fr.phrougerie.ultilyrics.data.model.Lyrics.LyricsResult
import fr.phrougerie.ultilyrics.data.model.tracks.TrackResult
import retrofit2.Call

import retrofit2.http.GET
import retrofit2.http.Query

interface IMusixService {

    @GET(TRACK_SEARCH)
    fun trackSearch(
        @Query(NAME_KEY) apiKey: String,
        @Query("q_artist") artist: String? = null,
        @Query("q_track") track: String? = null

    ): Call<TrackResult>

    @GET(TRACK_LYRICS)
    fun trackLyrics(
        @Query(NAME_KEY) apiKey: String,
        @Query("track_id") trackId : Int
    ): Call<LyricsResult>
}
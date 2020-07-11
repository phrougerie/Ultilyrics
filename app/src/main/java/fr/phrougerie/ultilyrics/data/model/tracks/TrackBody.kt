package fr.phrougerie.ultilyrics.data.model.tracks

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class TrackBody(
    @SerializedName("track_list")
    @Expose
    val tracks: List<TrackItem>? = null)
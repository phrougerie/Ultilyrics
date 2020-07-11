package fr.phrougerie.ultilyrics.data.model.tracks

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class TrackResult(
        @SerializedName("message")
        @Expose
        val trackMessage: TrackMessage
)











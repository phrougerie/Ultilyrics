package fr.phrougerie.ultilyrics.data.model.tracks

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import fr.phrougerie.ultilyrics.data.model.Header

data class TrackMessage(
    @SerializedName("header")
    @Expose
    val header: Header,
    @SerializedName("body")
    @Expose
    val trackBody: TrackBody
)

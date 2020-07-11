package fr.phrougerie.ultilyrics.data.model.Lyrics

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class LyricsResult(
    @SerializedName("message")
    @Expose
    val lyricsMessage: LyricsMessage
)
package fr.phrougerie.ultilyrics.data.model.Lyrics

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import fr.phrougerie.ultilyrics.data.model.Header

data class LyricsMessage(
    @SerializedName("header")
    @Expose
    val header: Header,
    @SerializedName("body")
    @Expose
    val lyricsBody: LyricsBody
)
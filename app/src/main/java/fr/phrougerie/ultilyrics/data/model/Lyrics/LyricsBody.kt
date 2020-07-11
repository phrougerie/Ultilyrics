package fr.phrougerie.ultilyrics.data.model.Lyrics

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class LyricsBody(
    @SerializedName("lyrics")
    @Expose
    val lyrics : Lyrics? = null)
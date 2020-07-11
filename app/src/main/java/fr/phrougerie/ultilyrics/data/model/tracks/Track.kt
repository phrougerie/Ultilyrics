package fr.phrougerie.ultilyrics.data.model.tracks

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable


@Entity(tableName = "tracks")
data class Track(
    @PrimaryKey @SerializedName("track_id") val trackId: Int,
    @SerializedName("track_name") var trackName: String,
    @SerializedName("artist_id") var artistId: Int,
    @SerializedName("artist_name") var artistName: String,
    var lyrics: String? = null) : Serializable {
    companion object{
        private const val serialVersionUID: Long = -4567449467658913556L
    }

}
package fr.phrougerie.ultilyrics.data.persistance

import androidx.lifecycle.LiveData

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import fr.phrougerie.ultilyrics.data.model.tracks.Track

@Dao
interface TrackDao {
    //Note :les requetes avec livedata sont asynchrones
    @Query("SELECT * FROM tracks")
    fun getAllTracks() : LiveData<List<Track>>

    @Query("SELECT * FROM tracks Where trackId = :trackId")
    fun finTrackById(trackId: Int) : LiveData<Track>

    @Insert(onConflict = REPLACE)
    fun insertTrack(track: Track)

    @Insert
    fun insertAllTracks(vararg tracks: Track)

    @Update(onConflict = REPLACE )
    fun updateTrack(track: Track)

    @Delete
    fun deleteTrack(track: Track)

    
}
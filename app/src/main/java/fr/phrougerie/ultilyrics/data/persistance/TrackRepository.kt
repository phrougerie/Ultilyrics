package fr.phrougerie.ultilyrics.data.persistance

import fr.phrougerie.ultilyrics.data.model.tracks.Track
import fr.phrougerie.ultilyrics.data.Services.getService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors


class TrackRepository(private val trackDao: TrackDao) {

    var client = getService()
    suspend fun insert(track: Track) = withContext(Dispatchers.IO)  { trackDao.insertTrack((track)) }
    suspend fun delete(track: Track) = withContext(Dispatchers.IO){ trackDao.deleteTrack((track)) }
    suspend fun update(track: Track) = withContext(Dispatchers.IO){ trackDao.updateTrack(track)}
    suspend fun insertAll(vararg tracks: Track) = withContext(Dispatchers.IO){trackDao.insertAllTracks(*tracks)}
    fun findById(trackId: Int) = trackDao.finTrackById(trackId)
    fun getAll() = trackDao.getAllTracks()




}
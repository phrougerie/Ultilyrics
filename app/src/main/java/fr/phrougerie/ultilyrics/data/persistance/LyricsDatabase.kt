package fr.phrougerie.ultilyrics.data.persistance

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import fr.phrougerie.ultilyrics.UltiLyricsApplication
import fr.phrougerie.ultilyrics.data.model.tracks.Track
import java.lang.RuntimeException

private const val TRACK_DB_NAME = "track_database"

@Database(entities = [Track::class], version = 1)
abstract class LyricsDatabase : RoomDatabase(){
    abstract fun trackDAO() : TrackDao

    companion object {
        private var application: Application? = null
        @Volatile
        private var instance: LyricsDatabase? = null

        fun getInstance(): LyricsDatabase {
            if (application != null) {
                if (instance == null)
                    synchronized(this) {
                        if (instance == null)
                            instance = Room.databaseBuilder(
                                application!!.applicationContext,
                                LyricsDatabase::class.java,
                                TRACK_DB_NAME
                            ).build()
                    }

                return instance!!

            } else throw RuntimeException("The database is not inititialize")
        }

        @Synchronized
        fun initialize(app: UltiLyricsApplication) {
            if (application == null)
                application = app
            else
                throw RuntimeException("the database must not be initialized twice")
        }
    }


}
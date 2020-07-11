package fr.phrougerie.ultilyrics

import android.app.Application
import fr.phrougerie.ultilyrics.data.persistance.LyricsDatabase

class UltiLyricsApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        LyricsDatabase.initialize(this)
    }
}
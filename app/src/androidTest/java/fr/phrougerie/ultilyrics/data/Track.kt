package fr.phrougerie.ultilyrics.data

data class Track(var artist: String = "",
                 var track: String = "",
                 var lyrics: String = "",
                 var language: String = "",
                 var notice : String = "",
                 var text : String = "")
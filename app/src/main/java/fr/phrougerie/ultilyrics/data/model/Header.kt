package fr.phrougerie.ultilyrics.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Header(
    @SerializedName("status_code")
    @Expose
    val status: Int,
    @SerializedName("execute_time")
    @Expose
    val execute_time: Double,
    @SerializedName("available")
    @Expose
    val available: Int)
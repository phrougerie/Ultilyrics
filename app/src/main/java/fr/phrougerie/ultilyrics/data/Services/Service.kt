package fr.phrougerie.ultilyrics.data.Services

import com.google.gson.GsonBuilder
import fr.phrougerie.ultilyrics.data.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

fun getService()  : IMusixService{

    val gson = GsonBuilder().setLenient().create()

    val retrofit = Retrofit.Builder()
        .baseUrl(Constants.API_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()



    return retrofit.create(IMusixService::class.java)
}
package uz.gita.newsappfinaly.data.remote.api

import retrofit2.Response
import retrofit2.http.GET
import uz.gita.newsappfinaly.data.model.Country
import uz.gita.newsappfinaly.utils.ResultData

interface CountryApi {

    @GET("npm/country-flag-emoji-json@2.0.0/dist/index.json")
    suspend fun getAllCountry(): Response<List<Country>>
}
package com.spectro.socialbankmovieapi.api

import com.spectro.socialbankmovieapi.model.MovieDetail
import com.spectro.socialbankmovieapi.model.MovieList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("?apikey=a071bd4f&type=movie")//i=tt0462499
    suspend fun getMovies(@Query("s") search: String): Response<MovieList>

    @GET("?apikey=a071bd4f")
    suspend fun getMovieDetails(@Query("i") id: String): Response<MovieDetail>

}
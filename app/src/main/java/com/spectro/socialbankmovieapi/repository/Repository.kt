package com.spectro.socialbankmovieapi.repository

import com.spectro.socialbankmovieapi.api.RetrofitInstance
import com.spectro.socialbankmovieapi.model.MovieDetail
import com.spectro.socialbankmovieapi.model.MovieList
import retrofit2.Response

class Repository {

    suspend fun getMovies(search: String): Response<MovieList> {
        return RetrofitInstance.api.getMovies(search)
    }

    suspend fun getMovieDetails(id: String) : Response<MovieDetail>{
        return RetrofitInstance.api.getMovieDetails(id)
    }
}
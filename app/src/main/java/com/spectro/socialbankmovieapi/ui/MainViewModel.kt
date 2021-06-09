package com.spectro.socialbankmovieapi.ui


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.spectro.socialbankmovieapi.repository.Repository
import com.spectro.socialbankmovieapi.model.MovieDetail
import com.spectro.socialbankmovieapi.model.MovieList
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel(private val repository: Repository): ViewModel() {

    val myResponse: MutableLiveData<Response<MovieList>> = MutableLiveData()
    val myResponse2: MutableLiveData<Response<MovieDetail>> = MutableLiveData()

    fun getMovie(search: String){
        viewModelScope.launch {
            val response = repository.getMovies(search)
            myResponse.value = response
        }
    }

    fun getMovieDetails(id: String){
        viewModelScope.launch {
            val response = repository.getMovieDetails(id)
            myResponse2.value = response
        }
    }

}
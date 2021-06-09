package com.spectro.socialbankmovieapi.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.spectro.socialbankmovieapi.R
import com.spectro.socialbankmovieapi.model.MovieDetail
import com.spectro.socialbankmovieapi.repository.Repository
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.movie_details.*
import kotlinx.android.synthetic.main.toolbar.*
import retrofit2.Response

class MovieDetails : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.movie_details)

        val id: String? = intent.getStringExtra("id")
        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)

        if (id != null) {
            viewModel.getMovieDetails(id)
            progress_bar.visibility = View.VISIBLE
        }
        viewModel.myResponse2.observe(this, {
            bindMovieDetails(it)
            progress_bar.visibility = View.GONE
        })
    }

    private fun bindMovieDetails(movie: Response<MovieDetail>){

        tv_movie_title.text = movie.body()?.Title
        tv_movie_type.text = movie.body()?.Type
        tv_movie_genre.text = movie.body()?.Genre
        tv_movie_year.text = movie.body()?.Year
        tv_movie_released.text = movie.body()?.Released
        tv_movie_imdbrating.text = movie.body()?.imdbRating
        tv_movie_plot.text = movie.body()?.Plot

        Glide.with(iv_movie_poster)
            .load(movie.body()?.Poster)
            .apply(RequestOptions.centerCropTransform())
            .error(R.drawable.poster_movie_default)
            .into(iv_movie_poster)
    }


}
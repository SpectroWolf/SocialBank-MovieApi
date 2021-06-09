package com.spectro.socialbankmovieapi.ui

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.spectro.socialbankmovieapi.R
import com.spectro.socialbankmovieapi.model.Movie
import kotlinx.android.synthetic.main.movie_list_item.view.*

class RecyclerViewAdapter(private val context: Context): RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>() {

    var movies = ArrayList<Movie>()

    fun setMovieList(data: ArrayList<Movie>){
        this.movies = data
    }


    class MyViewHolder(view: View): RecyclerView.ViewHolder(view){
        val tvTitle = view.tv_Title
        val tvYear = view.tv_Year
        val ivPoster = view.iv_Poster

        fun bind (data: Movie, context: Context) {
            tvTitle.text = data.Title
            tvYear.text = data.Year

            Glide.with(ivPoster)
                .load(data.Poster)
                .apply(RequestOptions.centerCropTransform())
                .into(ivPoster)

            itemView.setOnClickListener {
                val intent = Intent(context, MovieDetails::class.java)
                intent.putExtra("id", data.imdbID)
                context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_list_item, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(movies[position], context)
    }

    override fun getItemCount(): Int {
        return movies.size
    }
}
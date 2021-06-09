package com.spectro.socialbankmovieapi.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import android.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.spectro.socialbankmovieapi.R
import com.spectro.socialbankmovieapi.model.Movie
import com.spectro.socialbankmovieapi.repository.Repository
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerViewAdapter: RecyclerViewAdapter
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initRecyclerView()
        createData()
    }

    private fun initRecyclerView(){
        recyclerView.layoutManager = GridLayoutManager(this,2)
        recyclerViewAdapter = RecyclerViewAdapter(this)
        recyclerView.adapter = recyclerViewAdapter
    }

    private fun createData(){
        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)

        btn_search.setOnClickListener{
            txt_empty.visibility = View.GONE
            val search = et_search.text.toString()
            viewModel.getMovie(search)
            progress_bar_main.visibility = View.VISIBLE
            viewModel.myResponse.observe(this, Observer{response ->
                if(response.isSuccessful && response.body()?.Response != false){
                    recyclerViewAdapter.setMovieList(response.body()?.Search as ArrayList<Movie>)
                    recyclerViewAdapter.notifyDataSetChanged()
                    progress_bar_main.visibility = View.GONE
                } else {
                    progress_bar_main.visibility = View.GONE
                    Toast.makeText(this@MainActivity, "Filme não encontrado!", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }
}
package com.spectro.socialbankmovieapi.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
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
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerViewAdapter = RecyclerViewAdapter(this)
        recyclerView.adapter = recyclerViewAdapter
    }

    private fun createData(){
        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)

        btn_search.setOnClickListener{
            val search = et_search.text.toString()
            viewModel.getMovie(search)

            viewModel.myResponse.observe(this, Observer{response ->
                if(response.isSuccessful && response.body()?.Response != false){
                    recyclerViewAdapter.setMovieList(response.body()?.Search as ArrayList<Movie>)
                    recyclerViewAdapter.notifyDataSetChanged()
                } else {
                    Toast.makeText(this@MainActivity, "Erro ao buscar filme.", Toast.LENGTH_SHORT).show()
                }
            })
            viewModel.myResponse.observe(this, Observer { response ->
                Log.d("Response", response.body()?.Search.toString())
            })
        }
    }
}
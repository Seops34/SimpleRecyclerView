package com.seosh.simplerecyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.seosh.simplerecyclerview.adpater.MovieAdapter
import com.seosh.simplerecyclerview.adpater.data.MovieItem
import com.seosh.simplerecyclerview.databinding.ActivityMainBinding
import com.seosh.simplerecyclerview.viewmodel.MainViewModel
import com.seosh.simplerecyclerview.viewmodel.MovieAdapterViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val firstList = listOf<MovieItem>(
        MovieItem("A", "10")
    )

    private val secondList = listOf<MovieItem>(
        MovieItem("A", "1"),
        MovieItem("B", "2")
    )


    private val mainViewModel : MainViewModel by lazy {
        MainViewModel()
    }

    private val movieAdapterViewModel : MovieAdapterViewModel by lazy {
        MovieAdapterViewModel()
    }

    lateinit var linearLayoutManager: LinearLayoutManager
    lateinit var movieAdapter: MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding : ActivityMainBinding = DataBindingUtil.setContentView(this@MainActivity, R.layout.activity_main)

        with(binding) {
            setLayout(this)
            subscribeUI(this)
        }
    }

    private fun setLayout(binding: ActivityMainBinding) {
        linearLayoutManager = LinearLayoutManager(this@MainActivity)
        movieAdapter = MovieAdapter(movieAdapterViewModel)

        binding.recyclerViewMovie.apply {
            layoutManager = linearLayoutManager
            adapter = movieAdapter
        }

        movieAdapter.submitList(firstList)

        binding.btnAdd.setOnClickListener {
            movieAdapter.submitList(secondList)
        }
    }

    private fun subscribeUI(binding: ActivityMainBinding) {

    }

}
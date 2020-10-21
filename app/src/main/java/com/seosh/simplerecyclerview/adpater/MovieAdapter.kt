package com.seosh.simplerecyclerview.adpater

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.seosh.simplerecyclerview.adpater.data.MovieItem
import com.seosh.simplerecyclerview.databinding.ItemMovieBinding
import com.seosh.simplerecyclerview.viewmodel.MovieAdapterViewModel

class MovieAdapter(private val viewModel: MovieAdapterViewModel) : ListAdapter<MovieItem, MovieAdapter.MovieViewHolder>(MovieDifferCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        (holder as MovieViewHolder).bind(getItem(position))
    }

    inner class MovieViewHolder(private val binding: ItemMovieBinding) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.viewModel = viewModel
        }

        fun bind(movieItem: MovieItem) {
            binding.movieItem = movieItem
        }
    }
}

class MovieDifferCallback : DiffUtil.ItemCallback<MovieItem>() {
    // 두 객체가 같은 항목의 아이템을 표시하는지 결정한다.
    override fun areItemsTheSame(oldItem: MovieItem, newItem: MovieItem): Boolean {
        Log.d("seosh", "areItemsTheSame (${oldItem} / ${newItem}) : ${oldItem.title == newItem.title}")

        return oldItem.title == newItem.title
    }

    // 두 항목의 데이터가 같은지 여부를 결정한다.
    override fun areContentsTheSame(oldItem: MovieItem, newItem: MovieItem): Boolean {
        Log.d("seosh", "areContentsTheSame (${oldItem} / ${newItem}) : ${oldItem == newItem}")

        return oldItem == newItem
    }

}
package id.radenyaqien.jetpackdicoding.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import id.radenyaqien.jetpackdicoding.DetailMoviesActivity
import id.radenyaqien.jetpackdicoding.MoviesMapping
import id.radenyaqien.jetpackdicoding.databinding.ItemMoviesBinding
import id.radenyaqien.jetpackdicoding.room.MoviesEntity
import id.radenyaqien.jetpackdicoding.utils.Dummy
import id.radenyaqien.jetpackdicoding.utils.bindImageUrlNoFit

class FavoriteMoviesAdapter :
    PagedListAdapter<MoviesEntity, FavoriteMoviesAdapter.MViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MoviesEntity>() {
            override fun areItemsTheSame(oldItem: MoviesEntity, newItem: MoviesEntity): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: MoviesEntity, newItem: MoviesEntity): Boolean {
                return oldItem == newItem
            }

        }
    }

    class MViewHolder(private val binding: ItemMoviesBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val mapper by lazy { MoviesMapping() }
        fun bind(moviesEntity: MoviesEntity) {
            with(binding) {
                this.imageView.bindImageUrlNoFit(Dummy.image + moviesEntity.posterPath)
                this.txtId.text = moviesEntity.id.toString()
                this.txtGenre.text = moviesEntity.genreIds.toString()
                this.txtNama.text = moviesEntity.title
                this.root.setOnClickListener {
                    val movies = mapper.to(moviesEntity)
                    val intent = Intent(itemView.context, DetailMoviesActivity::class.java)
                    intent.putExtra(DetailMoviesActivity.EXTRA_MOVIES, movies)
                    itemView.context.startActivity(intent)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MViewHolder {
        val itemHolder =
            ItemMoviesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MViewHolder(itemHolder)
    }

    override fun onBindViewHolder(holder: MViewHolder, position: Int) {
        val item = getItem(position)

        if (item != null) {
            holder.bind(item)
        }
    }
}
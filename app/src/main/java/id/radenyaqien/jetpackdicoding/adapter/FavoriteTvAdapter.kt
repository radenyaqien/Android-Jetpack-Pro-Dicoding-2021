package id.radenyaqien.jetpackdicoding.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import id.radenyaqien.jetpackdicoding.DetailTvShowsActivity
import id.radenyaqien.jetpackdicoding.TvMapping
import id.radenyaqien.jetpackdicoding.databinding.ItemPopularBinding
import id.radenyaqien.jetpackdicoding.room.TvEntity
import id.radenyaqien.jetpackdicoding.utils.Dummy
import id.radenyaqien.jetpackdicoding.utils.bindImageUrlNoFit

class FavoriteTvAdapter : PagedListAdapter<TvEntity, FavoriteTvAdapter.viewHolder>(DIFF_CALLBACK) {


    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<TvEntity>() {
            override fun areItemsTheSame(oldItem: TvEntity, newItem: TvEntity): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: TvEntity, newItem: TvEntity): Boolean {
                return oldItem == newItem
            }

        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val itemsAcademyBinding =
            ItemPopularBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return viewHolder(itemsAcademyBinding)
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        val tvshow = getItem(position)

        if (tvshow != null) {
            holder.bind(tvshow)
        }

    }

    class viewHolder(private val binding: ItemPopularBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val mapper by lazy { TvMapping() }
        fun bind(tvEntity: TvEntity) {
            with(binding) {
                this.imageView.bindImageUrlNoFit(Dummy.image + tvEntity.posterPath)
                this.txtId.text = tvEntity.id.toString()
                this.txtGenre.text = tvEntity.firstAirDate
                this.txtNama.text = tvEntity.name
                this.root.setOnClickListener {
                    val popularTv = mapper.to(tvEntity)
                    val intent = Intent(itemView.context, DetailTvShowsActivity::class.java)
                    intent.putExtra(DetailTvShowsActivity.EXTRA_TVSHOWS, popularTv)
                    itemView.context.startActivity(intent)
                }
            }
        }
    }
}
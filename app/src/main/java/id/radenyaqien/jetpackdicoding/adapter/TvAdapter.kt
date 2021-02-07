package id.radenyaqien.jetpackdicoding.adapter

import com.squareup.picasso.Picasso
import id.radenyaqien.jetpackdicoding.R
import id.radenyaqien.jetpackdicoding.databinding.ItemTvshowsBinding
import id.radenyaqien.jetpackdicoding.models.TvShows

class TvAdapter : BaseRVAdapter<TvShows, ItemTvshowsBinding>() {

    override fun getLayout() = R.layout.item_tvshows

    override fun onBindViewHolder(
            holder: Companion.BaseViewHolder<ItemTvshowsBinding>,
            position: Int
    ) {
        Picasso
                .get()
                .load(items[position].image)
                .into(holder.binding.imageView)

        holder.binding.txtGenre.text = items[position].genre
        holder.binding.txtId.text = items[position].id.toString()
        holder.binding.txtNama.text = items[position].name
        holder.binding.root.setOnClickListener {
            listener?.invoke(it, items[position], position)
        }
    }
}
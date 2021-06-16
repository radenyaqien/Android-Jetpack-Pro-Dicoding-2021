package id.radenyaqien.jetpackdicoding.adapter

import com.squareup.picasso.Picasso
import id.radenyaqien.jetpackdicoding.R
import id.radenyaqien.jetpackdicoding.databinding.ItemMoviesBinding
import id.radenyaqien.jetpackdicoding.models.PopularMovies
import id.radenyaqien.jetpackdicoding.utils.Dummy

class MoviesAdapter : BaseRVAdapter<PopularMovies, ItemMoviesBinding>() {
    override fun getLayout() = R.layout.item_movies

    override fun onBindViewHolder(
        holder: Companion.BaseViewHolder<ItemMoviesBinding>,
        position: Int
    ) {
        Picasso
            .get()
            .load(Dummy.image + items[position].posterPath)
            .into(holder.binding.imageView)

        holder.binding.txtGenre.text = items[position].genreIds[0].toString()
        holder.binding.txtId.text = items[position].id.toString()
        holder.binding.txtNama.text = items[position].title
        holder.binding.root.setOnClickListener {

            listener?.invoke(it, items[position], position)
        }
    }
}
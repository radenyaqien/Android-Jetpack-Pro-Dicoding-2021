package id.radenyaqien.jetpackdicoding.adapter

import id.radenyaqien.jetpackdicoding.R
import id.radenyaqien.jetpackdicoding.databinding.ItemPopularBinding
import id.radenyaqien.jetpackdicoding.models.PopularTv
import id.radenyaqien.jetpackdicoding.utils.Dummy
import id.radenyaqien.jetpackdicoding.utils.bindImageUrlNoFit

class popularAdapter : BaseRVAdapter<PopularTv, ItemPopularBinding>() {
    override fun getLayout() = R.layout.item_popular

    override fun onBindViewHolder(
        holder: Companion.BaseViewHolder<ItemPopularBinding>,
        position: Int
    ) {

        val model = items[position]
        holder.binding.imageView.bindImageUrlNoFit(Dummy.image + items[position].posterPath)
        holder.binding.txtId.text = model.id.toString()
        holder.binding.txtGenre.text = model.firstAirDate
        holder.binding.txtNama.text = model.name
        holder.binding.root.setOnClickListener {
            listener?.invoke(it, model, position)
        }


    }
}
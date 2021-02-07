package id.radenyaqien.jetpackdicoding.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView


abstract class BaseRVAdapter<T : Any, VB : ViewDataBinding> :
        RecyclerView.Adapter<BaseRVAdapter.Companion.BaseViewHolder<VB>>() {


    var items = mutableListOf<T>()

    fun addItems(items: List<T>) {

        this.items = items as MutableList<T>
        notifyDataSetChanged()
    }

    fun removeItems(i: Int) {
        this.items.removeAt(i)
        notifyDataSetChanged()
    }

    fun addItem(items: List<T>) {
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    fun clearItems() {
        this.items.clear()
        notifyDataSetChanged()
    }

    var listener: ((view: View, item: T, position: Int) -> Unit)? = null
    var listener1: ((view: View, item: T, position: Int) -> Unit)? = null
    abstract fun getLayout(): Int

    override fun getItemCount() = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = BaseViewHolder<VB>(
            DataBindingUtil.inflate(LayoutInflater.from(parent.context), getLayout(), parent, false)
    )

    companion object {
        class BaseViewHolder<VB : ViewDataBinding>(var binding: VB) :
                RecyclerView.ViewHolder(binding.root)

    }
}
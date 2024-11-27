package ir.ha.meproject.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ir.ha.meproject.data.model.UserEntity
import ir.ha.meproject.databinding.ItemAdapterRecyclerViewBinding

class UsersAdapter : RecyclerView.Adapter<UsersAdapter.VH>() {

    private val items = arrayListOf<UserEntity>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(
            ItemAdapterRecyclerViewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.binding(items[position])
    }


    fun submitList(list: List<UserEntity>) {
        items.clear()
        items.addAll(list)
        notifyDataSetChanged()
    }


    fun clearList() {
        items.clear()
        notifyDataSetChanged()
    }


    inner class VH(val binding: ItemAdapterRecyclerViewBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun binding(user: UserEntity) {
            binding.userName.text = user.firstName.plus(" - " + user.lastName)

        }


    }
}
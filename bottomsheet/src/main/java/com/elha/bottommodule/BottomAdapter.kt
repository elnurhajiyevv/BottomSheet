package com.elha.bottommodule

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.elha.bottommodule.databinding.ItemBottomTypeBinding

/**
 * Created by elnurh on 4/23/2024.
 */
class BottomAdapter(
    private val clickListener: BottomItemClick
) : BaseAdapter<BottomModule, BottomAdapter.BottomViewHolder>(
    areItemsTheSame = { oldItem, newItem -> oldItem.title == newItem.title && oldItem.title == newItem.title }) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BottomViewHolder {
        return BottomViewHolder(
            ItemBottomTypeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }


    class BottomItemClick(val clickListener: (model: BottomModule) -> Unit) {
        fun onClick(model: BottomModule) = clickListener(model)
    }

    class BottomViewHolder(private val binding: ItemBottomTypeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(model: BottomModule) {
            binding.apply {
                title.text = model.title
                description.text = model.description
                balance.text = model.balance
            }
        }
    }

    override fun onBindViewHolder(holder: BottomViewHolder, position: Int) {
        holder.bind(getItem(position))
        holder.itemView.setOnClickListener {
            clickListener.onClick(getItem(position))
        }
    }
}

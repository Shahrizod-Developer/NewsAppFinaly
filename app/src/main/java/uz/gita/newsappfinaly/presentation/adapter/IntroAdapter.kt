package uz.gita.newsappfinaly.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import uz.gita.newsappfinaly.data.model.IntroData
import uz.gita.newsappfinaly.databinding.ItemIntroBinding

class IntroAdapter : ListAdapter<IntroData, IntroAdapter.ViewHolder>(
    IntroDiffUtilCallBack
) {
    inner class ViewHolder(private val binding: ItemIntroBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind() {
            binding.text.text = getItem(absoluteAdapterPosition).text
            binding.image.setImageResource(getItem(absoluteAdapterPosition).image)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemIntroBinding.inflate(LayoutInflater.from(parent.context),
            parent,
            false))
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind()
    }
}

private val IntroDiffUtilCallBack = object : DiffUtil.ItemCallback<IntroData>() {

    override fun areItemsTheSame(oldItem: IntroData, newItem: IntroData): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: IntroData, newItem: IntroData): Boolean {
        return oldItem.image == newItem.image
                && oldItem.text == newItem.text
    }

}
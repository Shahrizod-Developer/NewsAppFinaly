package uz.gita.newsappfinaly.presentation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.ImageLoader
import coil.decode.SvgDecoder
import coil.load
import coil.request.ImageRequest
import coil.transform.CircleCropTransformation
import uz.gita.newsappfinaly.R
import uz.gita.newsappfinaly.data.local.entity.entity.CountryEntity
import uz.gita.newsappfinaly.data.model.Category
import uz.gita.newsappfinaly.databinding.ItemCategoryBinding
import uz.gita.newsappfinaly.databinding.ItemCountryBinding
import kotlin.math.abs

class CategoryAdapter :
    ListAdapter<Category, CategoryAdapter.ViewHolder>(CountryDiffUtilCallBack) {

    private val list = ArrayList<Category>()
    private var onItemClickListener: ((Category) -> Unit)? = null
    var state = MutableLiveData(false)
    private var selectedPosition = -1

    var code = ""
    fun submitOnItemClickListener(block: (Category) -> Unit) {
        onItemClickListener = block
    }


    inner class ViewHolder(private val binding: ItemCategoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val checkbox = binding.checkbox

        @SuppressLint("CheckResult", "NotifyDataSetChanged")
        fun onBind() {

            val item = getItem(absoluteAdapterPosition)

            binding.name.text = item?.name
            binding.image.load(item.image) {
                crossfade(true)
                placeholder(R.drawable.images)
                transformations(CircleCropTransformation())
            }
        }
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        state.value = true
        if (selectedPosition == position) {
            holder.itemView.setBackgroundResource(R.drawable.back_style_category_selected)
            holder.checkbox.isChecked = true
        } else {
            holder.itemView.setBackgroundResource(R.drawable.back_style_category)
            holder.checkbox.isChecked = false
        }

        holder.checkbox.setOnClickListener {
            if (holder.checkbox.isChecked) {
                code = ""
                holder.itemView.setBackgroundResource(R.drawable.back_style_category)
                holder.checkbox.isChecked = false
            } else {
                code = currentList[holder.absoluteAdapterPosition].name
                holder.itemView.setBackgroundResource(R.drawable.back_style_category_selected)
                holder.checkbox.isChecked = true
            }
            notifyItemChanged(selectedPosition)
            selectedPosition = holder.absoluteAdapterPosition
            notifyItemChanged(selectedPosition)
        }

        holder.itemView.setOnClickListener {

            state.value = true
            if (holder.checkbox.isChecked) {
                holder.itemView.setBackgroundResource(R.drawable.back_style_category)
                holder.checkbox.isChecked = false
            } else {
                holder.itemView.setBackgroundResource(R.drawable.back_style_category_selected)
                holder.checkbox.isChecked = true
            }
            notifyItemChanged(selectedPosition)
            selectedPosition = holder.absoluteAdapterPosition
            notifyItemChanged(selectedPosition)
        }
        holder.onBind()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemCategoryBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

}

private val CountryDiffUtilCallBack = object : DiffUtil.ItemCallback<Category>() {

    override fun areItemsTheSame(oldItem: Category, newItem: Category): Boolean {
        return oldItem == newItem
    }


    override fun areContentsTheSame(oldItem: Category, newItem: Category): Boolean {
        return oldItem.name == newItem.name &&
                oldItem.id == newItem.id &&
                oldItem.image == newItem.image &&
                oldItem.isChecking == newItem.isChecking

    }
}
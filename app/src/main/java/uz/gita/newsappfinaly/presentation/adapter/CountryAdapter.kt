package uz.gita.newsappfinaly.presentation.adapter

import android.annotation.SuppressLint
import android.app.Activity
import android.graphics.drawable.PictureDrawable
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.ImageLoader
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import kotlinx.coroutines.CoroutineScope
import uz.gita.newsappfinaly.R
import uz.gita.newsappfinaly.data.local.entity.entity.CountryEntity
import uz.gita.newsappfinaly.data.model.Country
import uz.gita.newsappfinaly.databinding.ItemCountryBinding
import uz.gita.newsappfinaly.utils.flow


class CountryAdapter :
    ListAdapter<CountryEntity, CountryAdapter.ViewHolder>(CountryDiffUtilCallBack) {

    private val list = ArrayList<CountryEntity>()
    private var onItemClickListener: ((CountryEntity) -> Unit)? = null
    var state = MutableLiveData(false)
    private var selectedPosition = -1
    var code = ""

    fun submitOnItemClickListener(block: (CountryEntity) -> Unit) {
        onItemClickListener = block
    }


    inner class ViewHolder(private val binding: ItemCountryBinding) :
        RecyclerView.ViewHolder(binding.root) {

        val isSelect = binding.isSelect

        @SuppressLint("CheckResult", "NotifyDataSetChanged")
        fun onBind() {

            val item = getItem(absoluteAdapterPosition)

            binding.code.text = item?.code?.uppercase().toString()
            binding.countryName.text = item?.name

            val imageLoader = ImageLoader.Builder(binding.root.context)
                .componentRegistry { add(SvgDecoder(binding.root.context)) }.build()
            val request = ImageRequest.Builder(binding.root.context).crossfade(true).crossfade(500)
                .placeholder(R.drawable.images).error(R.drawable.ic_launcher_background)
                .data(item.image).target(binding.image).build()
            imageLoader.enqueue(request)
        }
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        if (selectedPosition == holder.absoluteAdapterPosition) {
            code = ""
            holder.itemView.setBackgroundResource(R.drawable.back_style_item_country_selected)
            holder.isSelect.setImageResource(R.drawable.dot_circle_svgrepo_com)
        } else {
            code = currentList[position]?.code!!
            holder.itemView.setBackgroundResource(R.drawable.back_style_item_country)
            holder.isSelect.setImageResource(R.drawable.circle_dot_svgrepo_com)
        }
        holder.itemView.setOnClickListener {
            state.value = true
            notifyItemChanged(selectedPosition)
            selectedPosition = holder.position
            notifyItemChanged(selectedPosition)
        }
        holder.onBind()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemCountryBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

}

private val CountryDiffUtilCallBack = object : DiffUtil.ItemCallback<CountryEntity>() {

    override fun areItemsTheSame(oldItem: CountryEntity, newItem: CountryEntity): Boolean {
        return oldItem == newItem
    }


    override fun areContentsTheSame(oldItem: CountryEntity, newItem: CountryEntity): Boolean {
        return oldItem.code == newItem.code &&
                oldItem.name == newItem.name &&
                oldItem.id == newItem.id &&
                oldItem.image == newItem.image &&
                oldItem.unicode == newItem.unicode &&
                oldItem.emoji == newItem.emoji &&
                oldItem.state == newItem.state
    }
}
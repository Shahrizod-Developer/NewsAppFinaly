package uz.gita.newsappfinaly.utils

import androidx.recyclerview.widget.DiffUtil
import uz.gita.newsappfinaly.data.local.entity.entity.CountryEntity

class CountryDiffUtil(
    var oldList: ArrayList<CountryEntity>,
    var newList: ArrayList<CountryEntity>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].code == newList[newItemPosition].code
                && oldList[oldItemPosition].id == newList[newItemPosition].id
                && oldList[oldItemPosition].name == newList[newItemPosition].name
                && oldList[oldItemPosition].emoji == newList[newItemPosition].emoji
                && oldList[oldItemPosition].image == newList[newItemPosition].image
                && oldList[oldItemPosition].unicode == newList[newItemPosition].unicode
                && oldList[oldItemPosition].state == newList[newItemPosition].state

    }
}
package com.example.datematch.utils

import androidx.recyclerview.widget.DiffUtil
import com.example.datematch.models.Users

class UserDiffCallback(
    private val old: List<Users>,
    private val new: List<Users>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return old.size
    }

    override fun getNewListSize(): Int {
        return new.size
    }

    override fun areItemsTheSame(oldPosition: Int, newPosition: Int): Boolean {
        return old[oldPosition].id == new[newPosition].id
    }

    override fun areContentsTheSame(oldPosition: Int, newPosition: Int): Boolean {
        return old[oldPosition] == new[newPosition]
    }

}
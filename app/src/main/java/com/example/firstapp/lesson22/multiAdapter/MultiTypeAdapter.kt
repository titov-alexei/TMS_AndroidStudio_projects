package com.example.firstapp.lesson22.multiAdapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.firstapp.databinding.AuthorLayoutBinding
import com.example.firstapp.databinding.ImageLayoutBinding
import com.example.firstapp.databinding.TextLayoutBinding

class MultiTypeAdapter(private var data: List<Elements>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val TYPE_NAME_TEXT = 0
        private const val TYPE_IMAGE_TEXT = 1
        private const val TYPE_TEXT_BUTTON = 2

    }

    override fun getItemViewType(position: Int): Int {
        return when(data[position]) {
            is Elements.NameText -> TYPE_NAME_TEXT
            is Elements.ImageText -> TYPE_IMAGE_TEXT
            is Elements.TextButton -> TYPE_TEXT_BUTTON
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return when(viewType) {
            TYPE_NAME_TEXT -> {
                val binding = AuthorLayoutBinding.inflate(layoutInflater, parent, false)
                AuthorViewHolder(binding)
            }
            TYPE_IMAGE_TEXT -> {
                val binding = ImageLayoutBinding.inflate(layoutInflater, parent, false)
                ImageViewHolder(binding)
            }
            TYPE_TEXT_BUTTON -> {
                val binding = TextLayoutBinding.inflate(layoutInflater, parent, false)
                TextViewHolder(binding)
            }
            else -> throw IllegalStateException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is AuthorViewHolder -> holder.bind(data[position] as Elements.NameText)
            is ImageViewHolder -> holder.bind(data[position] as Elements.ImageText)
            is TextViewHolder -> holder.bind(data[position] as Elements.TextButton)
        }
    }


    override fun getItemCount(): Int = data.size

    class AuthorViewHolder(private val binding: AuthorLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Elements.NameText) {
            binding.authorName.text = item.authorName
            binding.textAuthor.text = item.textAuthor
        }
    }

    class ImageViewHolder(private val binding: ImageLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Elements.ImageText) {
            binding.image.setImageDrawable(item.image)
            binding.textImage.text = item.text
        }
    }

    class TextViewHolder(private val binding: TextLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Elements.TextButton) {
            binding.textViewText.text = item.text
            binding.btnText.text = item.btnText
        }
    }

    fun updateView(list: List<Elements>) {
        val diffUtilResult = DiffUtil.calculateDiff(MultiDiffUtilCallback(list, secondList = data))
        data = list
        diffUtilResult.dispatchUpdatesTo(this)
        //notifyDataSetChanged()
    }
}
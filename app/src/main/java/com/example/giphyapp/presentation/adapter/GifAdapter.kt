package com.example.giphyapp.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.giphyapp.R
import com.example.giphyapp.databinding.RvItemBinding
import com.example.giphyapp.domain.model.response.Data

class GifAdapter: PagingDataAdapter<Data, GifAdapter.VH>(GifListDiffCallback()) {

	inner class VH(private val binding: RvItemBinding) : RecyclerView.ViewHolder(binding.root) {
		fun onBind(gif: Data) {
			Glide.with(binding.root.context).load(gif.images.fixed_height_small.url).placeholder(R.drawable.loading)
				.into(binding.gifImageView)
		}
	}

	class GifListDiffCallback : DiffUtil.ItemCallback<Data>() {
		override fun areItemsTheSame(oldItem: Data, newItem: Data) =
			oldItem.id == newItem.id

		override fun areContentsTheSame(
			oldItem: Data,
			newItem: Data
		) = oldItem == newItem

		override fun getChangePayload(oldItem: Data, newItem: Data): Any? {
			if (oldItem != newItem) {
				return newItem
			}
			return super.getChangePayload(oldItem, newItem)
		}
	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
		val layoutInflater = LayoutInflater.from(parent.context)
		return VH(RvItemBinding.inflate(layoutInflater, parent, false))
	}

	override fun onBindViewHolder(
		holder: VH,
		position: Int,
		payloads: MutableList<Any>
	) {
		if (payloads.isNullOrEmpty()) {
			super.onBindViewHolder(holder, position, payloads)
		} else {
			val newItem = payloads[0] as Data
			holder.onBind(newItem)
		}
	}

	override fun onBindViewHolder(holder: VH, position: Int) {
		val item: Data? = getItem(position)
		item?.let {
			holder.onBind(item)
		}
	}
}

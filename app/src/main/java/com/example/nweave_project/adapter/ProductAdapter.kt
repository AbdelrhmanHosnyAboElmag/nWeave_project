package com.example.nweave_project.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.nweave_project.R
import com.example.nweave_project.databinding.ProductItemBinding
import com.example.nweave_project.model.Product
import com.example.nweave_project.source.local.ProductDatabase

class ProductAdapter(
    private val data :ArrayList<ProductDatabase>,
    val onSelectedItem: (DataPosition:Int) -> Unit,
) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    var adapterData = data


    class ProductViewHolder(val binding: ProductItemBinding) : RecyclerView.ViewHolder(binding.root) {
        /**
         * Set item data
         */
        fun setData(data: ProductDatabase) = binding.apply {
            binding.tvTitle.text = data.name
            binding.tvPrice.text = data.price
            binding.tvDetail.text = data.description

            Glide.with(itemView.context)
                .load(data.image_url)
                .centerCrop()
                .placeholder(R.drawable.no_picture_found)
                .into(binding.imageView)
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = ProductItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.setData(adapterData[position])
        holder.itemView.rootView.setOnClickListener {
            onSelectedItem(position)
        }

    }


    override fun getItemCount(): Int = adapterData.size
}
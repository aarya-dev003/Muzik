package com.example.muzik.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.muzik.activities.SongList
import com.example.muzik.databinding.CategoryItemRecyclerViewBinding
import com.example.muzik.model.CategoryModel

class CategoryAdapter (private val categoryList: List<CategoryModel>): RecyclerView.Adapter<CategoryAdapter.MyViewholder>(){
    class MyViewholder(private val binding: CategoryItemRecyclerViewBinding) :
            RecyclerView.ViewHolder(binding.root){


                fun bindData(category: CategoryModel){
                    binding.nameTextView.text = category.name
                    Glide.with(binding.coverImage).load(category.coverUrl)
                        .apply(
                            RequestOptions().transform(RoundedCorners(32))
                        )
                        .into(binding.coverImage)
//                    Log.i("SONGS",category.songs.size.toString())
                    // starts songs activity
                    val context =binding.root.context
                    binding.root.setOnClickListener{
                        SongList.category = category
                        context.startActivity(Intent(context,SongList::class.java))
                    }
                }
            }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewholder {
        val binding = CategoryItemRecyclerViewBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyViewholder(binding)
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

    override fun onBindViewHolder(holder: MyViewholder, position: Int) {
        holder.bindData(categoryList[position])
    }
}
package com.example.muzik.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.muzik.MyExoplayer
import com.example.muzik.activities.PlayerActivity
import com.example.muzik.databinding.SongListRecyclerViewBinding
import com.example.muzik.model.SongModel
import com.google.firebase.firestore.FirebaseFirestore

class SongListAdapter (private val songIdList : List<String>) : RecyclerView.Adapter<SongListAdapter.MyViewholder>() {
    class MyViewholder(private val binding: SongListRecyclerViewBinding) : RecyclerView.ViewHolder(binding.root){
        fun bindData(songID : String){
            // to test the list
//            binding.songTitleTextView.text = songID

            FirebaseFirestore.getInstance().collection("songs")
                .document(songID).get()
                .addOnSuccessListener {
                    val song = it.toObject(SongModel::class.java)
                    song?.apply {
                        binding.songTitleTextView.text = title
                        binding.songSubtitleTextView.text = subtitle
                        Glide.with(binding.songCoverImageView).load(coverUrl)
                            .apply(
                                RequestOptions().transform(RoundedCorners(32))
                            )
                            .into(binding.songCoverImageView)
                        binding.root.setOnClickListener{
                            MyExoplayer.startPlaying(binding.root.context, song)
                            it.context.startActivity(Intent(it.context, PlayerActivity::class.java))
                        }
                    }
                }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewholder {
        val binding = SongListRecyclerViewBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyViewholder(binding)

    }

    override fun getItemCount(): Int {
        return songIdList.size
    }

    override fun onBindViewHolder(holder: MyViewholder, position: Int) {
        holder.bindData(songIdList[position])
    }
}
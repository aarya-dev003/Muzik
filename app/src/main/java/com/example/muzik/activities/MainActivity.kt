package com.example.muzik.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.muzik.MyExoplayer
import com.example.muzik.adapters.CategoryAdapter
import com.example.muzik.adapters.TrendingSongListAdapter
import com.example.muzik.databinding.ActivityMainBinding
import com.example.muzik.model.CategoryModel
import com.google.firebase.firestore.FirebaseFirestore


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var categoryAdapter: CategoryAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        getCategories()
        setupSections("section_1", binding.TrendingRelativeLayout,binding.trendingTextView, binding.trendingRecyclerView)
        setupSections("section_3", binding.ClassicRelativeLayout,binding.ClassicTextView, binding.ClassicRecyclerView)
        setupSections("section_2", binding.likesRelativeLayout,binding.likesTextView, binding.likesRecyclerView)


    }

    override fun onResume() {
        super.onResume()
        showPlayerView()
    }

    fun showPlayerView(){
        binding.playerViewBottom.setOnClickListener{
            startActivity(Intent(this@MainActivity, PlayerActivity::class.java))
        }
        MyExoplayer.getCurrentSong()?.let {
            binding.playerViewBottom.visibility = View.VISIBLE
            binding.songTitleTextView.text = it.title
            Glide.with(binding.songCoverImageView).load(it.coverUrl)
                .circleCrop()
                .into(binding.songCoverImageView)
        }?: run{
            binding.playerViewBottom.visibility = View.GONE
        }
    }

    fun getCategories(){
        FirebaseFirestore.getInstance().collection("Category")
            .get().addOnSuccessListener {
                val categoryList = it.toObjects(CategoryModel::class.java)
                setupCategoryRecyclerView(categoryList)
            }
    }
    fun setupCategoryRecyclerView(categoryList: List<CategoryModel>){
        categoryAdapter = CategoryAdapter(categoryList)
        binding.categoriesRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false)
        binding.categoriesRecyclerView.adapter = categoryAdapter
    }

    //setup sections

    fun setupSections(id : String , mainLayout: RelativeLayout, titleView: TextView, recyclerView: RecyclerView){
        FirebaseFirestore.getInstance().collection("Sections").
                document(id).get().addOnSuccessListener {
                    val section = it.toObject(CategoryModel::class.java)
                    section?.apply {
                        mainLayout.visibility= View.VISIBLE
                        titleView .text = name
                        recyclerView.layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
                        recyclerView.adapter = TrendingSongListAdapter(songs)
                        mainLayout.setOnClickListener{
                            SongList.category = section
                            startActivity(Intent(this@MainActivity,SongList::class.java))
                        }
                    }
        }
    }
}
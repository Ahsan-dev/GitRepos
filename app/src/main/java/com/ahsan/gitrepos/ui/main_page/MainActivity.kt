package com.ahsan.gitrepos.ui.main_page

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.ahsan.gitrepos.R
import com.ahsan.gitrepos.data.remote.models.Item
import com.ahsan.gitrepos.data.remote.models.ResponseWrapper
import com.ahsan.gitrepos.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding
    private val newsList: ArrayList<Item> = ArrayList()
    private lateinit var newsLM: LinearLayoutManager
//    private lateinit var newsAdapter: NewsAdapter
    private var totalPages = 0
    private var isLoading = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
//        setContentView(R.layout.activity_main)
        setContentView(binding.root)
        setupObserver()
    }

    fun setupObserver() {
        viewModel.repoResponse.observe(this){
            when (it) {
                is ResponseWrapper.Success -> {
                    Log.d("RepoLog", it.data.total_count.toString())
                }
                is ResponseWrapper.Error -> {
                    Log.d("RepoLog", "Error")
                }
                is ResponseWrapper.Loading -> {

                }
            }
        }
    }
}
package com.ahsan.gitrepos.ui.main_page

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.ahsan.gitrepos.R
import com.ahsan.gitrepos.data.remote.models.Item
import com.ahsan.gitrepos.data.remote.models.ResponseWrapper
import com.ahsan.gitrepos.databinding.ActivityMainBinding
import com.ahsan.gitrepos.ui.main_page.adapter.RepoAdapter
import com.ahsan.gitrepos.utils.PaginationScrollListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding
    private val repoList: ArrayList<Item> = ArrayList()
    private lateinit var repoLM: LinearLayoutManager
    private lateinit var repoAdapter: RepoAdapter
    private var totalPages = 0
    private var isLoading = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
//        setContentView(R.layout.activity_main)
        repoLM = LinearLayoutManager(this)
        setContentView(binding.root)
        setupObserver()
        setupAdapter()
    }

    fun setupObserver() {

        viewModel.isLoading.observe(this, Observer {
            isLoading = it
            if (it){
                binding.progressBar.visibility = View.VISIBLE
            }else{
                binding.progressBar.visibility = View.GONE
            }
        })

        viewModel.repoResponse.observe(this, Observer {
            when (it) {
                is ResponseWrapper.Success ->{
                    Log.d("News", "Total news: ${it.data.total_count}")
                    repoList.clear()
                    repoList.addAll(ArrayList(it.data.items))
                    totalPages = it.data.total_count/20
                    repoAdapter.notifyDataSetChanged()
                }
                is ResponseWrapper.Error -> {
                    Log.d("RepoLog", "Error called")
                }
                is ResponseWrapper.Loading ->{
                    Log.d("RepoLog", "Loading called")
                }
            }

        })

        viewModel.moreRepoResponse.observe(this, Observer {
            when (it) {
                is ResponseWrapper.Success -> {
                    repoAdapter.addAll(ArrayList(it.data.items))
                }
                is ResponseWrapper.Error -> {

                }
                is ResponseWrapper.Loading -> TODO()
            }
        })
    }

    fun setupAdapter() {
        repoAdapter = RepoAdapter(this, repoList)
        binding.rvRepos.apply {
            adapter = repoAdapter
            layoutManager = repoLM
            hasFixedSize()

            addOnScrollListener(object : PaginationScrollListener(repoLM){
                override fun isLastPage(): Boolean {
                    return totalPages == viewModel.currentPage
                }

                override fun isLoading(): Boolean {
                    return isLoading
                }

                override fun loadMore() {
                    viewModel.currentPage++
                    viewModel.getMoreRepos()
                }

            })
        }
    }
}
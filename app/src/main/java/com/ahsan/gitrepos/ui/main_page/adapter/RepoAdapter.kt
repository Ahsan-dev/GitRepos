package com.ahsan.gitrepos.ui.main_page.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ahsan.gitrepos.data.remote.models.Item
import com.ahsan.gitrepos.databinding.RepoItemLayoutBinding
import com.ahsan.gitrepos.ui.main_page.viewholder.RepoItemViewHolder

class RepoAdapter(private val context: Context, private val repoList: ArrayList<Item>):
    RecyclerView.Adapter<RepoItemViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoItemViewHolder {
        return RepoItemViewHolder(
            //LayoutInflater.from(parent.context).inflate(R.layout.news_item_layout,parent,false)
            RepoItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun getItemCount(): Int {
        return repoList.size
    }

    override fun onBindViewHolder(holder: RepoItemViewHolder, position: Int) {
        val repo = repoList[position]
        holder.bind(repo, context)
    }

    fun addAll(newses: ArrayList<Item>) {
        var size = this.repoList.size
        repoList.addAll(newses)
        var newSize = this.repoList.size
        notifyItemRangeChanged(size, newSize)
    }
}
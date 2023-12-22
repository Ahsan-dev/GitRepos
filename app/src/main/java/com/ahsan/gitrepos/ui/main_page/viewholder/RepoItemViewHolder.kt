package com.ahsan.gitrepos.ui.main_page.viewholder


import android.content.Context
import android.content.Intent
import android.os.Parcelable
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.ahsan.gitrepos.R
import com.ahsan.gitrepos.data.remote.models.Item
import com.ahsan.gitrepos.databinding.RepoItemLayoutBinding
import com.ahsan.gitrepos.ui.details_page.DetailsActivity
import com.ahsan.gitrepos.utils.Constants
import java.io.Serializable

class RepoItemViewHolder(private val binding: RepoItemLayoutBinding): RecyclerView.ViewHolder(binding.root) {

    fun bind(item: Item, context: Context) {
        binding.tvTitle.text = item.name
        binding.tvDescription.text = item.description
        binding.tvOwner.text = item.owner.login
        binding.imageOwner.load(item?.owner?.avatar_url) {
            placeholder(R.drawable.placeholder)
            error(R.drawable.placeholder)
        }

        binding.llRepo.setOnClickListener {
            val myIntent = Intent(context, DetailsActivity::class.java)
            myIntent.putExtra(Constants.SEND_REPO, item as Serializable)
            context.startActivity(myIntent)
        }
    }

}
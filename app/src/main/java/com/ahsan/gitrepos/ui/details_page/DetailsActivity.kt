package com.ahsan.gitrepos.ui.details_page

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import coil.load
import com.ahsan.gitrepos.R
import com.ahsan.gitrepos.data.remote.models.Item
import com.ahsan.gitrepos.databinding.ActivityDetailsBinding
import com.ahsan.gitrepos.databinding.RepoItemLayoutBinding
import com.ahsan.gitrepos.utils.Constants
import java.io.Serializable

class DetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
//        setContentView(R.layout.activity_details)
        setContentView(binding.root)

        val repo: Item? = intent.getSerializable(Constants.SEND_REPO, Item::class.java)

        binding.tvDetailTitle.text = repo?.name
        binding.tvDetailsOwnerName.text = "Owner: ${repo?.name}"
        binding.textView2.text = repo?.description




    }

    fun <T : Serializable?> Intent.getSerializable(key: String, m_class: Class<T>): T {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
            this.getSerializableExtra(key, m_class)!!
        else
            @Suppress("DEPRECATION")
            this.getSerializableExtra(key) as T
    }
}
package com.ahsan.gitrepos.data.remote.models

data class ReposResponse(
    val incomplete_results: Boolean,
    val items: List<Item>,
    val total_count: Int
)
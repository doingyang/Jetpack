package com.project.jetpack.paging3

import com.google.gson.annotations.SerializedName

class RepoResponse(
    @SerializedName("items") val items: List<Repo> = emptyList()
)
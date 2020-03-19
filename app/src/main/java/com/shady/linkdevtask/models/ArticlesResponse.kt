package com.shady.linkdevtask.models

import com.google.gson.annotations.SerializedName

class ArticlesResponse(
    @SerializedName("status") val status: String?,
    @SerializedName("source") val source: String?,
    @SerializedName("sortBy") val sortBy: String?,
    @SerializedName("articles") val articles: MutableList <Article>?
)
package com.shady.linkdevtask.viewmodels

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.shady.linkdevtask.models.ArticlesResponse
import com.shady.linkdevtask.repositories.ArticlesRepository
import com.shady.linkdevtask.utils.Constants

class HomeViewModel : ViewModel() {

    private val repository = ArticlesRepository()
    val articlesLiveData = MediatorLiveData<ArticlesResponse>()

    init {
        fetchData()
    }

    private fun fetchData() {
        articlesLiveData.addSource(
            repository.fetchArticles(Constants.SOURCE_THE_NEXT_WEB)
        ) { result -> articlesLiveData.value = combineResults(articlesLiveData.value, result) }

        articlesLiveData.addSource(
            repository.fetchArticles(Constants.SOURCE_ASSOCIATED_PRESS)
        ) { result -> articlesLiveData.value = combineResults(articlesLiveData.value, result) }
    }

    private fun combineResults(old: ArticlesResponse?, new: ArticlesResponse): ArticlesResponse {
        if (old?.articles == null)
            return new

        if (new.articles != null)
            old.articles.addAll(new.articles)

        return old
    }
}

package com.shady.linkdevtask.repositories

import androidx.lifecycle.MutableLiveData
import com.shady.linkdevtask.models.ArticlesResponse
import com.shady.linkdevtask.network.ApiClient
import com.shady.linkdevtask.utils.Constants
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ArticlesRepository {

    fun fetchArticles(source: String): MutableLiveData<ArticlesResponse> {
        val liveData = MutableLiveData<ArticlesResponse>()

        ApiClient
            .getInstance()
            .getArticles(source, Constants.API_KEY)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { response -> liveData.value = response },
                { t -> liveData.value = ArticlesResponse(t.message, null, null, null) })

        return liveData
    }

}
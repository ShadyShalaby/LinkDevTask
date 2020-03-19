package com.shady.linkdevtask.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shady.linkdevtask.models.Article
import com.shady.linkdevtask.ui.activities.MainActivity
import com.shady.linkdevtask.ui.adapters.ArticlesAdapter
import com.shady.linkdevtask.utils.Constants
import com.shady.linkdevtask.viewmodels.HomeViewModel
import com.shady.myapplication.R

class HomeFragment : Fragment(), ArticlesAdapter.OnArticleClickListener {

    companion object {
        fun newInstance() = HomeFragment()
    }

    private lateinit var viewModel: HomeViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private val articlesAdapter = ArticlesAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.home_fragment, container, false)

        initUI(view)
        setupArticlesList()

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        setupViewModelObservers()
    }

    private fun initUI(view: View) {
        recyclerView = view.findViewById(R.id.rv_articles)
        progressBar = view.findViewById(R.id.progress)
    }

    private fun setupArticlesList() {
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = articlesAdapter
    }

    private fun setupViewModelObservers() {
        viewModel.articlesLiveData.observe(viewLifecycleOwner, Observer {
            if (it?.articles != null) {
                articlesAdapter.setArticles(it.articles)
                recyclerView.visibility = View.VISIBLE
                progressBar.visibility = View.GONE
            } else if (Constants.RESPONSE_STATUS_OK != it?.status) {
                Toast.makeText(context, it.status, Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun onArticleClicked(article: Article) {
        (activity as MainActivity)
            .openFragment(ArticleDetailsFragment.newInstance(article))
    }

}

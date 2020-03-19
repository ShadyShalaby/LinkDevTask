package com.shady.linkdevtask.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.shady.linkdevtask.models.Article
import com.shady.linkdevtask.utils.Utils
import com.shady.myapplication.R
import com.squareup.picasso.Picasso
import java.util.*

class ArticlesAdapter(private val onArticleClickListener: OnArticleClickListener) :
    RecyclerView.Adapter<ArticleViewHolder>() {

    interface OnArticleClickListener {
        fun onArticleClicked(article: Article)
    }

    private lateinit var articles: ArrayList<Article>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        return ArticleViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.row_article, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return articles.size
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article = articles[position]
        holder.bind(article, onArticleClickListener)
    }

    fun setArticles(articles: List<Article>?) {
        this.articles = articles as ArrayList<Article>
    }
}

class ArticleViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val tvTitle: TextView = view.findViewById(R.id.tv_article_title)
    private val tvAuthor: TextView = view.findViewById(R.id.tv_article_author)
    private val tvDate: TextView = view.findViewById(R.id.tv_article_date)
    private val ivArticleImg: ImageView = view.findViewById(R.id.iv_article_image)

    fun bind(article: Article, onArticleClickListener: ArticlesAdapter.OnArticleClickListener) {

        tvTitle.text = article.title
        tvAuthor.text = String.format("By %s", article.author)
        tvDate.text = Utils.getDateFormattedFromString(article.publishedAt, "MMMM d, yyyy")
        if (article.urlToImage != null && article.urlToImage.isNotEmpty()) {
            Picasso
                .get()
                .load(article.urlToImage)
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.placeholder)
                .into(ivArticleImg)
        } else {
            ivArticleImg.setImageResource(R.drawable.placeholder)
        }

        itemView.setOnClickListener(
            View.OnClickListener(
                fun(it: View) {
                    onArticleClickListener.onArticleClicked(article)
                })
        )
    }

}

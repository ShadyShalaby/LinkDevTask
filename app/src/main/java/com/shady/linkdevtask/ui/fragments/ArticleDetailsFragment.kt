package com.shady.linkdevtask.ui.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.shady.linkdevtask.models.Article
import com.shady.linkdevtask.utils.Utils
import com.shady.myapplication.R
import com.squareup.picasso.Picasso

/**
 *
 */
class ArticleDetailsFragment : Fragment() {

    companion object {
        private const val BUNDLE_ARTICLE = "bundle_article"
        fun newInstance(article: Article): ArticleDetailsFragment {
            val fragment = ArticleDetailsFragment()

            val bundle = Bundle();
            bundle.putSerializable(BUNDLE_ARTICLE, article)
            fragment.arguments = bundle

            return fragment
        }
    }

    private lateinit var article: Article

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_article_details, container, false)

        readArguments(arguments)
        initUI(view)
        attachListeners(view)

        return view
    }

    private fun attachListeners(view: View?) {
        view?.findViewById<Button>(R.id.btn_open_site)!!.setOnClickListener(View.OnClickListener {
            Utils.openUrlInBrowser(context, article.url)
        })
    }

    private fun initUI(view: View) {
        view.findViewById<TextView>(R.id.tv_article_title).text = article.title
        view.findViewById<TextView>(R.id.tv_article_author).text =
            String.format("By %s", article.author)
        view.findViewById<TextView>(R.id.tv_article_date).text =
            Utils.getDateFormattedFromString(article.publishedAt, "MMMM d, yyyy")
        view.findViewById<TextView>(R.id.tv_article_content).text = article.description

        if (article.urlToImage != null && article.urlToImage!!.isNotEmpty()) {
            Picasso
                .get()
                .load(article.urlToImage)
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.placeholder)
                .into(view.findViewById<ImageView>(R.id.iv_article_image))
        }
    }

    private fun readArguments(arguments: Bundle?) {
        article = arguments?.getSerializable(BUNDLE_ARTICLE) as Article
    }

}

package com.example.eh_ho.posts


import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.eh_ho.R
import com.example.eh_ho.data.Post
import com.example.eh_ho.data.PostsRepo
import com.example.eh_ho.data.RequestError
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_posts.*
import kotlinx.android.synthetic.main.fragment_posts.parentLayout
import kotlinx.android.synthetic.main.fragment_topics.*
import kotlinx.android.synthetic.main.view_retry.*

class PostsFragment : Fragment() {
    var listener: PostsInteractionListener? = null
    lateinit var adapter: PostsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_topics, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_posts, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        listPosts.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        listPosts.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        listPosts.adapter = adapter

        buttonCreatePost.setOnClickListener {
            goToCreatePost()
        }
        buttonRetry.setOnClickListener {
        loadPosts()
    }
    }

    override fun onResume() {
        super.onResume()
        loadPosts()
    }

    private fun loadPosts() {
         enableLoading(true)

        context?.let {
            PostsRepo.getPosts(it,
                {
                    enableLoading(false)
                    adapter.setPosts(it)
                },
                {
                     enableLoading(false)
                    handleRequestError(it)
                })
        }
    }

private fun enableLoading(enabled: Boolean) {
    viewRetry.visibility = View.INVISIBLE
    if(enabled) {
        listPosts.visibility = View.INVISIBLE
        buttonCreatePost.hide()
        viewLoading.visibility = View.VISIBLE
    } else {
        listPosts.visibility = View.VISIBLE
        buttonCreatePost.show()
        viewLoading.visibility = View.INVISIBLE
    }
}

    private fun handleRequestError(it: RequestError) {
        listPosts.visibility = View.INVISIBLE
        viewRetry.visibility = View.VISIBLE

        val message = if (it.messageResId != null)
            getString(it.messageResId)
        else if (it.message != null)
            it.message
        else
            getString(R.string.error_request_default)
        Snackbar.make(parentLayout, message, Snackbar.LENGTH_LONG).show()

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_log_out -> listener?.onLogOut()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun goToCreatePost() {
        listener?.onGoToCreatePost()
    }

    interface PostsInteractionListener {
        fun onPostSelected(post: Post)
        fun onGoToCreatePost()
        fun onLogOut()
    }
}
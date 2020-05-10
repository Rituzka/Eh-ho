package com.example.eh_ho.posts

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.eh_ho.R
import com.example.eh_ho.data.Post
import kotlinx.android.synthetic.main.item_post.view.*


class PostsAdapter (
    val postClickListener: ((Post) -> Unit)? = null
    ): RecyclerView.Adapter<PostsAdapter.PostHolder>(){
        private val posts = mutableListOf<Post>()

        private val listener: ((View) -> Unit)= {
            val post = it.tag as Post
            postClickListener?.invoke(post)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostHolder {
            val view=
                LayoutInflater.from(parent.context).inflate(R.layout.item_post,parent, false)

            return PostHolder(view)
        }

        override fun getItemCount(): Int {
            return posts.size
        }

        override fun onBindViewHolder(holder: PostHolder, position: Int) {
            val post = posts[position]
            holder.post = post
            holder.itemView.setOnClickListener(listener)
        }

        fun setPosts(posts: List<Post>){
            this.posts.clear()
            this.posts.addAll(posts)
            notifyDataSetChanged()
        }

        inner class PostHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
            var post: Post? = null
                set(value){
                    field = value

                    with(itemView) {
                        tag = field
                        field?.let {
                            labelPostTitle.text = it.title
                            labelUser.text = it.username
                            labelContent.text = it.content

                        }
                    }
                }
        }
    }



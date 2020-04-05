package com.example.eh_ho.posts

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.eh_ho.R
import com.example.eh_ho.data.Post
import kotlinx.android.synthetic.main.activity_posts.view.*
import kotlinx.android.synthetic.main.fragment_create_post.view.*
import kotlinx.android.synthetic.main.item_topic.view.labelDate
import java.util.*

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
                LayoutInflater.from(parent.context).inflate(R.layout.item_topic,parent, false)

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
                            labelTitle.text = it.title
                            labelUsername.text = it.username
                            setTimeOffset(it.getTimeOffset())
                        }
                    }
                }
            private fun setTimeOffset(timeOffset: Post.TimeOffset){
                val quantityString = when(timeOffset.unit) {
                    Calendar.YEAR -> R.plurals.years
                    Calendar.MONTH -> R.plurals.months
                    Calendar.DAY_OF_MONTH -> R.plurals.days
                    Calendar.HOUR -> R.plurals.hours
                    else -> R.plurals.minutes
                }
                itemView.labelDate.text = if(timeOffset.amount != 0)
                    itemView.context.resources.getQuantityString(quantityString, timeOffset.amount, timeOffset.amount)
                else
                    itemView.context.resources.getString(R.string.minutes_zero)
            }
        }
    }



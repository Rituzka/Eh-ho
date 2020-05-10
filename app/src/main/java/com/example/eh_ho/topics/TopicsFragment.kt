package com.example.eh_ho.topics

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.eh_ho.R
import com.example.eh_ho.data.RequestError
import com.example.eh_ho.data.Topic
import com.example.eh_ho.data.TopicsRepo
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_topics.*
import kotlinx.android.synthetic.main.view_retry.*

class TopicsFragment : Fragment() {
    var listener: TopicsInteractionListener? = null
    lateinit var adapter: TopicsAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context is TopicsInteractionListener)
            listener = context
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

        adapter = TopicsAdapter{
            goToPosts(it)
        }
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
        return inflater.inflate(R.layout.fragment_topics, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        listTopics.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        listTopics.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL ) )
        listTopics.adapter = adapter

        buttonCreate.setOnClickListener {
            goToCreateTopic()
        }
        buttonRetry.setOnClickListener {
            loadTopics()
        }
    }

    override fun onResume() {
        super.onResume()
        loadTopics()
    }

    private fun loadTopics() {
        enableLoading(true)

        context?.let {
            TopicsRepo.getTopics(it,
                {
                    enableLoading(false)
                    adapter.setTopics(it)
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
            listTopics.visibility = View.INVISIBLE
            buttonCreate.hide()
            viewLoading.visibility = View.VISIBLE
        } else {
            listTopics.visibility = View.VISIBLE
            buttonCreate.show()
            viewLoading.visibility = View.INVISIBLE
        }
    }

    private fun handleRequestError(it: RequestError) {
        listTopics.visibility = View.INVISIBLE
        viewRetry.visibility = View.VISIBLE

        val message =  if(it.messageResId != null)
            getString(it.messageResId)
        else if (it.message != null)
            it.message
        else
            getString(R.string.error_request_default)
        Snackbar.make(parentLayout, message, Snackbar.LENGTH_LONG).show()

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.action_log_out -> listener?.onLogOut()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun goToCreateTopic() {
        listener?.onGoToCreateTopic()
    }

    private fun goToPosts(it: Topic) {
        listener?.onTopicSelected(it)
    }

    interface TopicsInteractionListener {
        fun onTopicSelected(topic: Topic)
        fun onGoToCreateTopic()
        fun onLogOut()
    }
}

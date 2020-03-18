package com.example.eh_ho.topics

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.eh_ho.R
import com.example.eh_ho.data.Topic
import com.example.eh_ho.data.TopicsRepo
import kotlinx.android.synthetic.main.fragment_topics.*

class TopicsFragment : Fragment() {
    var listener: TopicsInteractionListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context is TopicsInteractionListener)
            listener = context
    }

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
        return inflater.inflate(R.layout.fragment_topics, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = TopicsAdapter { goToPosts(it) }
        adapter.setTopics(TopicsRepo.topics)

        listTopics.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        listTopics.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL ) )
        listTopics.adapter = adapter

        buttonCreate.setOnClickListener {
            goToCreateTopic()
        }
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

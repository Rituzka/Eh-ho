package com.example.eh_ho.topics

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.example.eh_ho.R
import com.example.eh_ho.data.TopicsRepo
import kotlinx.android.synthetic.main.fragment_create_topic.*

class CreateTopicFragment: Fragment() {

    var listener: CreateTopicInteractionListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if(context is CreateTopicInteractionListener)
            listener = context
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_create_topic,container, false)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_create_topic,menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
       when(item.itemId) { //cuando item.itemId sea igual a:
            R.id.action_send -> createTopic()
       }
        return super.onOptionsItemSelected(item)
    }
    private fun createTopic() {
        if (isFormValid()) {
            TopicsRepo.addTopic(inputTitle.text.toString(),
            inputContent.text.toString())

            listener?.onTopicCreated()

        } else {
            showErrors()
        }
    }

    private fun showErrors() {
        if (inputTitle.text?.isEmpty() == true)
            inputTitle.error = getString(R.string.error_empty)
        if (inputContent.text?.isEmpty() == true)
            inputContent.error = getString(R.string.error_empty)
    }

    private fun isFormValid() =
        //en caso que la parte izquierda sea nula devuelve un falso
        inputTitle.text?.isNotEmpty() ?:false  &&
                inputContent.text?.isNotEmpty() ?: false

    interface CreateTopicInteractionListener {
        fun onTopicCreated()
    }
}

package com.example.eh_ho.posts

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.example.eh_ho.R
import com.example.eh_ho.data.CreatePostModel
import com.example.eh_ho.data.RequestError
import com.example.eh_ho.data.PostsRepo
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_create_post.*


class CreatePostFragment : Fragment() {
    var listener: CreatePostInteractionListener? = null
    //lateinit var loadingDialog: LoadingDialogFragment

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if(context is CreatePostInteractionListener)
            listener = context
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

        //loadingDialog = LoadingDialogFragment.newInstance(getString(R.string.label_create_topic))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_create_post,container, false)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_create_topic,menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) { //cuando item.itemId sea igual a:
            R.id.action_send -> createPost()
        }
        return super.onOptionsItemSelected(item)
    }
    private fun createPost() {
            postTopic()
    }

    private fun postTopic() {
        val model = CreatePostModel(
            inputContent.text.toString()
        )
        context?.let {
           // enableLoadingDialog(true)
            PostsRepo.createPost(
                it,
                model,
                {
                    //enableLoadingDialog(false)
                    listener?.onPostCreated()
                },
                {
                   // enableLoadingDialog(false)
                    handleError(it)

                }
            )}
    }

  /*  private fun enableLoadingDialog(enabled: Boolean){
        if (enabled)
            loadingDialog.show(childFragmentManager, TAG_LOADING_DIALOG)
        else
            loadingDialog.dismiss()
    }*/

    private fun handleError(requestError: RequestError) {
        val message =  if(requestError.messageResId != null)
            getString(requestError.messageResId)
        else if (requestError.message != null)
            requestError.message
        else
            getString(R.string.error_request_default)
        Snackbar.make(parentLayout, message, Snackbar.LENGTH_LONG).show()

    }

   /* private fun showErrors() {

        if (inputContent.text?.isEmpty() == true)
            inputContent.error = getString(R.string.error_empty)
    }*/

   /* private fun isFormValid() =
        //en caso que la parte izquierda sea nula devuelve un falso
        inputTitle.text?.isNotEmpty() ?:false  &&
                inputContent.text?.isNotEmpty() ?: false*/

    interface CreatePostInteractionListener {
        fun onPostCreated()
    }
}


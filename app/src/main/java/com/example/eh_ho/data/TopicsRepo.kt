package com.example.eh_ho.data

import android.content.Context
import com.android.volley.NetworkError
import com.android.volley.Request
import com.android.volley.ServerError
import com.example.eh_ho.R
import org.json.JSONObject

object TopicsRepo {

    fun getTopics(
        context: Context,
        onSuccess: (List<Topic>) -> Unit,
        onError: (RequestError) -> Unit
    ){
        val username = UserRepo.getUsername(context)
        val request = UserRequest(
            username,
            Request.Method.GET,
            ApiRoutes.getTopics(),
            null,
            {
                it?.let {
                    onSuccess.invoke(Topic.parseTopics(it))
                }
                if(it == null)
                onError.invoke(RequestError(messageResId = R.string.error_invalid_response))
            },
            {
                it.printStackTrace()
                if(it is NetworkError)
                    onError.invoke(RequestError(messageResId = R.string.error_network))
                else
                    onError.invoke(RequestError(it))
            })
        ApiRequestQueue.getRequestQueue(context)
            .add(request)
    }
    fun createTopic (
        context: Context,
        model: CreateTopicModel,
        onSuccess: (CreateTopicModel) -> Unit,
        onError: (RequestError) -> Unit
    ){
        val username = UserRepo.getUsername(context)
        val request = UserRequest(
            username,
            Request.Method.POST,
            ApiRoutes.createTopic(),
            model.toJson(),
            {
                it?.let {
                    onSuccess.invoke(model)
                }

                if(it == null)
                    onError.invoke(RequestError(messageResId = R.string.error_invalid_response))
            },
            {
                it.printStackTrace()
                if (it is ServerError && it.networkResponse.statusCode == 422) {
                    //la info de errores del server nos llega en forma de bytes por eso lo pasamos a un string y luego a un json
                    val body = String(it.networkResponse.data, Charsets.UTF_8)
                    val jsonError = JSONObject(body)

                    //esto nos dá el objeto entero de array de errores, obtendremos el array de error
                    val errors = jsonError.getJSONArray("errors")
                    //y ahora concatenaremos todos estos elementos.
                    var errorMessage = ""
                    for (i in 0 until errors.length()) {
                        errorMessage += "${errors[i]}"
                    }

                    onError.invoke(RequestError(it, message = errorMessage))

                }else if (it is NetworkError)
                        onError.invoke(RequestError(it, messageResId = R.string.error_network))
                    else
                        onError.invoke(RequestError(it))
                }
                )

        ApiRequestQueue.getRequestQueue(context)
            .add(request)
    }
}
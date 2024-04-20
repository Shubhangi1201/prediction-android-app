package com.example.turnoverprediction

import android.net.http.HttpException
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.turnoverprediction.api.PostRequest
import com.example.turnoverprediction.api.ResultResponse
import com.example.turnoverprediction.api.RetorfitInstance
import com.example.turnoverprediction.databinding.ActivityPredictionBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

class PredictionActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityPredictionBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val postRequest = PostRequest("John Doe")

        RetorfitInstance.api.postData(postRequest).enqueue(object : Callback<ResultResponse> {
            override fun onResponse(
                call: Call<ResultResponse>,
                response: Response<ResultResponse>
            ) {
                if (response.isSuccessful) {
                    val postResponse = response.body()
                    val result = postResponse?.result
                    Log.d("abc", "$result")

                    // do something with the result
                }
            }

            override fun onFailure(call: Call<ResultResponse>, t: Throwable) {
                Log.d("abc", "${t.message.toString()}")


            }

        })

        }



}
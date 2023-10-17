package com.example.and101_lab5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import com.example.and101_lab5.databinding.ActivityMainBinding
import okhttp3.Headers

class MainActivity : AppCompatActivity() {
    var petImageURL = ""
    private lateinit var binding: ActivityMainBinding
    private val button get() = binding.btnRandomPet
    private val image get()  = binding.ivPetImage


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getDogImageURL()
        Log.d("petImageURL", "pet image URL set")

        getNextImage(button, image)






        }

    private fun getDogImageURL(){

        val client = AsyncHttpClient()

        client["https://dog.ceo/api/breeds/image/random", object : JsonHttpResponseHandler() {
            override fun onSuccess(statusCode: Int, headers: Headers, json: JsonHttpResponseHandler.JSON) {
                Log.d("Dog", "response successful$json")
                petImageURL = json.jsonObject.getString("message")

            }

            override fun onFailure(
                statusCode: Int,
                headers: Headers?,
                errorResponse: String,
                throwable: Throwable?
            ) {
                Log.d("Dog Error", errorResponse)
            }
        }]


    }

    private fun getNextImage(button: Button, imageView: ImageView){
        button.setOnClickListener{
            getDogImageURL()
            Glide.with(this)
                .load(petImageURL)
                .fitCenter()
                .into(binding.ivPetImage)
        }

    }


}











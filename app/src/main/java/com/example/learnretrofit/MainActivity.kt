package com.example.learnretrofit

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.liveData
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val text = findViewById<TextView>(R.id.textView)

        val retrofitApi = RetrofitInstance
            .getRetrofitInstance()
            .create(RetrofitApiService::class.java)

        val responseLiveData: LiveData<Response<Albums>> = liveData {
//            val response = retrofitApi.getAlbums()
            val response2 = retrofitApi.getSpecificAlbum(8)
            emit(response2)
        }

        responseLiveData.observe(this, Observer {
            val albumList = it.body()?.listIterator()
            if (albumList != null) {
                while (albumList.hasNext()) {
                    val albumItem = albumList.next()
//                    Log.d("MyTag", albumItem.id.toString())

                    val result = "Album title: ${albumItem.title} \n"
                    text.append(result)
                }
            }

        })

            // Example: Add a new album
            val newAlbum = AlbumItem(userId = 1, id = 0, title = "New Album Title")
            val addAlbumLiveData: LiveData<Response<AlbumItem>> = liveData {
                val response = retrofitApi.addAlbum(newAlbum)
                emit(response)
            }
        addAlbumLiveData.observe(this, Observer {
            val addedAlbum = it.body()
            addedAlbum?.let {
                Log.i("MyTag", "Added Album: ${it.title}")
            }
        })


    }
}

package com.example.noted

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.noted.database.WordViewModel
import com.example.noted.database.WordViewModelFactory
import com.example.noted.databinding.ActivityMainBinding

//RoomWordSample App from AndroidDev

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    //request code
    private val newWordActivityRequestCode = 1

    // create the ViewModel instance
    private val wordsViewModel: WordViewModel by viewModels {
        WordViewModelFactory((application as WordsApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val recyclerView = binding.recyclerview
        val adapter = WordListAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        wordsViewModel.allWord.observe(this, Observer { words ->
            words?.let { adapter.submitList(it) }
        })

        binding.fab.setOnClickListener {
            getContent.launch("string")
        }

    }
    val getContent = registerForActivityResult(ActivityResultContracts.GetContent(),) {uri: Uri? ->

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

    }
}
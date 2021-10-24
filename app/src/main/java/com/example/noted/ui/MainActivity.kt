package com.example.noted.ui

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.noted.R
import com.example.noted.WordListAdapter
import com.example.noted.database.Word
import com.example.noted.database.WordViewModel
import com.example.noted.database.WordViewModelFactory
import com.example.noted.databinding.ActivityMainBinding

//RoomWordSample App from AndroidDev

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    //request code
//    private val newWordActivityRequestCode = 1

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

        wordsViewModel.allWords.observe(this, Observer { words ->
            words?.let { adapter.submitList(it) }
        })

        val intent = Intent(this, NewWordActivity::class.java)
        binding.fab.setOnClickListener {
            getContent.launch(intent)
        }

    }
    // Registering the contract to the Activity
    private val getContent = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        // The parseResult will return this as String?
        if (result.resultCode == Activity.RESULT_OK) {
            val data = result.data //.getStringExtra(NewWordActivity.EXTRA_REPLY)
            val wordExtra = data?.getStringExtra(NewWordActivity.EXTRA_REPLY).also {
                it?.let {
                    val word = Word(it)
                    wordsViewModel.insert(word)
                }
            }

            Toast.makeText(this, "Result: $wordExtra", Toast.LENGTH_LONG).show()
        }
        else
            Toast.makeText(applicationContext, R.string.empty_not_saved, Toast.LENGTH_LONG).show()

        // For custom Contracts
//        if (result != null) //
    }

}

/*
class NewWordActivityContract: ActivityResultContract<Int, String?>() {
    override fun createIntent(context: Context, input: Int?): Intent {
        return Intent(context, NewWordActivity::class.java).apply {
            putExtra(NewWordActivity.REQUEST_CODE, input)
        }
    }

    override fun parseResult(resultCode: Int, intent: Intent?): String? {
        val data = intent?.getStringExtra(NewWordActivity.EXTRA_REPLY)
        return if (resultCode == Activity.RESULT_OK && data != null)
            data
        else
            null
    }
}
*/

package com.example.noted.ui

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import com.example.noted.databinding.ActivityNewWordBinding

class NewWordActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNewWordBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewWordBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val editWordView = binding.editWord

        binding.buttonSave.setOnClickListener {
            val replyIntent = Intent()
            if (TextUtils.isEmpty(editWordView.text)) {
                setResult(Activity.RESULT_CANCELED, replyIntent)
            } else {
                val word = editWordView.text.toString()
                replyIntent.putExtra(EXTRA_REPLY, word)
                setResult(Activity.RESULT_OK, replyIntent)
            }
            finish()
        }
    }

    companion object {
//        const val REQUEST_CODE = "com.example.noted.ui.NewWordActivity.CODE"
        const val EXTRA_REPLY = "com.example.noted.ui.NewWordActivity.REPLY"
    }
}
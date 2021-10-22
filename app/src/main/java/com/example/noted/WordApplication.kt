package com.example.noted

import android.app.Application
import com.example.noted.database.WordRepository
import com.example.noted.database.WordRoomDatabase

//managing dependencies to have only one instance of the database and the repository
class WordApplication: Application() {
    // Using by lazy so the database and the repository are only created when they're needed
    // rather than when the application starts
    val database by lazy { WordRoomDatabase.getDatabase(this) }
    val repository by lazy { WordRepository(database.wordDao()) }
}
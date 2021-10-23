package com.example.noted

import android.app.Application
import com.example.noted.database.WordRepository
import com.example.noted.database.WordRoomDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

//managing dependencies to have only one instance of the database and the repository
class WordsApplication: Application() {
    // No need to cancel this scope as it'll be torn down by the process
    private val applicationScope = CoroutineScope(SupervisorJob())

    // Using by lazy so the database and the repository are only created when they're needed
    // rather than when the application starts
    val database by lazy { WordRoomDatabase.getDatabase(this, applicationScope) }
    val repository by lazy { WordRepository(database.wordDao()) }
}
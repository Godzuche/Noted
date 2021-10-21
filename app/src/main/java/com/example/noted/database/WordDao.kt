package com.example.noted.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface WordDao {
    //get all words arranged in ascending order
    @Query ("SELECT * FROM word_table ORDER BY word ASC")
    fun getAlphabetizedWords() : Flow<List<Word>>

    //insert a word
    @Insert (onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(word: Word)

    //delete all words
    @Query ("DELETE FROM word_table")
    suspend fun deleteAll()
}